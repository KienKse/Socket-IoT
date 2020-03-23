package Socket.IoT.model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadEnviarMsg implements Runnable {

	private Socket socket;
	private int delay;
	private boolean user = true;

	public ThreadEnviarMsg(Socket socket, int delay) {
		super();
		this.socket = socket;
		this.delay = delay;
	}

	@Override
	public void run() {
		while(user) {
			try {
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				out.flush();
				out.println(ThreadLocalRandom.current().nextInt(0, 100) + "oF");
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				System.out.println("thread - Erro de Iterrupcao");
				user = false;
			} catch (IOException e) {
				System.out.println("Desconectado");
				user = false;
			}
		}
	}

}

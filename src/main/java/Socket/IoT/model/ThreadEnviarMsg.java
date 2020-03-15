package Socket.IoT.model;

import java.io.IOException;
import java.io.ObjectOutputStream;
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
	
	public boolean isUser() {
		return user;
	}

	public void setUser(boolean user) {
		this.user = user;
	}

	@Override
	public void run() {
		while(user) {
			try {
				ObjectOutputStream msg = new ObjectOutputStream(socket.getOutputStream());
				msg.flush();
				msg.writeObject(ThreadLocalRandom.current().nextInt(0, 100) + "ºF\r\n");
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				System.out.println("thread - Erro de Iterrupção");
				user = false;
			} catch (IOException e) {
				System.out.println("Desconectado");
				user = false;
			}
		}
	}

}

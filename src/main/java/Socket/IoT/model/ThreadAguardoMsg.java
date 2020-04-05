package Socket.IoT.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadAguardoMsg implements Runnable {

	private Socket cliente;
	private boolean user = true;

	public ThreadAguardoMsg(Socket cliente) {
		super();
		this.cliente = cliente;
	}

	@Override
	public void run() {
		while(user) {
			try {
				BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
				String mensagemCliente = entrada.readLine();
				System.out.println("Mensagem Cliente: " + mensagemCliente);
			} catch (IOException e) {
				System.out.println("DESCONECTADO");
			} catch (NullPointerException e) {
				System.out.println("DESCONECTADO");
			} finally {
				user = false;
			}
		}
	}

}

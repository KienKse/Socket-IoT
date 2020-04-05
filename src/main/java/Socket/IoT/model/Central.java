package Socket.IoT.model;

import java.net.ServerSocket;
import java.net.Socket;

public class Central {
	public static void main(String args[]) {
		try {
			final int porta = 8082;
			System.out.println("Central alocada na porta: " + porta);
			ServerSocket serverSocket = new ServerSocket(porta);
			
			while (serverSocket.isBound()) {
				Socket cliente = serverSocket.accept();

				System.out.println("Container conectado: " + cliente.getInetAddress());
				
				new Thread(new ThreadAguardoMsg(cliente)).start();
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
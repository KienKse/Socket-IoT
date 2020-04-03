package Socket.IoT.model;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String args[]) {
		try {
			final int porta = 8082;
			System.out.println("Server alocado na porta: " + porta);
			ServerSocket serverSocket = new ServerSocket(porta);
			while (serverSocket.isBound()) {
				Socket cliente = serverSocket.accept();

				System.out.println("Cliente conectado: " + cliente.getInetAddress());

				new Thread(new ThreadAguardoMsg(cliente)).start();
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
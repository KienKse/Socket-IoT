package Socket.IoT.model;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String args[]) {
		try {
			final int porta = 8082;
			int delay = 30000;
			System.out.println("Server alocado na porta: " + porta);
			ServerSocket serverSocket = new ServerSocket(porta);
			while (serverSocket.isBound()) {
				Socket socket = serverSocket.accept();

				System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());

				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				out.flush();
				out.println("kien_device ATIVADO " + (delay/1000));

				new Thread(new ThreadEnviarMsg(socket, delay)).start();
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
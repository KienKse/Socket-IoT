package Socket.IoT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String args[]) throws IOException {
		final int porta = 3322;
		System.out.println("Server alocado na porta: " + porta);
		ServerSocket serverSocket = new ServerSocket(porta);
		while (true) {
			Socket socket = serverSocket.accept();
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			pw.println("Qual seu nome para, ..Ping?");

			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String str = br.readLine();

			pw.println("Lá vai, " + str);
//			pw.close();
//			socket.close();

			System.out.println("Pong: " + str);
		}
	}
}
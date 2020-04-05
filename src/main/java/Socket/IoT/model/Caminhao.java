package Socket.IoT.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class Caminhao {
	public static void main(String args[]) throws InterruptedException {
		try {
//			String host = "smbo.ddns.net";
			String host = "localhost";
			int porta = 8082;

			Socket socket = new Socket(host, porta);

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.flush();
			out.println("CHEGUEI_CONTAINER");
			System.out.println("Enviando comando ao container");
			Thread.sleep(2000);
		}  catch (SocketException e) {
			System.out.println("Erro de socket");
		} catch (IOException e) {
			System.out.println("Erro de conexao");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ocorreu um erro inesperado");
		}
	}
}

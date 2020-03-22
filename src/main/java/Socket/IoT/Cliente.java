package Socket.IoT;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Cliente {
	public static void main(String args[]) {
		try {
			String host;
			int porta;
			System.out.println("INICIANDO PROGRAMA");
			System.out.println("Digite o {ip do host} e a {porta}, respectivamente");
			Scanner sc = new Scanner(System.in);
			host = sc.next();
			porta = sc.nextInt();

			Socket socket = new Socket(host, porta);
			System.out.println("Conectado, aguardando mensagem do servidor...");
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("Mensagem Servidor: " + br.readLine());

			while (true) {
//				System.out.println("msg cliente");
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader userInputBR = new BufferedReader(new InputStreamReader(System.in));
				String userInput = userInputBR.readLine();
				if(userInput != null) {
					out.flush();
					out.println(userInput + "\r\n");
//					System.out.println("msg enviada");
				}
			}
		} catch (SocketException e) {
			System.out.println("!!Conexao Encerrada!!");
		} catch (IOException e) {
			System.out.println("Erro de conexao");
		}
	}
}

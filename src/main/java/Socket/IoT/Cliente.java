package Socket.IoT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Cliente {

	public static void main(String args[]) {
		try {
			String host = null;
			int porta = 0;
			System.out.println("Digite o {ip do host} e a {porta}, respectivamente");
			
			Scanner sc = new Scanner(System.in);
			host = sc.next();
			porta = sc.nextInt();
	
			while (true) {
				Socket socket = new Socket(host, porta);
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	
				System.out.println("SERVER_LOG: " + br.readLine());
	
				BufferedReader userInputBR = new BufferedReader(new InputStreamReader(System.in));
				String userInput = userInputBR.readLine();
				
				if ("bye".equalsIgnoreCase(userInput)) {
					socket.close();
					break;
				}
				
				out.println(userInput);
		
				System.out.println("SERVER_LOG: " + br.readLine());
			}
			
		} catch (SocketException e) {
			System.out.println("!!Conexão Encerrada!!");
			// TODO: handle exception
		} catch (IOException e) {
			System.out.println("Erro de conexão");
			// TODO: handle exception
		}
	}
}

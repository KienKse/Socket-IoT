package Socket.IoT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
// OK - JESSICA{Cliente}
	public static void main(String args[]) throws IOException {
		try {
			final int porta = 3322;
			System.out.println("Server alocado na porta: " + porta);
			ServerSocket serverSocket = new ServerSocket(porta);
			while (serverSocket.isBound()) {
				Socket socket = serverSocket.accept();
				
				System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());
				
				ObjectOutputStream mensagemServidor = new ObjectOutputStream(socket.getOutputStream());
				mensagemServidor.flush();
				mensagemServidor.writeObject("ObjectOutputStream method");
				
				OutputStream os = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(os, true);
				pw.println("Qual seu nome para, ..Ping?");
	
//				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//				String str = br.readLine();
				
				Scanner sc = new Scanner(socket.getInputStream());
				while (sc.hasNextLine()) {
					System.out.println(sc.nextLine());					
				}
				
//				System.out.println("msg: " + str);
				
//				pw.println("Lá vai, " + str);
//				pw.close();
//				socket.close();
//				System.out.println("Pong: " + str);
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			// TODO: handle exception
		}
	}
}
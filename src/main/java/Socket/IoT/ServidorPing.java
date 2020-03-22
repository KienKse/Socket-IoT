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

public class ServidorPing {

	public static void main(String args[]) throws IOException {
		/*
		Lucas - CONFIRMAR
		Jessica - OK
		Sergio - OK
		Matheus - OK
		 */
		try {
			final int porta = 8082;
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

				Scanner sc = new Scanner(socket.getInputStream());
				while (sc.hasNextLine()) {
					System.out.println(sc.nextLine());					
				}
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
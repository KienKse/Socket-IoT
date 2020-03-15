package Socket.IoT.model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.bind.DataBindingException;

public class Servidor {
	public static void main(String args[]) throws IOException {
		try {
			final int porta = 3322;
			int delay = 30000;
			System.out.println("Server alocado na porta: " + porta);
			ServerSocket serverSocket = new ServerSocket(porta);
			while (serverSocket.isBound()) {
				Socket socket = serverSocket.accept();
				
				System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());
				
				ObjectOutputStream mensagemServidor = new ObjectOutputStream(socket.getOutputStream());
				mensagemServidor.flush();
				mensagemServidor.writeObject(socket.getInetAddress().getHostAddress()
						+ " ATIVADO " + (delay/1000));
				
				new Thread(new ThreadEnviarMsg(socket, delay)).start();
				
//				while (true) {
//					try {
//						ObjectOutputStream msg = new ObjectOutputStream(socket.getOutputStream());
//						msg.flush();
//						msg.writeObject(ThreadLocalRandom.current().nextInt(0, 100) + "oF\r\n");
//						Thread.sleep(delay);
//					} catch (SocketException e) {
//						// TODO: handle exception
//					}
//				}
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			// TODO: handle exception
		}
	}
}
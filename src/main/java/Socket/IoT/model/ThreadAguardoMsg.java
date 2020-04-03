package Socket.IoT.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadAguardoMsg implements Runnable {

	private final String DEVICE_NAME =  "Temperatura_K";
	private final String SITUACAO_SERVICO =  "ATIVADO";
//	private final String SITUACAO_SERVICO =  "DESATIVADO";

	private Socket cliente;
	private int delay = 4000;
	private boolean user = true;

	public ThreadAguardoMsg(Socket cliente) {
		super();
		this.cliente = cliente;
	}

	@Override
	public void run() {
		while(user) {
			try {
				BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
				String mensagemCliente = entrada.readLine();
				if(mensagemCliente != null & (!mensagemCliente.contains("0.tcp.ngrok.io") || !mensagemCliente.contains("\u0015") )) {
					System.out.println("Mensagem Cliente: " + mensagemCliente);
					String[] msg = mensagemCliente.split(" ");
					if (msg[1].equalsIgnoreCase("conectar") && msg[2].equalsIgnoreCase(DEVICE_NAME)) {
						PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
						out.flush();
						out.println(DEVICE_NAME + " " + SITUACAO_SERVICO + " " + (delay / 1000));
						if(!SITUACAO_SERVICO.equalsIgnoreCase("desativado")) {
							new Thread(new ThreadEnviarMsg(cliente, delay)).start();
						} else {
							user = false;
							cliente.close();
						}
					}
				} else {
					user = false;
				}
			} catch (IOException e) {
				System.out.println("DESCONECTADO");
				user = false;
			} catch (NullPointerException e) {
				System.out.println("DESCONECTADO");
				user = false;
			}
		}
	}

}

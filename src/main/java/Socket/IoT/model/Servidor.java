package Socket.IoT.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Servidor {

	private static final int ID_CONTAINER = 1;

	public static void main(String args[]) {
		try {
			int portaServer = 8083;
			ServerSocket servidor = new ServerSocket(portaServer);
			System.out.println("Servidor iniciado na porta " + portaServer);

			Container container = new Container(ID_CONTAINER);
			inicializarServicos(container);

			while (servidor.isBound()) {
				Socket cliente = servidor.accept();
				System.out.println("Caminhão conectado no IP " + cliente.getInetAddress());

				BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
				String mensagemCliente = entrada.readLine();
				System.out.println("Comando recebido: " + mensagemCliente);

				if (mensagemCliente.equalsIgnoreCase("CHEGUEI_CONTAINER")) {
					esvaziarContainer(container);
					int time = (int) (Math.random() * 10000) + 1000;
					System.out.println(time);
					Thread.sleep(time);
					inicializarServicos(container);
				}
				cliente.close();
			}
		} catch (SocketException e) {
			System.out.println("Erro de servidor");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Erro inesperado");
			e.printStackTrace();
		}

	}

	private static void esvaziarContainer(Container container) {
		new Thread(new ThreadPreencherLixo(container, true)).start();
	}

	public static void inicializarServicos(Container container) {
		if(emptyContainer(container)) {
			new Thread(new ThreadPreencherLixo(container, container.isCaminhao())).start();
			new Thread(new ThreadPreencherLixo(container, container.isCaminhao())).start();
			new Thread(new ThreadPreencherLixo(container, container.isCaminhao())).start();
			new Thread(new ThreadPreencherLixo(container, container.isCaminhao())).start();
		}
	}

	private static boolean emptyContainer(Container container) {
		for (int i = 0; i < container.getTipoContainer().length-1; i++) {
			if(container.getTipoContainer()[i] == 0) {
				return true;
			}
		}
		return false;
	}
}
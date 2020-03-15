package Socket.IoT.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Cliente {
	public static void main(String args[]) {
		try {
			String host = null;
			int porta = 0;
			System.out.println("GO AHEAD BEAUTY...\nSe conecte");
			Scanner sc = new Scanner(System.in);
			String[] comando = sc.nextLine().split(" ");
//			String[] comando = {"", "conectar", "Local"};

			if (comando[1].equalsIgnoreCase("CONECTAR")) {

				JSONParser jsonParser = new JSONParser();

				try (FileReader reader = new FileReader("devices.json")) {
					Object arquivo = jsonParser.parse(reader);
					JSONObject objeto = (JSONObject) arquivo;
					JSONObject conector = (JSONObject) objeto.get(comando[2]);

					host = (String) conector.get("ip");

					porta = Integer.parseInt((String) conector.get("porta"));

					Socket socket = new Socket(host, porta);

					while(true){
						ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
						String mensagemDoServidor = null;
						try {
							mensagemDoServidor = (String) entrada.readObject();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
						System.out.println(mensagemDoServidor);

//						Scanner sc2 = new Scanner(socket.getInputStream());
//						while (sc2.hasNextLine()) {
//							//								System.out.println(sc.nextLine());
//							out.flush();
//							out.println(sc.nextLine() + "\r\n");
//						}

//						System.out.println("SERVER_LOG: " + br.readLine());
					}					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (SocketException e) {
					System.out.println("Erro de socket");
				} catch (IOException e) {
					System.out.println("Erro de conexao");
				}

			} else {
				System.out.println("O correto seria...\nDP1 CONECTAR DP2");
			}
		} catch (Exception e) {
			// TODO: handle exception
		} 
	}
}

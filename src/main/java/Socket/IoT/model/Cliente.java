package Socket.IoT.model;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Cliente {
	public static void main(String args[]) {
		try {
			String host;
			int porta;
			System.out.println("GO AHEAD BEAUTY...\nSe conecte");
			Scanner sc = new Scanner(System.in);
			String[] comando = sc.nextLine().split(" ");
//			String[] comando = {"", "conectar", "servidor_teste"};

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
						BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						System.out.println("Mensagem Servidor: " + br.readLine());
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
			System.out.println("Ocorreu um erro inesperado");
		}
	}
}

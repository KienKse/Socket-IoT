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
			System.out.println("GO AHEAD BEAUTY...");
			Scanner sc = new Scanner(System.in);
			String[] comando = sc.nextLine().split(" ");
//			String[] comando = {"Computador_K", "CONECTAR", "Temperatura_Sergio"};

			if (comando[1].equalsIgnoreCase("CONECTAR")) {

				JSONParser jsonParser = new JSONParser();

				try (FileReader reader = new FileReader("devices.json")) {
					Object arquivo = jsonParser.parse(reader);
					JSONObject objeto = (JSONObject) arquivo;
					JSONObject conector = (JSONObject) objeto.get(comando[2]);

					host = (String) conector.get("ip");

					porta = Integer.parseInt((String) conector.get("porta"));

					Socket socket = new Socket(host, porta);

                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.flush();
                    out.println(comando[0] + " " +  comando [1] + " " +  comando[2]);

                    String msgServ;

                    while(true){
                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        msgServ = br.readLine();
						if(msgServ != null) {
                            System.out.println("Mensagem Servidor: " + msgServ);
                        } else {
                            System.out.println("Desconectado");
                            throw new SocketException();
                        }
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
			e.printStackTrace();
			System.out.println("Ocorreu um erro inesperado");
		}
	}
}

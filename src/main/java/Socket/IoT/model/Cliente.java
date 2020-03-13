package Socket.IoT.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Cliente {
	public static void main(String args[]) {
		try {
			String host = null;
			int porta = 0;
			System.out.println("GO AHEAD BEAUTY... ");
			Scanner sc = new Scanner(System.in);
			String[] comando = sc.nextLine().split(" ");
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
						//							ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
						//							String mensagemDoServidor = (String) entrada.readObject();
						//							System.out.println(mensagemDoServidor);

						BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

						ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
						String mensagemDoServidor = null;
						try {
							mensagemDoServidor = (String) entrada.readObject();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(mensagemDoServidor);
						//							if(br.readLine() != null)
						//								System.out.println("SERVER_LOG: " + br.readLine());

						//							BufferedReader userInputBR = new BufferedReader(new InputStreamReader(System.in));
						//							String userInput = userInputBR.readLine();

						Scanner sc2 = new Scanner(socket.getInputStream());
						while (sc2.hasNextLine()) {
							//								System.out.println(sc.nextLine());
							out.flush();
							out.println(sc.nextLine() + "\r\n");
						}


						//							if ("bye".equalsIgnoreCase(userInput)) {
						//								socket.close();
						//								break;
						//							}

						//							out.flush();
						//							out.println(userInput + "\r\n" + "\r\n");
						//							out.print("\r\n");

						System.out.println("SERVER_LOG: " + br.readLine());
					}					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			//		} catch (SocketException e) {
			//			System.out.println("!!Conexão Encerrada!!");
			//			// TODO: handle exception
			//		} catch (IOException e) {
			//			System.out.println("Erro de conexão");
			//			// TODO: handle exception
			//		}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

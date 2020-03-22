package Socket.IoT;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) {
        ServerSocket servidor;
        try {
            int portaServer = 8082;
            servidor = new ServerSocket(portaServer);
            System.out.println("Servidor iniciado na porta " + portaServer);
            while (servidor.isBound()) {
                Socket clienteSocket = servidor.accept();
                System.out.println("Cliente conectado " + clienteSocket.getInetAddress());
                ObjectOutputStream mensagemServidor = new ObjectOutputStream(clienteSocket.getOutputStream());
                mensagemServidor.flush();
                mensagemServidor.writeObject("Conectado ao servidor - KIEN" + "\r\n");
                Scanner entrada = new Scanner(clienteSocket.getInputStream());
                while(entrada.hasNext()) {
                    System.out.println(entrada.next());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
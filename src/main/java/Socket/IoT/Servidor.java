package Socket.IoT;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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
                PrintWriter out = new PrintWriter(clienteSocket.getOutputStream(), true);
//                ObjectOutputStream mensagemServidor = new ObjectOutputStream(clienteSocket.getOutputStream());
                out.flush();
                out.println("Conectado ao servidor - KIEN");
                Scanner entrada = new Scanner(clienteSocket.getInputStream());
                while(entrada.hasNext()) {
                    System.out.println(entrada.nextLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
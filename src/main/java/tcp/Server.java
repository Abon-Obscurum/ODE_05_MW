package tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        // Server-Socket auf Port 4711
        try (ServerSocket serverSocket = new ServerSocket(4711)) {
            System.out.println("Port 4711 is open");

            // Neue Clients annehmen
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader inputSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter outputSocket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {

                        System.out.println("Client connected: " + socket.getInetAddress());

                        // Nachrichten lesen, bis der Client trennt
                        String message;
                        while ((message = inputSocket.readLine()) != null) {
                            System.out.println("Client screams: " + message);
                        }

                        System.out.println("Client disconnected");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

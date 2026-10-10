package tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// Console TCP server that echoes messages back to the client
public class Server {

    public static void main(String[] args) {
        // Listen on port 4711, closed automatically on exit
        try (ServerSocket serverSocket = new ServerSocket(4711)) {
            System.out.println("Port 4711 is open");

            // Accept clients one after another
            while (true) {
                // Socket, reader and writer are closed when the client is done
                try (Socket socket = serverSocket.accept();
                     BufferedReader inputSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     // autoFlush: send each line immediately
                     PrintWriter outputSocket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {

                    System.out.println("Client connected: " + socket.getInetAddress());

                    // Read lines until the client disconnects (null)
                    String message;
                    while ((message = inputSocket.readLine()) != null) {
                        System.out.println("Client screams: " + message);
                        // Loopback: send the message back
                        outputSocket.println(message);
                    }

                    System.out.println("Client disconnected");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
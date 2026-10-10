package tcp;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

// Background thread that reads server responses
public class ReaderThread extends Thread {
    // Connection to read from
    Socket socket;
    // Target list in the GUI
    @FXML ListView messageList;

    public ReaderThread(Socket socket, ListView messageList) {
        this.socket = socket;
        this.messageList = messageList;
        // Don't keep the app alive after the window closes
        setDaemon(true);
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String line;
            // Read until the server closes the connection
            while ((line = reader.readLine()) != null) {
                // Lambda needs an effectively final variable
                String finalLine = line;
                // GUI updates only on the FX thread
                Platform.runLater(() -> messageList.getItems().add(finalLine));
            }
        } catch (SocketException e) {
            // Socket closed, normal on exit
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
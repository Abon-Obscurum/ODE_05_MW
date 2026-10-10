package tcp;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController {
    // Injected from FXML via fx:id
    @FXML private TextField messageField;
    @FXML private ListView<String> messageList;

    // Connection to the server
    private Socket socket;
    private PrintWriter out;

    // Called automatically after the FXML is loaded
    @FXML
    public void initialize() {
        try {
            // Connect to server
            socket = new Socket("localhost", 4711);
            // Writer with autoFlush, sends immediately
            out = new PrintWriter(socket.getOutputStream(), true);
            // Read server responses in the background
            new ReaderThread(socket, messageList).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Called when the Send button is clicked
    @FXML
    private void onSend() {
        String message = messageField.getText();
        // println: the server reads line by line
        out.println(message);
        messageField.setText("");
    }
}
package tcp;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController {
    @FXML private TextField messageField;
    @FXML private ListView<String> messageList;

    private Socket socket;
    private PrintWriter out;

    @FXML
    public void initialize() {
        try {
            // Connect to server
            socket = new Socket("localhost", 4711);
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSend(){
        String message = messageField.getText();
        out.println(message);
        messageField.setText("");
    }

}

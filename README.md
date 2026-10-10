# ODE 5 — TCP Loopback Chat

Coursework exercise for ODE (FH Technikum Wien, SS 2026).

A TCP client/server application. The server runs in the console, the client has a JavaFX GUI built with Scene Builder. Messages sent from the client are printed on the server and echoed back to the client.

## Stack

- Java 17
- JavaFX 21
- Maven
- FXML + Scene Builder
- `java.net` sockets (TCP)

## Structure

```
src/main/java/tcp/
  Server.java             console server, echoes messages back
  Client.java             entry point, stage setup
  ClientController.java   connects to the server, sends messages
  ReaderThread.java       reads server responses in the background
src/main/resources/tcp/
  client-view.fxml        the GUI
```

## How it works

- Server listens on `localhost:4711` and accepts clients one after another.
- Messages are line-based (`println` / `readLine`), since TCP is a stream with no message boundaries.
- Client connects once on startup and sends the text field content on button click.
- `ReaderThread` waits for server responses and adds them to the `ListView` via `Platform.runLater`.

## Run

1. Start the server: run `tcp.Server` in IntelliJ, or from the terminal after building:
   ```
   java -cp target/classes tcp.Server
   ```
2. Start the client: run `tcp.Client`.
3. Type a message and click **Send**. It appears in the server console and in the client's list.
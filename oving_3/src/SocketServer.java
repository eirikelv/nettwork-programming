import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SocketServer {

    private static final ArrayList<ClientHandler> clients = new ArrayList<>();
    private static final int PORTNR = 1250;
    private static final ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORTNR);

        while (true){
            System.out.println("Login for connection. Waiting...");
            Socket connection = server.accept();  // venter inntil noen tar kontakt
            System.out.println("Server Connected to Client");
            ClientHandler clientThread = new ClientHandler(connection);
            System.out.println("connection created");
            clients.add(clientThread);

            pool.execute(clientThread);
        }
    }
}


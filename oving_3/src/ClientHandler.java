import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class ClientHandler implements Runnable {
    private Socket connection;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.connection = clientSocket;
    }

    @Override
    public void run() {
        PrintWriter writer = null;
        try {
            InputStreamReader readerConnection = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(readerConnection);
            writer = new PrintWriter(connection.getOutputStream(), true);

            writer.println("Hi You have connected to server");
            writer.println("Write an simple equation a + b");

            String oneLine = reader.readLine();
            while (oneLine != null) {  // forbindelsen på klientsiden er lukket
                System.out.println("The client wrote: " + oneLine);
                String[] equation = oneLine.split(" ");
                try {
                    writer.println("The answer to your equation is: " + calc(equation));  // sender svar til klienten
                } catch (Exception e) {
                    writer.println(e.getMessage());
                }
                oneLine = reader.readLine();
            }

            /* Lukker forbindelsen */
            reader.close();
            writer.close();
        } catch (Exception e) {
            assert writer != null;
            writer.println(e.getMessage());
        }
    }

    static int calc(String[] equation) {
        int firstNumb = Integer.parseInt(equation[0]);
        String symbol = equation[1];
        int secondNumb = Integer.parseInt(equation[2]);
        if (Objects.equals(symbol, "+")){
            return firstNumb + secondNumb;
        }else if (Objects.equals(symbol, "-")) {
            return firstNumb - secondNumb;
        }else if (Objects.equals(symbol, "*")) {
            return firstNumb * secondNumb;
        }else if (Objects.equals(symbol, "/")) {
            return firstNumb / secondNumb;
        }
        else return 0;
    }
}

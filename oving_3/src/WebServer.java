import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    public static void main(String[] args) throws IOException {
        final int PORTNR = 8080;

        /* Setter opp forbindelsen til tjenerprogrammet */
        ServerSocket client = new ServerSocket(PORTNR);
        System.out.println("Connection successful.");
        Socket connection = client.accept();

        InputStreamReader readerConnection = new InputStreamReader(connection.getInputStream());
        BufferedReader reader = new BufferedReader(readerConnection);
        PrintWriter writer = new PrintWriter(connection.getOutputStream(), true);


        StringBuilder header = new StringBuilder("<UL>");
        String enLinje = reader.readLine();
        while (!enLinje.equals("")){
            header.append("<LI>").append(enLinje).append("</LI>");
            enLinje = reader.readLine();
        }
        header.append("</UL>");
        System.out.println(header);

        // mottar en linje med tekst
        writer.println("HTTP/1.0 200 OK </br>");
        writer.println("Content-Type: text/html; charset=utf-8 </br>");
        writer.println("");
        writer.println("<HTML><BODY>");
        writer.println("<H1> Hilsen. Du har koblet deg opp til min enkle web-tjener </h1>");
        writer.println("Header fra klient er:");
        writer.println(header);
        writer.println("</BODY></HTML>");

        reader.close();
        writer.close();
        connection.close();

    }
}

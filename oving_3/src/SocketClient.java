import java.io.*;
import java.net.*;
import java.util.Scanner;

class SocketClient {
    public static void main(String[] args) throws IOException {
        final int PORTNR = 1250;
        Scanner cmdReader = new Scanner(System.in);
        System.out.print("Validate the name of the server machine: ");
        String clientMachine = cmdReader.nextLine();

        /* Setter opp forbindelsen til tjenerprogrammet */
        Socket connection = new Socket(clientMachine, PORTNR);
        System.out.println("Connection successful.");

        /* åpner en connection for kommunikasjon med tjenerprogrammet */
        InputStreamReader readerConnection = new InputStreamReader(connection.getInputStream());
        BufferedReader reader = new BufferedReader(readerConnection);
        PrintWriter writer = new PrintWriter(connection.getOutputStream(), true);

        /* Leser innledning fra tjeneren og skriver den til kommandovinduet */
        String introLine1 = reader.readLine();
        String introLine2 = reader.readLine();
        System.out.println(introLine1 + "\n" + introLine2);

        /* Leser tekst fra kommandovinduet (brukeren) */
        String oneLine = cmdReader.nextLine();
        while (!oneLine.equals("")) {
            writer.println(oneLine);  // sender teksten til tjeneren
            String response = reader.readLine();  // mottar response fra tjeneren
            System.out.println("From Server: " + response);
            oneLine = cmdReader.nextLine();
        }

        /* Lukker forbindelsen */
        reader.close();
        writer.close();
        connection.close();
    }
}

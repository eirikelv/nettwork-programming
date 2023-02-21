import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) throws IOException {

        DatagramSocket client = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localhost");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Write a simple equation a + b");
        String input = scanner.nextLine();
        byte[] buf = input.getBytes();
        DatagramPacket dp = new DatagramPacket(buf, buf.length, address, 8080);
        client.send(dp);

        DatagramPacket clientReceiverDp = new DatagramPacket(buf, buf.length);
        client.receive(clientReceiverDp);
        String response = new String(clientReceiverDp.getData(), 0, clientReceiverDp.getLength());
        System.out.println("Response from server is " + response);

    }
}

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class UDPServer {
    public static void main(String[] args) throws IOException{
        DatagramSocket server = new DatagramSocket(8080);

        byte[] serverBuff = new byte[256];
        DatagramPacket serverDp = new DatagramPacket(serverBuff, serverBuff.length);
        server.receive(serverDp);
        String response = new String(serverDp.getData(), serverDp.getOffset(), serverDp.getLength(), StandardCharsets.UTF_8);
        String[] equation = response.split(" ");
        String answer = String.valueOf(math(equation));

        byte[] answerBuff = answer.getBytes();
        DatagramPacket answerDp = new DatagramPacket(answerBuff, answerBuff.length,serverDp.getAddress(),serverDp.getPort());
        server.send(answerDp);
    }

    public static int math(String[] equation){
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

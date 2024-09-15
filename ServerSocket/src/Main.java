import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("监听");

            Socket clientSocket = serverSocket.accept();
            System.out.println("连接成功");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String message, response;

            while (true) {
                message = in.readLine();

                if (message.equals("false")) {
                    System.out.println("客户端断开");
                    break;
                }
                System.out.println("收到客户端消息: " + message);
                out.println("ok");
            }
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
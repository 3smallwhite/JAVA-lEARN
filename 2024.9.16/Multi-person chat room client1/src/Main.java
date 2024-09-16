import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9999);
            System.out.println("连接成功");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String message, response;
            BufferedReader write = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                message = write.readLine();

                if (message.equals("退出聊天室")) {
                    break;
                }

                out.println(message);
            }

            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
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

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String message;
            BufferedReader write = new BufferedReader(new InputStreamReader(System.in));

            new ClientThread(socket).start();

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
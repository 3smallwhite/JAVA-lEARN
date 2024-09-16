import java.io.*;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("发送请求.");
        Socket socket = new Socket("127.0.0.1", 9999);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String message, response;

        while (true) {
            System.out.println("我说：");
            message = reader.readLine();

            if (message.equals("false")) {
                out.println("false");
                break;
            }

            out.println(message);
            response = in.readLine();
            System.out.println(response);
        }

        socket.close();
    }
}
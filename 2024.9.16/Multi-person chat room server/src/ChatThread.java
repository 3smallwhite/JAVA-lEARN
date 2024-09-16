import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatThread extends Thread{
    Socket socket;
    List<Socket> socketList = new ArrayList<>();
    InetAddress inetAddress;

    public ChatThread(Socket socket, List<Socket> socketList, InetAddress inetAddress) {
        this.socket = socket;
        this.socketList = socketList;
        this.inetAddress = inetAddress;
    }

    void push(String message) throws IOException {
        for (Socket socket1: socketList) {
            if (socket1.getInetAddress() != this.inetAddress) {
                PrintWriter out = new PrintWriter(socket1.getOutputStream(), true);
                out.println(this.socket.getInetAddress() + "：" + message);
            }
        }
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            String message;
            while (true) {
               message = in.readLine();

               if (message.equals("退出聊天室")) {
                   System.out.println(this.inetAddress + "退出聊天室");
                   break;
               }
               System.out.println(this.socket.getInetAddress() + "用户消息：" + message);
               this.push(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

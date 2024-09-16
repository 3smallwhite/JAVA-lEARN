import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;
        List<Socket> socketList = new ArrayList<>();

        try {
            serverSocket = new ServerSocket(9999);

            while (true) {
                System.out.println("等待连接：");
                socket = serverSocket.accept();
                socketList.add(socket);
                System.out.println("连接成功！");
                System.out.println("目前群聊共有" + socketList.size() + "人");
                new ChatThread(socket, socketList, socket.getInetAddress()).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
package control;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void start() {
        ServerSocket serverSocket;
        Socket socket;

        try {
            System.out.println("服务器启动");
            serverSocket = new ServerSocket(13579);

            int cnt = 0;
            while (true) {
                socket = serverSocket.accept();
                ++cnt;
                System.out.println(cnt + "个连接");
                new OperationThread(socket).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
//            socket.close();
//            serverSocket.close();
        }
    }
}

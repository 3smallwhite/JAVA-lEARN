package control;

import entity.Statu;
import entity.Student;
import entity.User;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class OperationThread extends Thread {
    private Socket socket;

    public OperationThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(this.socket.getInputStream()));
            ObjectOutputStream out = new ObjectOutputStream(this.socket.getOutputStream());


            Datas datas = new Datas();

            while (true) {
                User user = (User)in.readObject();
                // 判断用户名，密码是否正确
                if (datas.loginCheck(user)) {
                    System.out.println(this.socket.getInetAddress() + "用户登入");
                    out.writeObject(new Statu("accept"));
                    break;
                } else {
                    out.writeObject(new Statu("wrong answer"));
                }
            }

            while (true) {
                Statu statu = (Statu)in.readObject();
                if (statu.getStatu().equals("new a student")) {
                    if (datas.newAStudent((Student) in.readObject())) {
                        out.writeObject(new Statu("accept"));
                    } else {
                        out.writeObject(new Statu("wrong answer"));
                    }
                } else if (statu.getStatu().equals("find students")) {
                    ArrayList<Student> students = datas.findStudents((Student) in.readObject());
                    out.writeObject(students);
                } else if (statu.getStatu().equals("delete a student")) {
                    datas.delStudent((Student) in.readObject());
                } else if (statu.getStatu().equals("update student")) {
                    if (datas.updateStudent((Student) in.readObject())) {
                        out.writeObject(new Statu("accept"));
                    } else {
                        out.writeObject(new Statu("wrong answer"));
                    }
                }else {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                this.socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

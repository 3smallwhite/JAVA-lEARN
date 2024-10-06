package control;

import entity.Statu;
import entity.Student;
import entity.User;

import java.io.*;
import java.net.Socket;
import java.sql.Array;
import java.util.ArrayList;

public class Datas {
    static Socket socket;
    static ObjectInputStream in;
    static ObjectOutputStream out;

    static {
        try {
            socket = new Socket("10.161.33.106", 13579);

            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean userLogin(User user) {
        try {

            out.writeObject(user);

            Statu statu = (Statu) in.readObject();


            return statu.getStatu().equals("accept");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public Boolean setStudent(Student student) throws IOException, ClassNotFoundException {
        out.writeObject(new Statu("new a student"));

        out.writeObject(student);

        if (((Statu)in.readObject()).getStatu().equals("accept")) {
            return true;
        }
        return false;
    }

    public ArrayList<Student> findStudent(Student student) throws IOException, ClassNotFoundException {
        if (student.getId().equals("")) {
            student.setId("%");
        }
        if (student.getName().equals("")) {
            student.setName("%");
        }
        if (student.getMajor().equals("")) {
            student.setMajor("%");
        }
        if (student.getEnrollmentDate().equals("")) {
            student.setEnrollmentDate("%");
        }

        out.writeObject(new Statu("find students"));

        out.writeObject(student);

        return (ArrayList<Student>) in.readObject();
    }

    public void delStudent(Student student) throws IOException {
        out.writeObject(new Statu("delete a student"));
        out.writeObject(student);
    }

    public boolean uptStudent(Student student) throws IOException, ClassNotFoundException {
        out.writeObject(new Statu("update student"));

        out.writeObject(student);

        return ((Statu) in.readObject()).equals("accept");
    }
}

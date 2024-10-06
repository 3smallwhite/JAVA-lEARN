package test;

import control.Datas;
import entity.User;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.awt.SystemColor.control;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        User user = new User("user1", "password1", new Date());
        Datas datas = new Datas();
        System.out.println(datas.loginCheck(user));;
    }
}
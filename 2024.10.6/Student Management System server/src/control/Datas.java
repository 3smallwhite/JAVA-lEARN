package control;

import com.mysql.cj.jdbc.ClientPreparedStatement;
import entity.Student;
import entity.User;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Datas {

    Connection getConnection(String schema, String root, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + schema, root, password);
        return con;
    }

    public Boolean loginCheck(User user) throws SQLException, ClassNotFoundException {
        Connection con = getConnection("javawork1", "root", "123456");
        Statement statement = con.createStatement();
        String sql = "select password from users where user =" + "'" + user.getName() + "'";
        ResultSet resultSet = statement.executeQuery(sql);
        String password = null;
        while (resultSet.next()) {
            password = resultSet.getString(1);
        }
        if (password == null || !password.equals(user.getPassword())) {
            con.close();
            return false;
        }
        // 密码正确就更新登入时间
        sql = "update users set logintime = " + "'" + new SimpleDateFormat("yyyy-MM-dd").format(user.getDate()) + "'" + "where user = " + "'" + user.getName() + "'";
        statement.executeUpdate(sql);
        con.close();
        return true;
    }

    public String getMajorId(String majorName) throws SQLException, ClassNotFoundException {
        Connection con = getConnection("javawork1", "root", "123456");
        Statement statement = con.createStatement();

        String majorId = null;
        String sql = "select id from majors where name = " + "'" + majorName + "'";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            majorId = resultSet.getString(1);
        }
        con.close();
        return majorId;
    }

    public int getStudentNumber() throws SQLException, ClassNotFoundException {
        Connection con = getConnection("javawork1", "root", "123456");
        Statement statement = con.createStatement();

        int num = 0, nnum;
        String sql = "select enrollnum from enrollnums where university = 'test'";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            num = resultSet.getInt(1);
        }
        nnum = num + 1;
        sql = "update enrollnums set enrollnum = " + nnum + " where university = 'test'";
        statement.executeUpdate(sql);
        con.close();
        return num;
    }

    public boolean updateStudent(Student student) throws SQLException, ClassNotFoundException, ParseException {
        if (getMajorId(student.getMajor()) == null) return false;
        delStudent(new Student(student.getId()));
        insert(student);
        return true;
    }

    public void insert(Student student) throws SQLException, ClassNotFoundException, ParseException {
        Connection con = getConnection("javawork1", "root", "123456");
        Statement statement = con.createStatement();

        String sql = "Insert into students values(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, student.getId());
        preparedStatement.setString(2, student.getName());
        preparedStatement.setString(3, student.getSex());
        preparedStatement.setString(4, student.getMajor());
        preparedStatement.setDate(5, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(student.getEnrollmentDate()).getTime()));
        preparedStatement.setFloat(6, Float.parseFloat(student.getScore()));
        preparedStatement.executeUpdate();
        con.close();
    }

    public Boolean newAStudent(Student student) throws SQLException, ClassNotFoundException, ParseException {

        String majorId = getMajorId(student.getMajor());
        String id = "";
        if (majorId == null) {
            return false;
        }
        id += majorId;
        id += student.getEnrollmentDate().toString().substring(0, 4);
        id += getStudentNumber() + 1;

        student.setId(id);

        insert(student);

        return true;
    }

    public ArrayList<Student> findStudents(Student student) throws SQLException, ClassNotFoundException {
        Connection con = getConnection("javawork1", "root", "123456");
        Statement statement = con.createStatement();
        String sql = "select * from students where " + "name like " + " '" + student.getName() + "'  and " + "majorname like " + " '" + student.getMajor() + "' and " + "enrollmentdate like" + " '" + student.getEnrollmentDate() + "-%-%" + "' " + "and id like '" + student.getId() + "' ";
        //System.out.println(sql);
        ResultSet resultSet = statement.executeQuery(sql);

        ArrayList<Student> arrayList = new ArrayList<>();
        while (resultSet.next()) {
            arrayList.add(new Student(resultSet.getString(1),  resultSet.getString(2),  resultSet.getString(3),  resultSet.getString(4),  resultSet.getString(5),  resultSet.getString(6)));
        }

        con.close();

        return arrayList;
    }

    public void delStudent(Student student) throws SQLException, ClassNotFoundException {
        Connection con = getConnection("javawork1", "root", "123456");
        Statement statement = con.createStatement();

        String sql = "delete from students where id = " + "'" + student.getId() + "' ";

        statement.executeUpdate(sql);
    }
}

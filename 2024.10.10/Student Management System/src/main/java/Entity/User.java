package Entity;

import java.util.Date;

public class User {
    private String user;
    private String password;

    private Date loginTime;

    public User(String user, String password, Date loginTiem) {
        this.user = user;
        this.password = password;
        this.loginTime = loginTiem;
    }

    public User() {
    }

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }
    public User(User user) {
        this.user = user.user;
        this.password = user.password;
    }

    public Date getLoginTiem() {
        return loginTime;
    }

    public void setLoginTiem(Date loginTiem) {
        this.loginTime = loginTiem;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

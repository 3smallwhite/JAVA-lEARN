package entity;

import java.util.Date;

public class User implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String password;
    private Date date;

    public User(String name, String password, Date date) {
        this.name = name;
        this.password = password;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

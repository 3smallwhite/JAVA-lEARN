package entity;

import java.util.Date;

public class Student implements java.io.Serializable {
    private static final long serialVersionUID = 2L;
    private String id;
    private String name;

    @Override
    public String toString() {
        return "学号：'" + id + '\'' +
                ", 姓名：'" + name + '\'' +
                ", 性别：'" + sex + '\'' +
                ", 专业：'" + majorName + '\'' +
                ", 入学时间：'" + enrollmentDate + '\'' +
                ", 成绩：'" + score;
    }

    private String sex;
    private String majorName;
    private String enrollmentDate;
    private String score;

    public Student(String id, String name, String sex, String major, String enrollmentDate, String score) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.majorName = major;
        this.enrollmentDate = enrollmentDate;
        this.score = score;
    }

    public Student(String id) {
        this.id = id;
    }

    public Student(String id, String name, String majorName, String enrollmentDate) {
        this.id = id;
        this.name = name;
        this.majorName = majorName;
        this.enrollmentDate = enrollmentDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMajor() {
        return majorName;
    }

    public void setMajor(String major) {
        this.majorName = major;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
package Entity;

import java.util.Date;

public class Staff {
    private Integer id;
    private String name;
    private int age;
    private String sex;
    private Date startingTime;
    private String postName;

    private Float salary;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Staff(Integer id, String name, int age, String sex, Date startingTime, String postName, Float salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.startingTime = startingTime;
        this.postName = postName;
        this.salary = salary;
    }

    public Staff() {
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", startingTime=" + startingTime +
                ", postName='" + postName + '\'' +
                ", salary=" + salary +
                '}';
    }
}

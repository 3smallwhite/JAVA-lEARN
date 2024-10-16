package Entity;

import lombok.ToString;

import java.sql.Date;

@ToString
public class QueryCondition {
    private String name;
    private String sex;
    private String postName;
    private Date t1;
    private Date t2;
    private Float s1;
    private Float s2;

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

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Date getT1() {
        return t1;
    }

    public void setT1(Date t1) {
        this.t1 = t1;
    }

    public Date getT2() {
        return t2;
    }

    public void setT2(Date t2) {
        this.t2 = t2;
    }

    public Float getS1() {
        return s1;
    }

    public void setS1(Float s1) {
        this.s1 = s1;
    }

    public Float getS2() {
        return s2;
    }

    public void setS2(Float s2) {
        this.s2 = s2;
    }

    public QueryCondition(String name, String sex, String postName, Date t1, Date t2, Float s1, Float s2) {
        this.name = name;
        this.sex = sex;
        this.postName = postName;
        this.t1 = t1;
        this.t2 = t2;
        this.s1 = s1;
        this.s2 = s2;
    }

    public QueryCondition() {
    }
}

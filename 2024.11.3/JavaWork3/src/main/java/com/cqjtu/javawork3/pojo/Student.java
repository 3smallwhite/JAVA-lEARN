package com.cqjtu.javawork3.pojo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Student {
    @Id
    private Long id;
    @Column
    private Integer age;
    @Column(length = 45)
    private String college;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date enrollmentDate;
    @Column(length = 45)
    private String name;
    @Column(length = 1)
    private String sex;
    @Column(length = 11)
    private String telephone;
    @ManyToOne
    private Major major;
    @Column
    private String majorName;
    public Student(Long id, Integer age, String college, Date enrollmentDate, String name, String sex, String telephone, Major major, String majorName) {
        this.id = id;
        this.age = age;
        this.college = college;
        this.enrollmentDate = enrollmentDate;
        this.name = name;
        this.sex = sex;
        this.telephone = telephone;
        this.major = major;
        this.majorName = majorName;
    }

    public Student(Long id) {
        this.id = id;
    }
}

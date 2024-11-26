package com.cqjtu.javawork3.pojo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Grade {
    @Id
    private Long id;
    @Column
    private Double grade;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastOperate;

    public Grade(Long id, Double grade, Student student, Course course) {
        this.id = id;
        this.grade = grade;
        this.student = student;
        this.course = course;
    }
}

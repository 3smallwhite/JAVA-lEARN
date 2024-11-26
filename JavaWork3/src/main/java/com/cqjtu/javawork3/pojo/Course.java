package com.cqjtu.javawork3.pojo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.atn.BlockEndState;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Course {
    @Id
    private Long id;
    @Column
    private String name;
    @Column
    private Double score;
    @Column
    private String introduction;
    @Column(columnDefinition = "Integer default 0")
    private Integer num;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastOperate;

    public Course(Long id) {
        this.id = id;
    }

    public Course(Long id, String name, Double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

}

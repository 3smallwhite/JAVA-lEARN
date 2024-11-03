package com.cqjtu.javawork3.pojo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.atn.BlockEndState;

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

    public Course(Long id) {
        this.id = id;
    }

    public Course(String name, Double score) {
        this.name = name;
        this.score = score;
    }
}

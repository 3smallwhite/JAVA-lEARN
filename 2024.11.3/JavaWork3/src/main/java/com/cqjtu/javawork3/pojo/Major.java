package com.cqjtu.javawork3.pojo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Entity
@NoArgsConstructor
@Data
public class Major {
    @Id
    private Long id;
    @Column(length = 45)
    private String name;

    public Major(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Major(String name) {
        this.name = name;
    }

    public Major(Long id) {
        this.id = id;
    }
}

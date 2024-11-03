package com.cqjtu.javawork3.pojo;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Entity
public class User {
    @Id
    private Long username;
    @Column
    private String password;
    @Column
    private String role;

    public User(Long username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(Long username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }
}

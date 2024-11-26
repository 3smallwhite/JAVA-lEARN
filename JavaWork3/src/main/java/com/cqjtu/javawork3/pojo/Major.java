package com.cqjtu.javawork3.pojo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;


@Entity
@NoArgsConstructor
@Data
public class Major {
    @Id
    private Long id;
    @Column(length = 45)
    private String name;
    @Column
    private String introduction;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date lastOperate;

    public Major(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Major(Long id, String name, Date lastOperate) {
        this.id = id;
        this.name = name;
        this.lastOperate = lastOperate;
    }

    public Major(String name) {
        this.name = name;
    }

    public Major(Long id) {
        this.id = id;
    }
}

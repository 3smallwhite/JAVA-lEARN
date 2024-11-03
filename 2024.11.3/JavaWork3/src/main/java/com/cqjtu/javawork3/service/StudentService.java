package com.cqjtu.javawork3.service;

import com.cqjtu.javawork3.pojo.Student;
import org.springframework.stereotype.Service;

public interface StudentService {
    boolean register(Student student);

    String generateId(Student student);
}

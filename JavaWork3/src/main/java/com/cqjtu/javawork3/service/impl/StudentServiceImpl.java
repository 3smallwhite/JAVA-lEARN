package com.cqjtu.javawork3.service.impl;

import com.cqjtu.javawork3.pojo.Major;
import com.cqjtu.javawork3.pojo.Student;
import com.cqjtu.javawork3.pojo.User;
import com.cqjtu.javawork3.repository.MajorRepository;
import com.cqjtu.javawork3.repository.StudentRepository;
import com.cqjtu.javawork3.repository.UserRepository;
import com.cqjtu.javawork3.service.StudentService;
import com.cqjtu.javawork3.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    @Value("${user.init.password}")
    private String initPassword;

    @Override
    public boolean register(Student student) {
        Student student1 = studentRepository.findByTelephone(student.getTelephone());
        if (student1 != null) {
            return false;
        }
        Long id = Long.parseLong(generateId(student));
        Long majorId = majorRepository.findByName(student.getMajorName()).getId();
        student.setId(id);
        student.setMajor(new Major(majorId, student.getMajorName()));
        User user = new User(id, Md5Utils.toMd5(initPassword), "student", new Date());
        student.setLastOperate(new Date());
        studentRepository.save(student);
        userRepository.save(user);
        return true;
    }

    @Autowired
    private MajorRepository majorRepository;
    @Override
    public String generateId(Student student) {
        if (studentRepository.findMaxId(student.getMajorName()) != null) {
            return "" + (studentRepository.findMaxId(student.getMajorName()) + 1L);
        }
        Major major = majorRepository.findByName(student.getMajorName());
        String year = new SimpleDateFormat("yyyy").format(student.getEnrollmentDate());
        return major.getId() + year + 1;
    }

    @Override
    public boolean existTelephone(String telephone) {
        if (studentRepository.findByTelephone(telephone) != null) return true;
        return false;
    }
}

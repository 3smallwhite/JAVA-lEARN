package com.cqjtu.javawork3.repository;

import com.cqjtu.javawork3.pojo.Major;
import com.cqjtu.javawork3.pojo.Student;
import com.cqjtu.javawork3.service.StudentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentRepository extends JpaRepository<Student, String> {
    @Query(value = "select * from student where telephone = ?1", nativeQuery = true)
    Student findByTelephone(String telephone);

    @Query(value = "select max(s.id) from Student s where s.majorName = :name")
    Long findMaxId(String name);

    @Query(value = "select s from Student s where s.major = :major")
    List<Student> findByMajor(Major major);
}

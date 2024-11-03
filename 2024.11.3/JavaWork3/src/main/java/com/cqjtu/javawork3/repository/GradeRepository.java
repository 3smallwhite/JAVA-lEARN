package com.cqjtu.javawork3.repository;

import com.cqjtu.javawork3.pojo.Course;
import com.cqjtu.javawork3.pojo.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GradeRepository extends JpaRepository<Grade, String> {
    @Query(value = "select g from Grade g where g.course = :course")
    List<Grade> findByCourse(Course course);
}

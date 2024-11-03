package com.cqjtu.javawork3.repository;

import com.cqjtu.javawork3.pojo.Course;
import com.cqjtu.javawork3.pojo.Major;
import com.cqjtu.javawork3.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CourseRepository extends JpaRepository<Course, String> {

    @Query(value = "select c from Course c where c in (select g.course from Grade g where g.student.id = :studentId)")
    List<Course> findByStudent(Long studentId);

    @Query(value = "select c from Course c where c not in (select g.course from Grade g where g.student.id = :studentId)")
    List<Course> findNoCourseByStudent(Long studentId);

    @Query(value = "select c from Course c where c.name like :findCondition")
    List<Course> findByFindCondition(String findCondition);

    @Query(value = "select c from Course c where c.score = :score")
    List<Course> findByScore(Double score);

    @Query(value = "select c from Course c where c.id = :id")
    Course findByStringId(String id);

    @Query(value = "select max(c.id) from Course c")
    Long findMaxId();
}

package com.cqjtu.javawork3.repository;

import com.cqjtu.javawork3.pojo.Course;
import com.cqjtu.javawork3.pojo.Grade;
import com.cqjtu.javawork3.pojo.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GradeRepository extends JpaRepository<Grade, String> {
    @Query(value = "select g from Grade g where g.course = :course")
    List<Grade> findByCourse(Course course);

    @Query(value = "select g.student from Grade g where g.course.id = :courseId")
    Page<List<Student>> findStudentByCourseId(Long courseId, Pageable pageable);

    @Query(value = "select g.student from Grade g where g.student.id = :studentId")
    Student findStudentByStudentId(Long studentId);

    @Query(value = "select g.student from Grade g where g.student.name like :studentName")
    List<Student> findStudentByStudentName(String studentName);

    @Query(value = "select g.student from Grade g where g.student.majorName like :studentMajorName")
    List<Student> findStudentByStudentMajorName(String studentMajorName);

    @Query(value = "select g.student from Grade g where g.student.sex = :studentSex")
    List<Student> findStudentByStudentSex(String studentSex);

    @Query(value = "select g.student from Grade g where g.student.id = :studentId or g.student.name like :studentName or g.student.majorName like :studentMajorName or g.student.sex = :studentSex")
    Page<List<Student>> findStudents(Long studentId, String studentName, String studentMajorName, String studentSex, Pageable pageable);

    @Query(value = "select count(g.student) from Grade g where g.course.id = :courseId")
    Integer findStudentCount(Long courseId);
}

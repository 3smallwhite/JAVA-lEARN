package com.cqjtu.javawork3.controller;

import com.cqjtu.javawork3.pojo.Course;
import com.cqjtu.javawork3.pojo.Grade;
import com.cqjtu.javawork3.pojo.Result;
import com.cqjtu.javawork3.pojo.Student;
import com.cqjtu.javawork3.repository.GradeRepository;
import com.cqjtu.javawork3.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("grade")
public class GradeController {

    @Autowired
    private GradeRepository gradeRepository;

    @PostMapping("add")
    public Result addCourse(@Param("courseId") Long courseId, @RequestHeader("token") String token) {
        Long studentId = null;
        try {
            Map<String, Object> map = JwtUtils.parseToken(token);
            studentId = Long.valueOf(map.get("id").toString());
        } catch (Exception e) {
            return Result.error("请登入");
        }
        Grade grade = new Grade(Long.valueOf("" + courseId + studentId), null, new Student(studentId), new Course(courseId));
        gradeRepository.save(grade);
        return Result.success();
    }

    @PostMapping("sub")
    public Result subCourse(@Param("courseId") Long courseId, @RequestHeader("token") String token) {
        Long studentId = null;
        try {
            Map<String, Object> map = JwtUtils.parseToken(token);
            studentId = Long.valueOf(map.get("id").toString());
        } catch (Exception e) {
            return Result.error("请登入");
        }
        gradeRepository.deleteById("" + courseId + studentId);
        return Result.success();
    }
}

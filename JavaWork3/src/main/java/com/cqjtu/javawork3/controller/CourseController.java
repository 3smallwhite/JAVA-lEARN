package com.cqjtu.javawork3.controller;

import com.cqjtu.javawork3.pojo.Course;
import com.cqjtu.javawork3.pojo.Grade;
import com.cqjtu.javawork3.pojo.Result;
import com.cqjtu.javawork3.pojo.Student;
import com.cqjtu.javawork3.repository.CourseRepository;
import com.cqjtu.javawork3.repository.GradeRepository;
import com.cqjtu.javawork3.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @GetMapping("/mycourses")
    public Result getMyCourses(@RequestHeader("token") String token) {
        Long id = null;
        try {
            Map<String, Object> map = JwtUtils.parseToken(token);
            id = Long.valueOf(map.get("id").toString());
        } catch (Exception e) {
            return Result.error("请登入");
        }
        return Result.success(courseRepository.findByStudent(id));
    }

    @GetMapping("notmycourse")
    public Result getNotMyCourse(HttpServletRequest httpServletRequest, @RequestHeader("token") String token) {
        Long id = null;
        try {
            Map<String, Object> map = JwtUtils.parseToken(token);
            id = Long.valueOf(map.get("id").toString());
        } catch (Exception e) {
            return Result.error("请登入");
        }
        return Result.success(courseRepository.findNoCourseByStudent(id));
    }

    @GetMapping("get")
    public Result getAllCourse() {
        return Result.success(courseRepository.findAll());
    }

    @PostMapping("/admin/add")
    public Result addCourse(Course course) {
        Long id = courseRepository.findMaxId();
        if (id == null) id = 999L;
        course.setId(id + 1);
        course.setLastOperate(new Date());
        courseRepository.save(course);
        return Result.success();
    }

    @PostMapping("/admin/upt")
    public Result updateCourse(Course course) {
        course.setLastOperate(new Date());
        courseRepository.save(course);
        return Result.success();
    }

    @PostMapping("/admin/del")
    public Result deleteCourse(@Param("id") Long id) {
        List<Grade> list = gradeRepository.findByCourse(new Course(id));
        if (list != null && list.size() > 0) return Result.error("该课程已有学生，无法删除");
        courseRepository.delete(new Course(id));
        return Result.success();
    }

    @GetMapping("/admin/fin")
    public Result findCourse(@Param("findCondition") String findCondition) {
        List<Course> list = courseRepository.findByFindCondition("%" + findCondition + "%");
        try {
            Double score = Double.valueOf(findCondition);
            list.addAll(courseRepository.findByScore(score));
        } catch (Exception e) {

        }
        try {
            Long id = Long.valueOf(findCondition);
            list.add(courseRepository.findByStringId(findCondition));
        } catch (Exception e) {

        }
        // 去重
        list = new ArrayList<>(new HashSet<>(list));
        return Result.success(list);
    }

    @GetMapping("admin/students")
    public Result findStudents(@Param("courseId") Long courseId, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by("lastOperate").descending());
        return Result.success(gradeRepository.findStudentByCourseId(courseId, pageable));
    }

    @GetMapping("admin/students/total")
    public Result getStudentCount(Long courseId) {
        return Result.success(gradeRepository.findStudentCount(courseId));
    }

    @GetMapping("admin/student/fin")
    public Result findStudent(@Param("findCondition") String findCondition, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize) {
        Long studentId = null;
        try {
            studentId = Long.valueOf(findCondition);
        } catch (Exception e) {

        }
        if (studentId == null) studentId = 0L;
        findCondition = "%" + findCondition + "%";
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by("lastOperate").descending());
        return Result.success(gradeRepository.findStudents(studentId, findCondition, findCondition, findCondition, pageable));
    }
}

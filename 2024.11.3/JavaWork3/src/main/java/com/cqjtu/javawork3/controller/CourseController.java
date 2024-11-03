package com.cqjtu.javawork3.controller;

import com.cqjtu.javawork3.pojo.Course;
import com.cqjtu.javawork3.pojo.Grade;
import com.cqjtu.javawork3.pojo.Result;
import com.cqjtu.javawork3.repository.CourseRepository;
import com.cqjtu.javawork3.repository.GradeRepository;
import com.cqjtu.javawork3.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @GetMapping("/mycourses")
    public Result getMyCourses(HttpServletRequest httpServletRequest, @RequestHeader("token") String token) {
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
        course.setId(id + 1);
        courseRepository.save(course);
        return Result.success();
    }

    @PostMapping("/admin/upt")
    public Result updateCourse(Course course) {
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
            System.out.println("1");
        }
        try {
            Long id = Long.valueOf(findCondition);
            list.add(courseRepository.findByStringId(findCondition));
        } catch (Exception e) {
            System.out.println("2");
        }
        return Result.success(list);
    }
}

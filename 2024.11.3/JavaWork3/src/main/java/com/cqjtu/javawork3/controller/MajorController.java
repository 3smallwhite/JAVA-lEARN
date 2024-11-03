package com.cqjtu.javawork3.controller;

import com.cqjtu.javawork3.pojo.Major;
import com.cqjtu.javawork3.pojo.Result;
import com.cqjtu.javawork3.pojo.Student;
import com.cqjtu.javawork3.repository.MajorRepository;
import com.cqjtu.javawork3.repository.StudentRepository;
import com.cqjtu.javawork3.repository.UserRepository;
import com.cqjtu.javawork3.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/major")
public class MajorController {
    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get")
    public Result getMajors() {
        return Result.success(majorRepository.findAll());
    }

    @PostMapping("/admin/add")
    public Result addMajor(Major major) {
        Long id = majorRepository.findMaxId();
        major.setId(id + 1);
        majorRepository.save(major);
        return Result.success();
    }

    @PostMapping("/admin/upt")
    public Result updateMajor(Major major) {
        majorRepository.save(major);
        return Result.success();
    }

    @PostMapping("/admin/del")
    public Result deleteMajor(@Param("id") Long id) {
        List<Student> list = studentRepository.findByMajor(new Major(id));
        if (list != null && list.size() > 0) return Result.error("该专业有学生，无法删除");
        majorRepository.delete(new Major(id));
        return Result.success();
    }

    @GetMapping("/admin/fin")
    public Result findMajor(@Param("findCondition") String findCondition) {
        if (findCondition == "") return Result.success(majorRepository.findAll());
        List<Major> list = new ArrayList<>();
        list.addAll(majorRepository.findByFindCondition("%" + findCondition + "%"));
        try {
            Long id = Long.valueOf(findCondition);
            list.add(majorRepository.findByStringId(findCondition));
        } catch (Exception e) {

        }
        return Result.success(list);
    }
}

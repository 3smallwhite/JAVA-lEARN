package com.cqjtu.javawork3.controller;

import com.cqjtu.javawork3.pojo.Major;
import com.cqjtu.javawork3.pojo.Result;
import com.cqjtu.javawork3.pojo.User;
import com.cqjtu.javawork3.repository.UserRepository;
import com.cqjtu.javawork3.utils.JwtUtils;
import com.cqjtu.javawork3.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.NestingKind;
import java.util.*;

@RestController
@RequestMapping("/admin")
public class UserController {

    @Value("${user.init.password}")
    private String PASSWORD;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public Result login(@RequestParam("username") Long username, @RequestParam("password") String password) {
        User user = userRepository.findByUsername(username);
        String toMd5 = Md5Utils.toMd5(password);
        if (user != null && user.getPassword().equals(toMd5) && user.getRole().equals("admin")) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getUsername());
            String token = JwtUtils.genToken(claims);
            user.setLastOperate(new Date());
            userRepository.save(user);
            return Result.success(token);
        } else {
            return Result.error("密码错误");
        }
    }

    @GetMapping("/user")
    public Result getUsers() {
        return Result.success(userRepository.findAll());
    }

    @PostMapping("init")
    public Result initPassword(@Param("username") Long username) {
        userRepository.save(new User(username, Md5Utils.toMd5(PASSWORD), "student", new Date()));
        return Result.success();
    }

    @GetMapping("/admin/fin")
    public Result findUser(@Param("findCondition") String findCondition) {
        if (findCondition.equals("")) return Result.success(userRepository.findAll());
        try {
            Long id = Long.valueOf(findCondition);
            List<User> list = new ArrayList<>();
            list.add(userRepository.findByUsername(id));
            return Result.success(list);
        } catch (Exception e) {
            return Result.error("无数据");
        }
    }

    @PostMapping("/admin/del")
    public Result deleteUser(User user) {
        userRepository.delete(user);
        return Result.success();
    }
}

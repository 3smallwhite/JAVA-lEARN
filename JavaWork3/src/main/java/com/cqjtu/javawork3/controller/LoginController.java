package com.cqjtu.javawork3.controller;

import com.cqjtu.javawork3.pojo.Major;
import com.cqjtu.javawork3.pojo.Result;
import com.cqjtu.javawork3.pojo.Student;
import com.cqjtu.javawork3.pojo.User;
import com.cqjtu.javawork3.repository.UserRepository;
import com.cqjtu.javawork3.service.StudentService;
import com.cqjtu.javawork3.utils.JwtUtils;
import com.cqjtu.javawork3.utils.Md5Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @Value("${user.init.password}")
    private String initPassword;

    @PostMapping("/login")
    public Result login(@RequestParam("username") Long username, @RequestParam("password") String password) {
        User user = userRepository.findByUsername(username);
        String toMd5 = Md5Utils.toMd5(password);
        if (user != null && user.getPassword().equals(toMd5) && user.getRole().equals("student")) {
            if (password.equals(initPassword)) {
                return Result.error("请修改密码后登入");
            }
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getUsername());
            String token = JwtUtils.genToken(claims);
            return Result.success(token);
        } else {
            return Result.error("密码错误");
        }
    }

    @PostMapping("/change")
    public Result changePassword(@RequestParam("username") Long username, @RequestParam("password") String password, @RequestParam("newPassword") String newPasswrod) {
        if (newPasswrod.length() < 7) return Result.error("密码格式过于简单");
        Boolean haveN = false, haveC = false, haveE = false;
        for (int i = 0; i < newPasswrod.length(); i++) {
            if (newPasswrod.charAt(i) >= '1' && newPasswrod.charAt(i) <= '9') haveN = true;
            else if (newPasswrod.charAt(i) >= 'a' && newPasswrod.charAt(i) <= 'z' || newPasswrod.charAt(i) >= 'A' && newPasswrod.charAt(i) <= 'Z') haveC = true;
            else haveE = true;
        }
        if ((haveC && haveE && haveN) == false) return Result.error("密码格式过于简单");
        User user = userRepository.findByUsername(username);
        String toMd5 = Md5Utils.toMd5(password);
        if (user == null || !user.getPassword().equals(toMd5) || !user.getRole().equals("student")) {
            return Result.error("密码错误");
        }
        user.setPassword(Md5Utils.toMd5(newPasswrod));
        user.setLastOperate(new Date());
        userRepository.save(user);
        return Result.success("修改密码成功");
    }

    @Autowired
    private StudentService studentService;

    @PostMapping("register")
    public Result register(Student student) {
        if (studentService.register(student)) {
            return Result.success();
        } else {
            return Result.error("注册失败");
        }
    }

    @PostMapping("/register/check")
    public Result checkTelephone(@Param("telephone") String telephone) {
        if (studentService.existTelephone(telephone)) return Result.error("电话号也被绑定");
        return Result.success();
    }
}

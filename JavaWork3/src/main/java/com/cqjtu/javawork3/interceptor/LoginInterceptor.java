package com.cqjtu.javawork3.interceptor;

import com.cqjtu.javawork3.pojo.Result;
import com.cqjtu.javawork3.pojo.User;
import com.cqjtu.javawork3.repository.UserRepository;
import com.cqjtu.javawork3.utils.JwtUtils;
import com.cqjtu.javawork3.utils.Md5Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${interceptor.resource.static}")
    private String staticRes;

    @Value("${interceptor.resource.admin}")
    private String adminRes;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String url = String.valueOf(request.getRequestURL());
        if (url.matches(staticRes)) {
            return true;
        }
//        if (url.matches(adminRes)) {
//            try {
//                String token = request.getHeader("token");
//                Map<String, Object> map = JwtUtils.parseToken(token);
//                Long id = Long.valueOf(map.get("id").toString());
//                User user = userRepository.findByUsername(id);
//                if (user == null || !user.getRole().equals("admin")) return false;
//            } catch (Exception e) {
//                return false;
//            }
//        }
        return true;
    }
}

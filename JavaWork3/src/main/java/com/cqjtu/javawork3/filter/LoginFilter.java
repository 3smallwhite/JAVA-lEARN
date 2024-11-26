package com.cqjtu.javawork3.filter;


import com.cqjtu.javawork3.pojo.User;
import com.cqjtu.javawork3.repository.UserRepository;
import com.cqjtu.javawork3.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Map;

@Configuration
public class LoginFilter implements Filter {

    @Value("${regex.filter}")
    private String regex;

    @Autowired
    private UserRepository userRepository;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String url = String.valueOf(httpServletRequest.getRequestURL());
        if (url.matches(regex)) {
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest,servletResponse);
//        String token = httpServletRequest.getHeader("token");
//        try {
//            Map<String, Object> claims = JwtUtils.parseToken(token);
//            Long id = Long.valueOf(claims.get("id").toString());
//            User user = userRepository.findByUsername(id);
//            if (user != null && user.getRole().equals("admin")) filterChain.doFilter(servletRequest,servletResponse);
//        } catch (Exception e) {
//
//        }
    }
}

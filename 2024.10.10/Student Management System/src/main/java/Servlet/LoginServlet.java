package Servlet;

import Dao.LoginDao;
import Entity.User;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPath;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/Servlet/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jsonStr = request.getParameter("user");
        User user = JSONObject.parseObject(jsonStr, JSONObject.class).toJavaObject(User.class);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "tle");
        try {
            if (LoginDao.check(user)) {
                JSONPath.set(jsonObject, "status", "ac");
                request.getSession().setAttribute("token", "ok");
                jsonObject.put("url", "http://localhost:8080/center/center.html");
            }
            else {
                response.getWriter().print(jsonObject);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        response.getWriter().print(jsonObject);
    }
}

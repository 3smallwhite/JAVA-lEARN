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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jsonStr = request.getParameter("user");
        User user = JSONObject.parseObject(jsonStr, JSONObject.class).toJavaObject(User.class);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "tle");
        try {
            if (LoginDao.check(user)) {
                JSONPath.set(jsonObject, "status", "ac");
                request.getSession().setAttribute("token", "ok");
                response.sendRedirect("http://localhost:8080/center/center.html");
            }
            else
                JSONPath.set(jsonObject, "status", "wa");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PrintWriter pw = response.getWriter();
        pw.print(jsonObject);
        pw.close();
    }
}

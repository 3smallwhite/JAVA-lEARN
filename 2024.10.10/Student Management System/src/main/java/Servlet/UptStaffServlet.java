package Servlet;

import Dao.LoginDao;
import Dao.StaffDao;
import Entity.QueryCondition;
import Entity.Staff;
import Entity.User;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPath;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/center/upt")
public class UptStaffServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //response.setHeader("Access-Control-Allow-Origin", "*");
        String jsonStr = request.getParameter("data");
        Staff staff = JSONObject.parseObject(jsonStr, JSONObject.class).toJavaObject(Staff.class);

        JSONObject jsonObject = new JSONObject();
        boolean b = StaffDao.updateStaff(staff);
        if (b) jsonObject.put("status", "ac");
        else jsonObject.put("status", "wa");
        response.getWriter().print(jsonObject);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}

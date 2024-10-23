package Servlet;

import Dao.LoginDao;
import Dao.StaffDao;
import Entity.QueryCondition;
import Entity.Staff;
import Entity.User;
import Mapper.StaffMapper;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

@WebServlet("/center/insert")
public class AddStaffServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jsonStr = request.getParameter("data");
        Staff staff = JSONObject.parseObject(jsonStr, JSONObject.class).toJavaObject(Staff.class);
        boolean b = StaffDao.newAStaff(staff);
        JSONObject jsonObject = new JSONObject();
        if (b) jsonObject.put("status", "ac");
        else jsonObject.put("status", "wa");
        response.getWriter().print(jsonObject);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {


    }
}

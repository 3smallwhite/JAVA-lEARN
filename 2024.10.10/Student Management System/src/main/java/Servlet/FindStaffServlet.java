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

@WebServlet("/center/find")
public class FindStaffServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jsonStr = request.getParameter("data");
        QueryCondition queryCondition = JSONObject.parseObject(jsonStr, JSONObject.class).toJavaObject(QueryCondition.class);

        JSONArray jsonArray = new JSONArray();
        List<Staff> staffList = StaffDao.selectStaff(queryCondition);
        for (Staff staff: staffList) {
            jsonArray.add(JSONObject.toJSONString(staff));
        }
        response.getWriter().print(jsonArray);

    }
}

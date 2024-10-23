package Servlet;

import Dao.StaffDao;
import Entity.Staff;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/center/sela")
public class SelAStaffServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Integer id = Integer.parseInt(request.getParameter("data"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("staff", StaffDao.selectStaff(id));
        response.getWriter().print(jsonObject);
    }
}

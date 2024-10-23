package Servlet;

import Dao.LoginDao;
import Dao.PostDao;
import Entity.Post;
import Entity.User;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPath;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/center/posts")
public class PostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Post> list = PostDao.selectPost();
        JSONArray jsonArray = new JSONArray();
        for (Post post: list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("post", post);
            jsonArray.add(jsonObject);
        }
        response.getWriter().print(jsonArray);
    }
}

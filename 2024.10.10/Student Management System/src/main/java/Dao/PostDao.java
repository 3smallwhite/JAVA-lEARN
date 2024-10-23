package Dao;

import Entity.Post;
import Mapper.PostMapper;
import Mapper.StaffMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PostDao {
    static String resource = "mybatis-config.xml";
    static InputStream inputStream;
    static SqlSessionFactory sqlSessionFactory;
    static SqlSession session;
    static PostMapper postMapper;
    static {
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession(true);
        postMapper = session.getMapper(PostMapper.class);
    }
    public static List<Post> selectPost() {
        return postMapper.selectAll();
    }
}

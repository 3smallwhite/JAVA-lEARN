package Dao;

import Entity.User;
import Mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Date;


public class LoginDao {
    public static Boolean check(User user) throws ClassNotFoundException, SQLException, IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession(true);
        UserMapper userMapper = session.getMapper(UserMapper.class);

        if (userMapper.selectPassword(user.getUser()) == null || !userMapper.selectPassword(user.getUser()).equals(user.getPassword())) return false;

        userMapper.updateLoginTime(new Timestamp(new Date().getTime()), user.getUser());
        return true;
    }
}

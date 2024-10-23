package Test;

import Dao.LoginDao;
import Dao.StaffDao;
import Entity.QueryCondition;
import Entity.Staff;
import Entity.User;
import Mapper.PostMapper;
import Mapper.StaffMapper;
import Mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, ParseException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();

        StaffMapper staffMapper = session.getMapper(StaffMapper.class);

        // 测试新增员工
        if (StaffDao.newAStaff(new Staff(null, "zhan1gsan", 19, "men", new Date(new java.util.Date().getTime()), "It", 4000f))) {
            System.out.println(new Date(1727712000000L));
        }

//        // 测试查询员工
//        if (true) {
//            List<Staff> list = staffMapper.selectStaffs("%", "%", "%", new Date(new java.util.Date().getTime() - 1000), new Date(new java.util.Date().getTime() + 1000), 0f, 10000f);
//            System.out.println(list);
//        }

        // 测试删除员工
//        System.out.println(staffMapper.selectStaffs(new QueryCondition()));
    }
}

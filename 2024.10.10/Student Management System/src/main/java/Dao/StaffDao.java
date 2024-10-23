package Dao;

import Entity.QueryCondition;
import Entity.Staff;
import Mapper.PostMapper;
import Mapper.StaffMapper;
import com.alibaba.fastjson2.JSONObject;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StaffDao {
    static String resource = "mybatis-config.xml";
    static InputStream inputStream;
    static SqlSessionFactory sqlSessionFactory;
    static SqlSession session;
    static StaffMapper staffMapper;
    static {
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession(true);
        staffMapper = session.getMapper(StaffMapper.class);
    }

    public static Boolean newAStaff(Staff staff) throws IOException {

        PostMapper postMapper = session.getMapper(PostMapper.class);

        // 获取当前岗位的员工数量
        Integer num = postMapper.selectStaffNum(staff.getPostName()) + 1;
        Integer postId = postMapper.selectId(staff.getPostName());
        // 生成员工 id
        staff.setId(Integer.parseInt("" + postId + num));

        if (staffMapper.insertAStaff(staff) == 1) {
            // 更新岗位员工数量
            postMapper.addStaffNum(num, postId);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static List<Staff> selectStaff(QueryCondition queryCondition) {
        return staffMapper.selectStaffs(queryCondition);
    }

    public static Staff selectStaff(Integer id) {
        return staffMapper.selectById(id);
    }

    public static Boolean deleteStaff(Staff staff) {
        PostMapper postMapper = session.getMapper(PostMapper.class);
        Integer num = postMapper.selectStaffNum(staff.getPostName()) - 1;
        Integer postId = postMapper.selectId(staff.getPostName());
        if (staffMapper.deleteStaff(staff.getId()) == 1) {
            postMapper.reduceStaffNum(num, postId);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static Boolean updateStaff(Staff staff) {
        if (staffMapper.updateStaff(staff) == 1) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}

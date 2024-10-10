package Mapper;

import Entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;

public interface UserMapper {
    @Select("select password from users where user = #{user}")
    String selectPassword(@Param("user") String user);

    @Update("update users set login_time = #{loginTime} where user = #{user}")
    int updateLoginTime(@Param("loginTime") Timestamp loginTime, @Param("user") String user);

    @Update("update users set password = #{password} where user = #{user}")
    int updatePassword(@Param("password") String password, @Param("user") String user);
}

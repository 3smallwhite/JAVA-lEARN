package Mapper;

import Entity.QueryCondition;
import Entity.Staff;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

public interface StaffMapper {
    @Select("select * from staffs")
    List<Staff> selectAll();

    Staff selectById(@Param("id") Integer id);

    @Insert("insert into staffs values(#{id}, #{name}, #{age}, #{sex}, #{startingTime}, #{postName}, #{salary})")
    int insertAStaff(Staff staff);

    List<Staff> selectStaffs(QueryCondition queryCondition);

    @Update("update staffs set name = #{name}, age = #{age}, sex = #{sex}, starting_time = #{startingTime}, post_name = #{postName}, salary = #{salary} where id = #{id}")
    int updateStaff(Staff staff);

    @Delete("delete from staffs where id = #{id}")
    int deleteStaff(Integer id);
}

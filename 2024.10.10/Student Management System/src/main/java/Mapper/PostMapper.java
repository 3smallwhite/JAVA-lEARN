package Mapper;

import Entity.Post;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PostMapper {
    @Select("select * from post")
    List<Post> selectAll();

    @Select("select id from post where name = #{name}")
    Integer selectId(String name);

    @Select("select name from post where id = #{id}")
    String selectName(Integer id);

    @Select("select staff_num from post where name = #{name}")
    Integer selectStaffNum(String name);

    @Update("update post set staff_num = #{staff_num} where id = #{id}")
    Integer addStaffNum(@Param("staff_num") Integer num, @Param("id") Integer id);

    @Update("update post set staff_num = #{staff_num} where id = #{id}")
    Integer reduceStaffNum(@Param("staff_num") Integer num, @Param("id") Integer id);
}

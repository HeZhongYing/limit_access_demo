package com.hezy.mapper;

import com.hezy.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author hezhongying
 * @create 2024/9/29 10:30
 */
@Mapper
public interface UserMapper {

    @Delete("delete from i_users where id = #{id}")
    void deleteUserById(@Param("id") Integer id);

    @Delete("delete from i_user_roles_mapping where user_name = #{id}")
    void deleteUserRoleMapper(@Param("id") Integer id);

    @Select("select * from i_users where id = #{id}")
    User selectUserById(@Param("id") Integer id);
}

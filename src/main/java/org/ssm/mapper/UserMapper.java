package org.ssm.mapper;

import org.apache.ibatis.annotations.*;
import org.ssm.until.TbUser;

import java.util.List;

public interface UserMapper {
//    添加
    @Insert("INSERT INTO tb_user(NAME,SEX,AGE) VALUES(#{name},#{sex},#{age})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int saveUser(TbUser user);
//    查询
    @Select("SELECT * FROM tb_user WHERE id = #{id} ")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "sex",property = "sex"),
            @Result(column = "age",property = "age")
    })
    TbUser selectUserById(Integer id);
//    修改
    @Update("UPDATE tb_user SET NAME = #{name},SEX = #{sex}, AGE = #{age} WHERE ID = #{id}")
    void modifyUser(TbUser user);
//    删除
    @Delete("DELETE FROM tb_user WHERE ID = #{id}")
    int removeUser(@Param("id") Integer id);
//    查询多条
    @Select("SELECT * FROM tb_user")
    List<TbUser> selectUserList();
}

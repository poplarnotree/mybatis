package org.ssm.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.ssm.until.TbOrder;

public interface OrderMapper {
    @Select("SELECT * FROM tb_order WHERE id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "CODE",property = "code"),
            @Result(column = "total",property = "total"),
            @Result(column = "users_id",property = "usersId"),
            @Result(column = "users_id",property = "users",
            one = @One(select = "org.ssm.mapper.UsersMapper.selectById",
            fetchType = FetchType.EAGER)),
            @Result(column = "id",property = "articles",
            many = @Many(select = "org.ssm.mapper.ArticleMapper.selectByOrderId",
            fetchType = FetchType.LAZY))
    })
    TbOrder selectById(Integer id);
}

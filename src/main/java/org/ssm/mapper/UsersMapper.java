package org.ssm.mapper;

import org.apache.ibatis.annotations.Select;
import org.ssm.until.TbUsers;

public interface UsersMapper {
    @Select("SELECT * FROM tb_users WHERE id = #{id}")
    TbUsers selectById(Integer id);
}

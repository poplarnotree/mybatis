package org.ssm.mapper;

import org.apache.ibatis.annotations.Select;
import org.ssm.until.TbCard;

public interface CardMapper {
    @Select("SELECT * FROM tb_card WHERE ID = #{id}")
    TbCard selectCardById(Integer id);
}

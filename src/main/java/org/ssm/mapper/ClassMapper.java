package org.ssm.mapper;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.ssm.until.TbClass;

public interface ClassMapper {
    @Select("SELECT * FROM tb_class WHERE id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "CODE",property = "code"),
            @Result(column = "NAME",property = "name"),
            @Result(column = "id",property = "students",
                many = @Many(
                        select = "org.ssm.mapper.StudentMapper.selectByClass_id",
                        fetchType = FetchType.LAZY
                )
            )
    })
    TbClass selectById(Integer id);
}

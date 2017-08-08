package org.ssm.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.ssm.until.TbStudent;

import java.util.List;

public interface StudentMapper {
    @Select("SELECT * FROM tb_student WHERE class_id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "sex",property = "sex"),
            @Result(column = "age",property = "age")
    })
    List<TbStudent> selectByClass_id(Integer class_id);
}

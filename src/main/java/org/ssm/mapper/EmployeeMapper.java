package org.ssm.mapper;

import org.apache.ibatis.annotations.SelectProvider;
import org.ssm.until.TbEmployee;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    @SelectProvider(type = EmployeeDynaSqlProvider.class,method = "selectWhitParam")
    List<TbEmployee> selectWhitParam(Map<String , Object> param);
    @SelectProvider(type = EmployeeDynaSqlProvider.class,method = "insertEmployee")
    List<TbEmployee> insertWhitParam(TbEmployee employee);
}

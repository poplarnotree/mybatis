package org.ssm.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.ssm.mapper.EmployeeMapper;
import org.ssm.until.TbEmployee;
import org.ssm.utils.MybatisUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicSQLTest {
    SqlSession sqlSession;
    @Test
    public void selectEmployeeTest(){
        sqlSession = MybatisUtil.getSqlSession();
        MybatisUtil.getSqlSessionFactory().getConfiguration().addMapper(EmployeeMapper.class);
        try {
            EmployeeMapper em = sqlSession.getMapper(EmployeeMapper.class);
            Map<String , Object> param = new HashMap<String, Object>();
            param.put("loginname","yang");
            param.put("password","123456");
            List<TbEmployee> list = em.selectWhitParam(param);
            sqlSession.commit();
            for (TbEmployee e : list){
                System.out.println("--------" + e.getLoginname());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }

    @Test
    public void insertEmployeeTest(){
        sqlSession = MybatisUtil.getSqlSession();
        MybatisUtil.getSqlSessionFactory().getConfiguration().addMapper(EmployeeMapper.class);
        try {
            EmployeeMapper em = sqlSession.getMapper(EmployeeMapper.class);
            TbEmployee employee = new TbEmployee();
            employee.setLoginname("jack");
            employee.setPassword("123456");
            em.insertWhitParam(employee);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }
}

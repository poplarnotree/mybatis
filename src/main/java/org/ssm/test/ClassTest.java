package org.ssm.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.ssm.until.TbClass;
import org.ssm.until.TbStudent;
import org.ssm.utils.MybatisUtil;

public class ClassTest {
    SqlSession sqlSession;

    @Test
    public void QueryOneToMany(){
        sqlSession = MybatisUtil.getSqlSession();
        try {
            TbClass tbClass = sqlSession.selectOne("mapper.TbClassMapper.selectByPrimaryKey",1);
            sqlSession.commit();
            System.out.println(tbClass.getName());
            System.out.println("----------"+tbClass.getStudents().size());
            for (TbStudent s : tbClass.getStudents()){
                System.out.println(s.getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }
    @Test
    public void QueryManyToOne(){
        sqlSession = MybatisUtil.getSqlSession();
        int id = 1;
        try {
            TbStudent student = sqlSession.selectOne("mapper.TbStudentMapper.selectById",id);
            sqlSession.commit();
            System.out.println(student.getName());
            System.out.println(student.getTbClass().getName());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }
}

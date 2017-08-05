package org.ssm.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.ssm.until.TbClass;
import org.ssm.until.TbStudent;
import org.ssm.utils.MybatisUtil;

import java.util.List;

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
}

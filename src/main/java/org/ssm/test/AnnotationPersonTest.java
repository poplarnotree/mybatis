package org.ssm.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.ssm.mapper.CardMapper;
import org.ssm.mapper.PersonMapper;
import org.ssm.until.TbPerson;
import org.ssm.utils.MybatisUtil;

public class AnnotationPersonTest {
    SqlSession sqlSession;
    @Test
    public void selectOneToOneTest(){
        sqlSession = MybatisUtil.getSqlSession();
        try {
            MybatisUtil.getSqlSessionFactory().getConfiguration().addMapper(PersonMapper.class);
            MybatisUtil.getSqlSessionFactory().getConfiguration().addMapper(CardMapper.class);
            PersonMapper pm = sqlSession.getMapper(PersonMapper.class);
            int id = 1;
            TbPerson person = pm.selectPersonById(id);
            sqlSession.commit();
            System.out.println("------"+person.getName());
            System.out.println("------"+person.getCard().getCode());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }
}

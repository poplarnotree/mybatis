package org.ssm.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.ssm.until.TbPerson;
import org.ssm.utils.MybatisUtil;

public class CardTest {
    SqlSession sqlSession;
    @Test
    public void queryPerson(){
        sqlSession = MybatisUtil.getSqlSession();
        try {
            TbPerson p = sqlSession.selectOne("mapper.TbPersonMapper.selectMappingPersonToCart",1);
            sqlSession.commit();
            System.out.println(p.getName()+p.getCard().getCode());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }
}

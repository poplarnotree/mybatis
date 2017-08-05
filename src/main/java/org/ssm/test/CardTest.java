package org.ssm.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.ssm.until.TbCard;
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

    @Test
    public void insertPerson(){
        sqlSession = MybatisUtil.getSqlSession();
        TbCard card = new TbCard();
        card.setCode("123456789012345678");
        TbPerson person = new TbPerson();
        person.setName("李世");
        person.setSex("男");
        person.setAge(18);
        try{
            sqlSession.insert("mapper.TbCardMapper.insertSelective",card);
            sqlSession.commit();
            TbCard c = sqlSession.selectOne("mapper.TbCardMapper.selectByCode",card.getCode());
            sqlSession.commit();
            person.setCardId(c.getId());
            sqlSession.insert("mapper.TbPersonMapper.insertSelective",person);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }

    @Test
    public void deletePerson(){
        sqlSession = MybatisUtil.getSqlSession();
        try {
            TbPerson p = sqlSession.selectOne("mapper.TbPersonMapper.selectByPrimaryKey",10);
            sqlSession.commit();
            sqlSession.delete("mapper.TbCardMapper.deleteByPrimaryKey",p.getCardId());
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }
}

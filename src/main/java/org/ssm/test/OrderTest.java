package org.ssm.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.ssm.until.TbArticle;
import org.ssm.until.TbOrder;
import org.ssm.until.TbUsers;
import org.ssm.utils.MybatisUtil;

public class OrderTest {
    SqlSession sqlSession;
    @Test
    public void ManyToManyTest(){
        sqlSession = MybatisUtil.getSqlSession();
        int id = 1;
        try {
            TbUsers users = sqlSession.selectOne("mapper.TbUsersMapper.selectByPrimaryKey",id);
            sqlSession.commit();
            System.out.println("-----------"+users.getUsername());
            for(TbOrder o : users.getOrders()){
                System.out.println("--------"+o.getTotal());
                for(TbArticle a : o.getArticles())
                System.out.println("---------"+a.getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }

    @Test
    public void OneToManyTest(){
        sqlSession = MybatisUtil.getSqlSession();
        int id = 1;
        try {
            TbOrder order = sqlSession.selectOne("mapper.TbOrderMapper.selectById",id);
            sqlSession.commit();
            System.out.println("订单代码:"+order.getCode());
            System.out.println("用户:"+order.getUsers().getUsername());
            System.out.println("购买的商品:");
            for(TbArticle a : order.getArticles()){
                System.out.println(a.getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }
}

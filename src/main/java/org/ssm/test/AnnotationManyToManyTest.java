package org.ssm.test;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.ssm.mapper.ArticleMapper;
import org.ssm.mapper.OrderMapper;
import org.ssm.mapper.UsersMapper;
import org.ssm.until.TbArticle;
import org.ssm.until.TbOrder;
import org.ssm.utils.MybatisUtil;

public class AnnotationManyToManyTest {
    SqlSession sqlSession;

    @Test
    public void selectByOrder(){
        sqlSession = MybatisUtil.getSqlSession();
        MybatisUtil.getSqlSessionFactory().getConfiguration().addMapper(OrderMapper.class);
        MybatisUtil.getSqlSessionFactory().getConfiguration().addMapper(ArticleMapper.class);
        MybatisUtil.getSqlSessionFactory().getConfiguration().addMapper(UsersMapper.class);
        int id = 1;
        try {
            OrderMapper om = sqlSession.getMapper(OrderMapper.class);
            TbOrder order = om.selectById(id);
            sqlSession.commit();
            System.out.println("订单金额:"+order.getTotal());
            System.out.println("用户名:"+order.getUsers().getUsername());
            for (TbArticle article : order.getArticles()){
                System.out.println("购买书籍:"+article.getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }

    }
}

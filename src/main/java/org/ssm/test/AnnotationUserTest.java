package org.ssm.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.ssm.mapper.UserMapper;
import org.ssm.until.TbUser;
import org.ssm.utils.MybatisUtil;

import java.util.List;

public class AnnotationUserTest {
    SqlSession sqlSession;
//    插入数据
    @Test
    public void InsertUser(){
        sqlSession = MybatisUtil.getSqlSession();
        try {
            MybatisUtil.getSqlSessionFactory().getConfiguration().addMapper(UserMapper.class);
            UserMapper um = sqlSession.getMapper(UserMapper.class);
            TbUser user = new TbUser();
            user.setName("test");
            user.setSex('男');
            user.setAge(21);
            um.saveUser(user);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }
//    根据ID查询数据
    @Test
    public void selectUserById(){
        sqlSession = MybatisUtil.getSqlSession();
        try {
            MybatisUtil.getSqlSessionFactory().getConfiguration().addMapper(UserMapper.class);
            int id = 1;
            UserMapper um = sqlSession.getMapper(UserMapper.class);
            TbUser u = um.selectUserById(id);
            sqlSession.commit();
            System.out.println("--------"+u.getName());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }
//    修改数据
    @Test
    public void UpdateUser(){
        sqlSession = MybatisUtil.getSqlSession();
        try {
            MybatisUtil.getSqlSessionFactory().getConfiguration().addMapper(UserMapper.class);
            int id = 4;
            UserMapper um = sqlSession.getMapper(UserMapper.class);
            TbUser u = um.selectUserById(id);
            sqlSession.commit();
            u.setName("琼恩·坦格利安");
            u.setAge(28);
            um.modifyUser(u);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }
//    删除数据
    @Test
    public void remove(){
        sqlSession = MybatisUtil.getSqlSession();
        try {
            MybatisUtil.getSqlSessionFactory().getConfiguration().addMapper(UserMapper.class);
            int id = 4;
            UserMapper um = sqlSession.getMapper(UserMapper.class);
            um.removeUser(id);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }
//    查询表中所有数据
    @Test
    public void selectUserList(){
        sqlSession = MybatisUtil.getSqlSession();
        try {
            MybatisUtil.getSqlSessionFactory().getConfiguration().addMapper(UserMapper.class);
            UserMapper um = sqlSession.getMapper(UserMapper.class);
            List<TbUser> user = um.selectUserList();
            for (TbUser u : user){
                System.out.println("ID:"+u.getId()+",姓名:"+u.getName()+",性别:"+u.getSex()+"，年龄"+u.getAge());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }
}

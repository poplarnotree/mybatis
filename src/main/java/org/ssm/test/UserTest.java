package org.ssm.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.ssm.until.User;
import org.ssm.utils.MybatisUtil;

public class UserTest {
    SqlSession sqlSession;
    @Test
    public void insertPerson(){
        sqlSession = MybatisUtil.getSqlSession();
        String name = "test";
        char sex = 'm';
        int age = 18;
        User user = new User();
        user.setName(name);
        user.setSex(sex);
        user.setAge(age);
        try {
            sqlSession.insert("insertUser",user);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }

    @Test
    public void queryUser(){
        sqlSession = MybatisUtil.getSqlSession();
        int id = 1;
        try {
            User user = sqlSession.selectOne("queryById",id);
            sqlSession.commit();
            System.out.println(user.getName()+","+user.getAge()+","+user.getSex());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }


    @Test
    public void updateUser(){
        sqlSession = MybatisUtil.getSqlSession();
        int id = 2;
        try {
            User user = sqlSession.selectOne("queryById",id);
            sqlSession.commit();
            user.setSex('女');
            user.setAge(19);
            sqlSession.update("updateUser",user);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }
    @Test
    public void updateUser1(){
        sqlSession = MybatisUtil.getSqlSession();
        int id = 2;
        try {
            User user = new UserTest().queryUser1();
            user.setName("江滋婵");
            user.setSex('女');
            user.setAge(21);
            sqlSession.update("updateUser",user);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }

    @Test
    public void deleteUser(){
        sqlSession = MybatisUtil.getSqlSession();
        int id = 5;
        try{
            sqlSession.delete("deleteUser",id);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }
//测试是否可以使用返回值
    public User queryUser1(){
        sqlSession = MybatisUtil.getSqlSession();
        int id = 5;
        User user = null;
        try {
            user = sqlSession.selectOne("queryById",id);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
        return user;
    }
}

package org.ssm.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.ssm.mapper.ClassMapper;
import org.ssm.mapper.StudentMapper;
import org.ssm.until.TbClass;
import org.ssm.until.TbStudent;
import org.ssm.utils.MybatisUtil;

public class AnnotationStudentTest {
    SqlSession sqlSession;
    @Test
    public void selectManyToMany(){
        sqlSession = MybatisUtil.getSqlSession();
        MybatisUtil.getSqlSessionFactory().getConfiguration().addMapper(StudentMapper.class);
        MybatisUtil.getSqlSessionFactory().getConfiguration().addMapper(ClassMapper.class);
        int id = 1;
        try {
            ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
            TbClass tbClass = cm.selectById(id);
            sqlSession.commit();
            System.out.println("班级:" + tbClass.getName());
            for (TbStudent student : tbClass.getStudents()){
                System.out.println("姓名:" + student.getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }
}

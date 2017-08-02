# 搭建Mybatis  
#### github:[mybatis](https://github.com/poplarnotree/mybatis)
## 1.数据库设计
```
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_user
-- Source Database       : mybatis
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(18) NOT NULL,
  `SEX` char(2) NOT NULL,
  `AGE` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
```
## 2.新建一个maven项目
### 项目结构
![image](C:\Users\yang\Desktop\jiegou.png)
## 3.pom.xml文件配置
```xml
<dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
    <!--mysql-->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.29</version>
    </dependency>
    <!--mybatis-->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.3.0</version>
    </dependency>
    <!--日志管理-->
    <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.17</version>
    </dependency>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>1.7.12</version>
    </dependency>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-log4j12</artifactId>
      <version>1.7.12</version>
    </dependency>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
    </dependency>
  </dependencies>
```
## 4.配置文件
### 1.在resources文件夹中新建一个config.properties文件用来保存数据库信息
```properties
driver = com.mysql.jdbc.Driver
url = jdbc:mysql://localhost:3306/mybatis
username = root
password = 123456
```
### 2.同样在resources文件夹下再新建一个log4j.properties文件用来设置日志信息的搭建
```properties
log4j.rootLogger=debug, stdout, R

log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout

log4j.appender.stdout.layout.ConversionPattern=%5p - %m%n

log4j.appender.R=org.apache.log4j.RollingFileAppender
log4j.appender.R.File=firestorm.log

log4j.appender.R.MaxFileSize=100KB
log4j.appender.R.MaxBackupIndex=1

log4j.appender.R.layout=org.apache.log4j.PatternLayout
log4j.appender.R.layout.ConversionPattern=%p %t %c - %m%n

log4j.logger.com.codefutures=DEBUG
```
### 3.同样在resources文件夹下新建mybatis-config.xml设置数据库
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <properties resource="config.properties"/>
    <typeAliases>
        <typeAlias type="org.ssm.until.User" alias="User"/>
    </typeAliases>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="mapper/User.xml"/>
    </mappers>
</configuration>
```
### 4.在resources文件夹下建立一个mapper文件夹，用来保存mapper文件。
### 5.在mapper文件夹下创建一个User.xml文件，用来进行数据库的映射及操作。
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com">
    <insert id="insertUser" parameterType="User" >
        INSERT INTO tb_user(NAME,SEX,AGE)VALUES (#{name},#{sex},#{age})
    </insert>

    <select id="queryById" parameterType="int" resultType="User">
        SELECT * FROM tb_user WHERE ID=#{id}
    </select>
    <update id="updateUser">
        UPDATE tb_user SET NAME =#{name},AGE = #{age},SEX = #{sex} WHERE ID = #{id}
    </update>
    <delete id="deleteUser">
        DELETE  FROM tb_user WHERE ID = #{id}
    </delete>

</mapper>
```
## 5.创建POJO类User.java
```java
public class User {
    private Integer id;
    private String name;
    private char sex;
    private Integer age;

    public User() {
    }

    public User( String name, char sex, Integer age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
    ...
    //get,set方法省略
    ...
```
## 6.创建Mybatis工具类，MybatisUtil.java
```java
package org.ssm.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MybatisUtil {
    private final static SqlSessionFactory sqlSessionFactory;
    static {
        String resource = "mybatis-config.xml";
        Reader reader =null;
        try {
            reader = Resources.getResourceAsReader(resource);
        }catch (IOException e){
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }
//    获取SqlSessionFactory
//    @return SqlSessionFactory
    public static SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }
//    获取SqlSession
//    @return SqlSession
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
//    关闭SqlSession
    public static void closeSession(SqlSession sqlSession){
        if(sqlSession!=null){
            sqlSession.close();
        }
    }
}
```
## 7.建立测试类UserTest.java,测试是否成功
```
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
```
### 至此，mybatis就已经搭建好了，增删改查已经实现。
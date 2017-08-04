### 0.这是generator的配置
``` xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
    <properties resource="config.properties"></properties>
    <classPathEntry location="${driverLocation}" />
    <!--classPathEntry location="D:\zngkpt\m2\repository\mysql\mysql-connector-java\5.1.40\mysql-connector-java-5.1.40.jar" /-->
    <context id="context1" targetRuntime="MyBatis3">

        <commentGenerator>
            <!-- 去除自动生成的注释 -->
            <property name="suppressAllComments" value="true" />
        </commentGenerator>

        <!-- 数据库连接配置 -->
        <jdbcConnection driverClass="${driver}"
                        connectionURL="${url}"
                        userId="${username}"
                        password="${password}" />
        <!--jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://localhost:3306/test"
                        userId="root"
                        password="mysql" /-->

        <!-- 非必需，类型处理器，在数据库类型和java类型之间的转换控制-->
        <javaTypeResolver>
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>

        <!--配置生成的实体包
            targetPackage：生成的实体包位置，默认存放在src目录下
            targetProject：目标工程名
         -->
        <javaModelGenerator targetPackage="org.ssm.until"
                            targetProject="src/main/java" />

        <!-- 实体包对应映射文件位置及名称，默认存放在src目录下 -->
        <sqlMapGenerator targetPackage="mapper" targetProject="src/main/resources" />

        <!-- 配置表
            schema：不用填写
            tableName: 表名
            enableCountByExample、enableSelectByExample、enableDeleteByExample、enableUpdateByExample、selectByExampleQueryId：
            去除自动生成的例子
        -->
        <table schema="" tableName="tb_user" enableCountByExample="false" enableSelectByExample="false"
               enableDeleteByExample="false" enableUpdateByExample="false" selectByExampleQueryId="false" >
        </table>
        <table schema="" tableName="tb_person" enableCountByExample="false" enableSelectByExample="false"
               enableDeleteByExample="false" enableUpdateByExample="false" selectByExampleQueryId="false" >
        </table>
        <table schema="" tableName="tb_card" enableCountByExample="false" enableSelectByExample="false"
               enableDeleteByExample="false" enableUpdateByExample="false" selectByExampleQueryId="false" >
        </table>
    </context>
</generatorConfiguration>
```
点击Maven Projects，找到generator插件运行即可生成Mapper文件和POJO类，Mapper文件中自动生成的数据库操作语句，是最基本的，有需要还是需要自己编写。
### 1.一对一映射
#### 1.在生成的Person POJO类中添加Cart的对象参数。
```java
private TbCard card;
```
#### 2.编写查询语句，在person的Mapper配置文件中，添加下列语句，即可关联查询到
```xml
<select id="selectMappingPersonToCart" parameterType="java.lang.Integer" resultMap="BaseResultMap">
    SELECT * FROM tb_person p JOIN tb_card c WHERE p.card_id = c.id AND p.id = #{id,jdbcType=INTEGER}
  </select>
```
#### 3.但是此时，查询到的cart表中的数据并没有传送到cart的对象中，此时在resultMap标签中添加关联属性,porperty的值即为person的POJO类中声明的cart对象参数。
``` xml
<association property="card" resultMap="mapper.TbCardMapper.BaseResultMap"/>
```
#### 4.此时即可在测试类中调用到cart类中的属性值
```java
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
```
#### 5.值得注意的是，因为generator插件自动生成的Mapper文件中，数据库操作语句的id都是一样的，所以为了区分，不发生异常，应该在sqlsession调用操作数据的时候，加上mapper文件中，mapper标签下，namespace的属性值。比如PersonMapper的是：
```xml
<mapper namespace="mapper.TbUserMapper">
```
#### 则应该在测试类中，如上85行，加上这个namespace,后面则跟上对应的数据库操作语句的Id值。
```java
TbPerson p = sqlSession.selectOne("mapper.TbPersonMapper.selectMappingPersonToCart",1);
```
#### 6.因为自动生成的mapper文件的数据库操作语句都是基本的增删改查，如果需要使用到其他的，比如关联查询，应该自己再编写。如上70行，就是自己编写的一对一映射关联查询语句。
```xml
<select id="selectMappingPersonToCart" parameterType="java.lang.Integer" resultMap="BaseResultMap">
    SELECT * FROM tb_person p JOIN tb_card c WHERE p.card_id = c.id AND p.id = #{id,jdbcType=INTEGER}
  </select>
```
#### 7.不建议使用generator插件自动生成的Explam类，第一太臃肿，里面给出的方法，并不能完全用到，而且太繁多，会给阅读造成麻烦。
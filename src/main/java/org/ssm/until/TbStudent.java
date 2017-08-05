package org.ssm.until;

public class TbStudent {
    private Integer id;

    private String name;

    private String sex;

    private Integer age;

    private Integer classId;

    private TbClass tbClass;

    public TbClass getTbClass() {
        return tbClass;
    }

    public void setTbClass(TbClass tbClass) {
        this.tbClass = tbClass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}
package org.ssm.until;

import java.util.List;

public class TbClass {
    private Integer id;

    private String code;

    private String name;

    private List<TbStudent> students;

    public List<TbStudent> getStudents() {
        return students;
    }

    public void setStudents(List<TbStudent> students) {
        this.students = students;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
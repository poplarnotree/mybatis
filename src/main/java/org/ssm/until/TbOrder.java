package org.ssm.until;

import java.util.List;

public class TbOrder {
    private Integer id;

    private String code;

    private Double total;

    private Integer usersId;

    private TbUsers users;

    private List<TbArticle> articles;

    public TbUsers getUsers() {
        return users;
    }

    public void setUsers(TbUsers users) {
        this.users = users;
    }

    public List<TbArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<TbArticle> articles) {
        this.articles = articles;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }
}
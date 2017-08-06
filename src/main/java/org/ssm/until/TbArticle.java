package org.ssm.until;

import java.util.List;

public class TbArticle {
    private Integer id;

    private String name;

    private Double price;

    private String remark;

    private List<TbOrder> orders;

    public List<TbOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<TbOrder> orders) {
        this.orders = orders;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
package com.jxxdxy.entity;

import java.util.List;

public class Order {
    private int id;
    private int userId;
    private List<Product> products;

    public Order() {
    }

    public Order(int userId) {
        this.userId = userId;
    }


    public Order(int id, int userId, List<Product> products) {
        this.id = id;
        this.userId = userId;
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", products=" + products +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

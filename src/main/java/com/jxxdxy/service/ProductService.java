package com.jxxdxy.service;

import com.jxxdxy.entity.Product;

import java.util.List;

public interface ProductService {
    //查询所有product
    List<Product> queryAllProduct();
    //通过id查
    Product queryProductById(int id);
    //通过名字模糊查询
    List<Product> queryProductLikeName(String name);
    //通过集合id查询
    List<Product> queryProductByIds(List<Integer> ids);
}

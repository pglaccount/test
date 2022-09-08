package com.jxxdxy.dao;

import com.jxxdxy.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {
    //查询所有product
    List<Product> queryAllProduct();
    //通过id查
    Product queryProductById(int id);
    //通过名字模糊查询
    List<Product> queryProductLikeName(String name);
    //通过集合id查询
    List<Product> queryProductByIds(List<Integer> ids);
}

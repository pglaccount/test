package com.jxxdxy.service.impl;

import com.jxxdxy.dao.ProductMapper;
import com.jxxdxy.entity.Product;
import com.jxxdxy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    public List<Product> queryAllProduct() {
        return productMapper.queryAllProduct();
    }

    public Product queryProductById(int id) {
        return productMapper.queryProductById(id);
    }

    public List<Product> queryProductLikeName(String name) {
        return productMapper.queryProductLikeName(name);
    }

    public List<Product> queryProductByIds(List<Integer> ids) {
        return productMapper.queryProductByIds(ids);
    }
}

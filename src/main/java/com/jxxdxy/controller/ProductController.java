package com.jxxdxy.controller;

import com.jxxdxy.entity.Product;
import com.jxxdxy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @ResponseBody
    @RequestMapping("/getAllProduct")
    public List<Product> getAllProduct(){
        List<Product> products = productService.queryAllProduct();
        return products;
    }
}

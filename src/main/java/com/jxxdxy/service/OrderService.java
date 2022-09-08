package com.jxxdxy.service;

import com.jxxdxy.entity.Order;
import com.jxxdxy.entity.Product;
import com.jxxdxy.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {
    //根据订单id查询用户
    User queryUserByOrderId(int orderId);

    //根据用户id查询订单id列表
    List<Integer> queryOrderByUserId(int userId);

    //根据订单id查询商品列表
    List<Product> queryProductListByOrderId(int orderId);
    List<Order> queryOrderListByUserId(int userId);

    //为了好插入orderitem表，传入order以便于返回orderid
    int addOrderByUserId(Order order);

     //批量插入
    int addOrderItemByOrderId(int userId, List<Integer> productIds);

}

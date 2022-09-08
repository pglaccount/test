package com.jxxdxy.service.impl;

import com.jxxdxy.dao.OrderMapper;
import com.jxxdxy.entity.Order;
import com.jxxdxy.entity.Product;
import com.jxxdxy.entity.User;
import com.jxxdxy.service.OrderService;
import com.jxxdxy.service.ProductService;
import com.jxxdxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    /**
     * 根据订单id查到用户
     * @param orderId 订单id
     * @return
     */
    public User queryUserByOrderId(int orderId) {
        Order order = orderMapper.queryOrderById(orderId);
        int userId = order.getUserId();
        return userService.queryUserById(userId);
    }

    /**
     * 根据用户id查到订单列表
     * @param userId 用户id
     * @return
     */
    public List<Integer> queryOrderByUserId(int userId) {
        return orderMapper.queryOrderByUserId(userId);
    }

    /**
     * 根据订单id查订单商品对照表，
     * @param orderId 订单id
     * @return 商品列表
     */
    public List<Product> queryProductListByOrderId(int orderId) {
        List<Integer> integers = orderMapper.queryProductIdByOrderId(orderId);
        //如果↑返回为null 也就是库里没有orderId和productId对应，
        if (isEmptyList(integers)) {
            return null;
        }
        List<Product> products = productService.queryProductByIds(integers);
        return products;
    }
        /**
     * 根据用户id查订单商品对照表，
     * @param userId 用户
     * @return 商品列表
     */
    public List<Order> queryOrderListByUserId(int userId) {
        List<Integer> orderIdList = orderMapper.queryOrderByUserId(userId);
        if (isEmptyList(orderIdList)){
            return null;
        }
        List<Order> orderList = new ArrayList<Order>();
        //对订单号进行循环，查找每一个订单号对应的商品
        for (Integer integer : orderIdList) {
            Order order = orderMapper.queryOrderById(integer);
            List<Product> products = queryProductListByOrderId(integer);
            order.setProducts(products);
            orderList.add(order);
        }
        return orderList;
    }

    /**
     * 添加userid返回orderid用于插入orderitem表中的orderId
     * @param order
     * @return orderId
     */
    public int addOrderByUserId(Order order) {
       orderMapper.addOrderByUserId(order);
        return order.getId();
    }

    /**
     * @param userId userId
     * @param productIds 商品列表
     * @return
     */
    public int addOrderItemByOrderId(int userId, List<Integer> productIds) {
        if (userId == 0){
            return 0;
        }
        Order order = new Order(userId);
        int orderId = addOrderByUserId(order);
        if (isEmptyList(productIds)) {
            return 0;
        }
        int i = 0;
        for (Integer productId : productIds) {
           i += orderMapper.addOrderItemByOrderId(orderId, productId);
        }
        return i;
    }

    private boolean isEmptyList(List list){
        return list == null || list.size() < 1;
    }



}

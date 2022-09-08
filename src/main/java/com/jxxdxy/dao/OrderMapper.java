package com.jxxdxy.dao;

import com.jxxdxy.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderMapper {
    //通过订单id找到订单
    Order queryOrderById(int id);

    //通过用户id找到订单id列表
    List<Integer> queryOrderByUserId(int userId);

    //通过订单id找到商品列表id
    List<Integer> queryProductIdByOrderId(int orderId);

    //为了好插入orderitem表，传入order以便于返回orderid
    int addOrderByUserId(Order order);

    /**
     *
     * @param orderId 订单号
     * @param productId 商品号
     * @return
     */
    int addOrderItemByOrderId(@Param("orderId") int orderId, @Param("productId") int productId);


}

package com.jxxdxy.controller;

import com.jxxdxy.entity.Order;
import com.jxxdxy.entity.Product;
import com.jxxdxy.entity.User;
import com.jxxdxy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ResponseBody
    @RequestMapping("/getOrders")
    public List<Order> getProductsByUserId(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if (user == null){
            return null;
        }
        return orderService.queryOrderListByUserId(user.getId());
    }

    @ResponseBody
    @RequestMapping("/addOrder")
    public String addOrder(@RequestBody List<Integer> orderId, HttpSession session){
         User user = (User) session.getAttribute("user");
        int i = orderService.addOrderItemByOrderId(user.getId(), orderId);
        if (i > 0){
          return "success";
        }else {
            return "error";
        }


    }


}

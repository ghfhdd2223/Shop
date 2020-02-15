package com.test.shop.controller;
import com.test.shop.pojo.Order;
import com.test.shop.pojo.ResponseVo;
import com.test.shop.pojo.User;
import com.test.shop.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;
    @GetMapping("orders")
    private ResponseVo<List<Order>> getOrders(Integer userId,String orderNum){
        ResponseVo<List<Order>> orders = orderService.getOrdersByUserId(userId, orderNum);
        return orders;
    }
    @GetMapping("order")
    private ResponseVo<Order> getOrder(Integer id){
        ResponseVo<Order> order = orderService.getOrderByOrderId(id);
        return order;
    }
    @PutMapping("order")
    private ResponseVo<Order> newOrder(User user,Order order){
        ResponseVo responseVo = orderService.newOrder(user, order);
        return responseVo;
    }
    @PutMapping("orderDelivery")
    private ResponseVo<Order> orderDelivery(Order order){
        ResponseVo responseVo = orderService.updataOrderForDelivery(order);
        return responseVo;
    }
    @PutMapping("orderReceive")
    private ResponseVo<Order> orderReceive(Order order){
        ResponseVo responseVo = orderService.updataOrderForReceive(order);
        return responseVo;
    }
    @PutMapping("orderCancel")
    private ResponseVo<Order> orderCancel(Order order){
        ResponseVo responseVo = orderService.updataOrderForCancel(order);
        return responseVo;
    }
}

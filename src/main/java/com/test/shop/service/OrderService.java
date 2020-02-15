package com.test.shop.service;

import com.test.shop.pojo.Order;
import com.test.shop.pojo.ResponseVo;
import com.test.shop.pojo.User;

import java.util.List;

public interface OrderService {
    ResponseVo newOrder(User user, Order order);
    ResponseVo insertOrder();
    ResponseVo<Order>getOrderByOrderId(Integer id);
    ResponseVo<List<Order>>getOrdersByUserId(Integer id,String orderNum);
    ResponseVo updataOrderForPay(Order order);
    ResponseVo updataOrderForDelivery(Order order);
    ResponseVo updataOrderForReceive(Order order);
    ResponseVo updataOrderForCancel(Order order);

}

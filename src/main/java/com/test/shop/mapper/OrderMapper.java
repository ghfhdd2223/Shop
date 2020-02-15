package com.test.shop.mapper;

import com.test.shop.pojo.Order;

import java.util.List;

public interface OrderMapper {
    void insertOrder(Order order);
    Order getOrderById(int id);
    List<Order> getOrder(int userId,String orderNum);
    List<Order> getNotPayOrder(int userId);
    void updataOrderForPay(Order order);
    void updataOrderForDelivery(Order order);
    void updataOrderForReceive(Order order);
    void updataOrderForCancel(Order order);
    void updataOrderForTimeOut(Order order);
}

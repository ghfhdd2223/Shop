package com.test.shop.service.impl;

import com.test.shop.mapper.OrderMapper;
import com.test.shop.pojo.Order;
import com.test.shop.pojo.ResponseVo;
import com.test.shop.pojo.User;
import com.test.shop.service.OrderService;
import com.test.shop.util.DateUtil;
import com.test.shop.util.OrderNumberUtil;
import com.test.shop.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private RedisUtil redisUtil;
    private static DateUtil dateUtil =new DateUtil();
    @Resource
    private OrderMapper orderMapper;
    @Override
    public ResponseVo<Order> newOrder(User user, Order order) {
        ResponseVo<Order> responseVo = new ResponseVo();
        try {
            OrderNumberUtil orderNumberUtil = new OrderNumberUtil();
            Long incr = redisUtil.incr("OrderIncr", 1);
            if (incr==1){
                redisUtil.PEXPIREAT("OrderIncr",dateUtil.getExDate());
            }//判断redis中的自增数key是否过期如果还存在直接自增
            String orderNumber = orderNumberUtil.getOrderNumber(user.getUId().toString(),incr.toString());
            order.setOrderNum(orderNumber);
            Date delayDate= dateUtil.getDelayedDate();
            order.setDelayedTime(((Long)delayDate.getTime()).toString());
            redisUtil.set("Order:"+orderNumber,null);
            redisUtil.PEXPIREAT("Order:"+orderNumber,delayDate);
            redisUtil.lPush("OrderList",order);
            responseVo.setCode(1000);
            responseVo.setMsg("成功");
            responseVo.setData(order);
            insertOrder();

        }catch (Exception e){
            e.printStackTrace();
            responseVo.setCode(2000);
            responseVo.setMsg("订单错误，请重新下单");
        }
        return responseVo;
    }

    @Override
    public ResponseVo insertOrder() {
        boolean flag= true;
        while (flag) {
            Order order = (Order) redisUtil.bRPop("OrderList");
            if (order==null){
                flag=false;
                continue;
            }
            if (redisUtil.hasKey("Order:"+order.getOrderNum())){
                order.setOrderStatus(1);
                orderMapper.insertOrder(order);//订单状态1为未付款
            }else {
                long delayDate = Long.parseLong(order.getDelayedTime());

                if (delayDate>System.currentTimeMillis()){
                    order.setOrderStatus(2);
                    orderMapper.insertOrder(order);//订单状态2为已超时
                }
            }
        }
        return null;
    }

    @Override
    public ResponseVo<Order> getOrderByOrderId(Integer id) {
        ResponseVo<Order> responseVo = new ResponseVo<>();

        try {
            Order order = orderMapper.getOrderById(id);
            if (order==null){
                responseVo.setCode(1001);
                responseVo.setMsg("您输入的条件有误，不存在此订单");
            }
            responseVo.setData(order);
            responseVo.setCode(1000);
            responseVo.setMsg("成功");

        }catch (Exception e){
            e.printStackTrace();
            responseVo.setCode(2000);
            responseVo.setMsg("错误");
        }
        return responseVo;
    }

    @Override
    public ResponseVo<List<Order>> getOrdersByUserId(Integer id,String orderNum) {
        ResponseVo<List<Order>> responseVo = new ResponseVo<>();
        if (orderNum==null||orderNum.length()==0||"".equals(orderNum)){
            orderNum=null;
        }
        try {
            List<Order> notPayOrder=orderMapper.getNotPayOrder(id);
            for (Order order : notPayOrder) {
                long delayDate = Long.parseLong(order.getDelayedTime());
                if (delayDate<System.currentTimeMillis()){
                    orderMapper.updataOrderForTimeOut(order);//订单状态2为已超时
                }
            }
            List<Order> order = orderMapper.getOrder(id, orderNum);
            responseVo.setMsg("成功");
            responseVo.setCode(1000);
            responseVo.setData(order);
        }catch (Exception e){
            e.printStackTrace();
            responseVo.setCode(2000);
            responseVo.setMsg("错误");
        }
        return responseVo;
    }

    @Override
    public ResponseVo updataOrderForPay(Order order) {
        ResponseVo<Order> responseVo = new ResponseVo<>();
        try {
            order.setOrderStatus(3);
            orderMapper.updataOrderForPay(order);
            responseVo.setCode(1000);
            responseVo.setData(order);
        }catch (Exception e){
            e.printStackTrace();
            responseVo.setCode(2000);
            responseVo.setMsg("错误");
        }
        return responseVo;
    }

    @Override
    public ResponseVo updataOrderForDelivery(Order order) {
        ResponseVo<Order> responseVo = new ResponseVo<>();
        try {
            orderMapper.updataOrderForDelivery(order);
            responseVo.setCode(1000);
            responseVo.setData(order);
        }catch (Exception e){
            e.printStackTrace();
            responseVo.setCode(2000);
            responseVo.setMsg("错误");
        }
        return responseVo;
    }

    @Override
    public ResponseVo updataOrderForReceive(Order order) {
        ResponseVo<Order> responseVo = new ResponseVo<>();
        try {
            orderMapper.updataOrderForReceive(order);
            responseVo.setCode(1000);
            responseVo.setData(order);
        }catch (Exception e){
            e.printStackTrace();
            responseVo.setCode(2000);
            responseVo.setMsg("错误");
        }
        return responseVo;
    }
    @Override
    public ResponseVo updataOrderForCancel(Order order) {
        ResponseVo<Order> responseVo = new ResponseVo<>();
        try {
            order.setOrderStatus(7);//订单状态7为订单已取消
            orderMapper.updataOrderForCancel(order);
            responseVo.setCode(1000);
            responseVo.setData(order);
        }catch (Exception e){
            e.printStackTrace();
            responseVo.setCode(2000);
            responseVo.setMsg("错误");
        }
        return responseVo;
    }
}

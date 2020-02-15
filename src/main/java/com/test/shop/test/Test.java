package com.test.shop.test;

import com.test.shop.util.RedisUtil;

public class Test implements Runnable {
    private RedisUtil redisUtil;
    @Override
    public void run() {
        for (int i = 0;i<5;i++){
            Long incr =
                    redisUtil.incr("Test", 1);
            if (incr==1){
                redisUtil.expire("Test",60);
            }//判断redis中的自增数key是否过期如果还存在直接自增
            System.out.println(Thread.currentThread().getName()+"创建了"+incr+"号订单");
        }
        }


    public void   setRedisUtil(RedisUtil redisUtil){
        this.redisUtil=redisUtil;
    }
}

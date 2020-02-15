package com.test.shop.util;

public class OrderNumberUtil {
    DateUtil dateUtil = new DateUtil();
    public  String getOrderNumber(String userId,String incr){
        String newIncr;
        String newUserId;
        if (incr.length()<6){
            StringBuffer sb = new StringBuffer(incr);
            for (int i = 0;i<(6-incr.length());i++){
                sb.insert(0,"0");
            }
            newIncr=sb.toString();
        }else {
            newIncr=incr;
        }
        if (userId.length()<3){
            StringBuffer sb = new StringBuffer(userId);
            for (int i = 0;i<(3-userId.length());i++){
                sb.insert(0,"0");
            }
            newUserId=sb.toString();
        }else if (userId.length()>3){
            newUserId=userId.substring((userId.length()-3));
        }else {
            newUserId=userId;
        }
        String orderNumber= dateUtil.getDate()+newIncr+newUserId;
        return orderNumber;
    }
}

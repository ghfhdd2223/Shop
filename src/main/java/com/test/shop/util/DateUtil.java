package com.test.shop.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public  class DateUtil {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static SimpleDateFormat sf = new SimpleDateFormat("YYMMddHHmm");
   public  String getDate(){
        String date = sf.format(new Date());
        return date;
    }

    public Date getExDate(){
        String date = simpleDateFormat.format(new Date());
        long now=0;
        try {
            now = simpleDateFormat.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date(now+(60*1000));
    }
    public Date getDelayedDate(){
        Date date = new Date();
        date.setTime(date.getTime() + 15*60*1000);
       return date;
    }



}

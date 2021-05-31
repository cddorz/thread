package com.geekagain.threadLocal;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author hly
 * @Description: TODO
 * @create 2021-04-23 20:00
 */
public class ThreadLoacl{

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int count = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLoacl().date(count);
                    System.out.println(date);
                }
            });
            threadPool.shutdown();
        }
    }

    public String date(int seconds){
        //参数的单位是毫秒，从1970.1.1 00:00:00 GMT计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = ThreadFormatter.dateFormatThreadLoacl.get();
        //将日期格式化为日期/时间字符串。
        return dateFormat.format(date);
    }
}

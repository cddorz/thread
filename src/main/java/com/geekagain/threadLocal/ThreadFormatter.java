package com.geekagain.threadLocal;

import java.text.SimpleDateFormat;

/**
 * @author hly
 * @Description: TODO
 * @create 2021-04-23 20:01
 */
public class ThreadFormatter {
    /**
     * withInitial创建线程局部变量
     * 变量的初始值是通过get方法确定的
     * 线程私有
     */
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLoacl = ThreadLocal
            .withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
}

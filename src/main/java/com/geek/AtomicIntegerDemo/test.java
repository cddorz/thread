package com.geek.AtomicIntegerDemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hly
 * @Description: AtomicInteger常用方法分析
 * @create 2021-04-23 22:17
 */
public class test {
    private  AtomicInteger ctl = new AtomicInteger();
    public void test(){
        //先+1，然后获取新值
        ctl.incrementAndGet();
        //设置值
        ctl.set(1);
        //先获取旧值，再自增1
        ctl.getAndIncrement();
        //先增加delta，再获取新值
        ctl.addAndGet(1);
        //先-1，再获取新值
        ctl.decrementAndGet();
    }
}

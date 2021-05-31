package com.geek.AtomicIntegerDemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hly
 * @Description: CAS(compare and swap)demo
 * @create 2021-04-24 0:01
 */
public class CAS {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        //value == expect update value to 10
        System.out.println(atomicInteger.compareAndSet(5,10));
        System.out.println(atomicInteger.get());
        //value ！= expect 不更新
        System.out.println(atomicInteger.compareAndSet(5,11));
        System.out.println(atomicInteger.get());
    }
}

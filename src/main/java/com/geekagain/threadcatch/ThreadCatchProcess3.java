package com.geekagain.threadcatch;

/**
 * 重写方法，自定义异常处理器
 * @author hly
 */
public class ThreadCatchProcess3 implements Thread.UncaughtExceptionHandler {

    private String name;

    public ThreadCatchProcess3(String name) {
        this.name = name;
    }

    /**
     * 设置自己的异常处理器
     * @param t
     * @param e
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //可以进行一系列的操作
        System.out.println("线程异常终止进程" + t.getName());
        System.out.println(name + "捕获了异常" + t.getName() + "异常");

    }
}

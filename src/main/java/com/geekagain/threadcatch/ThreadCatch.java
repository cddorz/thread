package com.geekagain.threadcatch;

/**
 * 常见的异常处理方法：错误信息写入日志，或者重启线程、或执行其他修复或诊断
 * @author han long yi
 * @create 2021-04-19 10:47
 */
public class ThreadCatch implements Runnable{
    @Override
    public void run() {
        //抛出异常
        throw new RuntimeException();
    }

    public static void main(String[] args) throws InterruptedException {
        //利用自定义的异常处理器
        Thread.setDefaultUncaughtExceptionHandler(new ThreadCatchProcess3("获取异常"));

        new Thread(new ThreadCatch(),"MyThread-1").start();
        Thread.sleep(3000);
        new Thread(new ThreadCatch(),"MyThread-2").start();
        Thread.sleep(3000);
        new Thread(new ThreadCatch(),"MyThread-3").start();
    }
}

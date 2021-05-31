package com.geekagain.stopthread;

/**
 * @author hly
 * @Description: 中断线程最好的，最受推荐的方式是，使用共享变量（shared variable）发出信号
 * @create 2021-04-22 17:10
 */
public class ThreadVolatile extends Thread{

    volatile boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        ThreadVolatile thread = new ThreadVolatile();
        System.out.println("Starting thread...");
        thread.start();
        Thread.sleep(3000);
        System.out.println("Asking thread to stop...");
        //设置中断信号量
        thread.stop = true;
        Thread.sleep(3000);
        System.out.println("Stopping thread...");
    }

    @Override
    public void run(){
        //每隔一秒检查一下stop
        while (!stop){
            System.out.println("Thread is running...");
            long begin = System.currentTimeMillis();
            /**
             * 使用while循环模拟sleep方法，这里不要使用sleep，否则在阻塞时会抛InterruptedException异常而退出循环，
             * 这样while检测stop条件就不会执行，失去了意义。
             */
            while ((System.currentTimeMillis() - begin < 1000)){

            }
        }
        System.out.println("Thread exiting under request!");
    }
}

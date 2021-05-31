package com.geekagain.threadstate;

/**
 * @author hly
 * @Description: 解析线程状态：new、runnable、running、terminated
 * @create 2021-04-22 17:23
 */
public class ThreadStateChange implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("打印"+i);
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadStateChange());
        //new
        System.out.println(thread.getState());
        thread.start();
        //runnable
        System.out.println(thread.getState());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //RUNNABLE的状态，即使是正在运行，也是RUNNABLE 而不是RUNNING 外在表现是runnable 实际内部有一个变化
        System.out.println(thread.getState());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //terminated
        System.out.println(thread.getState());
    }
}

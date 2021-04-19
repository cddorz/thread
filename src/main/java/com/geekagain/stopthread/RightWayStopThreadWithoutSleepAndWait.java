package com.geekagain;

/**
 * 没有sleep和wait如何中断线程
 * @author han long yi
 * @create 2021-04-16 22:06
 */
public class RightWayStopThreadWithoutSleepAndWait implements Runnable{

    @Override
    public void run() {
        //查看当前是否设置了中断标示，判断当前线程是否中断 isInterrupted（）方法返回true，证明线程中断
        while (!Thread.currentThread().isInterrupted()){
            System.out.println("在没有sleep等方法结束了线程");
        }
        System.out.println("线程结束了！");
    }

    public static void main(String[] args) throws InterruptedException {
        //创建一个重写了run函数的线程对象
        Thread thread =new Thread(new RightWayStopThreadWithoutSleepAndWait());
        thread.start();
        Thread.sleep(1000);
        //发送中断请求
        thread.interrupt();
        System.out.println("thread is stopping...");
    }
}

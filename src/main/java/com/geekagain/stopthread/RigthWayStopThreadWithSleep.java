package com.geekagain.stopthread;

/**
 * 带有sleep 中断线程的写法
 * @author han long yi
 * @create 2021-04-16 23:08
 */
public class RigthWayStopThreadWithSleep {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable(){

            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()){
                    System.out.println("在有sleep的情况下中断线程");
                }
                try {
                    Thread.sleep(1000);
                    System.out.println("b");
                } catch (InterruptedException e) {
                    //线程堵塞导致线程无法停止，抛出异常
                    //当一个方法抛出InterruptedException时，它是在告诉您，如果执行该方法的线程被中断
                    //它将尝试停止它正在做的事情而提前返回，并通过InterruptedExceptio表明它提前返回。
                    // 行为良好的阻塞库方法应该能对中断作出响应并抛出InterruptedException，以便能够用于可取消活动中，而不至于影响响应。
                    System.out.println("线程阻塞");
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        //发送中断请求
        thread.interrupt();
        System.out.println("thread is stopping..");
    }
}

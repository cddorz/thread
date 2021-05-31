package com.geekagain.waitnotify;

/**
 * 一个wait和notify的小demo，交替输出奇偶数
 * @author hly
 * @Description:
 * 1.拿到锁就打印
 * 2.打印完唤醒其他线程，避免一直占用cpu造成资源浪费
 * @create 2021-04-19 14:03
 */
public class PrintOddEvenTwo {

    /**
     * 锁
     */
    private static Object object = new Object();

    /**
     * 计数奇偶
     */
    private static int count = 0;

    static class EvenAddRunnable implements Runnable{
        @Override
        public void run() {
            while (count <= 100){
                synchronized (object){
                    System.out.println(Thread.currentThread().getName() + ": " + count);
                    count++;
                    //唤醒其他线程
                    object.notify();
                    if (count <= 100) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new EvenAddRunnable(),"偶数").start();
        new Thread(new EvenAddRunnable(),"奇数").start();
    }
}

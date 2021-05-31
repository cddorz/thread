package com.geekagain.join;

/**
 * @author hly
 * @Description: join方法测试用例
 * @create 2021-04-21 14:41
 */
public class JoinThreadState {
    public static void main(String[] args) throws InterruptedException {
        //主线程
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println(mainThread.getState());
                    System.out.println(Thread.currentThread().getName()+"执行完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        System.out.println("等待子线程执行完毕");
        thread.join();
        System.out.println("子线程执行完毕");
    }
}

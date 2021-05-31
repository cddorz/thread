package com.geekagain.startthread;

/**
 * @author hly
 * @Description: 对比run和start方法
 * @create 2021-04-20 16:11
 */
public class StartOrRunMethod {
    public static void main(String[] args) {
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        //main方法中执行一个普通的方法
        runnable.run();

        //启动新线程，加入线程池
        //native本地方法
        new Thread(runnable).start();
    }
}

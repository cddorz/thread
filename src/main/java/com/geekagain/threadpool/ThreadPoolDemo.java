package com.geekagain.threadpool;

import java.util.concurrent.*;

/**
 * @author hly
 * @Description: 线程池创建小demo
 * @create 2021-04-25 15:25
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        //LinkedBlockingQueue阻塞队列
        LinkedBlockingDeque<Runnable> queue = new LinkedBlockingDeque<>(5);

        /**
         *饱和策略
         * */
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

        TimeUnit unit;
        BlockingQueue workQueue;
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10,
                5, TimeUnit.SECONDS, queue,handler);
        for (int i = 0; i < 16; i++) {
            threadPool.execute(new Thread(new ThreadT(),"线程"+i));
            System.out.println("线程池中活跃的线程数： " + threadPool.getPoolSize());

            if(queue.size()>0){
                System.out.println("-----队列中的阻塞线程数"+ queue.size());
            }
        }
        threadPool.shutdown();
    }
}

package com.geekagain.threadpool;

/**
 * @author hly
 * @Description: TODO
 * @create 2021-04-25 15:24
 */
public class ThreadT implements Runnable{
    @Override
    public void run() {
        try
        {
            Thread.sleep(300);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

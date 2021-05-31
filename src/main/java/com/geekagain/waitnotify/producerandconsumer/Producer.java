package com.geekagain.waitnotify.producerandconsumer;

import com.geekagain.waitnotify.producerandconsumer.AbstractStorage;

import javax.annotation.Resource;

/**
 * @author han long yi
 * @create 2021-04-19 11:26
 */
public class Producer extends Thread{
    private int num;

    @Resource
    private AbstractStorage abstractStorage;

    /**
     * 构造函数设置仓库
     * @param abstractStorage
     */
    public Producer(AbstractStorage abstractStorage){this.abstractStorage = abstractStorage;}


    @Override
    public void run(){
        try {
            produce(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用Storage生产函数
     * @param num
     * @throws InterruptedException
     */
    private void produce(int num) throws InterruptedException {
        abstractStorage.produce(num);
    }

    public void setNum(int num){
        this.num = num;
    }
}

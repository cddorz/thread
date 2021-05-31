package com.geekagain.waitnotify.producerandconsumer;

import javax.annotation.Resource;

/**
 * 消费者
 * @author han long yi
 * @create 2021-04-19 11:21
 */
public class Consumer extends Thread{

    private int num;
    @Resource
    private AbstractStorage abstractStorage;

    /**
     * 构造函数
     * 设置仓库
     * @param abstractStorage
     */
    public Consumer(AbstractStorage abstractStorage){this.abstractStorage = abstractStorage;}

    @Override
    public void run(){
        consume(num);
    }

    /**
     * 调用Storage生产函数
     * @param num
     */
    private void consume(int num){
        abstractStorage.consume(num);
    }

    public void setNum(int num){
        this.num = num;
    }
}

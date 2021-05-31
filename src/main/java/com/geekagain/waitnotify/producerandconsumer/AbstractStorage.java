package com.geekagain.waitnotify.producerandconsumer;

/**
 * @author hly
 * 抽象仓库类
 */
public interface AbstractStorage {

    /**
     * 消费
     * @param num
     */
    void consume(int num);

    /**
     * 生产
     * @param num
     */
    void produce(int num) throws InterruptedException;

}

package com.geekagain.waitnotify.producerandconsumer;

import java.util.LinkedList;

/**
 * wait and notify 模型————生产者和消费者模型
 * 所有通信都是通过中间堵塞队列进行缓冲
 * @author han long yi
 * @create 2021-04-19 11:06
 */
public class ProducerAndConsumerModel implements AbstractStorage{
    //仓库最大容量
    private final int MAX_SIZE = 100;

    /**
     * 阻塞队列
     */
    private LinkedList list = new LinkedList();


    /**
     * 消费者，消费商品
     * @param num
     */
    @Override
    public void consume(int num) {
        //同步
        synchronized (list){
            //仓库容量不足以消费
            while (num > list.size()){
                System.out.println("要消费的产品数量："+num+"\t库存量："
                        +list.size()+"\t暂时不能进行生产任务");
                try {
                    list.wait();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //消费条件满足，开始消费
            for(int i = 0; i < num; i++){
                list.remove();
            }
            System.out.println("已经消费的产品数量："+num+"\t库存："+list.size());
            list.notifyAll();
        }

    }

    /**
     * 生产者，生产商品
     * @param num
     */
    @Override
    public void produce(int num) throws InterruptedException {
        synchronized (list){
            //仓库容量不足以生产全部商品
            while (list.size() + num > MAX_SIZE){
                System.out.println("要生产的商品数量："+num+"\t剩余库存量："
                        +(MAX_SIZE-list.size())+"\t暂时不能进行生产任务");
                //生产阻塞
                list.wait();
            }

            //条件满足，开始生产
            for (int i = 0; i < num; i++) {
                list.add(new Object());
            }

            System.out.println("已经生产的数量："+num+"\t现在仓库库存量:"+list.size());

            list.notifyAll();
        }
    }
}

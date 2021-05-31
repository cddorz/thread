package com.geekagain.deadlock;

/**
 * 解决策略：哲学家换手
 * 原本问题：1-5号哲学家，1-5号筷子，若五位哲学家同时举起左边筷子，这就造成都不能eating，当哲学家持有对方所需要的筷子又不主动释放 导致所有人都没有办法继续eating
 * 解决：5号哲学家第一次举起右边的筷子，这样1号和5号会争夺1号筷子，有一位能抢到筷子，此时5号哲学家左边的筷子空出
 * 这样，4号哲学家就能拿起两双筷子eating，之后再放下筷子，其他哲学家开始eating。
 * @author hly
 * @Description:哲学家就餐问题
 * @create 2021-04-20 16:36
 */
public class DiningPhilosophers {
    public static void main(String[] args) {
        Philosophers[] philosophers = new Philosophers[5];
        Object[] chopsticks = new Object[philosophers.length];
        for (int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] = new Object();
        }
        for (int i = 0; i < philosophers.length; i++) {
            Object leftChopsticks = chopsticks[i];
            //因为坐圆桌
            Object rigthChopsticks = chopsticks[(i+1) % chopsticks.length];
            //表示第几个哲学家
            if(i != philosophers.length - 1){
                philosophers[i] = new Philosophers(leftChopsticks,rigthChopsticks);
            }else {
                //解决策略：第五位哲学家的“左边的筷子”是右边的筷子，有效解决，所有哲学家同时举起左边筷子，造成的死锁问题。
                philosophers[i] = new Philosophers(rigthChopsticks,leftChopsticks);
            }
            new Thread(philosophers[i],"哲学家"+(i+1)+"号").start();
        }
    }
}

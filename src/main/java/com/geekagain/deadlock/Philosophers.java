package com.geekagain.deadlock;

/**
 * @author hly
 * @Description:哲学家
 * @create 2021-04-20 16:49
 */
public class Philosophers implements Runnable{

    private Object leftChopstick;

    private Object rightChopstick;

    public Philosophers(Object leftChopstick, Object rightChopstick){
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    @Override
    public void run() {
        try {
            while (true){
                doAciton("thinking");
                synchronized (leftChopstick){
                    doAciton("Pick up left chopstick");
                    synchronized (rightChopstick){
                        doAciton("Pick up right chopstick --eating");
                        doAciton("Put down right chopstick");
                    }
                    doAciton("Put down left chopstick");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void doAciton(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep((long) (Math.random()*10));
    }
}

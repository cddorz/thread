package com.geekagain.stopthread;

/**
 * while循环在try块里，如果try在while循环里时，应该在catch块里重新设置一下中断标示
 * 因为抛出InterruptedException异常后，中断标示位会自动清除
 * @author hly
 */
public class RigihtWayDealMethodException implements Runnable {
    @Override
    public void run() {
        while(true ){
            if(Thread.currentThread().isInterrupted()) {
                System.out.println("被终结");
                break;
            }
            try {
                throwExceptionInMethod();
            } catch (InterruptedException e) {
                System.out.println("停止程序");
                Thread.currentThread().interrupt();
            }
        }
    }

    //不要在你的底层代码里捕获InterruptedException异常后不处理，会处理不当
    private void throwExceptionInMethod() throws InterruptedException {
            Thread.sleep(2000);
    }

    public static void main(String[] args) throws InterruptedException {
       Thread thread =  new Thread(new RigihtWayDealMethodException());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}

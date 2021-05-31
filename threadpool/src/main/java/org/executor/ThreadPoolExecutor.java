package org.executor;

import org.executor.handler.ThreadHandler;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hly
 * @Description: 重写线程池的创建
 * @create 2021-04-23 21:52
 */
public class ThreadPoolExecutor implements ExecutorService{

    //默认核心线程大小
    private static final int defaultPoolSize = 4;
    //默认任务队列大小
    private static final int defaultQueueSize = 40;
    //默认存活时间
    private static final long defaultAliveTime = 60L;
    //线程池最大的大小
    private static final int maxPoolSize = 50;
    //核心线程池大小
    private volatile int poolsize;
    //任务容量
    private long completedTaskCount;
    //拒绝策略
    private volatile ThreadHandler handler;
    //是否已经结束
    private volatile boolean isShutdown = false;
    /**
     * 当前激活的线程数量,可以自动更新的int
     * 使用AtomicInteger，可以保证ctl是原子性的，线程安全
     */
    private AtomicInteger ctl = new AtomicInteger();
    //队列
    private final BlockingQueue<Runnable> workQueue;

    //worker集合
    private final HashSet<Worker> workers = new HashSet<Worker>();
    //是否允许超时
    private volatile boolean allowThreadTimeOut;

    //保持存活时间
    private volatile long keepAliveTime;

    /**
     * 锁变量
     * 基于JVM
     */
    private final ReentrantLock mainLock = new ReentrantLock();

    /**
     *  构造器
     */
    public ThreadPoolExecutor(int poolsize, int queueSize, long keepAliveTime, ThreadHandler handler) {
        if (poolsize <= 0 || poolsize > maxPoolSize) {
            throw new IllegalArgumentException("线程池大小不能<=0");
        }
        this.poolsize = poolsize;
        this.handler = handler;
        this.keepAliveTime = keepAliveTime;
        if (keepAliveTime > 0) {
            allowThreadTimeOut = true;
        }
        this.workQueue = new ArrayBlockingQueue<Runnable>(queueSize);
    }

    public ThreadPoolExecutor(BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
    }

    @Override
    public void execute(Runnable task) {
        if(task==null){
            throw new NullPointerException("任务不能为空");
        }
        if(isShutdown){
            throw new IllegalArgumentException("线程已销毁，禁止提交任务");
        }
        int c = ctl.get();

        //小于核心线程数
        if(c < poolsize){
            if(addWorker(task,true)){
                return;
            }
            //加入阻塞队列
        }else if(workQueue.offer(task)){
            return;
            //拒绝策略
        }else {
            handler.rejected(task,this);
        }
    }

    @Override
    public void shutdown() {

    }

    @Override
    public int getPoolSize() {
        return ctl.get();
    }

    @Override
    public Runnable getTask() {
        try {
            //如果超过存活时间就返回null，如果不超过返回时间，就返回队列的头部
            return allowThreadTimeOut ? workQueue.poll(keepAliveTime, TimeUnit.SECONDS) : workQueue.take();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private boolean addWorker(Runnable r,boolean startNew){
        if(startNew){
            ctl.incrementAndGet();
        }

        boolean workerAdded = false;
        boolean workerStart = false;

        Worker worker = new Worker(r);
        Thread t = worker.thread;
        if(t != null){
            ReentrantLock mainLock = this.mainLock;
            mainLock.lock();
            try {
                //线程未关闭
                if(!isShutdown){
                    //检查线程是否处于运行状态 start 方法不能重复调用执行
                    if(t.isAlive()) {
                        throw new IllegalArgumentException();
                    }
                    workers.add(worker);
                    workerAdded = true;
                }
            }finally {
                mainLock.unlock();
            }
            if(workerAdded){
                t.start();
                workerStart = true;
            }
        }
        return workerStart;
    }

    private AtomicInteger atomic = new AtomicInteger();
    class Worker extends ReentrantLock implements Runnable{
        volatile long completedTask;
        final Thread thread;
        Runnable firstTask;

        public Worker(Runnable r){
            this.firstTask = r;
            this.thread = new Thread(this,"thread-name-" + atomic.incrementAndGet());
        }

        @Override
        public void run() {
            runWorker(this);
        }
    }

    private void runWorker(Worker worker){
        Thread t = Thread.currentThread();
        Runnable task = worker.firstTask;
        worker.firstTask = null;
        boolean completedAbruptly = true;

        try {
            while (task!=null && getTask()!=null){
                worker.lock();
                if(isShutdown&&!t.isInterrupted()){
                    t.interrupt();
                }
                try {
                    task.run();
                }finally {
                    task = null;
                    worker.completedTask++;
                    worker.unlock();
                }
            }
            completedAbruptly = false;
        }finally {
            processWorkerExit(worker, completedAbruptly);
        }
    }

    private void processWorkerExit(Worker worker,boolean completedAbruptly){
        if (completedAbruptly) {
            ctl.decrementAndGet();
        }
        final ReentrantLock lock = this.mainLock;
        mainLock.lock();
        try {
            completedTaskCount += worker.completedTask;
            workers.remove(worker);
        } finally {
            mainLock.unlock();
        }
        if (completedAbruptly && !workQueue.isEmpty()) {
            addWorker(null, false);

        }
    }
}

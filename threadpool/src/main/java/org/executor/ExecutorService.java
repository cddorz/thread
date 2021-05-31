package org.executor;

/**
 * @author hly
 * @Description: TODO
 * @create 2021-04-23 21:49
 */
public interface ExecutorService {

    /**
     * 执行方法
     */
    void execute(Runnable task);

    /**
     * 关闭方法
     */
    void shutdown();

    /**
     * 获取线程池数量
     * @return
     */
    int getPoolSize();

    /**
     * 获取任务
     * @return
     */
    Runnable getTask();
}

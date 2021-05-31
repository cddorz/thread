package org.executor.handler;

import org.executor.ThreadPoolExecutor;
import org.executor.exception.ThreadException;

/**
 * @author hly
 * @Description: 自定义拒绝策略
 * @create 2021-04-23 22:05
 */
public class ThreadHandlerImpl implements ThreadHandler{
    @Override
    public void rejected(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
        throw new ThreadException("任务已经满了");
    }
}

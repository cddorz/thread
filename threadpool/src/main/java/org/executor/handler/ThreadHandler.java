package org.executor.handler;

import org.executor.ThreadPoolExecutor;

/**
 * @author hly
 * @Description: TODO
 * @create 2021-04-23 22:04
 */
public interface ThreadHandler {
    void rejected(Runnable runnable, ThreadPoolExecutor threadPoolExecutor);
}

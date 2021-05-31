package org.executor.exception;

/**
 * @author hly
 * @Description: TODO
 * @create 2021-04-23 21:44
 */
public class ThreadException extends RuntimeException{
    /**
     * 将具体信息传递给父类
     */
    public ThreadException(String message){
        super(message);
    }
}

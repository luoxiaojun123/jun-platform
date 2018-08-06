package com.xiaojun.exception;

/**
 * @author long.luo
 * @date 2018/8/6 15:04
 */
public abstract class AbstractCustomException extends RuntimeException {

    public AbstractCustomException(String message) {
        super(message);
    }

    public AbstractCustomException(String message, Throwable t) {
        super(message, t);
    }

    /**
     * 错误码
     * @return
     */
    protected abstract String getErrorCode();

}

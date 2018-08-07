package com.xiaojun.exception;

import com.xiaojun.enums.ResponseCodeConstants;

/**
 * 服务端异常
 *
 * @author long.luo
 * @date 2018/8/6 15:19
 */
public class ServerErrorException extends AbstractCustomException {

    public ServerErrorException(String message) {
        super(message);
    }

    public ServerErrorException(String message, Throwable t) {
        super(message, t);
    }

    @Override
    protected String getErrorCode() {
        return ResponseCodeConstants.SERVER_ERROR;
    }
}

package com.xiaojun.service;

/**
 * @author long.luo
 * @date 2018/9/27 14:54
 */
public interface AsyncService {
    void asyncInvoke(AsyncExec asyncExec) throws Exception;

    @FunctionalInterface
    interface AsyncExec {
        void exec() throws Exception;
    }
}

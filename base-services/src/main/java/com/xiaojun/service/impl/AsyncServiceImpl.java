package com.xiaojun.service.impl;

import com.xiaojun.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author long.luo
 * @date 2018/9/27 14:57
 */
@Service
public class AsyncServiceImpl implements AsyncService {

    @Override
    @Async
    public void asyncInvoke(AsyncExec asyncExec) throws Exception {
        asyncExec.exec();
    }
}

package com.xiaojun.api.fallback;

import com.xiaojun.api.ProvideClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author long.luo
 * @date 2018/9/13 10:42
 */
@Component
@Slf4j
public class ProvideClientFallback implements FallbackFactory<ProvideClient> {

    @Override
    public ProvideClient create(Throwable throwable) {
        return userName -> "远程调用失败";
    }
}

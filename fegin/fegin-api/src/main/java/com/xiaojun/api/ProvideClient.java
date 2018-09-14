package com.xiaojun.api;

import com.xiaojun.api.config.FeginConfig;
import com.xiaojun.api.fallback.ProvideClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author long.luo
 * @date 2018/9/12 20:06
 */
@FeignClient(name = "fegin-provide-service", fallbackFactory = ProvideClientFallback.class, configuration = FeginConfig.class)
public interface ProvideClient {

    /**
     * 获取信息
     *
     * @param userName
     * @return 信息结果
     */
    @GetMapping("/info")
    String info(@RequestParam("userName") String userName);
}

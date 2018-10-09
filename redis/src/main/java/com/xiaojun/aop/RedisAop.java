package com.xiaojun.aop;

import com.xiaojun.annotation.RedisLock;
import com.xiaojun.distributedlock.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author long.luo
 * @date 2018/10/9 10:13
 */
@Aspect
@Component
@Slf4j
public class RedisAop {


    @Autowired
    private DistributedLock distributedLock;

    @Pointcut("@annotation(com.xiaojun.annotation.RedisLock)")
    public void distributed() {

    }

    @Around("distributed()&&@annotation(redisLock)")
    public void execute(ProceedingJoinPoint pjp, RedisLock redisLock) throws Throwable {
        String lockKey = redisLock.lockKey();
        Method method = getMethod(pjp);
        String key = redisLock.prefix() + ":" + parseKey(lockKey, method, pjp.getArgs());
        int retryTimes = redisLock.action().equals(RedisLock.LockFailAction.CONTINUE) ? redisLock.retryTimes() : 0;

        boolean lock = distributedLock.lock(key, redisLock.expireTime(), retryTimes, redisLock.sleepMills());
        if (!lock) {
            log.debug("get lock failed : " + key);
            return;
        }
        //得到锁,执行方法，释放锁
        log.debug("get lock success : " + key);
        try {
            pjp.proceed();
        } catch (Exception e) {
            log.error("execute locked method occured an exception", e);
        } finally {
            boolean releaseResult = distributedLock.releaseLock(key);
            log.debug("release lock : " + key + (releaseResult ? " success" : " failed"));
        }
    }


    /**
     * 获取被拦截方法对象
     * <p>
     * MethodSignature.getMethod() 获取的是顶层接口或者父类的方法对象
     * 而缓存的注解在实现类的方法上
     * 所以应该使用反射获取当前对象的方法对象
     *
     * @param pjp
     * @return
     */
    private Method getMethod(ProceedingJoinPoint pjp) {
        //获取参数的类型
        Object[] args = pjp.getArgs();
        Class[] argTypes = new Class[pjp.getArgs().length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }
        Method method = null;
        try {
            method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), argTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }

    /**
     * 获取缓存的key  key 定义在注解上，支持SPEL表达式
     *
     * @param key
     * @param method
     * @param args
     * @return
     */
    private String parseKey(String key, Method method, Object[] args) {
        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);

        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();

        //SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();

        //把方法参数放入SPEL上下文中
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context, String.class);
    }

}

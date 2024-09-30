package com.hezy.aspect;

import com.hezy.annotation.LimitAccess;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * AOP类（通知类）
 */
@Component
@Aspect
@Log4j2
public class LimitAspect {

    @Value("${access.enable:false}")
    private boolean enable;

    @Autowired
    private RedisTemplate redisTemplate;

    @Pointcut("@annotation(com.hezy.annotation.LimitAccess)")
    public void pt(){};

    @Around("pt()")
    public Object aopAround(ProceedingJoinPoint pjp) throws Throwable {
        // 设置一个开关，控制是否执行
        if (!enable) {
            return pjp.proceed();
        }
        // 获取切入点上面的自定义注解
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;

        // 获取方法上面的注解
        LimitAccess limitAccess = methodSignature.getMethod().getAnnotation(LimitAccess.class);
        // 获取注解上面的属性值
        int limit = limitAccess.times();
        String key = limitAccess.key();
        int duration = limitAccess.duration();

        // 递增键的值，如果键不存在则初始化为1
        Long currentCount = redisTemplate.opsForValue().increment(key, 1);

        // 如果键是新创建的，设置过期时间
        if (currentCount != null && currentCount == 1) {
            redisTemplate.expire(key, duration, TimeUnit.SECONDS);
        }

        // 检查是否超过限制
        if (currentCount != null && currentCount > limit) {
            log.info("访问过于频繁: " + pjp.toLongString());
            throw new RuntimeException("访问过于频繁");
        }
        return pjp.proceed();
    }
}

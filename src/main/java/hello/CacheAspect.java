package hello;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Aspect
@Configuration // 告诉spring这个类是与spring相关的配置，工作时需要考虑进去
public class CacheAspect {
    //    Map<String,Object> cache = new HashMap<>();
    // 在这里已经使用HashMap了为什么还要使用redis呢
    // 应用不可能只有一个jvm来处理，多个jvm分布式部署轮流处理请求时，容灾分担压力
    // HashMap都维持在jvm内部，无法共享，因此需要redis
    // redis缓存可以在多个分布式部署时共享集群
    // 当然redis也可以做集群

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Around("@annotation(hello.anno.Cache)")
    public Object cache(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature(); // 拿到方法的参数
        String methodName = signature.getName();
        Object cachedValue = redisTemplate.opsForValue().get(methodName);
        if(cachedValue != null){
            System.out.println("Get value from cache");
            return cachedValue;
        }else {
            System.out.println("Get value from real");
            Object realValue = joinPoint.proceed();
            redisTemplate.opsForValue().set(methodName,realValue);
            return realValue;
        }

    }
}

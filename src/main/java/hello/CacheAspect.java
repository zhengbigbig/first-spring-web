package hello;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Configuration // 告诉spring这个类是与spring相关的配置，工作时需要考虑进去
public class CacheAspect {
    Map<String,Object> cache = new HashMap<>();

    @Around("@annotation(hello.anno.Cache)")
    public Object cache(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature(); // 拿到方法的参数
        String methodName = signature.getName();
        Object cachedValue = cache.get(methodName);
        if(cachedValue != null){
            System.out.println("Get value from cache");
            return cachedValue;
        }else {
            System.out.println("Get value from real");
            Object realValue = joinPoint.proceed();
            cache.put(methodName,realValue);
            return realValue;
        }

    }
}

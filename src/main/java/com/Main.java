package com;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Main {
    static DataServiceImpl service = new DataServiceImpl();

    public static class LogInterceptor implements MethodInterceptor{
        // 拦截具体的实例方法
        private DataServiceImpl delegate;

        public LogInterceptor(DataServiceImpl delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println(method.getName() + " is invoked: " + Arrays.toString(objects));
            Object retValue = method.invoke(delegate, objects);
            System.out.println(method.getName() + " is finished: " + retValue);
            return retValue;
        }
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();  // 增强
        enhancer.setSuperclass(DataServiceImpl.class); // 动态继承，子类化。在jvm中通过动态生成继承了DataServiceImpl
        enhancer.setCallback(new LogInterceptor(service)); // 设置回调，在每个方法被调用时，调用

        DataServiceImpl enhancedService = (DataServiceImpl) enhancer.create();
        enhancedService.a(1);
        enhancedService.b(1);
    }
}

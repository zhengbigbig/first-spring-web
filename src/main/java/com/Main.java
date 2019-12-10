package com;

import java.lang.reflect.Proxy;

public class Main {
    static DataService service = new DataServiceImpl();

    public static void main(String[] args) {
        //
        /*
          Proxy有一个reflect和net包，此处使用reflect
          参数
            ClassLoader loader,
            Class<?>[] interfaces,  只能是接口，不然会报错
             InvocationHandler h  方法拦截后由谁处理
          下面的DataService是被动态代理的生成的，会被LogProxy拦截，已经不是原先的DataService

         */
        DataService dataService = (DataService) Proxy.newProxyInstance(service.getClass().getClassLoader(),
                new Class[]{DataService.class},
                new LogProxy(service));
        System.out.println(dataService.a(1));
    }
}

package com;

import java.util.HashMap;
import java.util.Map;

// 暂不考虑多线程不安全问题
public class CacheDecorator implements DataService {
    private Map<String, String> cache = new HashMap<>();

    private DataService delegate;

    public CacheDecorator(DataService delegate) {
        this.delegate = delegate;
    }

    @Override
    public String a(int i) {
        String cachedValue = cache.get("a");
        if (cachedValue != null) {
            return cachedValue;
        }
        String realValue = delegate.a(i);
        cache.put("a", realValue);
        return realValue;
    }

    @Override
    public String b(int i) {
        String cachedValue = cache.get("b");
        if (cachedValue != null) {
            return cachedValue;
        }
        String realValue = delegate.b(i);
        cache.put("b", realValue);
        return realValue;
    }
}

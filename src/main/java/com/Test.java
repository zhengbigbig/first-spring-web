package com;

import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field valueField = Integer.class.getDeclaredField("value");
        valueField.setAccessible(true);
        valueField.setInt(1, 2);
        Integer a = 1, b = 1;
        System.out.println("1+1=" + (a + b));
    }
}

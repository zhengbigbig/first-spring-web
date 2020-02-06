package com;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddTest {
    // junit通过反射去调用，每次调用都会创建一个新的实例，每个测试互不干扰

    @BeforeAll
    public static void setUpAll(){
        System.out.println("在所有测试用例之前运行且只运行一次");
    }

    @BeforeEach
    public void setUp(){
        System.out.println("初始化测试类的实例！");
    }

    @Test
    public void testAdd(){
        // 断言
        // 我断言xxx是正确的，如果是错误的就报错
        Assertions.assertEquals(3,Add.add(1,1,1));
    }

    @Test
    public void testAddFile(){
        Assertions.assertNotEquals(4,Add.add(1,1,1));

    }
}
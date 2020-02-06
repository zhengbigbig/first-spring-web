package com;

public class Test {
    public static void main(String[] args) {
        int i = 1;
        f(i);
        factorial(1);
        Object obj = new Object();
        print(obj); // 传递进去的是一个地址
        System.out.println("obj = " + obj);
    }

    public static void print(Object obj){
        obj = new Object();
        // 传递进来的是一个拷贝后的地址，重新new后会将这个地址重新指向新的对象
        // 当方法调用结束时，将销毁栈上面这个obj的地址
    }

    private static void f(int i) {
        i = i + 1; // java中所有参数的传递都是拷贝，这里的i的修改不影响main中的i
    }

    public static int factorial(int n) { // 每次进入一个方法时才会产生新的栈帧
        if (n < 2) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
}

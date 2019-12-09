package com;

public class Main {
    static DataService service = new CacheDecorator(new LogDecorator(new DataServiceImpl()));

    public static void main(String[] args) {

        System.out.println(service.a(1));
        System.out.println(service.b(1));

        System.out.println(service.a(1));
        System.out.println(service.b(1));
    }
}

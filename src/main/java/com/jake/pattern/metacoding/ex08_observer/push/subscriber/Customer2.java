package com.jake.pattern.metacoding.ex08_observer.push.subscriber;

public class Customer2 implements Customer {
    @Override
    public void update(String msg) {
        System.out.println("손님 2가 받은 알림 : " + msg);
    }
}

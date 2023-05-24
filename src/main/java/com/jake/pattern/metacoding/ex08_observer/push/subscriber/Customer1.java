package com.jake.pattern.metacoding.ex08_observer.push.subscriber;

public class Customer1 implements Customer {
    @Override
    public void update(String msg) {
        System.out.println("손님 1이 받은 알림 : " + msg);
    }
}

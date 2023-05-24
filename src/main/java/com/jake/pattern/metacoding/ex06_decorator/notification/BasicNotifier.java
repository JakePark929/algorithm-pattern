package com.jake.pattern.metacoding.ex06_decorator.notification;

// Wrapper 가 없는 Decorator - 중요!
public class BasicNotifier implements Notifier {
    // 이 친구는 뭔가를 의존하면 안된다.
    @Override
    public void send() {
        System.out.println("기본 알림");
    }
}

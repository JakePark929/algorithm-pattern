package com.jake.pattern.metacoding.ex08_observer.push.publisher;

import com.jake.pattern.metacoding.ex08_observer.push.subscriber.Customer;

public interface Mart {
    // 고객등록
    void add(Customer customer);
    // 고객 삭제
    void remove(Customer customer);
    // 상품받기
    void received();
    // 상품을 받으면 알림
    void notifyChange(String msg);
}

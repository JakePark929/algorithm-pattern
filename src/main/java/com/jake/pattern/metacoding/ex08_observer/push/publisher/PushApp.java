package com.jake.pattern.metacoding.ex08_observer.push.publisher;

import com.jake.pattern.metacoding.ex08_observer.push.subscriber.Customer1;
import com.jake.pattern.metacoding.ex08_observer.push.subscriber.Customer2;
import com.jake.pattern.metacoding.ex08_observer.push.subscriber.Customer3;

// 옵저버 패턴(push)
public class PushApp {
    public static void main(String[] args) {
        Mart lotteMart = new LotteMart();
        Mart lgMart = new LgMart();
        Customer1 customer1 = new Customer1();
        Customer2 customer2 = new Customer2();
        Customer3 customer3 = new Customer3();

        // 고객 등록(구독하기)
        lotteMart.add(customer1);
        lotteMart.add(customer2);
        lotteMart.add(customer3);

        // 고객 등록(구독취소)
        lotteMart.remove(customer2);

        // 고객 등록(구독하기)
        lgMart.add(customer2);
        
        // 마트 상품아 빨리 도착해
        new Thread(lotteMart::received).start();

        new Thread(lgMart::received).start();

    }
}

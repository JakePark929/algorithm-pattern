package com.jake.pattern.metacoding.ex08_observer.push.publisher;

import com.jake.pattern.metacoding.ex08_observer.push.subscriber.Customer;

import java.util.ArrayList;
import java.util.List;

public class LgMart implements Mart {
    // 고객 명단
    private List<Customer> customerList = new ArrayList<>();

    @Override
    public void add(Customer customer) {
        customerList.add(customer);
    }

    @Override
    public void remove(Customer customer) {
        customerList.remove(customer);
    }

    @Override
    public void received() {
        try {
            for(int i = 0; i < 3; i++) {
                System.out.println(".");
                Thread.sleep(1000);
            }
            notifyChange("LG 상품 들어왔어요");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    // 모든 고객중에 등록된 손님에게 알림을 보내야 함
    @Override
    public void notifyChange(String msg) {
        customerList.forEach((c) -> {
            c.update(msg);
        });
    }
}

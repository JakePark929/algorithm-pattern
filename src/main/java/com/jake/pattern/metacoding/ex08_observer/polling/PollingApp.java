package com.jake.pattern.metacoding.ex08_observer.polling;

public class PollingApp {
    public static void main(String[] args) {
        LotteMart lotteMart = new LotteMart();
        Customer1 customer1 = new Customer1();
        Customer2 customer2 = new Customer2();

        new Thread(() -> {
            lotteMart.received();
        }).start();
        
        
        // 한번 물어보면 해결이 안됨
        // 메인쓰레드에서 실행
        while (true) {
            // 어느정도 시간을 정해서 물어보자(Polling)
            try {
                System.out.println("상품 들어왔나요?....");
                // 물어보는 시간이 짧으면 상품이 들어온 것을 빠르게 알 수 있다.(누구보다 빠르게 구매가능, 단: 계속 물어봐야 되서 지침)
                // 물어보는 시간이 길면 들어온 것은 빠르게 알 수 없다.(장: 덜 지침, 단: 구매를 못할 수도)
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            if (lotteMart.getValue() != null) {
                customer1.update("요청하신 상품(" + lotteMart.getValue() + ")이 입고되었습니다.");
                customer2.update("요청하신 상품(" + lotteMart.getValue() + ")이 입고되었습니다.");
                break;
            } else {
                System.out.println("상품이 아직 들어오지 않았습니다.");
            }
        }
    }
}

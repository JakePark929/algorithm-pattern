package com.jake.pattern.metacoding.ex08_observer.polling;

public class LotteMart {
    private String value = null;

    public String getValue() {
        return value;
    }
    
    // 다른 쓰레드에서 실행
    public void received() {
        // 5초후에 값이 들어옴
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000);
            }

            value = "김치 10kg";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

package com.jake.pattern.metacoding.ex03_adaptor;

/**
 * [ 어댑터 패턴 ]
 * : 외부 요소를 기존 시스템에 재사용 하고 싶지만 아직 만들어 지지 않은 경우
 * : 외부 요소를 기존 시스템에 재사용 하고 싶지만 호환되지 않는 경우 (v)
 */
public class App {
    public static void main(String[] args) {
        Mouse mouse = new Mouse();
        Cat cat = new Cat();
        OuterTiger outerTiger = new OuterTiger();
        TigerAdaptor tigerAdaptor = new TigerAdaptor(outerTiger);
        Doorman doorman = new Doorman();
        doorman.goAway(mouse);
        doorman.goAway(cat);
//        doorman.goAway(outerTiger); // 타입이 안맞춰짐
        doorman.goAway(tigerAdaptor);
    }
}

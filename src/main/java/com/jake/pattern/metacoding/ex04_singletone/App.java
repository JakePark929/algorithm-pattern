package com.jake.pattern.metacoding.ex04_singletone;

public class App {
    public static void main(String[] args) {
        Mouse mouse = new Mouse();
        Cat cat = new Cat();
        Doorman doorman = Doorman.getInstance();
        Doorman doorman2 = Doorman.getInstance(); // 있는거 그대로 쓰는 거임
        doorman.goAway(mouse);
        doorman.goAway(cat);
    }
}

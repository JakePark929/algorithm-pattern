package com.jake.pattern.metacoding.ex02_proxy;

public class App {
    public static void main(String[] args) {
        Mouse mouse = new Mouse();
        Cat cat = new Cat();
//        Doorman doorman = new DoormanProxy(); // extends
        DoormanProxy doorman = new DoormanProxy(new Doorman()); // composition
        doorman.goAway(mouse);
        doorman.goAway(cat);
    }
}

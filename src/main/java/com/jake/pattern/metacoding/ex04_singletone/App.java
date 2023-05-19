package com.jake.pattern.metacoding.ex01_strategy;

public class App {
    public static void main(String[] args) {
        Mouse mouse = new Mouse();
        Cat cat = new Cat();
        Doorman doorman = new Doorman();
        doorman.goAway(mouse);
        doorman.goAway(cat);
    }
}

package com.jake.pattern.metacoding.ex02_proxy;

public class Doorman {
    public void goAway(Animal animal) {
        System.out.println(animal.getName() + " 쫓아내!");
    }
}
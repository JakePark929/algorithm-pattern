package com.jake.pattern.metacoding.ex02_proxy;

// extends 하는 방법
//public class DoormanProxy extends Doorman {
//    public void goAway(Animal animal) {
//        System.out.println("안녕");
//        super.goAway(animal);
//    }
//}

// Composition 하는 방법
public class DoormanProxy {
    private Doorman doorman;

    public DoormanProxy(Doorman doorman) {
        this.doorman = doorman;
    }

    public void goAway(Animal animal) {
        System.out.println("안녕");
        doorman.goAway(animal);
    }
}

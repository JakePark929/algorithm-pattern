package com.jake.pattern.metacoding.ex04_singletone;

/**
 * 문지기를 딱 한명만 만들고 싶음!
 */
public class Doorman {
    
    // static: main 메소드를 호출하기 전에 JVM 이 미리 읽어서 메모리에 올라오는 친구
    private static Doorman doorman = new Doorman();
    
    public static Doorman getInstance() { // heap 이 관리하는 메소드
        return doorman;
    }
    
    private Doorman() {
    }

    public void goAway(Animal animal) {
        System.out.println(animal.getName() + " 쫓아내!");
    }
}
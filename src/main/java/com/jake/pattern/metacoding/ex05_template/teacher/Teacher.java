package com.jake.pattern.metacoding.ex05_template.teacher;

public abstract class Teacher {
    private void enter() {
        System.out.println("입장하기");
    }
    
    private void check() {
        System.out.println("출석부르기");
    }

    // 오버라이드(부모의 메소드를 무효화하다)
    abstract void lecture();
    
    private void exit() {
        System.out.println("퇴장하기");
    }

    public void start() {
        enter();
        check();
        lecture();
        exit();
    }
}

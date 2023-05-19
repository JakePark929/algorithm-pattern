package com.jake.pattern.metacoding.ex05_template.teacher;

public class JavaTeacher extends Teacher {
    // 재정의
    void lecture() { // 동적바인딩 (C# 동적결합)
        System.out.println("Java 강의하기");
    }
}

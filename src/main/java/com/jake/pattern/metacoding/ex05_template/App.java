package com.jake.pattern.metacoding.ex05_template;

import com.jake.pattern.metacoding.ex05_template.teacher.JavaTeacher;
import com.jake.pattern.metacoding.ex05_template.teacher.PythonTeacher;
import com.jake.pattern.metacoding.ex05_template.teacher.Teacher;

public class App {
    public static void learn(Teacher teacher) {
        teacher.start();
    }

    public static void main(String[] args) {
//        Teacher t1 = new Teacher();
        JavaTeacher t1 = new JavaTeacher();
        learn(t1);

        PythonTeacher t2 = new PythonTeacher();
        learn(t2);
    }
}

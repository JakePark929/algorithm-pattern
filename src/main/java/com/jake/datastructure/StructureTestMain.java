package com.jake.datastructure;

import com.jake.datastructure.list.ArrayList;

import java.util.Arrays;

public class StructureTestMain implements Cloneable {
    public static void main(String[] args) throws CloneNotSupportedException {
        ArrayList<Integer> original = new ArrayList<>();
        System.out.println(original.size());
        original.add(10); // 10 추가
        original.add(20); // 10 추가
        original.add(30); // 10 추가
        original.add(40); // 10 추가
        original.add(50); // 10 추가
        original.add(60); // 10 추가
        original.add(70); // 10 추가
        original.add(80); // 10 추가
        original.add(90); // 10 추가
        original.add(100); // 10 추가
        original.add(110); // 10 추가
        original.add(120); // 10 추가

        System.out.println(original);

        original.remove(11); // 10 추가
        original.remove(10); // 10 추가
        original.remove(9); // 10 추가
        original.remove(8); // 10 추가

        ArrayList<Integer> copy = original;
        ArrayList<Integer> clone = (ArrayList<Integer>) original.clone();

        System.out.println(copy.add(20));
        System.out.println(clone.add(30));

        System.out.println(original);
        System.out.println(copy);
        System.out.println(clone);

        System.out.println(original.size());
        System.out.println(original.indexOf(30));
        System.out.println(original.contains(30));
        System.out.println(original.contains(120));
        System.out.println(original.isEmpty());
    }
}

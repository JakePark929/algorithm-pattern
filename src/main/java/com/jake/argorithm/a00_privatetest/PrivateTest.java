package com.jake.argorithm.a00_privatetest;

import java.util.LinkedList;

public class PrivateTest {
    public String solution(String word, int n) {
        char[] arr = word.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c : arr) {
            char code = getCode(c, n);
            sb.append(code);
        }
        return sb.toString();
    }

    private char getCode(char c, int n) {
        LinkedList<Character> lowers = new LinkedList<>();
        LinkedList<Character> uppers = new LinkedList<>();

        for (int i = 0; i < 26; i++) {
            lowers.add((char) (97 + i));
        }
        for (int i = 0; i < 26; i++) {
            uppers.add((char) (65 + i));
        }

        if(Character.isLowerCase(c)) {
            System.out.println(lowers.indexOf(c));
            return lowers.get((lowers.indexOf(c) + n) % 26);
        } else if(Character.isUpperCase(c)) {
            return uppers.get((uppers.indexOf(c) + n) % 26);
        } else {
            return c;
        }
    }

    public static void main(String[] args) {
        PrivateTest problem = new PrivateTest();
        System.out.println(problem.solution("z", 1));
    }
}

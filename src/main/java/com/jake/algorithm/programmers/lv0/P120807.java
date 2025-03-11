package com.jake.algorithm.programmers.lv0;

public class P120807 {
    public int solution(int num1, int num2) {

        return num1 == num2 ? 1 : -1;
    }

    public static void main(String[] args) {
        final P120807 p120807 = new P120807();

        System.out.println(p120807.solution(11, 11));
    }
}

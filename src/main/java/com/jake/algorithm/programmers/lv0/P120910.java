package com.jake.algorithm.programmers.lv0;

public class P120910 {
    public int solution(int n, int t) {
        int answer = n;

        for (int i = 1; i <= t; i++) {
            answer *= 2;
        }

        return answer;
    }

    public static void main(String[] args) {
        final P120910 p120910 = new P120910();

        System.out.println(p120910.solution(7, 15));
    }
}

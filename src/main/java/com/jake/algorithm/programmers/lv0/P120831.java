package com.jake.algorithm.programmers.lv0;

import java.util.stream.IntStream;

public class P120831 {
    public int solution(int n) {
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                answer += i;
            }
        }

        return answer;
    }

    public int solution2(int n) {

        return IntStream.rangeClosed(0, n)
                .filter(e -> e % 2 == 0)
                .sum();
    }

    public static void main(String[] args) {
        final P120831 p120831 = new P120831();

        System.out.println(p120831.solution(4));
    }
}

package com.jake.argorithm.programmers.lv1;

import java.util.stream.IntStream;

/**
 * 자연수 n이 매개변수로 주어집니다.
 * n을 x로 나눈 나머지가 1이 되도록 하는 가장 작은 자연수 x를 return 하도록 solution 함수를 완성해주세요.
 * 답이 항상 존재함은 증명될 수 있습니다.
 *
 * 작성일: 2023.06.02
 */
public class P87389 {
    public int solution(int n) {
        int answer = 0;
        if (3 <= n && n <= 1000000) {
            for (int i = 1; i <= n; i++) {
                if (n % i == 1) {
                    answer = i;
                    break;
                }
            }
        }
        return answer;
    }
    
    // 다른 사람의 풀이
    public int anotherSolution(int n) {
        return IntStream.range(2, n).filter(i -> n % i == 1).findFirst().orElse(0);
    }

    public static void main(String[] args) {
        P87389 p = new P87389();
        System.out.println(p.solution(12));
    }
}

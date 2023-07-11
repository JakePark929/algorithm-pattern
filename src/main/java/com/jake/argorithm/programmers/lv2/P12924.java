package com.jake.argorithm.programmers.lv2;

import java.util.Arrays;

/**
 * [ 숫자의 표현 ]
 *
 * Finn 은 요즘 수학공부에 빠져 있습니다.
 * 수학 공부를 하던 Finn 은 자연수 n을 연속한 자연수들로 표현 하는 방법이 여러개라는 사실을 알게 되었습니다.
 * 예를들어 15는 다음과 같이 4가지로 표현 할 수 있습니다.
 *
 * 1 + 2 + 3 + 4 + 5 = 15
 * 4 + 5 + 6 = 15
 * 7 + 8 = 15
 * 15 = 15
 * 자연수 n이 매개변수로 주어질 때, 연속된 자연수들로 n을 표현하는 방법의 수를 return하는 solution를 완성해주세요.
 *
 * 제한사항
 * n은 10,000 이하의 자연수 입니다.
 *
 * 작성일 : 2023.
 */
class P12924 {
    public int solution(int n) {
        int answer = 0;
        Integer[] list = new Integer[n];
        Arrays.setAll(list, i -> i + 1);
        for (Integer integer : list) {
            int sum = 0;
            for (int j = integer; j <= list.length; j++) {
                sum += j;
                if (sum == n) {
                    answer = answer + 1;
                    break;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        P12924 problem = new P12924();
        System.out.println(problem.solution(10000));
    }
}

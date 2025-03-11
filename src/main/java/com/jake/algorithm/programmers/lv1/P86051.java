package com.jake.algorithm.programmers.lv1;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * [ 없는 숫자 더하기 ]
 * <p>
 * 0부터 9까지의 숫자 중 일부가 들어있는 정수 배열 numbers가 매개변수로 주어집니다.
 * numbers에서 찾을 수 없는 0부터 9까지의 숫자를 모두 찾아 더한 수를 return 하도록 solution 함수를 완성해주세요.
 * <p>
 * 제한사항
 * 1 ≤ numbers의 길이 ≤ 9
 * 0 ≤ numbers의 모든 원소 ≤ 9
 * numbers의 모든 원소는 서로 다릅니다.
 * <p>
 * 작성일 : 2023.06.20
 */
class P86051 {
    public int solution(int[] numbers) {
        int sum = 0;
        LinkedList<Integer> list = new LinkedList<>();

        for (int i : numbers) {
            list.add(i);
        }

        for (int i = 0; i < 10; i++) {
            if (!list.contains(i)) {
                sum += i;
            }
        }
        return sum >= 0 ? sum : -1;
    }

    // 다른 사람의 풀이 1
    public int solution2(int[] numbers) {
        int sum = 45;
        for (int i : numbers) {
            sum -= i;
        }
        return sum;
    }

    // 다른 사람의 풀이 2
    public int solution3(int[] numbers) {
        return 45 - Arrays.stream(numbers).sum();
    }

    // 다른 사람의 풀이 3
    public int solution4(int[] numbers) {
        int answer = 0;
        int[] sum = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        for (int i = 0; i < sum.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (sum[i] == numbers[j]) {
                    sum[i] -= numbers[j];
                }
            }
            answer += sum[i];
        }
        return answer;
    }

    public static void main(String[] args) {
        P86051 problem = new P86051();
        System.out.println(problem.solution(new int[]{5, 8, 4, 0, 6, 7, 9}));
    }
}

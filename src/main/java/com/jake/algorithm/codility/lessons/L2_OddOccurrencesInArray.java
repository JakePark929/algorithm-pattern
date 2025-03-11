package com.jake.algorithm.codility.lessons;

import java.util.HashMap;

/**
 * [ OddOccurrencesInArray ]
 *
 * 작성일 : 24.10.04
 */
public class L2_OddOccurrencesInArray {
    public int solution(int[] A) {
        int answer = 0;

        HashMap<Integer, Integer> table = new HashMap<>();
        for (Integer n : A) {
            if (!table.containsKey(n)) {
                table.put(n, 1);
            } else {
                if (table.get(n) > 0) {
                    table.put(n, table.get(n) - 1);
                } else {
                    table.put(n, table.get(n) + 1);
                }
            }
        }

        for (Integer key : table.keySet()) {
            if (table.get(key) > 0) {
                answer = key;
            }
        }

        return answer;
    }

    // ????
    public int solution1(int[] A) {
        int result = 0;

        // 배열 내 모든 요소들을 XOR 연산으로 결합
        for (int num : A) {
            result ^= num;  // XOR 연산
        }

        return result;  // 쌍을 이루지 않는 숫자가 남음
    }

    public static void main(String[] args) {
        int[] list = {9, 3, 9, 3, 9, 7, 9};
        final int solution = new L2_OddOccurrencesInArray().solution(list);

        System.out.println(solution);
    }
}

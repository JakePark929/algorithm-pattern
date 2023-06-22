package com.jake.argorithm.programmers.lv1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * [ 두 개 뽑아서 더하기 ]
 * <p>
 * 정수 배열 numbers가 주어집니다.
 * numbers에서 서로 다른 인덱스에 있는 두 개의 수를 뽑아 더해서 만들 수 있는 모든 수를
 * 배열에 오름차순으로 담아 return 하도록 solution 함수를 완성해주세요.
 * <p>
 * 제한사항
 * numbers의 길이는 2 이상 100 이하입니다.
 * numbers의 모든 수는 0 이상 100 이하입니다.
 * <p>
 * 작성일 : 2023.06.22
 */
class P68644 {
    int[] combos;
    HashSet<Integer> hs = new HashSet<>();

    public Integer[] solution(int[] numbers) {
        int depth = 2;
        combos = new int[depth];

        comb(numbers, depth, 0, 0);

        Integer[] arr = hs.toArray(new Integer[0]);
        Arrays.sort(arr);

        return arr;
    }

    public void comb(int[] numbers, int depth, int cnt, int start) {
        if (cnt == depth) {
            hs.add(combos[0] + combos[1]);
            return;
        }

        for (int i = start; i < numbers.length; i++) {
            combos[cnt] = numbers[i];
            comb(numbers, depth, cnt + 1, i + 1);
        }
    }

    // 다른 사람의 풀이 1
    public int[] solution2(int[] numbers) {
        Set<Integer> set = new HashSet<>();

        for(int i=0; i<numbers.length; i++) {
            for(int j=i+1; j<numbers.length; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }

        return set.stream().sorted().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        P68644 problem = new P68644();
        System.out.println(Arrays.toString(problem.solution(new int[]{1, 1, 1, 1, 1, 1})));
    }
}

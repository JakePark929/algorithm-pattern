package com.jake.argorithm.programmers.lv1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * [ 나누어 떨어지는 숫자 배열 ]
 *
 * array의 각 element 중 divisor로 나누어 떨어지는 값을 오름차순으로 정렬한 배열을 반환하는 함수, solution을 작성해주세요.
 * divisor로 나누어 떨어지는 element가 하나도 없다면 배열에 -1을 담아 반환하세요.
 *
 * 제한사항
 * arr은 자연수를 담은 배열입니다.
 * 정수 i, j에 대해 i ≠ j 이면 arr[i] ≠ arr[j] 입니다.
 * divisor는 자연수입니다.
 * array는 길이 1 이상인 배열입니다.
 *
 * 작성일 : 2023.06.15
 */
class P12910 {
    public List<Integer> solution(int[] arr, int divisor) {
        LinkedList<Integer> nums = new LinkedList<>();

        for (int v : arr) {
            if (v % divisor == 0) nums.add(v);
        }

        if (nums.size() == 0) nums.add(-1);

        return nums.stream().sorted().collect(Collectors.toList());
    }

    // 다른 사람의 풀이
    public int[] divisible(int[] array, int divisor) {
        return Arrays.stream(array).filter(factor -> factor % divisor == 0).toArray();
    }

    // 다른 사람의 풀이 2
    public int[] divisible2(int[] array, int divisor) {
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % divisor == 0) {
                index++;
            }
        }

        int[] ret = new int[index];

        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % divisor == 0) {
                ret[count++] = array[i];

            }
        }

        return ret;
    }

    public static void main(String[] args) {
        P12910 problem = new P12910();
        System.out.println(problem.solution(new int[]{3, 2, 6}, 10));
    }
}

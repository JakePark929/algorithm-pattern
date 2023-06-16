package com.jake.argorithm.programmers.lv1;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * [ 제일 작은 수 제거하기 ]
 * <p>
 * 정수를 저장한 배열, arr 에서 가장 작은 수를 제거한 배열을 리턴하는 함수, solution을 완성해주세요.
 * 단, 리턴하려는 배열이 빈 배열인 경우엔 배열에 -1을 채워 리턴하세요.
 * 예를들어 arr이 [4,3,2,1]인 경우는 [4,3,2]를 리턴 하고, [10]면 [-1]을 리턴 합니다.
 * <p>
 * 제한 조건
 * arr은 길이 1 이상인 배열입니다.
 * 인덱스 i, j에 대해 i ≠ j이면 arr[i] ≠ arr[j] 입니다.
 * <p>
 * 작성일: 2023.06.15
 */
class P12935 {
    public LinkedList<Integer> solution(int[] arr) {
        LinkedList<Integer> answer = new LinkedList<>();

        if (arr.length == 1) {
            answer.add(-1);
            return answer;
        }

        int[] copyArr = arr.clone();
        Arrays.sort(copyArr);

        for (int i : arr) {
            if (i != copyArr[0]) {
                answer.add(i);
            }
        }
        return answer;
    }

    // 다른 사람의 풀이 1
    public int[] solution2(int[] arr) {
        if (arr.length <= 1) return new int[]{-1};
        int min = Arrays.stream(arr).min().getAsInt();
        return Arrays.stream(arr).filter(i -> i != min).toArray();
    }

    // 다른 사람의 풀이 2
    public int[] solution3(int[] arr) {
        if (arr.length == 1) {
            return new int[]{-1};
        }

        int[] answer = new int[arr.length - 1];
        int minIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[minIndex] > arr[i]) {
                minIndex = i;
            }
        }
        for (int i = minIndex + 1; i < arr.length; i++) {
            arr[i - 1] = arr[i];
        }
        System.arraycopy(arr, 0, answer, 0, answer.length);
        return answer;
    }

    public static void main(String[] args) {
        P12935 problem = new P12935();
        System.out.println(problem.solution(new int[]{4, 3, 2, 1}));
    }
}

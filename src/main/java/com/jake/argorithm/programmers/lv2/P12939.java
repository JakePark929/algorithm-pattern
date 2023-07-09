package com.jake.argorithm.programmers.lv2;

import java.util.Arrays;

/**
 * [ 최댓값과 최솟값 ]
 *
 * 문자열 s에는 공백으로 구분된 숫자들이 저장되어 있습니다.
 * str에 나타나는 숫자 중 최소값과 최대값을 찾아
 * 이를 "(최소값) (최대값)"형태의 문자열을 반환하는 함수, solution을 완성하세요.
 * 예를들어 s가 "1 2 3 4"라면 "1 4"를 리턴하고, "-1 -2 -3 -4"라면 "-4 -1"을 리턴하면 됩니다.
 *
 * 제한 조건
 * s에는 둘 이상의 정수가 공백으로 구분되어 있습니다.
 *
 * 작성일 : 2023.07.06
 */
class P12939 {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        int[] arr = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);

        answer.append(arr[0]);
        answer.append(" ");
        answer.append(arr[arr.length - 1]);

        return answer.toString();
    }

    public static void main(String[] args) {
        P12939 problem = new P12939();
        System.out.println(problem.solution("-1 -1"));
    }
}

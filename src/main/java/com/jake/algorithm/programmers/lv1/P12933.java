package com.jake.algorithm.programmers.lv1;

import java.util.Arrays;
import java.util.Comparator;

/**
 * [ 정수 내림차순으로 배치하기 ]
 *
 * 함수 solution은 정수 n을 매개변수로 입력받습니다.
 * n의 각 자릿수를 큰것부터 작은 순으로 정렬한 새로운 정수를 리턴해주세요.
 * 예를들어 n이 118372면 873211을 리턴하면 됩니다.
 *
 * 제한 조건
 * n은 1이상 8000000000 이하인 자연수입니다.
 *
 * 작성일 : 2023.06.16
 */
class P12933 {
    public long solution(long n) {
        String[] temp = Long.toString(n).split("");
        Arrays.sort(temp, Comparator.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for (String b : temp) {
            sb.append(b);
        }
        return Long.parseLong(String.valueOf(sb));
    }

    // 다른 사람의 풀이 1
//    String res = "";
//    public int reverseInt(int n){
//        Integer.toString(n).chars().sorted().forEach(c -> res = Character.valueOf((char)c) + res);
//        return Integer.parseInt(res);
//    }

    // 다른 사람의 풀이 2
//    public long solution2(long n) {
//        return Long.parseLong(String.valueOf(n).chars().mapToObj(ch -> (char) ch)
//                .sorted(Comparator.reverseOrder())
//                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//                .toString());
//    }

    public static void main(String[] args) {
        P12933 problem = new P12933();
        System.out.println(problem.solution(118372));
    }
}

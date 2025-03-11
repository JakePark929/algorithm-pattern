package com.jake.algorithm.programmers.lv1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * [ 문자열 내림차순으로 배치하기 ]
 * <p>
 * 문자열 s에 나타나는 문자를 큰것부터 작은 순으로 정렬해 새로운 문자열을 리턴하는 함수, solution을 완성해주세요.
 * s는 영문 대소문자로만 구성되어 있으며, 대문자는 소문자보다 작은 것으로 간주합니다.
 * <p>
 * 제한 사항
 * str은 길이 1 이상인 문자열입니다.
 * <p>
 * 작성일 : 2023.06.15
 */
class P12917 {
    public String solution(String s) {
        return Arrays.stream(s.split("")).sorted(Comparator.reverseOrder()).collect(Collectors.joining());
    }

    // 다른 사람의 풀이 1
    public String reverseStr(String str) {
        char[] sol = str.toCharArray();
        Arrays.sort(sol);
        return new StringBuilder(new String(sol)).reverse().toString();
    }

    // 다른 사람의 풀이 2(하드 코딩)
    public String reverseStr2(String str) {
        char[] ch = str.toCharArray();
        String lower = "";
        String upper = "";

        for (int i = 0; i < ch.length; i++) {
            int chnum = ch[i];

            if (chnum >= 65 && chnum <= 90)
                upper += ch[i];
            else
                lower += ch[i];
        }

        char[] chUpper = upper.toCharArray();
        char[] chLower = lower.toCharArray();
        upper = "";
        lower = "";

        Arrays.sort(chUpper);
        Arrays.sort(chLower);

        for (int i = chUpper.length - 1; i >= 0; i--)
            upper += chUpper[i];
        for (int i = chLower.length - 1; i >= 0; i--)
            lower += chLower[i];

        return lower + upper;
    }

    public static void main(String[] args) {
        P12917 problem = new P12917();
        System.out.println(problem.solution("Zbcdefg"));
    }
}

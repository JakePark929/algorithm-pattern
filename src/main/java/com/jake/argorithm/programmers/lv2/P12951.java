package com.jake.argorithm.programmers.lv2;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * [ JadenCase 문자열 만들기 ]
 * <p>
 * JadenCase란 모든 단어의 첫 문자가 대문자이고,
 * 그 외의 알파벳은 소문자인 문자열입니다.
 * 단, 첫 문자가 알파벳이 아닐 때에는 이어지는 알파벳은 소문자로 쓰면 됩니다. (첫 번째 입출력 예 참고)
 * 문자열 s가 주어졌을 때, s를 JadenCase로 바꾼 문자열을 리턴하는 함수, solution을 완성해주세요.
 * <p>
 * 제한 조건
 * s는 길이 1 이상 200 이하인 문자열입니다.
 * s는 알파벳과 숫자, 공백문자(" ")로 이루어져 있습니다.
 * 숫자는 단어의 첫 문자로만 나옵니다.
 * 숫자로만 이루어진 단어는 없습니다.
 * 공백문자가 연속해서 나올 수 있습니다.
 * <p>
 * 작성일 : 2023.07.07
 */
class P12951 {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        char[] cs = s.toCharArray();

        StringBuilder sb = new StringBuilder();
        for (char c : cs) {
            if (Character.isLetter(c) || Character.isDigit(c)) {
                sb.append(Character.toLowerCase(c));
            } else {
                if (sb.length() > 0) {
                    sb.replace(0, 1, String.valueOf(Character.toUpperCase(sb.charAt(0))));
                    answer.append(sb);
                    answer.append(" ");
                    sb.delete(0, sb.length());
                } else {
                    answer.append(" ");
                }
            }
        }

        if (sb.length() > 0) {
            sb.replace(0, 1, String.valueOf(Character.toUpperCase(sb.charAt(0))));
            answer.append(sb);
        }

        return answer.toString();
    }

    // 다른 사람의 풀이 1
    public String solution1(String s) {
        StringBuilder answer = new StringBuilder();
        String[] sp = s.toLowerCase().split("");
        boolean flag = true;

        System.out.println(Arrays.toString(sp));

        for(String ss : sp) {
            answer.append(flag ? ss.toUpperCase() : ss);
            flag = ss.equals(" ");
        }

        return answer.toString();
    }

    // 다른 사람의 풀이 2
    public String solution2(String s) {
        Matcher m = Pattern.compile("\\b([\\w])([\\w]*)").matcher(s);
        while (m.find()) {
            s = s.replaceAll("\\b" + m.group(), m.group(1).toUpperCase() + m.group(2).toLowerCase());
        }
        return s;
    }

    public static void main(String[] args) {
        P12951 problem = new P12951();
        System.out.println(problem.solution1("3people unFollowed me    sdf  "));
    }
}

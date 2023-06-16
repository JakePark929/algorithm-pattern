package com.jake.argorithm.programmers.lv1;

import java.util.Arrays;

/**
 * [문자열내 p와 y의 개수]
 * 
 * 대문자와 소문자가 섞여있는 문자열 s가 주어집니다. s에 'p'의 개수와 'y'의 개수를 비교해 같으면 True, 다르면 False를 return 하는 solution를 완성하세요.
 * 'p', 'y' 모두 하나도 없는 경우는 항상 True를 리턴합니다. 단, 개수를 비교할 때 대문자와 소문자는 구별하지 않습니다.
 *
 * 예를 들어 s가 "pPoooyY"면 true를 return하고 "Pyy"라면 false를 return합니다.
 *
 * 작성일: 2023.06.02
 */
class P12916 {
    boolean solution(String s) {
        boolean answer = true;

        int countP = 0;
        int countY = 0;

        String[] arr = s.toLowerCase().split("");

        for (int i = 0; i < s.length(); i++) {
            if (arr[i].equals("p")) {
                countP++;
            }
            if (arr[i].equals("y")) {
                countY++;
            }
        }

        answer = countP == countY;

        return answer;
    }
    
    // 다른 사람의 풀이
    boolean anotherSolution(String s) {
        s = s.toUpperCase();

        return s.chars().filter( e -> 'P'== e).count() == s.chars().filter( e -> 'Y'== e).count();
    }

    boolean anotherSolution2(String s) {
        int count = 0;
        s = s.toLowerCase();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'p') count++;
            else if (s.charAt(i) == 'y') count--;
        }

        return count == 0;
    }

    public static void main(String[] args) {
        P12916 p = new P12916();
        System.out.println(p.solution("SDFsadf"));
    }
}

package com.jake.argorithm.programmers.lv1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * [ 문자열 내 마음대로 정렬하기 ]
 *
 * 문자열로 구성된 리스트 strings와, 정수 n이 주어졌을 때, 각 문자열의 인덱스 n번째 글자를 기준으로 오름차순 정렬하려 합니다.
 * 예를 들어 strings가 ["sun", "bed", "car"]이고 n이 1이면 각 단어의 인덱스 1의 문자 "u", "e", "a"로 strings를 정렬합니다.
 *
 * 제한 조건
 * strings는 길이 1 이상, 50이하인 배열입니다.
 * strings의 원소는 소문자 알파벳으로 이루어져 있습니다.
 * strings의 원소는 길이 1 이상, 100이하인 문자열입니다.
 * 모든 strings의 원소의 길이는 n보다 큽니다.
 * 인덱스 1의 문자가 같은 문자열이 여럿 일 경우, 사전순으로 앞선 문자열이 앞쪽에 위치합니다.
 *
 * 작성일 : 2023.06.15
 */
class P12915 {
    public String[] solution(String[] strings, int n) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList(strings));
        Collections.sort(list);
        list.sort(Comparator.comparing(e -> e.charAt(n)));

        return list.toArray(new String[0]);
    }

    // 다른 사람의 풀이 1
    public String[] solution2(String[] strings, int n) {
        Arrays.sort(strings);
        Arrays.sort(strings, Comparator.comparing((s) -> s.substring(n, n + 1)));
        return strings;
    }

    // 다른 사람의 풀이 2 (하드코딩)
    public String[] solution3(String[] strings, int n) {
        String[] answer = strings;

        for (int i = 0; i < answer.length - 1; i++) {
            String temp = "";
          /*
          for(int j= 0;j<answer.length;j++){
              System.out.println(answer[j]);
          }
          */
            for (int k = i + 1; k < answer.length; k++) {
                if (answer[i].charAt(n) > answer[k].charAt(n)) {
                    temp = answer[i];
                    answer[i] = answer[k];
                    answer[k] = temp;
                }
            }
        }
        for (int i = 0; i < answer.length - 1; i++) {
            String temp = "";
            for (int k = i + 1; k < answer.length; k++) {
                if (answer[i].charAt(n) == answer[k].charAt(n)) {
                    if (answer[i].compareTo(answer[k]) > 0) { // compareTo 활용
                        temp = answer[i];
                        answer[i] = answer[k];
                        answer[k] = temp;
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        P12915 problem = new P12915();
        System.out.println(Arrays.toString(problem.solution(new String[]{"abce", "abcd", "cdx"}, 1)));
    }
}

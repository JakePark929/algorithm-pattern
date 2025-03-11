package com.jake.algorithm.baekjoon.lv14_set_map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * [ 서로 다른 부분 문자열의 개수 ]
 * <p>
 * 문자열 S가 주어졌을 때, S의 서로 다른 부분 문자열의 개수를 구하는 프로그램을 작성하시오.
 * 부분 문자열은 S에서 연속된 일부분을 말하며, 길이가 1보다 크거나 같아야 한다.
 * 예를 들어,
 * ababc의 부분 문자열은 a, b, a, b, c, ab, ba, ab, bc, aba, bab, abc, abab, babc, ababc가 있고,
 * 서로 다른것의 개수는 12개이다.
 * <p>
 * 입력
 * 첫째 줄에 문자열 S가 주어진다.
 * S는 알파벳 소문자로만 이루어져 있고,
 * 길이는 1,000 이하이다.
 * <p>
 * 출력
 * 첫째 줄에 S의 서로 다른 부분 문자열의 개수를 출력한다.
 * <p>
 * 작성일 : 2023.08.07
 */
class B11478_NumberOfDifferentString {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String s = br.readLine();
//        HashSet<String> parts = new HashSet<>();
//
//        for (int i = 1; i <= s.length(); i++) {
//            for (int j = 0; j < s.length() - (i - 1); j++) {
//                parts.add(s.substring(j, j + i));
//            }
//        }
//
//        System.out.println(parts.size());
//    }

    // 다른 사람의 풀이 1 - 접미사 배열, LCP 알고리즘 참고
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String[] str = new String[s.length()];

        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            str[i] = s.substring(i); //i번째 후로 자르기
        }

        Arrays.sort(str);

        answer += str[0].length(); //0번째는 비교할게 없으므로 ++

        System.out.println(Arrays.toString(str));

        for (int i = 1; i < str.length; i++) {
            answer += (str[i].length() - getCommon(str[i - 1], str[i]));
        }

        System.out.print(answer);
    }

    public static int getCommon(String s1, String s2) {
        int result = 0;
        System.out.println(s1 + ":" + s2);

        for (int i = 0; i < s1.length(); i++) {
            if (i >= s2.length()) {
                break;
            }
            if (s1.charAt(i) == s2.charAt(i)) {
                result++;
            } else {
                break;
            }
        }
        return result;
    } //중복되는 부분 count
}

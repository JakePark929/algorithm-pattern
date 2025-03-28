package com.jake.algorithm.baekjoon.lv13_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

/**
 * [ 단어 정렬 ]
 *
 * 알파벳 소문자로 이루어진 N개의 단어가 들어오면 아래와 같은 조건에 따라 정렬하는 프로그램을 작성하시오.
 *
 * 길이가 짧은 것부터
 * 길이가 같으면 사전 순으로
 * 단, 중복된 단어는 하나만 남기고 제거해야 한다.
 *
 * 입력
 * 첫째 줄에 단어의 개수 N이 주어진다.
 * (1 ≤ N ≤ 20,000) 둘째 줄부터 N개의 줄에 걸쳐 알파벳 소문자로 이루어진 단어가 한 줄에 하나씩 주어진다.
 * 주어지는 문자열의 길이는 50을 넘지 않는다.
 *
 * 출력
 * 조건에 따라 정렬하여 단어들을 출력한다.
 *
 * 작성일 : 2023.08.04
 */
class B1181_WordSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashSet<String> wordSet = new HashSet<>();

        for (int i = 0; i < n; i++) {
            wordSet.add(br.readLine());
        }

        String[] words = wordSet.toArray(new String[0]);

        Arrays.sort(words, (o1, o2) -> {
            if(o1.length() == o2.length()) {
                return o1.compareTo(o2);
            } else {
                return o1.length() - o2.length();
            }
        });

        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word).append('\n');
        }

        System.out.println(sb.toString().trim());
    }
}

package com.jake.argorithm.baekjoon.lv06_advanced_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [ 단어 공부 ]
 * <p>
 * 알파벳 대소문자로 된 단어가 주어지면,
 * 이 단어에서 가장 많이 사용된 알파벳이 무엇인지 알아내는 프로그램을 작성하시오.
 * 단, 대문자와 소문자를 구분하지 않는다.
 * <p>
 * 입력
 * 첫째 줄에 알파벳 대소문자로 이루어진 단어가 주어진다.
 * 주어지는 단어의 길이는 1,000,000을 넘지 않는다.
 * <p>
 * 출력
 * 첫째 줄에 이 단어에서 가장 많이 사용된 알파벳을 대문자로 출력한다.
 * 단, 가장 많이 사용된 알파벳이 여러 개 존재하는 경우에는 ?를 출력한다.
 * <p>
 * 작성일 : 2023.07.24
 */
class B1157_StudyWord {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] cs = br.readLine().toUpperCase().toCharArray();

        HashMap<String, Integer> hm = new HashMap<>();

        for (char c : cs) {
            hm.putIfAbsent(String.valueOf(c), 1);

            if (hm.get(String.valueOf(c)) != null) {
                hm.put(String.valueOf(c), hm.get(String.valueOf(c)) + 1);
            }
        }

        int maxValue = Integer.MIN_VALUE;
        List<String> keysWithMaxValue = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : hm.entrySet()) {
            int value = entry.getValue();
            if (value > maxValue) {
                keysWithMaxValue.clear();
                keysWithMaxValue.add(entry.getKey());
                maxValue = value;
            } else if (value == maxValue) {
                keysWithMaxValue.add(entry.getKey());
            }
        }

        if (keysWithMaxValue.size() > 1) {
            System.out.println("?");
        } else {
            System.out.println(keysWithMaxValue.get(0));
        }
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        // a : 97 -> 1100001(2), z : 123 -> 1111010(2) , A : 65 -> 1000001(2), Z : 90 -> 1011010(2)
        byte[] buf = new byte[1000001];
        InputStream is = System.in;
        is.read(buf);
        int[] cnt = new int[27]; //1 : a , 27 : z
        for (int i = 0; buf[i] > (byte) 64; i++) {
            cnt[buf[i] & 31]++;
        }
        int max_idx = 0, same_idx = 0;
        for (int j = 1; j < cnt.length; j++) {
            if (cnt[j] > cnt[max_idx]) {
                max_idx = j;
            } else if (cnt[j] == cnt[max_idx]) {
                same_idx = j;
            }
        }
        if (cnt[max_idx] == cnt[same_idx]) {
            System.out.println('?');
        } else {
            System.out.println((char) (max_idx + 64));
        }
    }

    // 다른 사람의 풀이 2
    public static void main2(String[] args) throws IOException {

        int[] arr = new int[26]; // 영문자의 개수는 26개임

        int c = System.in.read();

        while (c > 64) {    // 공백을 입력받는 순간 종료됨

            if (c < 91) {
                arr[c - 65]++;
            } else {
                arr[c - 97]++;
            }
            c = System.in.read();
        }


        int max = -1;
        int ch = -2;    // ? 는 63 이다.

        for (int i = 0; i < 26; i++) {

            if (arr[i] > max) {
                max = arr[i];
                ch = i;
            } else if (arr[i] == max) {
                ch = -2;
            }
        }
        System.out.print((char) (ch + 65));
    }
}

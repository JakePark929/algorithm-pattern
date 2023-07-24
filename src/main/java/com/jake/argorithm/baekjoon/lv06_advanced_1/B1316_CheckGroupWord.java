package com.jake.argorithm.baekjoon.lv06_advanced_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * [ 그룹 단어 체커 ]
 * <p>
 * 그룹 단어란 단어에 존재하는 모든 문자에 대해서,
 * 각 문자가 연속해서 나타나는 경우만을 말한다.
 * <p>
 * 예를 들면,
 * ccazzzzbb는 c, a, z, b가 모두 연속해서 나타나고,
 * kin도 k, i, n이 연속해서 나타나기 때문에 그룹 단어이지만,
 * aabbbccb는 b가 떨어져서 나타나기 때문에 그룹 단어가 아니다.
 * <p>
 * 단어 N개를 입력으로 받아 그룹 단어의 개수를 출력하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 단어의 개수 N이 들어온다.
 * N은 100보다 작거나 같은 자연수이다.
 * 둘째 줄부터 N개의 줄에 단어가 들어온다.
 * 단어는 알파벳 소문자로만 되어있고 중복되지 않으며, 길이는 최대 100이다.
 * <p>
 * 출력
 * 첫째 줄에 그룹 단어의 개수를 출력한다.
 * <p>
 * 작성일 : 2023.07.24
 */
class B1316_CheckGroupWord {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int count = 0;

        for (int i = 0; i < n; i++) {
            char[] cs = br.readLine().toCharArray();

            LinkedList<Character> ar = new LinkedList<>();

            for (char c : cs) {
                if (ar.size() == 0) {
                    ar.add(c);
                } else if (ar.getLast() != c) {
                    ar.add(c);
                }
            }

            HashMap<Character, Integer> hm = new HashMap<>();

            for (char c : ar) {
                if (hm.get(c) == null) {
                    hm.put(c, 1);
                } else {
                    count++;
                    break;
                }
            }
        }

        System.out.println(n - count);
    }

    // 다른 사람의 풀이 1
    static BufferedReader in = new BufferedReader((new InputStreamReader(System.in)));

    public static void main1(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (check()) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static boolean check() throws IOException {
        boolean[] check = new boolean[26];
        int prev = 0;

        String s = in.readLine();

        for (int j = 0; j < s.length(); j++) {
            int now = s.charAt(j);
            if (now != prev) {
                if (!check[now - 'a']) {
                    check[now - 'a'] = true;
                    prev = now;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}

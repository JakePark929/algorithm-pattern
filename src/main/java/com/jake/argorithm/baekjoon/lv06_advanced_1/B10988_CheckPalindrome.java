package com.jake.argorithm.baekjoon.lv06_advanced_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ 팰린드롬인지 확인하기 ]
 *
 * 알파벳 소문자로만 이루어진 단어가 주어진다.
 * 이때, 이 단어가 팰린드롬인지 아닌지 확인하는 프로그램을 작성하시오.
 *
 * 팰린드롬이란 앞으로 읽을 때와 거꾸로 읽을 때 똑같은 단어를 말한다.
 *
 * level, noon은 팰린드롬이고, baekjoon, online, judge는 팰린드롬이 아니다.
 *
 * 입력
 * 첫째 줄에 단어가 주어진다.
 * 단어의 길이는 1보다 크거나 같고, 100보다 작거나 같으며, 알파벳 소문자로만 이루어져 있다.
 *
 * 출력
 * 첫째 줄에 팰린드롬이면 1, 아니면 0을 출력한다.
 *
 * 작성일 : 2023.07.24
 */
class B10988_CheckPalindrome {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] cs = br.readLine().toCharArray();

        boolean isEqual = false;
        for(int i = 0; i < cs.length; i++) {
            if (cs[i] != cs[cs.length - 1 - i]) {
                isEqual = true;
                break;
            }
        }

        System.out.println(isEqual ? "0" : "1");
    }

    // 다른 사람의 풀이 1
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main1(String[] args) throws IOException {

        String str = br.readLine();
        boolean flag = true;

        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) flag = false;
        }

        System.out.println(flag ? "1" : "0");

        br.close();
    }
}

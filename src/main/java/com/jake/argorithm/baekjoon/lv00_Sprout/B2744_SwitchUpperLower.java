package com.jake.argorithm.baekjoon.lv00_Sprout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ 대소문자 바꾸기 ]
 *
 * 영어 소문자와 대문자로 이루어진 단어를 입력받은 뒤,
 * 대문자는 소문자로, 소문자는 대문자로 바꾸어 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 영어 소문자와 대문자로만 이루어진 단어가 주어진다. 단어의 길이는 최대 100이다.
 *
 * 출력
 * 첫째 줄에 입력으로 주어진 단어에서 대문자는 소문자로, 소문자는 대문자로 바꾼 단어를 출력한다.
 *
 * 작성일 : 2023.07.27
 */
class B2744_SwitchUpperLower {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] cs = br.readLine().toCharArray();

        for(int i = 0; i < cs.length; i++) {
            if(Character.isUpperCase(cs[i])) cs[i] = Character.toLowerCase(cs[i]);
            else cs[i] = Character.toUpperCase(cs[i]);
        }

        StringBuilder sb = new StringBuilder();

        for(char c : cs) {
            sb.append(c);
        }

        System.out.println(sb);
    }
}

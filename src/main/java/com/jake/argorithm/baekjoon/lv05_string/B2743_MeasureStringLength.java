package com.jake.argorithm.baekjoon.lv05_string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ 단어 길이 재기 ]
 *
 * 알파벳으로만 이루어진 단어를 입력받아, 그 길이를 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 영어 소문자와 대문자로만 이루어진 단어가 주어진다. 단어의 길이는 최대 100이다.
 *
 * 출력
 * 첫째 줄에 입력으로 주어진 단어의 길이를 출력한다.
 *
 * 작성일 : 2023.07.20
 */
class B2743_MeasureStringLength {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        System.out.println(s.length());
    }
}

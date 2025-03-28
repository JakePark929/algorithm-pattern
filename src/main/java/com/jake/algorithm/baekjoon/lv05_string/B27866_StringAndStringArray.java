package com.jake.algorithm.baekjoon.lv05_string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ 문자열 ]
 *
 * 단어 $S$와 정수 $i$가 주어졌을 때,
 * $S$의 $i$번째 글자를 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 영어 소문자와 대문자로만 이루어진 단어 $S$가 주어진다. 단어의 길이는 최대 $1\,000$이다.
 *둘째 줄에 정수 $i$가 주어진다. ($1 \le i \le \left|S\right|$)
 *
 * 출력
 * $S$의$i$번째 글자를 출력한다.
 *
 * 작성일 : 2023.07.20
 */
class B27866_StringAndStringArray {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int index = Integer.parseInt(br.readLine());

        System.out.println(input.toCharArray()[index - 1]);
    }
}

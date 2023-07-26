package com.jake.argorithm.baekjoon.lv08_math_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 진법 변환 ]
 *
 * B진법 수 N이 주어진다.
 * 이 수를 10진법으로 바꿔 출력하는 프로그램을 작성하시오.
 *
 * 10진법을 넘어가는 진법은 숫자로 표시할 수 없는 자리가 있다.
 * 이런 경우에는 다음과 같이 알파벳 대문자를 사용한다.
 *
 * A: 10, B: 11, ..., F: 15, ..., Y: 34, Z: 35
 *
 * 입력
 * 첫째 줄에 N과 B가 주어진다. (2 ≤ B ≤ 36)
 *
 * B진법 수 N을 10진법으로 바꾸면, 항상 10억보다 작거나 같다.
 *
 * 출력
 * 첫째 줄에 B진법 수 N을 10진법으로 출력한다.
 *
 * 작성일 : 2023.07.25
 */
class B2745_SwitchBinaries {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ss = br.readLine();
        String s = ss.substring(0, ss.indexOf(" "));
        int bin = Integer.parseInt(ss.substring(ss.indexOf(" ") + 1));

        System.out.println(Integer.parseInt(s, bin));
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String N = st.nextToken();
        int B = Integer.parseInt(st.nextToken());

        int value = 0;
        int k = 1;
        for (int i = 1; i < N.length(); i++) {
            k *= B;
        }

        for (int i = 0; i < N.length(); i++) {
            if (N.charAt(i) >= '0' && N.charAt(i) <= '9') {
                value = value + k * (N.charAt(i) - '0');
            } else if (N.charAt(i) >= 'A' && N.charAt(i) <= 'Z') {
                value = value + k * (N.charAt(i) - 55);
            }
            k /= B;
        }

        System.out.println(value);
    }
}

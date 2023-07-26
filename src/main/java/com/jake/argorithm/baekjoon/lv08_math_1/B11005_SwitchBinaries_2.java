package com.jake.argorithm.baekjoon.lv08_math_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 진법 변환 2 ]
 * <p>
 * 10진법 수 N이 주어진다.
 * 이 수를 B진법으로 바꿔 출력하는 프로그램을 작성하시오.
 * <p>
 * 10진법을 넘어가는 진법은 숫자로 표시할 수 없는 자리가 있다.
 * 이런 경우에는 다음과 같이 알파벳 대문자를 사용한다.
 * <p>
 * A: 10, B: 11, ..., F: 15, ..., Y: 34, Z: 35
 * <p>
 * 입력
 * 첫째 줄에 N과 B가 주어진다. (2 ≤ B ≤ 36) N은 10억보다 작거나 같은 자연수이다.
 * <p>
 * 출력
 * 첫째 줄에 10진법 수 N을 B진법으로 출력한다.
 * <p>
 * 작성일 : 2023.07.25
 */
class B11005_SwitchBinaries_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ss = br.readLine();
        int num = Integer.parseInt(ss.substring(0, ss.indexOf(" ")));
        int bin = Integer.parseInt(ss.substring(ss.indexOf(" ") + 1));

        System.out.println(Integer.toString(num, bin).toUpperCase());
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        while (true) {
            if (n % b < 10) sb.append(n % b);
            else sb.append((char) (n % b - 10 + 'A'));

            if (n / b == 0) break;
            n = n / b;
        }

        System.out.println(sb.reverse());
    }
}

package com.jake.argorithm.baekjoon.lv03_loop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ A + B - 4 ]
 * <p>
 * 두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 입력은 여러 개의 테스트 케이스로 이루어져 있다.
 * <p>
 * 각 테스트 케이스는 한 줄로 이루어져 있으며, 각 줄에 A와 B가 주어진다. (0 < A, B < 10)
 * <p>
 * 출력
 * 각 테스트 케이스마다 A+B를 출력한다.
 * <p>
 * 작성일 : 2023.07.17
 */
public class P10951_APlusB_4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;
        int a, b;
        while ((line = br.readLine()) != null) {
            if (line.length() == 0) break;
            a = line.charAt(0) - '0';
            b = line.charAt(2) - '0';
            sb.append(a + b).append("\n");
        }
        System.out.print(sb);
    }

    // 다른 사람의 풀이 1
    public static void main1(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str;

        while ((str = br.readLine()) != null) {
            int a = str.charAt(0) - 48;
            int b = str.charAt(2) - 48;
            sb.append(a + b).append("\n");
        }

        System.out.print(sb);
    }
}

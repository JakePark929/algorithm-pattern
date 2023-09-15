package com.jake.argorithm.baekjoon.lv24_devide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 곱셈 ]
 *
 * 자연수 A를 B번 곱한 수를 알고 싶다.
 * 단 구하려는 수가 매우 커질 수 있으므로 이를 C로 나눈 나머지를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 A, B, C가 빈 칸을 사이에 두고 순서대로 주어진다.
 * A, B, C는 모두 2,147,483,647 이하의 자연수이다.
 *
 * 출력
 * 첫째 줄에 A를 B번 곱한 수를 C로 나눈 나머지를 출력한다.
 *
 * 작성일 : 2023.09.13
 */
class B1629_Multiply {
    private static long c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());

        System.out.println(pow(a, b));
    }

    private static long pow(long a, long exponent) {
        // 지수가 1일 경우 a^1 이므로 a를 그대로 리턴
        if (exponent == 1) {
            return a % c;
        }

        // 지수의 절반에 해당하는 a^(exponent / 2)를 구함
        long temp = pow(a, exponent / 2);

        /*
         * 현재 지수가 홀수 였다면
         * a^(exponent / 2) * a^(exponent / 2) * a 이므로
         * a 를 한번더 곱해주어햐 한다.
         */
        if (exponent % 2 == 1) {
            return (temp * temp % c) * a % c;
        }

        return temp * temp % c;
    }
}

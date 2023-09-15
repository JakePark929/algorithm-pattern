package com.jake.argorithm.baekjoon.lv24_devide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 이항계수 2 ]
 *
 * 자연수 \(N\)과 정수 \(K\)가 주어졌을 때
 * 이항 계수 \(\binom{N}{K}\)를 10,007로 나눈 나머지를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 \(N\)과 \(K\)가 주어진다.
 * (1 ≤ \(N\) ≤ 1,000, 0 ≤ \(K\) ≤ \(N\))
 *
 * 출력
 * \(\binom{N}{K}\)를 10,007로 나눈 나머지를 출력한다.
 *
 * 작성일 : 2023.09.14
 */
class B11051_BinomialCoefficient_2 {
    private static final int div = 10007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        System.out.println((factorial(n) * modInverse(factorial(n - k) * factorial(k) % div, div - 2)) % div);
    }

    private static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }

        return (n * factorial(n - 1)) % div;
    }

    private static int modInverse(int a, int p) {
        int ret = 1;
        while (p > 0) {
            if (p % 2 == 1) {
                ret *= a;
                p--;
                ret %= div;
            }
            a *= a;
            a %= div;
            p >>= 1;
        }

        return ret;
    }

    // 다른 풀이 1
    static int[][] dp;
    public static final int divs = 10007;

    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][K + 1];

        System.out.println(BinomialCoefficient(N, K));
    }

    static int BinomialCoefficient(int n, int k) {
        // 이미 풀었던 sub 문제일 경우 값을 재활용
        if (dp[n][k] > 0) {
            return dp[n][k];
        }

        if (k == 0 || n == k) {
            return dp[n][k] = 1;
        }

        return dp[n][k] = (BinomialCoefficient(n - 1, k - 1) + BinomialCoefficient(n - 1, k)) % divs;
    }
}

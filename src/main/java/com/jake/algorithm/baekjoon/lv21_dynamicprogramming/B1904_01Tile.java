package com.jake.algorithm.baekjoon.lv21_dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ 01 타일 ]
 *
 * 지원이에게 2진 수열을 가르쳐 주기 위해, 지원이 아버지는 그에게 타일들을 선물해주셨다.
 * 그리고 이 각각의 타일들은 0 또는 1이 쓰여 있는 낱장의 타일들이다.
 *
 * 어느 날 짓궂은 동주가 지원이의 공부를 방해하기 위해
 * 0이 쓰여진 낱장의 타일들을 붙여서 한 쌍으로 이루어진 00 타일들을 만들었다.
 * 결국 현재 1 하나만으로 이루어진 타일 또는 0타일을 두 개 붙인 한 쌍의 00타일들만이 남게 되었다.
 *
 * 그러므로 지원이는 타일로 더 이상 크기가 N인 모든 2진 수열을 만들 수 없게 되었다.
 * 예를 들어, N=1일 때 1만 만들 수 있고, N=2일 때는 00, 11을 만들 수 있다. (01, 10은 만들 수 없게 되었다.)
 * 또한 N=4일 때는 0011, 0000, 1001, 1100, 1111 등 총 5개의 2진 수열을 만들 수 있다.
 *
 * 우리의 목표는 N이 주어졌을 때 지원이가 만들 수 있는 모든 가짓수를 세는 것이다.
 * 단 타일들은 무한히 많은 것으로 가정하자.
 *
 * 입력
 * 첫 번째 줄에 자연수 N이 주어진다. (1 ≤ N ≤ 1,000,000)
 *
 * 출력
 * 첫 번째 줄에 지원이가 만들 수 있는 길이가 N인 모든 2진 수열의 개수를 15746으로 나눈 나머지를 출력한다.
 *
 * 작성일 : 2023.09.05
 */
class B1904_01Tile {
    private static final int[] dp = new int[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < dp.length; i++) {
            dp[i] = -1;
        }

        System.out.println(Tile(n));
    }

    public static int Tile(int n) {
        if (dp[n] == -1) {
            dp[n] = (Tile(n - 1) + Tile(n - 2)) % 15746;
        }

        return dp[n];
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        System.out.println(Tile1(N));

    }

    private static int Tile1(int a) {
        if (a == 1) {
            return 1;
        }

        if (a == 2) {
            return 2;
        }

        // 초기 값
        int val1 = 1;
        int val2 = 2;
        int sum = 0;

        for (int i = 2; i < a; i++) {
            sum = (val2 + val1) % 15746;	// 이전 값과 이전의 이전 값의 합
            val1 = val2;	// 이전의 이전 값은 이전 값으로 변경
            val2 = sum;		// 이전 값은 현재 합 값으로 변경
        }

        return sum;
    }
}

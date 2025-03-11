package com.jake.algorithm.baekjoon.lv21_dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ RGB 거리 ]
 *
 * RGB 거리에는 집이 N개 있다.
 * 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있다.
 *
 * 집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다.
 * 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때,
 * 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.
 *
 * 1번 집의 색은 2번 집의 색과 같지 않아야 한다.
 * N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
 * i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
 *
 * 입력
 * 첫째 줄에 집의 수 N(2 ≤ N ≤ 1,000)이 주어진다.
 * 둘째 줄부터 N개의 줄에는 각 집을 빨강, 초록, 파랑으로 칠하는 비용이 1번 집부터 한 줄에 하나씩 주어진다.
 * 집을 칠하는 비용은 1,000보다 작거나 같은 자연수이다.
 *
 * 출력
 * 첫째 줄에 모든 집을 칠하는 비용의 최솟값을 출력한다.
 *
 * 작성일 : 2023.09.05
 */
class B1149_RGBDistance {
    private final static int RED = 0;
    private final static int GREEN = 1;
    private final static int BLUE = 2;

    private static int[][] cost;
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        int n = read();

        cost = new int[n][3];
        dp = new int[n][3];

        for (int i = 0; i < n; i++) {
            cost[i][RED] = read();
            cost[i][GREEN] = read();
            cost[i][BLUE] = read();
        }

        // dp 첫번째 값(집)은 각 색상 비용의 첫번째 값으로 초기화
        dp[0][RED] = cost[0][RED];
        dp[0][GREEN] = cost[0][GREEN];
        dp[0][BLUE] = cost[0][BLUE];

        System.out.println(Math.min(paintCost(n - 1, RED), Math.min(paintCost(n - 1, GREEN), paintCost(n - 1, BLUE))));
    }

    private static int paintCost(int n, int color) {
        // 탐색하지 않은 배열이면?
        if (dp[n][color] == 0) {
            // color 의 색에 따라 이전 집의 서로 다른 색을 재귀호출 하여 최솟값과 현재 집 비용의 값을 더해 dp 에 저장
            if (color == RED) {
                dp[n][RED] = Math.min(paintCost(n - 1, GREEN), paintCost(n - 1, BLUE)) + cost[n][RED];
            } else if (color == GREEN) {
                dp[n][GREEN] = Math.min(paintCost(n - 1, RED), paintCost(n - 1, BLUE)) + cost[n][GREEN];
            } else {
                dp[n][BLUE] = Math.min(paintCost(n - 1, RED), paintCost(n - 1, GREEN)) + cost[n][BLUE];
            }
        }

        return dp[n][color];
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    // 다른 사람의 풀이 1
    final static int Red = 0;
    final static int Green = 1;
    final static int Blue = 2;

    public static void main1(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] Cost = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            Cost[i][Red] = Integer.parseInt(st.nextToken());
            Cost[i][Green] = Integer.parseInt(st.nextToken());
            Cost[i][Blue] = Integer.parseInt(st.nextToken());

        }

        // 1부터 N-1까지 각 i별 i-1의 서로 다른 색상 중 최솟값을 누적하여 더한다.
        for (int i = 1; i < N; i++) {
            Cost[i][Red] += Math.min(Cost[i - 1][Green], Cost[i - 1][Blue]);
            Cost[i][Green] += Math.min(Cost[i - 1][Red], Cost[i - 1][Blue]);
            Cost[i][Blue] += Math.min(Cost[i - 1][Red], Cost[i - 1][Green]);
        }

        System.out.println(Math.min(Math.min(Cost[N - 1][Red], Cost[N - 1][Green]), Cost[N - 1][Blue]));
    }

    // 다른 사람의 풀이 2
    private static int read1() throws Exception {
        int c, N = System.in.read() - 48;
        while ((c = System.in.read()) > 32) N = 10 * N + c - 48;
        return N;
    }

    public static void main2(String[] args) throws Exception {
        int  n = read1();

        int r = read1();
        int g = read1();
        int b = read1();

        for (int i = 2; i <= n; i++) {
            int r1 = Math.min(g, b);
            int g1 = Math.min(r, b);
            int b1 =  Math.min(r, g);

            r =  r1 + read1();
            g =  g1 + read1();
            b =  b1 + read1();
        }

        int min = Math.min(r, Math.min(g, b));

        System.out.println(min);
    }
}

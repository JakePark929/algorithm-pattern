package com.jake.argorithm.baekjoon.lv21_dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [ 평범한 배낭 ]
 *
 * 이 문제는 아주 평범한 배낭에 관한 문제이다.
 * 한 달 후면 국가의 부름을 받게 되는 준서는 여행을 가려고 한다.
 * 세상과의 단절을 슬퍼하며 최대한 즐기기 위한 여행이기 때문에, 가지고 다닐 배낭 또한 최대한 가치 있게 싸려고 한다.
 *
 * 준서가 여행에 필요하다고 생각하는 N개의 물건이 있다.
 * 각 물건은 무게 W와 가치 V를 가지는데, 해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수 있다.
 * 아직 행군을 해본 적이 없는 준서는 최대 K 만큼의 무게만을 넣을 수 있는 배낭만 들고 다닐 수 있다.
 * 준서가 최대한 즐거운 여행을 하기 위해 배낭에 넣을 수 있는 물건들의 가치의 최댓값을 알려주자.
 *
 * 입력
 * 첫 줄에 물품의 수 N(1 ≤ N ≤ 100)과 준서가 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)가 주어진다.
 * 두 번째 줄부터 N개의 줄에 거쳐 각 물건의 무게 W(1 ≤ W ≤ 100,000)와 해당 물건의 가치 V(0 ≤ V ≤ 1,000)가 주어진다.
 * 입력으로 주어지는 모든 수는 정수이다.
 *
 * 출력
 * 한 줄에 배낭에 넣을 수 있는 물건들의 가치합의 최댓값을 출력한다.
 *
 * 작성일 : 2023.09.07
 */
class B12865_NormalBackpack {
    private static Integer[][] dp;
    private static int[] w; // weight
    private static int[] v; // value

    public static void main(String[] args) throws Exception {
        int n = read();
        int k = read();

        w = new int[n];
        v = new int[n];

        dp = new Integer[n][k + 1];

        for (int i = 0; i < n; i++) {
            w[i] = read();
            v[i] = read();
        }

        System.out.println(knapsack(n - 1, k));
        System.out.println(Arrays.deepToString(dp));
    }

    private static int knapsack(int i, int k) {
        // i 가 0 미만, 즉 범위 밖이 된다면
        if (i < 0) return 0;

        // 탐색하지 않은 위치라면?
        if (dp[i][k] == null) {
            // 현재 물건(i)을 추가로 못 담는 경우(이전 i값 탐색)
            if (w[i] > k) {
                dp[i][k] = knapsack(i - 1, k);
            } else {
                // 이전 i 값과 이전 i 값에 대한 k - w[i] 의 값 + 현재 가치(V[i]) 중 큰 값을 저장
                dp[i][k] = Math.max(knapsack(i - 1, k), knapsack(i - 1, k - w[i]) + v[i]);
            }
        }

        return dp[i][k];
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");


        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] W = new int[N + 1]; // 무게
        int[] V = new int[N + 1]; // 가치
        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {

                // i번째 무게를 더 담을 수 없는 경우
                if(W[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                }
                // i번째 무게를 더 담을 수 있는 경우
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - W[i]] + V[i]);
                }

            }
        }
        System.out.println(dp[N][K]);
    }

    // 다른 사람의 풀이 2
    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] W = new int[N + 1]; // 무게
        int[] V = new int[N + 1]; // 가치
        int[] dp = new int[K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            // K부터 탐색하여 담을 수 있는 무게 한계치가 넘지 않을 때까지 반복
            for (int j = K; j - W[i] >= 0; j--) {
                dp[j] = Math.max(dp[j], dp[j - W[i]] + V[i]);
            }
        }
        System.out.println(dp[K]);
    }

    // 다른 사람의 풀이 3
    public static void main3(String[] args) throws Exception {
        int N = read3(), K = read3();
        int[] DP = new int[K + 1];

        for (int i = 0; i < N; i++) {
            int W = read3(), V = read3();
            for (int j = K; j >= W; j--)
                if (DP[j] < DP[j - W] + V) DP[j] = DP[j - W] + V;
        }

        System.out.print(DP[K]);

    }

    private static int read3() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;

    }
}

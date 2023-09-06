package com.jake.argorithm.baekjoon.lv21_dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * [ 쉬운 계단 수 ]
 * <p>
 * 45656이란 수를 보자.
 * 이 수는 인접한 모든 자리의 차이가 1이다.
 * 이런 수를 계단 수라고 한다.
 * <p>
 * N이 주어질 때, 길이가 N인 계단 수가 총 몇 개 있는지 구해보자.
 * 0으로 시작하는 수는 계단수가 아니다.
 * <p>
 * 입력
 * 첫째 줄에 N이 주어진다. N은 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.
 * <p>
 * 출력
 * 첫째 줄에 정답을 1,000,000,000으로 나눈 나머지를 출력한다.
 * <p>
 * 작성일 : 2023.09.06
 */
class B10844_EasyStairsCount {
//    private static Long[][] dp;
//    private final static long MOD = 1000000000;
//
//    public static void main(String[] args) throws Exception {
//        int n = read();
//
//        dp = new Long[n + 1][10];
//
//        // 첫번째 자릿수 1로 초기화
//        for (int i = 0; i < 10; i++) {
//            dp[1][i] = 1L;
//        }
//
//        long result = 0;
//
//        // 마지막 자릿수 1 ~ 9까지의 경우의 수를 모두 더해 줌
//        for (int i = 1; i <= 9; i++) {
//            result += recursive(n, i);
//        }
//
//        System.out.println(result % MOD);
//    }
//
//    /*
//     * digit 은 자릿수, val 은 자릿값을 의미함
//     * 첫째 자리수는 각 val 이 끝이기 때문에
//     * 경우의 수는 1 밖에 없다.
//     * 즉 dp[1]의 각 자릿값은 1로 초기화 되어 있어야 한다.
//     */
//    private static long recursive(int digit, int val) {
//        // 첫째 자리수에 도착한다면 더 이상 탐색할 필요 없음
//        if (digit == 1) {
//            return dp[digit][val];
//        }
//
//        // 해당 자리수의 val 값에 대해 탐색하지 않았을 경우
//        if (dp[digit][val] == null) {
//            // val 이 0 일 경우 이전 자리는 1 밖에 못옴
//            if (val == 0) {
//                dp[digit][val] = recursive(digit - 1, 1);
//            } else if (val == 9) {
//                dp[digit][val] = recursive(digit - 1, 8);
//            } else {
//                dp[digit][val] = recursive(digit - 1, val - 1) + recursive(digit - 1, val + 1);
//            }
//        }
//
//        return dp[digit][val] % MOD;
//    }
//
//    private static int read() throws Exception {
//        int c, n = System.in.read() & 15;
//        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
//        if (c == 13) System.in.read();
//        return n;
//    }

    // 다른 사람의 풀이 1
    final static long mod = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N + 1][10];

        // 첫 번째 자릿수는 오른쪽 맨 끝의 자릿수이므로 경우의 수가 1개밖에 없음
        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }

        // 두 번째 자릿수부터 N까지 탐색
        for (int i = 2; i <= N; i++) {
            // i번째 자릿수의 자릿값들을 탐색 (0~9)
            for (int j = 0; j < 10; j++) {
                // j=0, 즉 자릿값이 0이라면 이전 자릿수의 첫번째 자릿수만 가능
                if (j == 0) {
                    dp[i][0] = dp[i - 1][1] % mod;
                }
                // j=9라면 이전 자릿수는 8만 가능
                else if (j == 9) {
                    dp[i][9] = dp[i - 1][8] % mod;
                }
                // 그 외의 경우 이전 자릿수의 자릿값 +1, -1 의 합이 됨
                else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod;
                }
            }
        }

        long result = 0;

        // 각 자릿값마다의 경우의 수를 모두 더해준다.
        for (int i = 0; i < 10; i++) {
            result += dp[N][i];
        }

        System.out.println(Arrays.deepToString(dp));
        System.out.println(result % mod);
    }
}

package com.jake.argorithm.baekjoon.lv21_dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ LCS ]
 * <p>
 * LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때,
 * 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.
 * <p>
 * 예를 들어, ACAYKP 와 CAPCAK 의 LCS 는 ACAK 가 된다.
 * <p>
 * 입력
 * 첫째 줄과 둘째 줄에 두 문자열이 주어진다.
 * 문자열은 알파벳 대문자로만 이루어져 있으며, 최대 1000글자로 이루어져 있다.
 * <p>
 * 출력
 * 첫째 줄에 입력으로 주어진 두 문자열의 LCS 의 길이를 출력한다.
 * <p>
 * 작성일 : 2023.09.07
 */
class B9251_LongestCommonSubsequence {
    private static char[] str1;
    private static char[] str2;

    private static Integer[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str1 = br.readLine().toCharArray();
        str2 = br.readLine().toCharArray();

        dp = new Integer[str1.length][str2.length];

        System.out.println(lcs(str1.length - 1, str2.length - 1));
    }

    private static int lcs(int x, int y) {
        // 인덱스 밖일 경우 0 반환
        if (x == -1 || y == -1) {
            return 0;
        }

        // 만약 탐색하지 않은 인덱스라면?
        if (dp[x][y] == null) {
            dp[x][y] = 0;

            // st1 의 x 번째 문자와 str2 의  y 번째 문자가 같은지 검사
            if (str1[x] == str2[y]) {
                dp[x][y] = lcs(x - 1, y - 1) + 1;
            } else {
                // 같지 않다면 lcs(dp)[x - 1][y]와 lcs(dp)[x][y - 1] 중 큰 값으로 초기화
                dp[x][y] = Math.max(lcs(x - 1, y), lcs(x, y - 1));
            }
        }

        return dp[x][y];
    }

    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 다른 사람의 풀이 1
        char[] st1 = br.readLine().toCharArray();
        char[] st2 = br.readLine().toCharArray();

        int[] memo = new int[st1.length];

        for (int j = 0; j < st2.length; j++) {
            int most = 0;
            for (int i = 0; i < st1.length; i++) {
                if (most < memo[i]) {
                    most = memo[i];
                } else if (st2[j] == st1[i]) {
                    memo[i] = 1 + most;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < st1.length; i++) {
            ans = Math.max(ans, memo[i]);
        }

        System.out.println(ans);
        br.close();
    }

}

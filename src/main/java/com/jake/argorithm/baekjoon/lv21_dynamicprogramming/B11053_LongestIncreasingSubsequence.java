package com.jake.argorithm.baekjoon.lv21_dynamicprogramming;

import java.io.IOException;

/**
 * [ 가장 긴 증가하는 부분수열 ]
 * <p>
 * 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
 * 예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에
 * 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.
 * <p>
 * 입력
 * 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.
 * 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)
 * <p>
 * 출력
 * 첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.
 * <p>
 * 작성일 : 2023.09.07
 */
class B11053_LongestIncreasingSubsequence {
    private static int[] seq;
    private static Integer[] dp;

    public static void main(String[] args) throws Exception {
        int n = read();

        seq = new int[n];
        dp = new Integer[n];

        for (int i = 0; i < n; i++) {
            seq[i] = read();
        }

        for (int i = 0; i < n; i++) {
            lis(i);
        }

        int max = dp[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }

    private static int lis(int n) {
        if (dp[n] == null) {
            dp[n] = 1;

            for (int i = n - 1; i >= 0; i--) {
                if (seq[i] < seq[n]) {
                    dp[n] = Math.max(dp[n], lis(i) + 1);
                }
            }
        }

        return dp[n];
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        int n = read1();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = read1();
        }

        int[] dp = new int[n];
        dp[0] = arr[0];
        int ptr = 1;

        for (int i = 1; i < n; i++) {
            int pos = binsearch(arr[i], ptr, dp);
            if (pos == ptr) {
                dp[ptr++] = arr[i];
            } else {
                dp[pos] = arr[i];
            }
        }

        System.out.println(ptr);
    }

    static int binsearch(int target, int r, int[] dp) {
        int l = 0;
        while (l < r) {
            int mid = (l + r) / 2;
            if (dp[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    static int read1() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}

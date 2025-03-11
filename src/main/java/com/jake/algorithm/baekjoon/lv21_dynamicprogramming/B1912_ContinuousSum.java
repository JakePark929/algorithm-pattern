package com.jake.algorithm.baekjoon.lv21_dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 연속합 ]
 * <p>
 * n개의 정수로 이루어진 임의의 수열이 주어진다.
 * 우리는 이 중 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합을 구하려고 한다.
 * 단, 수는 한 개 이상 선택해야 한다.
 * <p>
 * 예를 들어서 10, -4, 3, 1, 5, 6, -35, 12, 21, -1 이라는 수열이 주어졌다고 하자.
 * 여기서 정답은 12+21인 33이 정답이 된다.
 * <p>
 * 입력
 * 첫째 줄에 정수 n(1 ≤ n ≤ 100,000)이 주어지고 둘째 줄에는 n개의 정수로 이루어진 수열이 주어진다.
 * 수는 -1,000보다 크거나 같고, 1,000보다 작거나 같은 정수이다.
 * <p>
 * 출력
 * 첫째 줄에 답을 출력한다.
 * <p>
 * 작성일 : 2023.09.05
 */
class B1912_ContinuousSum {
    private static int[] arr;
    private static Integer[] dp;
    private static int MAX;

    public static void main(String[] args) throws Exception {
        int n = read();

        arr = new int[n];
        dp = new Integer[n];

        for (int i = 0; i < n; i++) {
            arr[i] = read();
        }

        /*
         * dp[0] 은 첫 원소로 이전에 더 이상 탬색할 것이 없기 때문에
         * arr[0] 자체 값이 되므로 arr[0]으로 초기화 해준다.
         * max 또한 첫 번째 원소로 초기화 해준다.
         */
        dp[0] = arr[0];
        MAX = arr[0];

        // dp 의 마지막 index 는 n-1 이므로 n-1 부터 top-down 탐색
        recursive(n - 1);

        System.out.println(MAX);
    }

    private static int recursive(int n) {
        // 탐색하지 않은 인덱스 라면
        if (dp[n] == null) {
            dp[n] = Math.max(recursive(n - 1) + arr[n], arr[n]);

            // 해당 dp[n] 과 max 중 큰 값으로 max 갱신
            MAX = Math.max(dp[n], MAX);
        }

        return dp[n];
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;
        if (isNegative) n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return isNegative ? ~n + 1 : n;
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] dp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        /*
         * dp[0]은 첫 원소로 이전에 더이상 탐색할 것이 없기 때문에
         * arr[0] 자체 값이 되므로 arr[0]으로 초기화 해준다.
         * max 또한 첫 번째 원소로 초기화 해준다.
         */
        dp[0] = arr[0];
        int max = arr[0];

        for (int i = 1; i < N; i++) {
            // (이전 dp + 현재 arr 값) 과 현재 arr 값 중 큰 것을 dp에 저장
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
            // 최댓값 갱신
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }

    // 다른 사람의 풀이 2
    static int N;
    static int[] dp1;

    public static void main2(String[] args) throws Exception {
        N = read();
        dp1 = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            int temp = read();
            dp1[i] = Math.max(dp1[i - 1] + temp, temp);
        }

        System.out.println(max(dp1));
    }

    public static int max(int[] dp) {
        int m = Integer.MIN_VALUE;

        for (int i = 1; i < dp.length; i++) {
            if (m < dp[i]) {
                m = dp[i];
            }
        }

        return m;
    }

    public static int read1() throws Exception {
        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;

        if (isNegative) {
            n = System.in.read() & 15;
        }

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        if (c == 13) {
            System.in.read();
        }

        return isNegative ? ~n + 1 : n;
    }
}

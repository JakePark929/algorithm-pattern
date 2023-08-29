package com.jake.argorithm.baekjoon.lv19_recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class B10870_Fibonacci_5 {
    public static void main(String[] args) throws Exception {
        int n = read();
        System.out.println(fibonacci(n));
    }

    private static int fibonacci(int n) {
        if (n == 0)	return 0;
        if (n == 1)	return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    // 다른 사람의 풀이 1
    static int[] dp = new int[21];

    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Arrays.fill(dp, -1);
        dp[0] = 0;
        dp[1] = 1;
        dp(20);

        System.out.println(dp[n]);
    }

    static int dp(int num) {
        if(dp[num] != -1) return dp[num];

        int answer = dp(num - 2) + dp(num - 1);
        dp[num] = answer;
        return answer;
    }

    // 다른 사람의 풀이 2
    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] fibonacci = new int[N + 1];	// F(0) 부터 시작하므로 N + 1 크기 생성

        for(int i = 0; i < fibonacci.length; i++) {
            // F(0) 과 F(1) 은 각각 0 과 1 로 초기화
            if(i == 0) fibonacci[0] = 0;
            else if(i == 1) fibonacci[1] = 1;
            else fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }

        System.out.println(fibonacci[N]);

    }
}

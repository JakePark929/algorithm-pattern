package com.jake.argorithm.baekjoon.lv21_dynamicprogramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [ 1로 만들기 ]
 * <p>
 * 정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
 * <p>
 * 1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
 * 2. X가 2로 나누어 떨어지면, 2로 나눈다.
 * 3. 1을 뺀다.
 * <p>
 * 정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다.
 * 연산을 사용하는 횟수의 최솟값을 출력하시오.
 * <p>
 * 입력
 * 첫째 줄에 1보다 크거나 같고, 106보다 작거나 같은 정수 N이 주어진다.
 * <p>
 * 출력
 * 첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.
 * <p>
 * 작성일 : 23.09.06
 */
class B1463_Make1 {
//    private static Integer[] dp;

    public static void main(String[] args) throws Exception {
        int n = read();

//        dp = new Integer[n + 1];
//        dp[0] = dp[1] = 0;

        System.out.println(recur(n, 0));
    }

    static int recur(int n, int count) {
        // n이 2 미만인 경우 누적된 count 값을 반환
        if (n < 2) {
            return count;
        }

        return Math.min(recur(n / 2, count + 1 + (n % 2)), recur(n / 3, count + 1 + (n % 3)));
    }

    private static int recursive(int n) {
        if (dp[n] == null) {
            if (n % 6 == 0) {
                // 6 으로 나눠지는 경우
                dp[n] = Math.min(recursive(n - 1), Math.min(recursive(n / 3), recursive(n / 2))) + 1;
            } else if (n % 3 == 0) {
                // 3 으로만 나눠지는 경우
                dp[n] = Math.min(recursive(n / 3), recursive(n - 1)) + 1;
            } else if (n % 3 == 2) {
                // 2 로만 나눠지는 경우
                dp[n] = Math.min(recursive(n / 2), recursive(n - 1)) + 1;
            } else {
                // 2 와 3으로 나누어지지 않는 경우
                dp[n] = recursive(n - 1) + 1;
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
    static int N;
    static Integer dp[];

    public static void main1(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new Integer[N + 1];
        dp[0] = dp[1] = 0;
        System.out.println(solution(N));
    }

    static int solution(int n) {
        if (dp[n] == null) {
            if (n % 6 == 0) {
                return dp[n] = Math.min(solution(n / 3), solution(n / 2)) + 1;
            } else if (n % 3 == 0) {
                return dp[n] = Math.min(solution(n / 3), solution((n - 1))) + 1;
            } else if (n % 2 == 0) {
                return dp[n] = Math.min(solution(n / 2), solution(n - 1)) + 1;
            } else return dp[n] = solution(n - 1) + 1;
        }

        return dp[n];
    }
}

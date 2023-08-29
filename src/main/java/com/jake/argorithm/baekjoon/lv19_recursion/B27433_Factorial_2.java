package com.jake.argorithm.baekjoon.lv19_recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * [ 팩토리얼 2 ]
 * <p>
 * 0보다 크거나 같은 정수 N이 주어진다.
 * 이때, N!을 출력하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 정수 N(0 ≤ N ≤ 20)이 주어진다.
 * <p>
 * 출력
 * 첫째 줄에 N!을 출력한다.
 * <p>
 * 작성일 : 2023.08.28
 */
class B27433_Factorial_2 {
    public static void main(String[] args) throws Exception {
        int n = read();
        System.out.println(factorial(n));
    }

    private static long factorial(long n) {
        if (n <= 1) {
            return 1;
        }

        return n * factorial(n - 1);
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
        int n = Integer.parseInt(br.readLine());
        ArrayList<Long> dp = new ArrayList<>();
        dp.add(1L);
        dp.add(1L);
        for (int i = 2; i <= n; i++) dp.add(i * dp.get(i - 1));
        System.out.println(dp.get(n));
    }
}

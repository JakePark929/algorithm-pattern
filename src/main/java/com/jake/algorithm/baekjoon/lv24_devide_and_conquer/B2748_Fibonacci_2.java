package com.jake.algorithm.baekjoon.lv24_devide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ 피보나치 수 2 ]
 *
 * 피보나치 수는 0과 1로 시작한다.
 * 0번째 피보나치 수는 0이고, 1번째 피보나치 수는 1이다.
 * 그 다음 2번째 부터는 바로 앞 두 피보나치 수의 합이 된다.
 *
 * 이를 식으로 써보면 Fn = Fn-1 + Fn-2 (n ≥ 2)가 된다.
 * n=17일때 까지 피보나치 수를 써보면 다음과 같다.
 *
 * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597
 *
 * n이 주어졌을 때, n번째 피보나치 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 n이 주어진다. n은 90보다 작거나 같은 자연수이다.
 *
 * 출력
 * 첫째 줄에 n번째 피보나치 수를 출력한다.
 *
 * 작성일 : 2023.09.18
 */
class B2748_Fibonacci_2 {
    private static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        arr = new long[n + 1];

        for (int i = 0; i < n + 1; i++) {
            arr[i] = -1;
        }

        arr[0] = 0;
        arr[1] = 1;
        System.out.println(fibonacci(n));
    }

    private static long fibonacci(int n) {
        if (arr[n] == -1) {
            arr[n] = fibonacci(n - 1) + fibonacci(n - 2);
        }

        return arr[n];
    }
}

package com.jake.algorithm.baekjoon.lv24_devide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ 피보나치 6 ]
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
 * 첫째 줄에 n이 주어진다. n은 1,000,000,000,000,000,000보다 작거나 같은 자연수이다.
 *
 * 출력
 * 첫째 줄에 n번째 피보나치 수를 1,000,000,007으로 나눈 나머지를 출력한다.
 *
 * 작성일 : 2023.09.18
 */
class B11444_Fibonacci_6 {
    private final static long MOD = 1000000007;
    private static final long[][] origin = {{1, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[][] a = {{1, 1}, {1, 0}};
        long n = Long.parseLong(br.readLine());

        System.out.println(pow(a, n - 1)[0][0]);
    }

    private static long[][] pow(long[][] a, long exp) {
        // 지수가 1 또는 0일 땐 A를 return 한다.
        if(exp == 1 || exp == 0) {
            return a;
        }

        // 지수를 절반으로 분할하여 재귀호출
        long[][] ret = pow(a, exp / 2);

        // 하위 재귀에서 얻은 행렬을 제곱해준다.
        ret = multiply(ret, ret);

        // 만약 지수가 홀수라면 마지막에  A^1 (origin)을 곱해준다.
        if(exp % 2 == 1L) {
            ret = multiply(ret, origin);
        }

        return ret;
    }

    private static long[][] multiply(long[][] o1, long[][] o2) {
        long[][] ret = new long[2][2];

        ret[0][0] = ((o1[0][0] * o2[0][0]) + (o1[0][1] * o2[1][0])) % MOD;
        ret[0][1] = ((o1[0][0] * o2[0][1]) + (o1[0][1] * o2[1][1])) % MOD;
        ret[1][0] = ((o1[1][0] * o2[0][0]) + (o1[1][1] * o2[1][0])) % MOD;
        ret[1][1] = ((o1[1][0] * o2[0][1]) + (o1[1][1] * o2[1][1])) % MOD;

        // 반복문으로 작성해주어도 무방함
		/*
         * for (int k = 0; k < 2; k++) {
         * 		for (int i = 0; i < 2; i++) {
         * 			for (int j = 0; j < 2; j++) {
         * 				ret[i][j] += o1[i][k] * o2[k][j];
         * 				ret[i][j] %= MOD;
         *          }
         * 		}
         * }
		*/

        return ret;
    }

//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        long n = Long.parseLong(br.readLine());
//
//        if (n == 1 || n == 0) {
//            System.out.println(n);
//            return;
//        }
//
//        // A^(n-1)의 [0][0] 원소를 구하면 되므로 1을 빼준다.
//        n--;
//
//        long[][] origin = {{1, 1}, {1, 0}};
//        long[][] a = {{1, 0}, {0, 1}}; // 초기 값 단위 행렬(I)
//
//        while (n > 0) {
//            if (n % 2 == 1) {
//                a = multiply(a, origin);
//            }
//            origin = multiply(origin, origin);
//            n /= 2;
//        }
//
//        System.out.println(a[0][0]);
//    }
}

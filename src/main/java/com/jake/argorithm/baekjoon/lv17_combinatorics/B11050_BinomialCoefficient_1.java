package com.jake.argorithm.baekjoon.lv17_combinatorics;

/**
 * [ 이항계수 1 ]
 *
 * 문제
 * 자연수 \(N\)과 정수 \(K\)가 주어졌을 때
 * 이항 계수 \(\binom{N}{K}\)를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 \(N\)과 \(K\)가 주어진다. (1 ≤\(N\)≤10, 0≤\(K\) ≤\(N\))
 *
 * 출력
 * \(\binom{N}{K}\)를 출력한다.
 *
 * 작성일 : 2023.08.25
 */
class B11050_BinomialCoefficient_1 {
    public static void main(String[] args) throws Exception {
        int n = read();
        int k = read();

        // nCk = n! / (k! * (n-k)!)
        System.out.println(factorial(n) / (factorial(k) * factorial(n - k)));
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    private static int factorial(int n) {
        // factorial(0) == 1 이다.
        if (n <= 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }

    // 동적 계획법 참고해 볼 것
    // link: https://st-lab.tistory.com/123
}

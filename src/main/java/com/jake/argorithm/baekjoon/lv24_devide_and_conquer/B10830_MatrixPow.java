package com.jake.argorithm.baekjoon.lv24_devide_and_conquer;

/**
 * [ 행렬 제곱 ]
 *
 * 크기가 N*N인 행렬 A가 주어진다.
 * 이때, A의 B 제곱을 구하는 프로그램을 작성하시오.
 * 수가 매우 커질 수 있으니, A^B의 각 원소를 1,000으로 나눈 나머지를 출력한다.
 *
 * 입력
 * 첫째 줄에 행렬의 크기 N과 B가 주어진다. (2 ≤ N ≤  5, 1 ≤ B ≤ 100,000,000,000)
 * 둘째 줄부터 N개의 줄에 행렬의 각 원소가 주어진다.
 * 행렬의 각 원소는 1,000보다 작거나 같은 자연수 또는 0이다.
 *
 * 출력
 * 첫째 줄부터 N개의 줄에 걸쳐 행렬 A를 B 제곱한 결과를 출력한다.
 *
 * 작성일 : 2023.09.18
 */
class B10830_MatrixPow {
    public static void main(String[] args) throws Exception {
        int n = read();
        int b = read();

        int[][] a = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = read();
            }
        }
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }
}

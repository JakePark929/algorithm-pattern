package com.jake.argorithm.baekjoon.lv22_prefixsum;

import java.io.Reader;
import java.util.Arrays;

/**
 * [ 구간 합 구하기 5 ]
 *
 * N×N개의 수가 N×N 크기의 표에 채워져 있다.
 * (x1, y1)부터 (x2, y2)까지 합을 구하는 프로그램을 작성하시오.
 * (x, y)는 x행 y열을 의미한다.
 *
 * 예를 들어, N = 4이고, 표가 아래와 같이 채워져 있는 경우를 살펴보자.
 *
 * 1	2	3	4
 * 2	3	4	5
 * 3	4	5	6
 * 4	5	6	7
 *
 * 여기서 (2, 2)부터 (3, 4)까지 합을 구하면 3+4+5+4+5+6 = 27이고, (4, 4)부터 (4, 4)까지 합을 구하면 7이다.
 *
 * 표에 채워져 있는 수와 합을 구하는 연산이 주어졌을 때, 이를 처리하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 표의 크기 N과 합을 구해야 하는 횟수 M이 주어진다. (1 ≤ N ≤ 1024, 1 ≤ M ≤ 100,000)
 * 둘째 줄부터 N개의 줄에는 표에 채워져 있는 수가 1행부터 차례대로 주어진다.
 * 다음 M개의 줄에는 네 개의 정수 x1, y1, x2, y2 가 주어지며, (x1, y1)부터 (x2, y2)의 합을 구해 출력해야 한다.
 * 표에 채워져 있는 수는 1,000보다 작거나 같은 자연수이다. (x1 ≤ x2, y1 ≤ y2)
 *
 * 출력
 * 총 M줄에 걸쳐 (x1, y1)부터 (x2, y2)까지 합을 구해 출력한다.
 *
 * 작성일 : 2023.09.11
 */
class B11660_GetPrefixSum_5 {
    public static void main(String[] args) throws Exception {
        int n = read();
        int m = read();

        int[][] dps = new int[n + 1][n + 1];

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                dps[i][j] = dps[i][j - 1] + read();
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int y1 = read();
            int x1 = read();
            int y2 = read();
            int x2 = read();

            int sum = 0;
            for (int y = y1; y <= y2; y++) {
                sum += dps[y][x2] - dps[y][x1 - 1];
            }

            sb.append(sum).append('\n');
        }

        System.out.println(sb);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws Exception {

        Reader2 in = new Reader2();
        StringBuilder sb = new StringBuilder();

        int N = in.nextInt() + 1;
        int M = in.nextInt();

        int[][] dp = new int[N][N];

        for (int x = 1; x < N; x++) {
            for (int y = 1; y < N; y++) {
                dp[x][y] = dp[x - 1][y] + dp[x][y - 1] - dp[x - 1][y - 1] + in.nextInt();
            }
        }

        while (M-- > 0) {

            int x1 = in.nextInt() - 1;
            int y1 = in.nextInt() - 1;
            int x2 = in.nextInt();
            int y2 = in.nextInt();

            sb.append(dp[x2][y2] - dp[x1][y2] - dp[x2][y1] + dp[x1][y1]).append('\n');

        }

        System.out.print(sb);

    }
}

class Reader2 {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        while ((c = read()) <= 32);
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return n;
    }
    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }
    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}

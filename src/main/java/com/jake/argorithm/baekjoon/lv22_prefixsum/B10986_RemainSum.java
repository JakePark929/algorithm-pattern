package com.jake.argorithm.baekjoon.lv22_prefixsum;

import java.util.Arrays;

/**
 * [ 나머지 합 ]
 *
 * 수 N개 A1, A2, ..., AN이 주어진다.
 * 이때, 연속된 부분 구간의 합이 M으로 나누어 떨어지는 구간의 개수를 구하는 프로그램을 작성하시오.
 *
 * 즉, Ai + ... + Aj (i ≤ j) 의 합이 M으로 나누어 떨어지는 (i, j) 쌍의 개수를 구해야 한다.
 *
 * 입력
 * 첫째 줄에 N과 M이 주어진다. (1 ≤ N ≤ 106, 2 ≤ M ≤ 103)
 * 둘째 줄에 N개의 수 A1, A2, ..., AN이 주어진다. (0 ≤ Ai ≤ 109)
 *
 * 출력
 * 첫째 줄에 연속된 부분 구간의 합이 M으로 나누어 떨어지는 구간의 개수를 출력한다.
 *
 * 작성일 : 2023.09.11
 */
class B10986_RemainSum {
//    public static void main(String[] args) throws Exception {
//        int n = read();
//        int m = read();
//
//        long[] s = new long[n]; // 합배열
//        long[] c = new long[m]; // 합배열 % M 을 동일하게 만들어 주는 i, j를 담는 배열
//
//        long answer = 0;
//
//        s[0] = read();
//
//        for (int i = 1; i < n; i++) {
//            s[i] = s[i - 1] + read();
//        }
//
//        for (int i = 0; i < n; i++) {
//            int remain = (int) (s[i] % m);
//
//            if (remain == 0) {
//               answer++;
//            }
//
//            c[remain]++;
//        }
//
//        for (int i = 0; i < m; i++) {
//            if (c[i] > 1) {
//                long cnt = c[i];
//                answer = answer + (cnt * (cnt - 1) / 2);
//            }
//        }
//
//        System.out.println(Arrays.toString(s));
//        System.out.println(Arrays.toString(c));
//
//        System.out.println(answer);
//    }

    public static void main(String[] args) throws Exception {
        int n = read();
        int m = read();

        int[] remainder = new int[m];

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += read() % m;
            remainder[sum % m]++;
        }

        long answer = remainder[0];

        for (int i = 0; i < m; i++) {
            int num = remainder[i];
            answer += (long) num * (num - 1) / 2;
        }

        System.out.println(answer);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    // 다른 사람의 풀이 1
    static int N, M;

    public static void main1(String[] args) throws Exception {

        N = next();
        M = next();

        int prefixSum = 0;
        long[] cnt = new long[M];
        for (int i = 0; i < N; i++) {
            prefixSum = (prefixSum + next()) % M;
            cnt[prefixSum]++;
        }

        long count = cnt[0];

        for (int i = 0; i < M; i++) {
            count += (cnt[i] * (cnt[i] - 1)) >> 1;
        }

        System.out.print(count);

    }

    static final int SIZE = 1 << 13;
    static byte[] buffer = new byte[SIZE];
    static int index, size;

    static int next() throws Exception {
        int n = 0;
        byte c;
        while ((c = read1()) <= 32);
        do n = (n << 3) + (n << 1) + (c & 15);
        while ((c = read1()) > 47 && c < 58);
        return n;
    }

    static byte read1() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}

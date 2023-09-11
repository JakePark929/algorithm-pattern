package com.jake.argorithm.baekjoon.lv22_prefixsum;

import java.io.Reader;
import java.util.Arrays;

/**
 * [ 수열 ]
 * <p>
 * 매일 아침 9시에 학교에서 측정한 온도가 어떤 정수의 수열로 주어졌을 때,
 * 연속적인 며칠 동안의 온도의 합이 가장 큰 값을 알아보고자 한다.
 * <p>
 * 예를 들어, 아래와 같이 10일 간의 온도가 주어졌을 때,
 * <p>
 * 3 -2 -4 -9 0 3 7 13 8 -3
 * <p>
 * 모든 연속적인 이틀간의 온도의 합은 아래와 같다.
 * <p>
 * 이때, 온도의 합이 가장 큰 값은 21이다.
 * 또 다른 예로 위와 같은 온도가 주어졌을 때, 모든 연속적인 5일 간의 온도의 합은 아래와 같으며,
 * <p>
 * 이때, 온도의 합이 가장 큰 값은 31이다.
 * 매일 측정한 온도가 정수의 수열로 주어졌을 때,
 * 연속적인 며칠 동안의 온도의 합이 가장 큰 값을 계산하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에는 두 개의 정수 N과 K가 한 개의 공백을 사이에 두고 순서대로 주어진다.
 * 첫 번째 정수 N은 온도를 측정한 전체 날짜의 수이다. N은 2 이상 100,000 이하이다.
 * 두 번째 정수 K는 합을 구하기 위한 연속적인 날짜의 수이다. K는 1과 N 사이의 정수이다.
 * 둘째 줄에는 매일 측정한 온도를 나타내는 N개의 정수가 빈칸을 사이에 두고 주어진다.
 * 이 수들은 모두 -100 이상 100 이하이다.
 * <p>
 * 출력
 * 첫째 줄에는 입력되는 온도의 수열에서 연속적인 K일의 온도의 합이 최대가 되는 값을 출력한다.
 * <p>
 * 작성일 : 2023.09.08
 */
class B2559_Sequence {
    public static void main(String[] args) throws Exception {
        int n = read();
        int k = read();

        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int num = read();

            if (i == 0) {
                dp[i + 1] = num;
            } else {
                dp[i + 1] = dp[i] + num;
            }
        }

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < dp.length - k; i++) {
            max = Math.max(max, dp[i + k] - dp[i]);
        }

        System.out.println(max);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;
        if (isNegative) n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return isNegative ? ~n + 1 : n;
    }

    // 다른 사람의 풀이 1
    static public void main1(String[] args) throws Exception{
        Reader1 in = new Reader1();
        int n = in.nextInt();
        int k = in.nextInt();
        int[] data = new int[n + 1];

        for (int i = 1; i <= n; i++) data[i] = data[i - 1] + in.nextInt();

        int max = Integer.MIN_VALUE;

        for (int i = k; i <= n; i++) max = Math.max(max, data[i] - data[i - k]);

        System.out.print(max);
    }
}

class Reader1 {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    char nextChar() throws Exception {
        char ch = ' ';
        byte c;
        while ((c = read()) <= 32);
        do ch = (char)c;
        while (isAlphabet(c = read()));
        return ch;
    }

    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32); //{ if (size < 0) return -1; }
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    long nextLong() throws Exception {
        long n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32);
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    double nextDouble() throws Exception {
        double n = 0, div = 1;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32);
        if (c == 45) { c = read(); isMinus = true; }
        else if (c == 46) { c = read(); }
        do n = (n * 10) + (c & 15);
        while (isNumber(c = read()));
        if (c == 46) { while (isNumber(c = read())){ n += (c - 48) / (div *= 10); }}
        return isMinus ? -n : n;
    }

    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }

    boolean isAlphabet(byte c){
        return 96 < c && c < 123;
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}

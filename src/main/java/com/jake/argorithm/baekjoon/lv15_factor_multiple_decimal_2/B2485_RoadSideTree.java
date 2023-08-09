package com.jake.argorithm.baekjoon.lv15_factor_multiple_decimal_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ 가로수 ]
 * <p>
 * 직선으로 되어있는 도로의 한 편에 가로수가 임의의 간격으로 심어져있다.
 * KOI 시에서는 가로수들이 모두 같은 간격이 되도록 가로수를 추가로 심는 사업을 추진하고 있다.
 * KOI 시에서는 예산문제로 가능한 한 가장 적은 수의 나무를 심고 싶다.
 * <p>
 * 편의상 가로수의 위치는 기준점으로 부터 떨어져 있는 거리로 표현되며, 가로수의 위치는 모두 양의 정수이다.
 * <p>
 * 예를 들어,
 * 가로수가 (1, 3, 7, 13)의 위치에 있다면
 * (5, 9, 11)의 위치에 가로수를 더 심으면
 * 모든 가로수들의 간격이 같게 된다.
 * 또한, 가로수가 (2, 6, 12, 18)에 있다면 (4, 8, 10, 14, 16)에 가로수를 더 심어야 한다.
 * <p>
 * 심어져 있는 가로수의 위치가 주어질 때,
 * 모든 가로수가 같은 간격이 되도록 새로 심어야 하는 가로수의 최소수를 구하는 프로그램을 작성하라.
 * 단, 추가되는 나무는 기존의 나무들 사이에만 심을 수 있다.
 * <p>
 * 입력
 * 첫째 줄에는 이미 심어져 있는 가로수의 수를 나타내는 하나의 정수 N이 주어진다(3 ≤ N ≤ 100,000).
 * 둘째 줄부터 N개의 줄에는 각 줄마다 심어져 있는 가로수의 위치가 양의 정수로 주어지며,
 * 가로수의 위치를 나타내는 정수는 1,000,000,000 이하이다.
 * 가로수의 위치를 나타내는 정수는 모두 다르다.
 * <p>
 * 출력
 * 모든 가로수가 같은 간격이 되도록 새로 심어야 하는 가로수의 최소수를 첫 번째 줄에 출력한다.
 * <p>
 * 작성일 : 2023.08.08
 */
class B2485_RoadSideTree {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = 0;

        int dist = b - a;
        int gcd = 0;
        for (int i = 0; i < n - 2; i++) {
            c = Integer.parseInt(br.readLine());
            int nextDist = c - b;
            gcd = gcd(dist, nextDist);
            dist = gcd;
            b = c;
        }

        int need = (c - a) / gcd + 1;

        System.out.println(need - n);
    }

    // 등차 수열의 합 - (큰항 - 작은항) / 공차 + 1

    public static int gcd(int a, int b) {
        while (b > 0) {
            int r = a % b;
            a = b;
            b = r;
        }

        return a;
    }

    // 다른 사람의 풀이 1
    static int read() throws Exception {
        int i, j = System.in.read() & 15;
        boolean nega = false;
        if (j == 13) {
            nega = true;
            j = System.in.read() & 15;
        }
        while ((i = System.in.read()) > 32) j = (j << 3) + (j << 1) + (i & 15);
        if (i == 13) System.in.read();
        if (nega) return -j;
        else return j;
    }

    static int Euclidean(int a, int b) throws Exception {
        int rem = -1;
        if (b > a) {
            int temp = a;
            a = b;
            b = temp;
        }
        while (rem != 0) {
            rem = a % b;
            a = b;
            b = rem;
        }
        return a;
    }

    public static void main1(String[] args) throws Exception {
        int n = read();
        int n1 = read(), n2 = read(), n3 = read(), rem = -1, temp = 0, prev = n3, count = 0, n4;
        int max = n1 > n2 && n1 > n3 ? n1 : (Math.max(n2, n3));
        int min = n1 < n2 && n1 < n3 ? n1 : (Math.min(n2, n3));
        int comp1 = Math.abs(n2 - n1), comp2 = Math.abs(n3 - n2);
        comp1 = Euclidean(comp1, comp2);
        for (int i = n - 3; i > 0; i--) {
            n4 = read();
            if (max < n4) max = n4;
            else if (min > n4) min = n4;
            comp2 = Math.abs(n4 - prev);
            comp1 = Euclidean(comp1, comp2);
            prev = n4;
        }
        count = (((max - min) / comp1) + 1) - n;
        System.out.print(count);
    }
}

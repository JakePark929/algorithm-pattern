package com.jake.algorithm.a01_lecture.fc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 2 x n 타일링 ]
 *
 * 문제
 * 2×n 크기의 직사각형을 1×2, 2×1 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.
 * 아래 그림은 2×5 크기의 직사각형을 채운 한 가지 방법의 예이다.
 *
 * 입력
 * 첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)
 *
 * 출력
 * 첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.
 *
 * 작성일 : 2026.04.22
 */
public class P9_B11726_2xNTilling {
    static FastReader scan = new FastReader();

    static int N;
    static int[] Dy;

    static void input() {
        N = scan.nextInt();
    }

    static void process() {
        Dy = new int[1005];

        Dy[1] = 1;
        Dy[2] = 2;

        for (int i = 3; i <= N; i++) {
            Dy[i] = (Dy[i - 1] + Dy[i - 2]) % 10007;
        }

        System.out.println(Dy[N]);
    }

    public static void main(String[] args) {
        input();

        process();
    }

    private static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

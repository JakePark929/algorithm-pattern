package com.jake.algorithm.a01_lecture.fc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ N-Queen ]
 *
 * 문제
 * N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.
 * N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. (1 ≤ N < 15)
 *
 * 출력
 * 첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.
 *
 * 작성일 : 2025.03.12
 */
public class P1_B9663_NQueen {
    static int N, ans;
    static int[] col;

    static FastReader scan = new FastReader();

    static void input() {
        N = scan.nextInt();
        col = new int[N + 1];
    }

    static boolean attackable(int r1, int c1, int r2, int c2) {
        if (c1 == c2) {

            return true;
        }

        if (r1 - c1 == r2 - c2) {

            return true;
        }

        if (r1 + c1 == r2 + c2) {

            return true;
        }

        return false;
    }

    static void recurrence(int row) {
        if (row == N + 1) {
            ans++;
        } else {
            for (int c = 1; c <= N; c++) {
                // valid check (row, col)
                boolean possible = true;
                for (int i = 1; i <= row - 1; i++) {
                    // (i, col[i])
                    if (attackable(row, c, i, col[i])) {
                        possible = false;

                        break;
                    }
                }
                if (possible) {
                    col[row] = c;
                    recurrence(row + 1);
                    col[row] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        input();

        recurrence(1);

        System.out.println(ans);
    }

    static class FastReader {
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

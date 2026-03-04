package com.jake.algorithm.a01_lecture.fc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [ 좋다 ]
 *
 * 문제
 * N개의 수 중에서 어떤 수가 다른 수 두 개의 합으로 나타낼 수 있다면 그 수를 “좋다(GOOD)”고 한다.
 * N개의 수가 주어지면 그 중에서 좋은 수의 개수는 몇 개인지 출력하라.
 * 수의 위치가 다르면 값이 같아도 다른 수이다.
 *
 * 입력
 * 첫째 줄에는 수의 개수 N(1 ≤ N ≤ 2,000), 두 번째 줄에는 i번째 수를 나타내는 Ai가 N개 주어진다.
 * (|Ai| ≤ 1,000,000,000, Ai는 정수)
 *
 * 출력
 * 좋은 수의 개수를 첫 번째 줄에 출력한다.
 *
 * 작성일 : 2026.03.03
 */
public class P3_B1253_Good {
    static FastReader scan = new FastReader();

    static int N;
    static int[] A, count;

    static void input() {
        N = scan.nextInt();
        A = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }

        count = new int[100000 + 1];
    }

    static boolean function(int targetIdx) {
        int L = 1, R = N;
        int target = A[targetIdx];

        while (L < R) {
            if (L == targetIdx) {
                L++;
            } else if (R == targetIdx) {
                R--;
            } else {
                if (A[L] + A[R] == target) {
                    return true;
                }

                if (A[L] + A[R] > target) {
                    R--;
                } else {
                    L++;
                }
            }
        }

        return false;
    }

    static void process() {
        // 최소, 최대를 바르게 알기 위해 정렬
        Arrays.sort(A, 1, N + 1);

        int answer = 0;

        for (int i = 1; i <= N; i++) {
            // i 번째 원소가 서로 다른 두 수의 합으로 표현이 되는가?
            if (function(i)) {
                answer++;
            }
        }

        System.out.println(answer);
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

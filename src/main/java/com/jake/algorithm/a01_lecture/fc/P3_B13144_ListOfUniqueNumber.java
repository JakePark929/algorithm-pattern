package com.jake.algorithm.a01_lecture.fc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ List of Unique Numbers ]
 *
 * 문제
 * 길이가 N인 수열이 주어질 때,
 * 수열에서 연속한 1개 이상의 수를 뽑았을 때 같은 수가 여러 번 등장하지 않는 경우의 수를 구하는 프로그램을 작성하여라.
 *
 * 입력
 * 첫 번째 줄에는 수열의 길이 N이 주어진다. (1 ≤ N ≤ 100,000)
 *
 * 두 번째 줄에는 수열을 나타내는 N개의 정수가 주어진다. 수열에 나타나는 수는 모두 1 이상 100,000 이하이다.
 *
 * 출력
 * 조건을 만족하는 경우의 수를 출력한다.
 *
 * 작성일 : 2026.03.03
 */
public class P3_B13144_ListOfUniqueNumber {
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

    static void process() {
        long answer = 0;

        for (int L = 1, R = 0; L <= N; L++) { // L 마다 R을 최대한 옮겨 줄 계획
            // R 을 옮길 수 있는 만큼 옮긴다.
            while (R + 1 <= N && count[A[R + 1]] == 0) {
                R++;
                count[A[R]]++;
            }

            // 정답을 갱신한다.
            answer += R - L + 1;

            // L 을 옮겨주면서 A[L] 의 개수를 감소시킨다.
            count[A[L]]--;
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

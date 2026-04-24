package com.jake.algorithm.a01_lecture.fc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 오르막 수 ]
 *
 * 문제
 * 오르막 수는 수의 자리가 오름차순을 이루는 수를 말한다. 이때, 인접한 수가 같아도 오름차순으로 친다.
 *
 * 예를 들어, 2234와 3678, 11119는 오르막 수이지만, 2232, 3676, 91111은 오르막 수가 아니다.
 *
 * 수의 길이 N이 주어졌을 때, 오르막 수의 개수를 구하는 프로그램을 작성하시오. 수는 0으로 시작할 수 있다.
 *
 * 입력
 * 첫째 줄에 N (1 ≤ N ≤ 1,000)이 주어진다.
 *
 * 출력
 * 첫째 줄에 길이가 N인 오르막 수의 개수를 10,007로 나눈 나머지를 출력한다.
 *
 * 작성일 : 2026.04.24
 */
public class P10_B11057_UphillNum {
    static FastReader scan = new FastReader();

    static int N;
    static int[][] Dy;
    static int[] A;

    static void input() {
        N = scan.nextInt();
        A = new int[N + 1];
        Dy = new int[N + 1][10];
    }

    static void process() {
        for (int num = 0; num <= 9; num++) {
            Dy[1][num] = 1;
        }

        // 점화식을 토대로 Dy 배열 채우기
        for (int len = 2; len <= N; len++) {
            for (int num = 0; num <= 9; num++) {
                // 길이가 len 이고 num 으로 끝나는 개수를 계산하자 == Dy[len][num] 을 계산하자.
                for (int prev = 0; prev <= num; prev++) {
                    Dy[len][num] += Dy[len - 1][prev];
                    Dy[len][num] %= 10007;
                }
            }
        }

        // Dy 배열로 정답 계산하기
        int answer = 0;
        for (int num = 0; num <= 9; num++) {
            answer += Dy[N][num];
            answer %= 10007;
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

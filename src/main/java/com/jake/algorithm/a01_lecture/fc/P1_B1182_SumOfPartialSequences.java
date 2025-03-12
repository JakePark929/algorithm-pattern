package com.jake.algorithm.a01_lecture.fc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 부분 수열의 합 ]
 *
 * 문제
 * N개의 정수로 이루어진 수열이 있을 때,
 * 크기가 양수인 부분수열 중에서 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 정수의 개수를 나타내는 N과 정수 S가 주어진다. (1 ≤ N ≤ 20, |S| ≤ 1,000,000)
 * 둘째 줄에 N개의 정수가 빈 칸을 사이에 두고 주어진다.
 * 주어지는 정수의 절댓값은 100,000을 넘지 않는다.
 *
 * 출력
 * 첫째 줄에 합이 S가 되는 부분수열의 개수를 출력한다.
 *
 * 작성일 : 2025.03.12
 */
public class P1_B1182_SumOfPartialSequences {
    static int N, S, answer;
    static int[] nums;

    static void input() {
        FastReader scan = new FastReader();

        N = scan.nextInt();
        S = scan.nextInt();
        nums = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            nums[i] = scan.nextInt();
        }
    }

    static void recurrence(int k, int value) {
        if (k == N + 1) {
            // value 가 S랑 같은지 확인하기
            if (value == S) {
                answer++;
            }
        } else {
            // k번째 원소를 포함시킬지 결정하고 재귀호출 해주기
            // Include
            recurrence(k + 1, value + nums[k]);

            // Not Include
            recurrence(k + 1, value);
        }
    }

    public static void main(String[] args) {
        input();

        // 1번째 원소부터 M번째 원소를 조건에 맞게 고르는 모든 방법을 탐색해줘
        recurrence(1, 0);

        if (S == 0) {
            answer--;
        }

        System.out.println(answer);
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

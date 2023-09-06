package com.jake.argorithm.baekjoon.lv21_dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ 파도반 수열 ]
 *
 * 오른쪽 그림과 같이 삼각형이 나선 모양으로 놓여져 있다.
 * 첫 삼각형은 정삼각형으로 변의 길이는 1이다.
 * 그 다음에는 다음과 같은 과정으로 정삼각형을 계속 추가한다.
 * 나선에서 가장 긴 변의 길이를 k라 했을 때, 그 변에 길이가 k인 정삼각형을 추가한다.
 *
 * 파도반 수열 P(N)은 나선에 있는 정삼각형의 변의 길이이다.
 * P(1)부터 P(10)까지 첫 10개 숫자는 1, 1, 1, 2, 2, 3, 4, 5, 7, 9이다.
 * N이 주어졌을 때, P(N)을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 테스트 케이스의 개수 T가 주어진다.
 * 각 테스트 케이스는 한 줄로 이루어져 있고, N이 주어진다. (1 ≤ N ≤ 100)
 *
 * 출력
 * 각 테스트 케이스마다 P(N)을 출력한다.
 *
 * 작성일 : 2023.09.05
 */
class B9461_PadovanSequence {
    private static long[] memo;

    public static void main(String[] args) throws Exception {
        int t = read();
        memo = new long[101];

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = read();
            sb.append(padovan(n)).append('\n');
        }

        System.out.println(sb.toString().trim());
    }

    private static long padovan(int n) {
        memo[0] = 1;
        memo[1] = 1;
        memo[2] = 1;
        memo[3] = 2;
        memo[4] = 2;

        for (int i = 5; i < n; i++) {
            memo[i] = memo[i - 1] + memo[i - 5];
        }

        return memo[n - 1];
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    // 다른 사람의 풀이 1
    static long[] seq = new long[101];

    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        padovan();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            sb.append(seq[Integer.parseInt(br.readLine())]).append('\n');
        }

        System.out.println(sb);
    }

    public static void padovan() {

        seq[1] = 1;
        seq[2] = 1;
        seq[3] = 1;

        for (int i = 4; i < 101; i++) {
            seq[i] = seq[i - 2] + seq[i - 3];
        }
    }
}

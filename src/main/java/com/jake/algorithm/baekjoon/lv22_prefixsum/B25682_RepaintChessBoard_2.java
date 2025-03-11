package com.jake.algorithm.baekjoon.lv22_prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 체스판 다시 칠하기 2 ]
 *
 * 지민이는 자신의 저택에서 MN 개의 단위 정사각형으로 나누어져 있는 M×N 크기의 보드를 찾았다.
 * 어떤 정사각형은 검은색으로 칠해져 있고, 나머지는 흰색으로 칠해져 있다.
 * 지민이는 이 보드를 잘라서 K×K 크기의 체스판으로 만들려고 한다.
 *
 * 체스판은 검은색과 흰색이 번갈아서 칠해져 있어야 한다.
 * 구체적으로, 각 칸이 검은색과 흰색 중 하나로 색칠되어 있고,
 * 변을 공유하는 두 개의 사각형은 다른 색으로 칠해져 있어야 한다.
 * 따라서 이 정의를 따르면 체스판을 색칠하는 경우는 두 가지뿐이다.
 * 하나는 맨 왼쪽 위 칸이 흰색인 경우, 하나는 검은색인 경우이다.
 *
 * 보드가 체스판처럼 칠해져 있다는 보장이 없어서,
 * 지민이는 K×K 크기의 체스판으로 잘라낸 후에 몇 개의 정사각형을 다시 칠해야겠다고 생각했다.
 * 당연히 K×K 크기는 아무데서나 골라도 된다.
 * 지민이가 다시 칠해야 하는 정사각형의 최소 개수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 정수 N, M, K가 주어진다.
 * 둘째 줄부터 N개의 줄에는 보드의 각 행의 상태가 주어진다.
 * B는 검은색이며, W는 흰색이다.
 *
 * 출력
 * 첫째 줄에 지민이가 잘라낸 K×K 보드를 체스판으로 만들기 위해 다시 칠해야 하는 정사각형 개수의 최솟값을 출력한다.
 *
 * 작성일 : 2023.09.11
 */
class B25682_RepaintChessBoard_2 {
    static int N, M, K;
    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new char[N][M];

        char[] temp;
        for (int i = 0; i < N; i++) {
            temp = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                board[i][j] = temp[j];
            }
        }

        int[][] prefixSumB = prefixSum('B');
        int[][] prefixSumW = prefixSum('W');

        System.out.println(Math.min(calculate(prefixSumB), calculate(prefixSumW)));
    }

    private static int calculate(int[][] prefixSum) {
        int cnt = Integer.MAX_VALUE;

        for (int i = 1; i <= N - K + 1; i++) {
            for (int j = 1; j <= M - K + 1; j++) {
                int num = prefixSum[i + K - 1][j + K - 1] - prefixSum[i + K - 1][j - 1] - prefixSum[i - 1][j + K - 1] + prefixSum[i - 1][j - 1];
                cnt = Math.min(cnt, num);
            }
        }

        return cnt;
    }

    private static int[][] prefixSum(char st) {
        int[][] temp = new int[N + 1][M + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int cur = ((i + j) % 2 == 0) ? board[i][j] == st ? 0 : 1 : board[i][j] == st ? 1 : 0;
                temp[i + 1][j + 1] = temp[i + 1][j] + temp[i][j + 1] - temp[i][j] + cur;
            }
        }

        return temp;
    }
}

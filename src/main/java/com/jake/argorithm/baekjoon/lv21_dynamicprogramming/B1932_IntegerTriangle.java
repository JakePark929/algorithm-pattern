package com.jake.argorithm.baekjoon.lv21_dynamicprogramming;

import java.io.IOException;

/**
 * [ 정수 삼각형 ]
 * <p>
 * 7
 * 3   8
 * 8   1   0
 * 2   7   4   4
 * 4   5   2   6   5
 * <p>
 * 위 그림은 크기가 5인 정수 삼각형의 한 모습이다.
 * <p>
 * 맨 위층 7부터 시작해서 아래에 있는 수 중 하나를 선택하여 아래층으로 내려올 때,
 * 이제까지 선택된 수의 합이 최대가 되는 경로를 구하는 프로그램을 작성하라.
 * 아래층에 있는 수는 현재 층에서 선택된 수의 대각선 왼쪽 또는 대각선 오른쪽에 있는 것 중에서만 선택할 수 있다.
 * <p>
 * 삼각형의 크기는 1 이상 500 이하이다. 삼각형을 이루고 있는 각 수는 모두 정수이며, 범위는 0 이상 9999 이하이다.
 * <p>
 * 입력
 * 첫째 줄에 삼각형의 크기 n(1 ≤ n ≤ 500)이 주어지고, 둘째 줄부터 n+1번째 줄까지 정수 삼각형이 주어진다.
 * <p>
 * 출력
 * 첫째 줄에 합이 최대가 되는 경로에 있는 수의 합을 출력한다.
 * <p>
 * 작성일 : 2023.09.05
 */
class B1932_IntegerTriangle {
    private static int n;
    private static int[][] arr;
    private static Integer[][] dp;

    public static void main(String[] args) throws Exception {
        n = read();

        arr = new int[n][n];
        dp = new Integer[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                int num = read();
                arr[i][j] = num;
            }
        }

        for (int i = 0; i < n; i++) {
            dp[n - 1][i] = arr[n - 1][i];
        }

        System.out.println(find(0, 0));
    }

    // depth 는 길이(행), idx 는 인덱스(열)를 의미
    private static int find(int depth, int idx) {
        // 만약 맨 밑(깊이)의 행에 도달하면 해당 인덱스를 반환
        if (depth == n - 1) return dp[depth][idx];

        // 만약 아직 탐색하지 않은 위치라면 다음 행의 양쪽 열 중 최댓값을 구함
        if (dp[depth][idx] == null) {
            /*
             * 바로 다름 행의 인덱스와 그 오른쪽의 인덱스 중
             * 큰 값을 찾아 dp 에 현재 인덱스의 값과 더하여 저장
             */
            dp[depth][idx] = Math.max(find(depth + 1, idx), find(depth + 1, idx + 1)) + arr[depth][idx];
        }

        return dp[depth][idx];
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        int n = readInt();
        int[][] maxNumbers = new int[n + 1][];

        for (int i = 0; i < n + 1; i++) {
            maxNumbers[i] = new int[i + 2];
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                maxNumbers[i][j] = Math.max(maxNumbers[i - 1][j - 1], maxNumbers[i - 1][j]) + readInt();
            }
        }

        int bottomMax = 0;
        for (int k = 1; k <= n; k++) {
            bottomMax = Math.max(bottomMax, maxNumbers[n][k]);
        }

        System.out.println(bottomMax);
    }

    private static int readInt() throws IOException {
        int value = 0;
        while (true) {
            // 입력 문자의 ASCII 코드 값.
            // 가령 '0'이 들어왔으면 숫자 0이 아니라 '0'의 ASCII 코드값인 48이다.
            int input = System.in.read();
            if (input == ' ' || input == '\n') { // 개행문자거나 공백이면 연산을 끊음
                return value; // 양수면 그냥 반환
            } else {
                value = value * 10 + (input - 48); // 기존 값을 10배하고 입력된 추가값을 파싱하여 더함
            }
        }
    }
}

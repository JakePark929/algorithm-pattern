package com.jake.argorithm.baekjoon.lv21_dynamicprogramming;

/**
 * [ 정수 삼각형 ]
 *
 *         7
 *       3   8
 *     8   1   0
 *   2   7   4   4
 * 4   5   2   6   5
 *
 * 위 그림은 크기가 5인 정수 삼각형의 한 모습이다.
 *
 * 맨 위층 7부터 시작해서 아래에 있는 수 중 하나를 선택하여 아래층으로 내려올 때,
 * 이제까지 선택된 수의 합이 최대가 되는 경로를 구하는 프로그램을 작성하라.
 * 아래층에 있는 수는 현재 층에서 선택된 수의 대각선 왼쪽 또는 대각선 오른쪽에 있는 것 중에서만 선택할 수 있다.
 *
 * 삼각형의 크기는 1 이상 500 이하이다. 삼각형을 이루고 있는 각 수는 모두 정수이며, 범위는 0 이상 9999 이하이다.
 *
 * 입력
 * 첫째 줄에 삼각형의 크기 n(1 ≤ n ≤ 500)이 주어지고, 둘째 줄부터 n+1번째 줄까지 정수 삼각형이 주어진다.
 *
 * 출력
 * 첫째 줄에 합이 최대가 되는 경로에 있는 수의 합을 출력한다.
 *
 * 작성일 : 2023.09.05
 */
class B1932_IntegerTriangle {
    private static int n;
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        n = read();
    }

    // depth 는 길이(행), idx 는 인덱스(열)를 의미
    int find(int depth, int idx) {
        // 만약 맨 밑(깊이)의 행에 도달하면 해당 인덱스를 반환
        if (depth == n - 1) return dp[depth][idx];

        // 만약 아직 탐색하지 않은 위치라면 다음 행의 양쪽 열 중 최댓값을 구함
        if (dp[depth][idx] == null) {
            /*
             * 바로 다름 행의 인덱스와 그 오른쪽의 인덱스 중
             * 큰 값을 찾아 dp 에 현재 인덱스의 값과 더하여 저장
             */
            dp[depth][idx] = Math.max(find(depth + 1, idx), find(depth + 1, idx + 1) + arr[depth][idx]);

            return dp[depth][idx];
        }
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }
}

package com.jake.argorithm.baekjoon.lv17_combinatorics;

import java.util.Arrays;

/**
 * [ 다리 놓기 ]
 *
 * 재원이는 한 도시의 시장이 되었다.
 * 이 도시에는 도시를 동쪽과 서쪽으로 나누는 큰 일직선 모양의 강이 흐르고 있다.
 * 하지만 재원이는 다리가 없어서 시민들이 강을 건너는데 큰 불편을 겪고 있음을 알고 다리를 짓기로 결심하였다.
 * 강 주변에서 다리를 짓기에 적합한 곳을 사이트라고 한다.
 * 재원이는 강 주변을 면밀히 조사해 본 결과
 * 강의 서쪽에는 N개의 사이트가 있고 동쪽에는 M개의 사이트가 있다는 것을 알았다. (N ≤ M)
 *
 * 재원이는 서쪽의 사이트와 동쪽의 사이트를 다리로 연결하려고 한다.
 * (이때 한 사이트에는 최대 한 개의 다리만 연결될 수 있다.)
 * 재원이는 다리를 최대한 많이 지으려고 하기 때문에 서쪽의 사이트 개수만큼 (N개) 다리를 지으려고 한다.
 * 다리끼리는 서로 겹쳐질 수 없다고 할 때 다리를 지을 수 있는 경우의 수를 구하는 프로그램을 작성하라.
 *
 * 입력
 * 입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다.
 * 그 다음 줄부터 각각의 테스트케이스에 대해 강의 서쪽과 동쪽에 있는 사이트의 개수 정수 N, M (0 < N ≤ M < 30)이 주어진다.
 *
 * 출력
 * 각 테스트 케이스에 대해 주어진 조건하에 다리를 지을 수 있는 경우의 수를 출력한다.
 *
 * 작성일 : 2023.08.25
 */
class B1010_BuildBridge {
    static int[][] dp = new int[30][30];

    public static void main(String[] args) throws Exception {
        int t = read();

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int r = read(), n = read();

            sb.append(combination(n, r)).append('\n');
        }

        System.out.println(sb);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    private static int combination(int n, int r) {
        // 이미 풀린 경우 바로 반환
        if(dp[n][r] > 0) {
            return dp[n][r];
        }

        if (n == r || r == 0) {
            return dp[n][r] = 1;
        }

        return dp[n][r] = combination(n - 1, r - 1) + combination(n - 1, r);
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int[][] dp = new int[30][30];

        for (int i = 0; i < 30; i++)
            dp[i][i] = dp[0][i] = 1;

        for (int n = 1; n < 30; n++)
            for (int m = n + 1; m < 30; m++)
                dp[n][m] = dp[n - 1][m - 1] + dp[n][m - 1];

        System.out.println(Arrays.deepToString(dp));

        int T = read1();

        while (T-- > 0)
            sb.append(dp[read1()][read1()]).append('\n');

        System.out.print(sb);

    }

    private static int read1() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}

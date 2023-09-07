package com.jake.argorithm.baekjoon.lv21_dynamicprogramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * [ 전깃줄 ]
 * <p>
 * 두 전봇대 A와 B 사이에 하나 둘씩 전깃줄을 추가하다 보니 전깃줄이 서로 교차하는 경우가 발생하였다.
 * 합선의 위험이 있어 이들 중 몇 개의 전깃줄을 없애 전깃줄이 교차하지 않도록 만들려고 한다.
 * <p>
 * 예를 들어, < 그림 1 >과 같이 전깃줄이 연결되어 있는 경우
 * A의 1번 위치와 B의 8번 위치를 잇는 전깃줄,
 * A의 3번 위치와 B의 9번 위치를 잇는 전깃줄,
 * A의 4번 위치와 B의 1번 위치를 잇는 전깃줄을 없애면
 * 남아있는 모든 전깃줄이 서로 교차하지 않게 된다.
 * <p>
 * < 그림 1 >
 * <p>
 * 전깃줄이 전봇대에 연결되는 위치는 전봇대 위에서부터 차례대로 번호가 매겨진다.
 * 전깃줄의 개수와 전깃줄들이 두 전봇대에 연결되는 위치의 번호가 주어질 때,
 * 남아있는 모든 전깃줄이 서로 교차하지 않게 하기 위해
 * 없애야 하는 전깃줄의 최소 개수를 구하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에는 두 전봇대 사이의 전깃줄의 개수가 주어진다.
 * 전깃줄의 개수는 100 이하의 자연수이다.
 * 둘째 줄부터 한 줄에 하나씩 전깃줄이 A 전봇대와 연결되는 위치의 번호와
 * B 전봇대와 연결되는 위치의 번호가 차례로 주어진다.
 * 위치의 번호는 500 이하의 자연수이고, 같은 위치에 두 개 이상의 전깃줄이 연결될 수 없다.
 * <p>
 * 출력
 * 첫째 줄에 남아있는 모든 전깃줄이 서로 교차하지 않게 하기 위해 없애야 하는 전깃줄의 최소 개수를 출력한다.
 * <p>
 * 작성일 : 2023.09.07
 */
class B2565_ElectricWire {
    private static Integer[] dp;
    private static int[][] wire;

    public static void main(String[] args) throws Exception {
        int n = read();

        dp = new Integer[n];
        wire = new int[n][2];

        for (int i = 0; i < n; i++) {
            wire[i][0] = read();
            wire[i][1] = read();
        }

        // 첫 번째 원소(A 전봇대)를 기준으로 오름차순으로 정렬
        Arrays.sort(wire, Comparator.comparingInt(o -> o[0]));

        int max = 0;
        /*
         * i 번째 A 전봇대를 기준으로 연결 가능한 개수 탐색 및 최댓값 찾기
         */
        for (int i = 0; i < n; i++) {
            max = Math.max(repair(i), max);
        }

        System.out.println(Arrays.deepToString(wire));
        System.out.println(Arrays.toString(dp));
        System.out.println(n - max);
    }

    private static int repair(int n) {
        // 탐색하지 않았던 위치라면
        if (dp[n] == null) {
            dp[n] = 1; // 최솟값 1로 초기화

            // A 의 n 번째와 이후의 전봇대들 비교
            for (int i = n + 1; i < dp.length; i++) {
                /*
                 * A 전봇대의 N 번째 전선이 연결되어 있는 B 전봇대보다
                 * A 의 i 번째 전봇대의 전선이 이어진 B 전봇대가 뒤에 있을 경우
                 * 전선을 설치할 수 있음
                 */
                if (wire[n][1] < wire[i][1]) {
                    // 연결 가능한 전선의 경우의 수 중 큰 값을 dp 에 저장한다
                    dp[n] = Math.max(dp[n], repair(i) + 1);
                }
            }
        }

        return dp[n];
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws Exception {
        int l = read1();
        int[][] lines = new int[l][2];
        for (int i = 0; i < l; i++) {
            lines[i][0] = read1();
            lines[i][1] = read1();
        }
        Arrays.sort(lines, Comparator.comparingInt(o -> o[0]));

        int[] dp = new int[l];
        Arrays.fill(dp, 1);

        for (int i = 1; i < l; i++)
            for (int j = 0; j < i; j++)
                if (lines[i][1] > lines[j][1])
                    dp[i] = Math.max(dp[i], dp[j] + 1);

        int lineCnt = 0;
        for (int i = 0; i < l; i++)
            lineCnt = Math.max(lineCnt, dp[i]);

        System.out.print(l - lineCnt);
    }

    private static int read1() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}

package com.jake.argorithm.baekjoon.lv19_recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ 하노이탑 이동순서 ]
 * <p>
 * 세 개의 장대가 있고 첫 번째 장대에는 반경이 서로 다른 n개의 원판이 쌓여 있다.
 * 각 원판은 반경이 큰 순서대로 쌓여있다.
 * 이제 수도승들이 다음 규칙에 따라 첫 번째 장대에서 세 번째 장대로 옮기려 한다.
 * <p>
 * 한 번에 한 개의 원판만을 다른 탑으로 옮길 수 있다.
 * 쌓아 놓은 원판은 항상 위의 것이 아래의 것보다 작아야 한다.
 * 이 작업을 수행하는데 필요한 이동 순서를 출력하는 프로그램을 작성하라.
 * 단, 이동 횟수는 최소가 되어야 한다.
 * <p>
 * 아래 그림은 원판이 5개인 경우의 예시이다.
 * <p>
 * 입력
 * 첫째 줄에 첫 번째 장대에 쌓인 원판의 개수 N (1 ≤ N ≤ 20)이 주어진다.
 * <p>
 * 출력
 * 첫째 줄에 옮긴 횟수 K를 출력한다.
 * 두 번째 줄부터 수행 과정을 출력한다.
 * 두 번째 줄부터 K개의 줄에 걸쳐 두 정수 A B를 빈칸을 사이에 두고 출력하는데,
 * 이는 A번째 탑의 가장 위에 있는 원판을 B번째 탑의 가장 위로 옮긴다는 뜻이다.
 * <p>
 * 작성일 : 2023.08.28
 */
public class B11729_MovingOrderHanoiTower {
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        sb.append((int) (Math.pow(2, n) - 1)).append('\n');

        Hanoi(n, 1, 2, 3);
        System.out.println(sb.toString().trim());
    }

    private static void Hanoi(int n, int start, int mid, int to) {
        // 이동할 원반의 수가 1개일 경우
        if (n == 1) {
            sb.append(start).append(" ").append(to).append('\n');
            return;
        }

        // A -> C
        // STEP 1 : n - 1 개를 A에서 B로 이동 (= start 지점의 n - 1 개의 원판을 mid 로 옮김)
        Hanoi(n - 1, start, to, mid);

        // STEP 2 : 1 개를 A에서 C로 이동 (= start 지점의 n 번째 원판을 to 로 옮김)
        sb.append(start).append(" ").append(to).append('\n');

        // STEP 3 : n - 1 개를 B에서 C로 이동 (= mid 지점의 n - 1 개의 원판을 to 로 옮김)
        Hanoi(n - 1, mid, start, to);
    }

    // 다른 사람의 풀이 1
    private static int N;
    private static int[] counts;
    private static StringBuilder[][] str;

    public static void main1(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        counts = new int[N + 1];
        str = new StringBuilder[N + 1][6];
        counts(N);
        str[1][0] = new StringBuilder("1 2\n");
        str[1][1] = new StringBuilder("2 1\n");
        str[1][2] = new StringBuilder("1 3\n");
        str[1][3] = new StringBuilder("3 1\n");
        str[1][4] = new StringBuilder("2 3\n");
        str[1][5] = new StringBuilder("3 2\n");

        System.out.println(counts[N]);
        System.out.println(build(N, 1, 3));
    }

    private static void counts(int n) {
        counts[1] = 1;
        for (int i = 2; i <= N; i++) {
            counts[i] = counts[i - 1] * 2 + 1;
        }
    }

    private static StringBuilder build(int height, int SP, int DP) {
        int index = 2 * (SP + DP - 3) + (SP > DP ? 1 : 0);

        if (str[height][index] == null) {
            int IP = 6 - SP - DP;
            str[height][index] = build(height - 1, SP, IP).append(str[1][index]).append(build(height - 1, IP, DP));
        }
        return new StringBuilder(str[height][index]);
    }
}

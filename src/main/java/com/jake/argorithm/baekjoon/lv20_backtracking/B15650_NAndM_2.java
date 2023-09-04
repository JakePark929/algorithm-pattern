package com.jake.argorithm.baekjoon.lv20_backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ N과 M 2 ]
 * <p>
 * 자연수 N과 M이 주어졌을 때,
 * 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
 * 고른 수열은 오름차순이어야 한다.
 * <p>
 * 입력
 * 첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
 * <p>
 * 출력
 * 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다.
 * 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
 * <p>
 * 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
 * <p>
 * 작성일 : 2023.09.04
 */
class B15650_NAndM_2 {
    private static int[] arr;
    private static int n, m;
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];

        dfs(1, 0);

        System.out.println(sb);
    }

    private static void dfs(int at, int depth) {
        if (depth == m) {
            for (int val : arr) {
                sb.append(val).append(" ");
            }
            sb.append('\n');
            return;
        }

        for (int i = at; i <= n; i++) {
            arr[depth] = i;
            dfs(i + 1, depth + 1);
        }
    }

    // 다른 사람의 풀이 1
    static StringBuilder sb1 = new StringBuilder();
    static int N, M;
    static int[] selected;

    static void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            selected = new int[M + 1];

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void rec_func(int k) {
        if (k == M + 1) {
            for (int i = 1; i <= M; i++) sb1.append(selected[i]).append(' ');
            sb1.append('\n');
        } else {
            for (int cand = selected[k - 1] + 1; cand <= N; cand++) {
                selected[k] = cand;
                rec_func(k + 1);
                selected[k] = 0;

            }
        }
    }

    public static void main1(String[] args) {
        input();
        rec_func(1);
        System.out.println(sb1.toString());
    }
}

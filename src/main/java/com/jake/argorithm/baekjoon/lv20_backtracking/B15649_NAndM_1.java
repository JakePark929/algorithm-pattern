package com.jake.argorithm.baekjoon.lv20_backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ N과 M ]
 *
 * 자연수 N과 M이 주어졌을 때,
 * 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
 *
 * 입력
 * 첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
 *
 * 출력
 * 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다.
 * 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
 *
 * 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
 *
 * 작성일 : 2023.08.31
 */
class B15649_NAndM_1 {
    private static int[] arr;
    private static boolean[] visit;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[m];
        visit = new boolean[n];
        dfs(n, m, 0);

        System.out.println(sb);
    }

    private static void dfs(int n, int m, int depth) {
        if (depth == m) {
            for (int val : arr) {
                sb.append(val).append(" ");
            }
            sb.append('\n');
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                arr[depth] = i + 1;
                dfs(n, m, depth + 1);
                visit[i] = false;
            }
        }
    }

    // 다른 사람의 풀이 1
    static int N, M;
    static char[] arr1;
    static boolean[] ck;
    static StringBuilder sb1 = new StringBuilder();

    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr1 = new char[2 * M];
        ck = new boolean[N + 1];
        back(0);
        System.out.print(sb);
    }

    static void back(int idx) {
        if (idx >= M) {
            arr[2 * M - 1] = '\n';
            sb.append(arr);
            return ;
        }

        int i = 0;
        for (i = 1; i <= N; i++) {
            if (!ck[i]) {
                ck[i] = true;
                arr[2 * idx] = (char)(i + '0');
                arr[2 * idx + 1] = ' ';
                back(idx + 1);
                ck[i] = false;
            }
        }
    }
}

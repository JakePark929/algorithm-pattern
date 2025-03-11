package com.jake.algorithm.baekjoon.lv20_backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ N과 M 3 ]
 * <p>
 * 자연수 N과 M이 주어졌을 때,
 * 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 * 1부터 N까지 자연수 중에서 M개를 고른 수열
 * 같은 수를 여러 번 골라도 된다.
 * <p>
 * 입력
 * 첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 7)
 * <p>
 * 출력
 * 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다.
 * 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
 * <p>
 * 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
 * <p>
 * 작성일 : 2023.09.04
 */
class B15651_NAndM_3 {
    private static int[] arr;
    private static int n, m;
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];

        dfs(0, 0);
//        dfs1(0);

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

        for (int i = 0; i < n; i++) {
            arr[depth] = i + 1;
            dfs(i + 1, depth + 1);
        }
    }

    // 다른 사람의 풀이 1
    private static void dfs1(int depth) {

        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(arr[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = 1; i <= n; i++) {
            arr[depth] = i;
            dfs1(depth + 1);
        }
    }

    // 다른 사람의 풀이 2
    static StringBuilder sb2;
    static char[] arr2;
    static int n2;
    static int m2;

    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        sb2 = new StringBuilder();
        n2 = Integer.parseInt(st.nextToken());
        m2 = Integer.parseInt(st.nextToken());

        arr2 = new char[2 * m2];
        for (int i = 1; i < 2 * m2 - 1; i += 2) arr2[i] = ' ';
        arr2[2 * m2 - 1] = '\n';

        backtracking(0);

        System.out.println(sb2);
    }

    public static void backtracking(int pos) {
        if (pos == m2)
            sb2.append(arr2);
        else {
            for (int i = 1; i <= n2; i++) {
                arr2[pos * 2] = ((char) (i + '0'));
                backtracking(pos + 1);
            }
        }
    }
}

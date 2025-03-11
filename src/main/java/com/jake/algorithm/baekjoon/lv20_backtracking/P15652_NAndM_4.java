package com.jake.algorithm.baekjoon.lv20_backtracking;

import java.io.*;
import java.util.StringTokenizer;

/**
 * [ N과 M 4 ]
 *
 * 자연수 N과 M이 주어졌을 때,
 * 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 *
 * 1부터 N까지 자연수 중에서 M개를 고른 수열
 * 같은 수를 여러 번 골라도 된다.
 * 고른 수열은 비내림차순이어야 한다.
 * 길이가 K인 수열 A가 A1 ≤ A2 ≤ ... ≤ AK-1 ≤ AK를 만족하면, 비내림차순이라고 한다.
 *
 * 입력
 * 첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
 *
 * 출력
 * 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
 * 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
 *
 * 작성일 : 2023.09.04
 */
class P15652_NAndM_4 {
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

        for (int i = at; i < n; i++) {
            arr[depth] = i + 1;
            dfs(i, depth + 1);
        }
    }

    // 다른 사람의 풀이 1
    private static void dfs1(int at, int depth) {

        if (depth == m) {
            for (int val : arr) {
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = at; i <= n; i++) {
            arr[depth] = i;
            dfs(i, depth + 1);
        }

    }

    // 다른 사람의 풀이 2
    private static int[] valueMemo;
    private static BufferedWriter bw;

    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "utf-8"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        // 1~N 까지 중 M개를 고름

        valueMemo = new int[M];

        dfs(0,M,1,N);
        bw.flush();
    }

    private static void dfs(int nowDepth, int lastDepth, int nowNum, int lastNum) throws IOException{
        if (nowDepth == lastDepth) {
            print();
            return;
        }

        for(int i = nowNum ; i <= lastNum ; i++) {
            valueMemo[nowDepth] = i;
            dfs(nowDepth + 1, lastDepth, i, lastNum);
        }
    }

    private static void print() throws IOException{
        for(int i = 0 ; i < valueMemo.length ; i++) {
            bw.write(valueMemo[i] + '0');
            bw.write(' ');
        }
        bw.write('\n');
    }
}

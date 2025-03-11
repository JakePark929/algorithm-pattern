package com.jake.algorithm.baekjoon.lv31_Graph_Circulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B24480_AlgorithmLecture_DFS_2 {
    static int cnt;
    static int[] checked;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer strTo;

        strTo = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(strTo.nextToken());
        int m = Integer.parseInt(strTo.nextToken());
        int r = Integer.parseInt(strTo.nextToken());

        checked = new int[n + 1];

        // 그래프 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // list 에 값 저장
        for (int i = 0; i < m; i++) {
            strTo = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(strTo.nextToken());
            int v = Integer.parseInt(strTo.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        for (int i = 1; i <= n; i++) {
            graph.get(i).sort(Collections.reverseOrder());
        }

        cnt = 1;
        dfs(r);

        for (int i = 1; i < checked.length; i++) {
            sb.append(checked[i]).append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int node) {
        checked[node] = cnt;

        for (int i = 0; i < graph.get(node).size(); i++) {
            int newNode = graph.get(node).get(i);
            if (checked[newNode] == 0) {
                cnt++;
                dfs(newNode);
            }
        }
    }

    // 다른 사람의 풀이 1
    public static List<Integer>[] graphs;
    public static int[] visited;
    public static int cnt1;

    public static void main1(String[] args) throws IOException {
        int n = read();
        int m = read();
        int r = read();

        graphs = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graphs[i] = new ArrayList<>();
        }
        while (m-- > 0) {
            int a = read();
            int b = read();
            graphs[a].add(b);
            graphs[b].add(a);
        }
        for (int i = 1; i <= n; i++) {
            Collections.sort(graphs[i], Collections.reverseOrder());
        }

        visited = new int[n + 1];
        cnt1 = 0;
        dfs2(r);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(visited[i]).append('\n');
        }
        System.out.println(sb);
    }

    public static void dfs2(int r) {
        visited[r] = ++cnt1;
        for (int next : graphs[r]) {
            if (visited[next] == 0) dfs2(next);
        }
    }

    public static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}

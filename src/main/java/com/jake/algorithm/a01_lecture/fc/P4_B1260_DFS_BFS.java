package com.jake.algorithm.a01_lecture.fc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [ DFS 와 BFS]
 *
 * 문제
 * 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오.
 * 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고,
 * 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.
 *
 * 입력
 * 첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다.
 * 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다.
 * 입력으로 주어지는 간선은 양방향이다.
 *
 * 출력
 * 첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다.
 * V부터 방문된 점을 순서대로 출력하면 된다.
 *
 * 작성일 : 2023.03.03
 */
public class P4_B1260_DFS_BFS {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, M, V;
    static int[][] adj;
    static boolean[] visit;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        V = scan.nextInt();
        adj = new int[N + 1][N + 1];

        for (int i = 1; i <= M; i++) {
            int x = scan.nextInt(), y = scan.nextInt();
            adj[x][y] = 1;
            adj[y][x] = 1;
        }
    }

    static void dfs(int x) {
        visit[x] = true;
        sb.append(x).append(" ");

        for (int y = 1; y <= N; y++) {
            if (adj[x][y] == 0) {
                continue;
            }

            if (visit[y]) {
                continue;
            }

            dfs(y);
        }
    }

    static void bfs(int x) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(x);
        visit[x] = true;

        while (!queue.isEmpty()) {
            x = queue.poll();
            sb.append(x).append(" ");
            for (int y = 1; y <= N; y++) {
                if (adj[x][y] == 0) {
                    continue;
                }

                if (visit[y]) {
                    continue;
                }

                queue.add(y);
                visit[y] = true;
            }
        }
    }

    static void process() {
        visit = new boolean[N + 1];
        dfs(V);

        sb.append('\n');
        for (int i = 1; i <= N; i++) {
            visit[i] = false;
        }

        bfs(V);

        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        input();

        process();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

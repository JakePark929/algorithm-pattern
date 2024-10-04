package com.jake.argorithm.baekjoon.lv31_Graph_Circulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B24479_AlgorithmLecture_DFS_1 {
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
            Collections.sort(graph.get(i));
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
    static int N;
    static int M;
    static int R;
    static int a;
    static int b;
    static int cnt2;
    static int nextNode;
    static int[] order;
    static boolean[] visit;
    static PriorityQueue<Integer>[] list;

    // 빠른 입력을 위한 함수
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
    public static void DFS(int node) {
        visit[node] = true;
        order[node] = ++cnt2;
        while(!list[node].isEmpty())
        {
            nextNode = list[node].poll();
            if(!visit[nextNode])
                DFS(nextNode);
        }
    }
    public static void main1(String[] args)throws Exception{
        N 					= read();
        M 					= read();
        R 					= read();
        order 				= new int[N+1];
        list 				= new PriorityQueue[N+1];
        visit				= new boolean[N+1];

        for(int i=1; i<=N; i++)
            list[i] = new PriorityQueue<>();

        for(int i=0; i<M; i++) {
            a = read();
            b = read();
            list[a].add(b);
            list[b].add(a);
        }


        DFS(R);

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++)
            sb.append(order[i]).append('\n');
        System.out.println(sb);
    }
}

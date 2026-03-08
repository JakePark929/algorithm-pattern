package com.jake.algorithm.a01_lecture.fc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [ 최소비용 구하기 ]
 *
 * 문제
 * N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 M개의 버스가 있다.
 * 우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다.
 * A번째 도시에서 B번째 도시까지 가는데 드는 최소비용을 출력하여라.
 * 도시의 번호는 1부터 N까지이다.
 *
 * 입력
 * 첫째 줄에 도시의 개수 N(1 ≤ N ≤ 1,000)이 주어지고 둘째 줄에는 버스의 개수 M(1 ≤ M ≤ 100,000)이 주어진다.
 * 그리고 셋째 줄부터 M+2줄까지 다음과 같은 버스의 정보가 주어진다.
 * 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다. 그리고 그 다음에는 도착지의 도시 번호가 주어지고 또 그 버스 비용이 주어진다.
 * 버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수이다.
 *
 * 그리고 M+3째 줄에는 우리가 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시번호가 주어진다.
 * 출발점에서 도착점을 갈 수 있는 경우만 입력으로 주어진다.
 *
 * 출력
 * 첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.
 *
 * 작성일 : 2026.03.06
 */
public class P8_B1916_GetMinCost {
    static FastReader scan = new FastReader();

    static int N, M, start, end;
    static int[] distance;
    static ArrayList<Edge>[] edges;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        distance = new int[N + 1];
        edges = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            int from = scan.nextInt();
            int to = scan.nextInt();
            int weight = scan.nextInt();
            edges[from].add(new Edge(to, weight));
        }

        start = scan.nextInt();
        end = scan.nextInt();
    }

    static void dijkstra(int start) {
        // 1) 모든 정점까지에 대해 거리 무한대로 초기화
        for (int i = 1; i <= N; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        // 최소 힙 생성
        PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.distance));

        // 시작점에 대한 정보를 기록에 추가, 거리 배열에 갱신
        pq.add(new Info(start, 0));
        distance[start] = 0;

        // 거리 정보 모두 소진 될 때 까지 거리 갱신 반복
        while(!pq.isEmpty()) { // 2)
            Info info = pq.poll(); // 3)

            if (distance[info.index] < info.distance) {
                continue;
            }

            for (Edge edge : edges[info.index]) {
                if (distance[info.index] + edge.weight >= distance[edge.to]) {
                    continue;
                }

                distance[edge.to] = distance[info.index] + edge.weight;
                pq.add(new Info(edge.to, distance[edge.to]));
            }
        }
    }

    static void process() {
        dijkstra(start);

        System.out.print(distance[end]);
    }

    public static void main(String[] args) {
        input();

        process();
    }

    static class Edge {
        public int to;
        public int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Info {
        public int index;
        public int distance;

        public Info(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }

    private static class FastReader {
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

package com.jake.argorithm.a00_privatetest.template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * [ 다익스트라 ]
 *
 *  그래프에서 최단 경로를 구하는 알고리즘.
 *  특히 음의 가중치가 없는 그래프에서 특정 노드에서 다른 노드까지의 최단 거리를 계산할 때 사용된다.
 *
 * 작성일 : 24.10.05
 */
public class Dijkstra {
    // 다익스트라 메소드
    public int[] dijkstra(int V, List<List<Node>> adj, int start) {
        // 거리 배열, 초기값은 무한대로 설정
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // 우선순위 큐 (최소 힙) 사용
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        pq.add(new Node(start, 0));

        // 다익스트라 알고리즘 실행
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            // 현재 정점의 모든 인접 노드를 확인
            for (Node neighbor : adj.get(u)) {
                int v = neighbor.vertex;
                int weight = neighbor.cost;

                // 더 짧은 경로를 발견했을 경우 갱신
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        // 최단 경로가 저장된 거리 배열 반환
        return dist;
    }

    // 노드를 표현하는 클래스
    static class Node {
        int vertex;
        int cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }

    // 테스트 메소드
    public static void main(String[] args) {
        int V = 5; // 정점 수
        List<List<Node>> adj = new ArrayList<>();

        // 인접 리스트 초기화
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // 간선 추가 (양방향 그래프인 경우 양쪽으로 추가)
        adj.get(0).add(new Node(1, 10));
        adj.get(0).add(new Node(4, 5));
        adj.get(1).add(new Node(2, 1));
        adj.get(1).add(new Node(4, 2));
        adj.get(2).add(new Node(3, 4));
        adj.get(3).add(new Node(0, 7));
        adj.get(3).add(new Node(2, 6));
        adj.get(4).add(new Node(1, 3));
        adj.get(4).add(new Node(2, 9));
        adj.get(4).add(new Node(3, 2));

        // 시작 정점 설정
        int start = 0;

        // 다익스트라 알고리즘 실행
        Dijkstra dijkstra = new Dijkstra();
        int[] dist = dijkstra.dijkstra(V, adj, start);

        // 최단 거리 출력
        System.out.println("Vertex\tDistance from Source");
        for (int i = 0; i < dist.length; i++) {
            System.out.println(i + "\t\t" + dist[i]);
        }
    }
}

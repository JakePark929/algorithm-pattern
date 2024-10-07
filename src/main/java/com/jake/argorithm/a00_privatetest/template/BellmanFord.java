package com.jake.argorithm.a00_privatetest.template;

import java.util.Arrays;

/**
 * [ 벨만-포드 알고리즘 ]
 *
 * 가중치가 있는 그래프에서 최단 경로를 찾는 알고리즘.
 * 음수 가중치가 있는 간선도 처리할 수 있지만, 음수 사이클이 존재하는 경우에는 작동하지 않는다.
 *
 * 작성일 : 24.10.05
 */
public class BellmanFord {
    // 벨만-포드 메소드
    public static void bellmanFord(int V, Edge[] edges, int startVertex) {
        int[] distances = new int[V];

        // 초기 거리 설정
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startVertex] = 0;

        // 모든 간선에 대해 반복
        for (int i = 1; i < V; i++) {
            for (Edge edge : edges) {
                if (distances[edge.source] != Integer.MAX_VALUE &&
                        distances[edge.source] + edge.weight < distances[edge.destination]) {
                    distances[edge.destination] = distances[edge.source] + edge.weight;
                }
            }
        }

        // 음수 사이클 검출
        for (Edge edge : edges) {
            if (distances[edge.source] != Integer.MAX_VALUE &&
                    distances[edge.source] + edge.weight < distances[edge.destination]) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }

        // 결과 출력
        printSolution(distances);
    }

    // 최단 경로 결과 출력
    private static void printSolution(int[] distances) {
        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < distances.length; i++) {
            System.out.println(i + "\t\t" + distances[i]);
        }
    }

    // 간선 정보를 담는 배열
    static class Edge {
        int source; // 시작 정점
        int destination; // 끝 정점
        int weight; // 가중치

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    // 메인 메소드
    public static void main(String[] args) {
        int V = 5; // 정점 수
        int E = 8; // 간선 수
        Edge[] edges = new Edge[E];

        // 그래프에 간선 추가 (source, destination, weight)
        edges[0] = new Edge(0, 1, -1);
        edges[1] = new Edge(0, 2, 4);
        edges[2] = new Edge(1, 2, 3);
        edges[3] = new Edge(1, 3, 2);
        edges[4] = new Edge(1, 4, 2);
        edges[5] = new Edge(3, 2, 5);
        edges[6] = new Edge(3, 1, 1);
        edges[7] = new Edge(4, 3, -3);

        // 시작 정점에서 벨만-포드 실행
        bellmanFord(V, edges, 0);
    }
}

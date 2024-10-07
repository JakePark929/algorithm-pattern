package com.jake.argorithm.a00_privatetest.template;

/**
 * [ 플로이드-워셜 알고리즘 ]
 *
 * 모든 정점 간의 최단 경로를 구하는 알고리즘.
 * 다익스트라 알고리즘과 달리, 플로이드-워셜은 음수 간선이 있는 경우에도 사용할 수 있으며,
 * 모든 정점 쌍 간의 최단 경로를 구하는 데 적합하다.
 *
 * 작성일 : 24.10.05
 */
public class FloydWarshall {
    // 플로이드-워셜
    public static void floydWarshall(int[][] graph) {
        int V = graph.length;
        int[][] dist = new int[V][V];

        // 초기화: 그래프의 가중치를 그대로 dist 배열에 복사
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        // 플로이드-워셜 알고리즘 실행
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    // dist[i][j]는 i에서 j까지 가는 최단 거리
                    // dist[i][k] + dist[k][j]를 거치는 경로가 더 짧으면 갱신
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        // 최단 거리 출력
        printSolution(dist);
    }

    // 최단 경로 행렬 출력 메소드
    public static void printSolution(int[][] dist) {
        int V = dist.length;
        System.out.println("The following matrix shows the shortest distances between every pair of vertices:");
        for (int[] ints : dist) {
            for (int j = 0; j < V; j++) {
                if (ints[j] == Integer.MAX_VALUE) {
                    System.out.print("INF ");
                } else {
                    System.out.print(ints[j] + "   ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int INF = Integer.MAX_VALUE;

        // 그래프를 인접 행렬로 표현 (0은 자기 자신으로의 경로, 그 외는 간선 가중치)
        int[][] graph = {
                {0, 3, INF, 5},
                {2, 0, INF, 4},
                {INF, 1, 0, INF},
                {INF, INF, 2, 0}
        };

        // 플로이드-워셜 알고리즘 실행
        floydWarshall(graph);
    }
}

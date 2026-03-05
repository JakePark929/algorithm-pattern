package com.jake.algorithm.a01_lecture.fc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [ 연구소 ]
 *
 * 문제
 * 인체에 치명적인 바이러스를 연구하던 연구소에서 바이러스가 유출되었다.
 * 다행히 바이러스는 아직 퍼지지 않았고, 바이러스의 확산을 막기 위해서 연구소에 벽을 세우려고 한다.
 * 연구소는 크기가 N×M인 직사각형으로 나타낼 수 있으며, 직사각형은 1×1 크기의 정사각형으로 나누어져 있다.
 * 연구소는 빈 칸, 벽으로 이루어져 있으며, 벽은 칸 하나를 가득 차지한다.
 * 일부 칸은 바이러스가 존재하며, 이 바이러스는 상하좌우로 인접한 빈 칸으로 모두 퍼져나갈 수 있다.
 * 새로 세울 수 있는 벽의 개수는 3개이며, 꼭 3개를 세워야 한다.
 * 예를 들어, 아래와 같이 연구소가 생긴 경우를 살펴보자.
 *
 * 2 0 0 0 1 1 0
 * 0 0 1 0 1 2 0
 * 0 1 1 0 1 0 0
 * 0 1 0 0 0 0 0
 * 0 0 0 0 0 1 1
 * 0 1 0 0 0 0 0
 * 0 1 0 0 0 0 0
 *
 * 이때, 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 곳이다. 아무런 벽을 세우지 않는다면, 바이러스는 모든 빈 칸으로 퍼져나갈 수 있다.
 * 2행 1열, 1행 2열, 4행 6열에 벽을 세운다면 지도의 모양은 아래와 같아지게 된다.
 *
 * 2 1 0 0 1 1 0
 * 1 0 1 0 1 2 0
 * 0 1 1 0 1 0 0
 * 0 1 0 0 0 1 0
 * 0 0 0 0 0 1 1
 * 0 1 0 0 0 0 0
 * 0 1 0 0 0 0 0
 *
 * 바이러스가 퍼진 뒤의 모습은 아래와 같아진다.
 *
 * 2 1 0 0 1 1 2
 * 1 0 1 0 1 2 2
 * 0 1 1 0 1 2 2
 * 0 1 0 0 0 1 2
 * 0 0 0 0 0 1 1
 * 0 1 0 0 0 0 0
 * 0 1 0 0 0 0 0
 *
 * 벽을 3개 세운 뒤, 바이러스가 퍼질 수 없는 곳을 안전 영역이라고 한다. 위의 지도에서 안전 영역의 크기는 27이다.
 * 연구소의 지도가 주어졌을 때 얻을 수 있는 안전 영역 크기의 최댓값을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다. (3 ≤ N, M ≤ 8)
 * 둘째 줄부터 N개의 줄에 지도의 모양이 주어진다. 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 위치이다.
 * 2의 개수는 2보다 크거나 같고, 10보다 작거나 같은 자연수이다.
 * 빈 칸의 개수는 3개 이상이다.
 *
 * 출력
 * 첫째 줄에 얻을 수 있는 안전 영역의 최대 크기를 출력한다.
 *
 * 작성일 : 2026.03.05
 */
public class P5_B14502_Laboratory {
    static FastReader scan = new FastReader();

    static int N, M, B, ans;
    static int[][] A, blank;
    static boolean[][] visit;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        A = new int[N + 1][M + 1];
        blank = new int[N * M + 1][2];
        visit = new boolean[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                A[i][j] = scan.nextInt();
            }
        }
    }

    // 바이러스 퍼트리기!
    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();

        // 모든 바이러스가 시작점으로 가능하므로, 전부 큐에 넣어준다.
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                visit[i][j] = false;

                if (A[i][j] == 2) {
                    queue.add(i);
                    queue.add(j);
                    visit[i][j] = true;
                }
            }
        }

        // BFS
        while (!queue.isEmpty()) {
            int x = queue.poll(), y = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0], ny = y + dir[k][1];

                if (nx < 1 || ny < 1 || nx > N || ny > M) {
                    continue;
                }

                if (A[nx][ny] != 0) {
                    continue;
                }

                if (visit[nx][ny]) {
                    continue;
                }

                visit[nx][ny] = true;
                queue.add(nx);
                queue.add(ny);
            }
        }

        // 탐색이 종료되면, 안전 영역 넓이를 계산하고, 정답을 갱신한다.
        int count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (A[i][j] == 0 && !visit[i][j]) {
                    count++;
                }
            }
        }

        ans = Math.max(ans, count);
    }

    static void dfs(int idx, int selectedCount) {
        // 3개의 벽을 모두 세운 상태
        if (selectedCount == 3) {
            bfs();
            return;
        }

        // 더이상 세울 수 있는 벽이 없는 상태
        if (idx > B) {
            return;
        }

        A[blank[idx][0]][blank[idx][1]] = 1;
        dfs(idx + 1, selectedCount + 1);

        A[blank[idx][0]][blank[idx][1]] = 0;
        dfs(idx + 1, selectedCount);
    }

    static void process() {
        // 모든 벽의 위치를 먼저 모아놓자
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (A[i][j] == 0) {
                    B++;
                    blank[B][0] = i;
                    blank[B][1] = j;
                }
            }
        }

        // 벽을 3개 세우는 모든 방법 확인
        dfs(1, 0);

        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();

        process();
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

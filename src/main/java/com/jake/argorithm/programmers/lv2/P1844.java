package com.jake.argorithm.programmers.lv2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * [ 게임 맵 최단거리 ]
 *
 * ROR 게임은 두 팀으로 나누어서 진행하며, 상대 팀 진영을 먼저 파괴하면 이기는 게임입니다.
 * 따라서, 각 팀은 상대 팀 진영에 최대한 빨리 도착하는 것이 유리합니다.
 *
 * 지금부터 당신은 한 팀의 팀원이 되어 게임을 진행하려고 합니다.
 * 다음은 5 x 5 크기의 맵에, 당신의 캐릭터가 (행: 1, 열: 1) 위치에 있고,
 * 상대 팀 진영은 (행: 5, 열: 5) 위치에 있는 경우의 예시입니다.
 *
 * 위 그림에서 검은색 부분은 벽으로 막혀있어 갈 수 없는 길이며, 흰색 부분은 갈 수 있는 길입니다.
 * 캐릭터가 움직일 때는 동, 서, 남, 북 방향으로 한 칸씩 이동하며, 게임 맵을 벗어난 길은 갈 수 없습니다.
 * 아래 예시는 캐릭터가 상대 팀 진영으로 가는 두 가지 방법을 나타내고 있습니다.
 *
 * 첫 번째 방법은 11개의 칸을 지나서 상대 팀 진영에 도착했습니다.
 *
 * 두 번째 방법은 15개의 칸을 지나서 상대팀 진영에 도착했습니다.
 *
 * 위 예시에서는 첫 번째 방법보다 더 빠르게 상대팀 진영에 도착하는 방법은 없으므로,
 * 이 방법이 상대 팀 진영으로 가는 가장 빠른 방법입니다.
 *
 * 만약, 상대 팀이 자신의 팀 진영 주위에 벽을 세워두었다면 상대 팀 진영에 도착하지 못할 수도 있습니다.
 * 예를 들어, 다음과 같은 경우에 당신의 캐릭터는 상대 팀 진영에 도착할 수 없습니다.
 *
 * 게임 맵의 상태 maps가 매개변수로 주어질 때, 캐릭터가 상대 팀 진영에 도착하기 위해서
 * 지나가야 하는 칸의 개수의 최솟값을 return 하도록 solution 함수를 완성해주세요.
 * 단, 상대 팀 진영에 도착할 수 없을 때는 -1을 return 해주세요.
 *
 * 작성일 : 24.10.04
 */
public class P1844 {
    public int solution(int[][] maps) {
        // 이동 방향 (상, 하, 좌, 우)
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int n = maps.length;
        int m = maps[0].length;

        // BFS 를 위한 큐 초기화
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0}); // 시작점 (0, 0)

        // 시작점에서의 거리 (1로 초기화)
        maps[0][0] = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            // 도착점에 도달하면 거리 반환
            if (x == n - 1 && y == m - 1) {
                return maps[x][y];
            }

            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 맵의 범위를 벗어나지 않고, 벽이 없는 경우
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && maps[nx][ny] == 1) {
                    // 현재 거리 + 1로 업데이트
                    maps[nx][ny] = maps[x][y] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        // 도착할 수 없는 경우
        return -1;
    }

    // 다른 사람의 풀이 1
    public int solution1(int[][] maps) {
        int rows = maps.length;
        int cols = maps[0].length;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1}); // 시작 위치와 거리

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int distance = current[2];

            if (row == rows - 1 && col == cols - 1) {
                return distance; // 목적지에 도달한 경우 최단거리 반환
            }

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && maps[newRow][newCol] == 1) {
                    maps[newRow][newCol] = 0; // 방문한 위치는 재방문하지 않도록 표시
                    queue.offer(new int[]{newRow, newCol, distance + 1});
                }
            }
        }

        return -1; // 목적지에 도달하지 못한 경우
    }

    public static void main(String[] args) {
        int[][] map = {
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 0, 1},
        };
        final int solution = new P1844().solution(map);

        System.out.println(solution);
    }
}

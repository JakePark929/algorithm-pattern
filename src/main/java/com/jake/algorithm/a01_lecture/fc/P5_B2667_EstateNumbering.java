package com.jake.algorithm.a01_lecture.fc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * [ 단지 번호 붙이기 ]
 *
 * 문제
 * <그림 1>과 같이 정사각형 모양의 지도가 있다. 1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다.
 * 철수는 이 지도를 가지고 연결된 집의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다.
 * 여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다.
 * 대각선상에 집이 있는 경우는 연결된 것이 아니다. <그림 2>는 <그림 1>을 단지별로 번호를 붙인 것이다.
 * 지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고,
 * 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.
 *
 * 출력
 * 첫 번째 줄에는 총 단지수를 출력하시오. 그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.
 *
 * 작성일 : 2026.03.04
 */
public class P5_B2667_EstateNumbering {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, groupCount;
    static String[] a;
    static boolean[][] visit;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static ArrayList<Integer> group;

    static void input() {
        N = scan.nextInt();
        a = new String[N];

        for (int i = 0; i < N; i++) {
            a[i] = scan.nextLine();
        }
        visit = new boolean[N][N];
    }
    
    // x, y 를 갈 수 있다는 걸 알고 방문한 상태
    static void dfs(int x, int y) {
        // 단지에 속한 집의 개수 증가, visit 체크하기
        groupCount++;
        visit[x][y] = true;

        // 인접한 집으로 새로운 방문하기
        for (int k = 0; k < 4; k++) {
            // (x, y) -> dir[k]
            int nx = x + dir[k][0], ny = y + dir[k][1];

            if (nx < 0 || ny < 0 || ny >= N || nx >= N) {
                continue;
            }

            if (a[nx].charAt(ny) == '0') {
                continue;
            }

            if (visit[nx][ny]) {
                continue;
            }

            dfs(nx, ny);
        }
    }

    static void process() {
        group = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j] && a[i].charAt(j) == '1') {
                    // 갈 수 있는 칸인데, 이미 방문처리 된, 즉 새롭게 만난 단지인 경우
                    groupCount = 0;
                    dfs(i, j);
                    group.add(groupCount);
                }
            }
        }

        // 찾은 단지의 정보를 출력하기
        Collections.sort(group);
        sb.append(group.size()).append('\n');

        for (int count : group) {
            sb.append(count).append('\n');
        }

        System.out.println(sb.toString());
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

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}

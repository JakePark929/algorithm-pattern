package com.jake.argorithm.baekjoon.lv20_backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 스타트와 링크 ]
 *
 * 오늘은 스타트링크에 다니는 사람들이 모여서 축구를 해보려고 한다.
 * 축구는 평일 오후에 하고 의무 참석도 아니다.
 * 축구를 하기 위해 모인 사람은 총 N 명이고 신기하게도 N은 짝수이다.
 * 이제 N/2명으로 이루어진 스타트 팀과 링크 팀으로 사람들을 나눠야 한다.
 *
 * BOJ 를 운영하는 회사 답게 사람에게 번호를 1부터 N 까지로 배정했고, 아래와 같은 능력치를 조사했다.
 * 능력치 Sij 는 i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치이다.
 * 팀의 능력치는 팀에 속한 모든 쌍의 능력치 Sij 의 합이다.
 * Sij 는 Sji 와 다를 수도 있으며, i번 사람과 j번 사람이 같은 팀에 속했을 때,
 * 팀에 더해지는 능력치는 Sij 와 Sji 이다.
 *
 * N=4이고, S가 아래와 같은 경우를 살펴보자.
 *
 * i\j	1	2	3	4
 * 1	 	1	2	3
 * 2	4	 	5	6
 * 3	7	1	 	2
 * 4	3	4	5
 *
 * 예를 들어, 1, 2번이 스타트 팀, 3, 4번이 링크 팀에 속한 경우에 두 팀의 능력치는 아래와 같다.
 *
 * 스타트 팀: S12 + S21 = 1 + 4 = 5
 * 링크 팀: S34 + S43 = 2 + 5 = 7
 *
 * 1, 3번이 스타트 팀, 2, 4번이 링크 팀에 속하면, 두 팀의 능력치는 아래와 같다.
 *
 * 스타트 팀: S13 + S31 = 2 + 7 = 9
 * 링크 팀: S24 + S42 = 6 + 4 = 10
 *
 * 축구를 재미있게 하기 위해서 스타트 팀의 능력치와 링크 팀의 능력치의 차이를 최소로 하려고 한다.
 * 위의 예제와 같은 경우에는 1, 4번이 스타트 팀, 2, 3번 팀이 링크 팀에 속하면
 * 스타트 팀의 능력치는 6, 링크 팀의 능력치는 6이 되어서 차이가 0이 되고 이 값이 최소이다.
 *
 * 입력
 * 첫째 줄에 N(4 ≤ N ≤ 20, N은 짝수)이 주어진다.
 * 둘째 줄부터 N개의 줄에 S가 주어진다.
 * 각 줄은 N개의 수로 이루어져 있고, i번 줄의 j번째 수는 Sij 이다.
 * Sii 는 항상 0이고, 나머지 Sij 는 1보다 크거나 같고, 100보다 작거나 같은 정수이다.
 *
 * 출력
 * 첫째 줄에 스타트 팀과 링크 팀의 능력치의 차이의 최솟값을 출력한다.
 *
 * 작성일 : 2023.09.04
 */
class B14889_StartLink {
    private static int MIN = Integer.MAX_VALUE;

    private static int n;
    private static int[][] map;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        team(0, 0);

        System.out.println(MIN);
    }

    private static void team(int idx, int count) {
        // 팀 조합이 완성될 경우
        if (count == n / 2) {
            /*
             * 방문한 팀과 방문하지 않은 팀을 각각 나누어
             * 각 팀의 점수를 구한 뒤 최솟값을 찾는다.
             */
            diff();
            return;
        }

        for (int i = idx; i < n; i++) {
            // 방문하지 않았다면?
            if (!visit[i]) {
                visit[i] = true; // 방문으로 변경
                team(i + 1, count + 1); // 재귀 호출
                visit[i] = false; // 재귀가 끝나면 비방문으로 변경
            }
        }
    }

    private static void diff() {
        int teamStart = 0;
        int teamLink = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                // i 번째 사람과 j 번째 사람이 true 라면 스타트팀으로 점수 플러스
                if (visit[i] && visit[j]) {
                    teamStart += map[i][j];
                    teamStart += map[j][i];
                } else if (!visit[i] && !visit[j]) {
                    // i 번째 사람과 j 번째 사람이 false 라면 링크팀으로 점수 플러스
                    teamLink += map[i][j];
                    teamLink += map[j][i];
                }
            }
        }

        // 두 팀의 점수 차이 (절대값)
        int val = Math.abs(teamStart - teamLink);

        /*
         * 두 팀의 점수차가 0 이라면 가장 낮은 최솟값이기 때문에
         * 더 이상의 탐색이 필요 없이 0 을 출력하고 종료
         */
        if (val == 0) {
            System.out.println(val);
            System.exit(0);
        }

        MIN = Math.min(val, MIN);
    }

    // 다른 사람의 풀이 1
    static int n1, ans = Integer.MAX_VALUE;
    static int[] r, l;

    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;

        n1 = Integer.parseInt(br.readLine());
        int total = 0;
        r = new int[n1];
        l = new int[n1];

        for (int i = 0; i < n1; i++) {
            stz = new StringTokenizer(br.readLine());
            for (int j = 0; j < n1; j++) {
                int t = Integer.parseInt(stz.nextToken());
                total += t;
                r[i] += t;
                l[j] += t;
            }
        }

        match(1, 1, total - r[0] - l[0]);
        System.out.println(ans);
    }

    static void match(int x, int count, int current) {
        if (count == n1 / 2) {
            ans = Math.min(ans, Math.abs(current));
            return;
        }

        if (x == n1) {
            return;
        }

        match(x + 1, count + 1, current - r[x] - l[x]);
        match(x + 1, count, current);
    }
}

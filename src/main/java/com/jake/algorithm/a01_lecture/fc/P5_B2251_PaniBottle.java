package com.jake.algorithm.a01_lecture.fc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [ 물통 ]
 *
 * 문제
 * 각각 부피가 A, B, C(1≤A, B, C≤200) 리터인 세 개의 물통이 있다.
 * 처음에는 앞의 두 물통은 비어 있고, 세 번째 물통은 가득(C 리터) 차 있다.
 * 이제 어떤 물통에 들어있는 물을 다른 물통으로 쏟아 부을 수 있는데,
 * 이때에는 한 물통이 비거나, 다른 한 물통이 가득 찰 때까지 물을 부을 수 있다.
 * 이 과정에서 손실되는 물은 없다고 가정한다.
 *
 * 이와 같은 과정을 거치다보면 세 번째 물통(용량이 C인)에 담겨있는 물의 양이 변할 수도 있다.
 * 첫 번째 물통(용량이 A인)이 비어 있을 때, 세 번째 물통(용량이 C인)에 담겨있을 수 있는 물의 양을 모두 구해내는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 세 정수 A, B, C가 주어진다.
 *
 * 출력
 * 첫째 줄에 공백으로 구분하여 답을 출력한다. 각 용량은 오름차순으로 정렬한다.
 *
 * 작성일 : 2026.03.04
 */
public class P5_B2251_PaniBottle {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int[] Limit;
    static boolean[] possible;
    static boolean[][][] visit;

    static void input() {
        Limit = new int[3];

        for (int i = 0; i < 3; i++) {
            Limit[i] = scan.nextInt();
        }

        visit = new boolean[205][205][205];
        possible = new boolean[205];
    }

    static void bfs(int x1, int x2, int x3) {
        Queue<State> Q = new LinkedList<>();
        visit[x1][x2][x3] = true;
        Q.add(new State(new int[] {x1, x2, x3}));

        // BFS 시작
        while (!Q.isEmpty()) {
            State st = Q.poll();
            if (st.X[0] == 0) {
                possible[st.X[2]] = true;
            }

            for (int from = 0; from < 3; from++) {
                for (int to = 0; to < 3; to++) {
                    if (from == to) {
                        continue;
                    }

                    State nxt = st.move(from, to, Limit);

                    if (!visit[nxt.X[0]][nxt.X[1]][nxt.X[2]]) {
                        visit[nxt.X[0]][nxt.X[1]][nxt.X[2]] = true;
                        Q.add(nxt);
                    }
                }
            }
        }
    }

    static void process() {
        bfs(0, 0, Limit[2]);

        for (int i = 0; i <= Limit[2]; i++) {
            if (possible[i]) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        input();

        process();
    }

    static class State {
        int[] X;

        State(int[] _X) {
            X = new int[3];
            System.arraycopy(_X, 0, X, 0, 3);
        }

        // from 물통에서 to 물통으로 물을 옮긴다
        State move(int from, int to, int[] Limit) {
            int[] nX = new int[]{X[0], X[1], X[2]};

            if (X[from] + X[to] >= Limit[to]) {
                nX[from] -= Limit[to] - X[to];
                nX[to] = Limit[to];
            } else {
                nX[to] += nX[from];
                nX[from] = 0;
            }

            return new State(nX);
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

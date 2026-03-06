package com.jake.algorithm.a01_lecture.fc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * [ 트리 ]
 * <p>
 * 문제
 * 트리에서 리프 노드란, 자식의 개수가 0인 노드를 말한다.
 * 트리가 주어졌을 때, 노드 하나를 지울 것이다.
 * 그 때, 남은 트리에서 리프 노드의 개수를 구하는 프로그램을 작성하시오.
 * 노드를 지우면 그 노드와 노드의 모든 자손이 트리에서 제거된다.
 * 예를 들어, 다음과 같은 트리가 있다고 하자.
 * <p>
 * 현재 리프 노드의 개수는 3개이다. (초록색 색칠된 노드) 이때, 1번을 지우면, 다음과 같이 변한다.
 * 검정색으로 색칠된 노드가 트리에서 제거된 노드이다.
 * <p>
 * 이제 리프 노드의 개수는 1개이다.
 * <p>
 * 입력
 * 첫째 줄에 트리의 노드의 개수 N이 주어진다. N은 50보다 작거나 같은 자연수이다.
 * 둘째 줄에는 0번 노드부터 N-1번 노드까지, 각 노드의 부모가 주어진다. 만약 부모가 없다면 (루트) -1이 주어진다.
 * 셋째 줄에는 지울 노드의 번호가 주어진다.
 * <p>
 * 출력
 * 첫째 줄에 입력으로 주어진 트리에서 입력으로 주어진 노드를 지웠을 때, 리프 노드의 개수를 출력한다.
 * <p>
 * 작성일 : 2026.03.06
 */
public class P6_B1068_Tree {
    static FastReader scan = new FastReader();

    static int n, root, erased;
    static ArrayList<Integer>[] child;
    static int[] leaf;

    static void input() {
        n = scan.nextInt();
        child = new ArrayList[n];
        leaf = new int[n];

        for (int i = 0; i < n; i++) {
            child[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            int parent = scan.nextInt();

            if (parent == -1) {
                root = i;
                continue;
            }

            child[parent].add(i);
        }

        erased = scan.nextInt();
    }

    static void dfs(int x) {
        if (child[x].isEmpty()) {
            leaf[x] = 1;
        }

        for (int y : child[x]) {
            dfs(y);
            leaf[x] += leaf[y];
        }
    }

    static void process() {
        for (int i = 0; i < n; i++) {
            if (child[i].contains(erased)) {
                child[i].remove((Integer) erased);
            }
        }

        if (root != erased) {
            dfs(root);
        }

        System.out.println(leaf[root]);
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

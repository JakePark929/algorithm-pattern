package com.jake.argorithm.baekjoon.lv24_devide_and_conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [ 쿼드 트리 ]
 * <p>
 * 흑백 영상을 압축하여 표현하는 데이터 구조로 쿼드 트리(Quad Tree)라는 방법이 있다.
 * 흰 점을 나타내는 0과 검은 점을 나타내는 1로만 이루어진 영상(2차원 배열)에서 같은 숫자의 점들이 한 곳에 많이 몰려있으면,
 * 쿼드 트리에서는 이를 압축하여 간단히 표현할 수 있다.
 * <p>
 * 주어진 영상이 모두 0으로만 되어 있으면 압축 결과는 "0"이 되고, 모두 1로만 되어 있으면 압축 결과는 "1"이 된다.
 * 만약 0과 1이 섞여 있으면 전체를 한 번에 나타내지를 못하고,
 * 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래, 이렇게 4개의 영상으로 나누어 압축하게 되며,
 * 이 4개의 영역을 압축한 결과를 차례대로 괄호 안에 묶어서 표현한다
 * <p>
 * 위 그림에서 왼쪽의 영상은 오른쪽의 배열과 같이 숫자로 주어지며,
 * 이 영상을 쿼드 트리 구조를 이용하여 압축하면 "(0(0011)(0(0111)01)1)"로 표현된다.
 * N × N 크기의 영상이 주어질 때, 이 영상을 압축한 결과를 출력하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에는 영상의 크기를 나타내는 숫자 N 이 주어진다.
 * N 은 언제나 2의 제곱수로 주어지며, 1 ≤ N ≤ 64의 범위를 가진다.
 * 두 번째 줄부터는 길이 N의 문자열이 N개 들어온다.
 * 각 문자열은 0 또는 1의 숫자로 이루어져 있으며, 영상의 각 점들을 나타낸다.
 * <p>
 * 출력
 * 영상을 압축한 결과를 출력한다.
 * <p>
 * 작성일 : 2023.09.13
 */
class B1992_QuadTree {
    private static int[][] img;
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        img = new int[n][n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();

            for (int j = 0; j < n; j++) {
                img[i][j] = str.charAt(j) - '0';
            }
        }

        quadTree(0, 0, n);

        System.out.println(sb);
    }

    private static void quadTree(int x, int y, int size) {
        if (isPossible(x, y, size)) {
            sb.append(img[x][y]);
            return;
        }

        int newSize = size / 2;

        sb.append('(');
        quadTree(x, y, newSize);
        quadTree(x, y + newSize, newSize);
        quadTree(x + newSize, y, newSize);
        quadTree(x + newSize, y + newSize, newSize);
        sb.append(')');
    }

    private static boolean isPossible(int x, int y, int size) {
        int value = img[x][y];

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (value != img[i][j]) return false;
            }
        }

        return true;
    }

    // 다른 사람의 풀이 1
    static int N;
    static int[][] image;
    static StringBuilder answer;

    public static void main1(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        image = new int[N][N];
        answer = new StringBuilder();

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                image[i][j] = line.charAt(j) - '0';
            }
        }
        quad(0, 0, N, N);

        System.out.println(answer);
    }

    public static void quad(int rs, int cs, int re, int ce) {
        int init = image[rs][cs];
        if (re - rs == 1 || isUnited(rs, cs, re, ce)) {
            answer.append(init);
            return;
        } else {
            int rm = (rs + re) / 2;
            int cm = (cs + ce) / 2;

            answer.append("(");
            quad(rs, cs, rm, cm);
            quad(rs, cm, rm, ce);
            quad(rm, cs, re, cm);
            quad(rm, cm, re, ce);
            answer.append(")");
        }
    }

    public static boolean isUnited(int rs, int cs, int re, int ce) {
        boolean result = true;
        int init = image[rs][cs];
        for (int r = rs; r < re; r++) {
            for (int c = cs; c < ce; c++) {
                if (image[r][c] != init) {
                    result = false;
                    break;
                }
            }
            if (!result) break;
        }

        return result;
    }
}

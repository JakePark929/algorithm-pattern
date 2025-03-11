package com.jake.algorithm.baekjoon.lv24_devide_and_conquer;

import java.io.IOException;

/**
 * [ 종이의 개수 ]
 * <p>
 * N × N 크기의 행렬로 표현되는 종이가 있다.
 * 종이의 각 칸에는 -1, 0, 1 중 하나가 저장되어 있다.
 * 우리는 이 행렬을 다음과 같은 규칙에 따라 적절한 크기로 자르려고 한다.
 * <p>
 * 1. 만약 종이가 모두 같은 수로 되어 있다면 이 종이를 그대로 사용한다.
 * 2. (1)이 아닌 경우에는 종이를 같은 크기의 종이 9개로 자르고, 각각의 잘린 종이에 대해서 (1)의 과정을 반복한다.
 * <p>
 * 이와 같이 종이를 잘랐을 때,
 * -1로만 채워진 종이의 개수, 0으로만 채워진 종이의 개수, 1로만 채워진 종이의 개수를 구해내는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 37, N은 3k 꼴)이 주어진다. 다음 N개의 줄에는 N개의 정수로 행렬이 주어진다.
 * <p>
 * 출력
 * 첫째 줄에 -1로만 채워진 종이의 개수를, 둘째 줄에 0으로만 채워진 종이의 개수를, 셋째 줄에 1로만 채워진 종이의 개수를 출력한다.
 * <p>
 * 작성일 : 2023.09.13
 */
class B1780_CountsOfPaper {
    private static int negative;
    private static int zero;
    private static int positive;
    private static int[][] paper;

    public static void main(String[] args) throws Exception {
        int n = read();
        paper = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                paper[i][j] = read();
            }
        }

        finder(0, 0, n);

        StringBuilder sb = new StringBuilder();
        sb.append(negative).append('\n').append(zero).append('\n').append(positive).append('\n');
        System.out.println(sb);
    }

    private static void finder(int row, int col, int size) {
        if (check(row, col, size)) {
            if (paper[row][col] == -1) {
                negative++;
            } else if (paper[row][col] == 1) {
                positive++;
            } else {
                zero++;
            }

            return;
        }

        int reSize = size / 3;

        finder(row, col, reSize);
        finder(row, col + reSize, reSize);
        finder(row, col + reSize + reSize, reSize);
        finder(row + reSize, col, reSize);
        finder(row + reSize, col + reSize, reSize);
        finder(row + reSize, col + reSize + reSize, reSize);
        finder(row + reSize + reSize, col, reSize);
        finder(row + reSize + reSize, col + reSize, reSize);
        finder(row + reSize + reSize, col + reSize + reSize, reSize);
    }

    private static boolean check(int row, int col, int size) {
        int ch = paper[row][col];

        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (ch != paper[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;
        if (isNegative) n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return isNegative ? ~n + 1 : n;
    }

    // 다른 사람의 풀이 1
    static int[] answer = new int[]{0, 0, 0};
    static int[][] map;

    private static int read1() throws IOException {
        int c, n = System.in.read();
        boolean isMinus = false;
        if (n == 45) {
            isMinus = true;
            n = System.in.read() & 15;
        } else {
            n = n & 15;
        }
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return isMinus ? 2 : n;
    }

    public static void solution(int x, int y, int size) {
        if (chk(x, y, size)) {
            answer[map[x][y]]++;
        } else {
            int div = size / 3;
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    solution(j * div + x, k * div + y, div);
                }
            }

        }
    }

    public static boolean chk(int x, int y, int size) {
        int n = map[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (n != map[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main1(String[] args) throws IOException {
        int N = read1();

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = read1();
            }
        }

        solution(0, 0, N);
        System.out.printf("%d\n%d\n%d", answer[2], answer[0], answer[1]);
    }
}

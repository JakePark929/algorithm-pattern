package com.jake.algorithm.baekjoon.lv13_sort;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * [ 죄표 정렬하기 ]
 * <p>
 * 2차원 평면 위의 점 N개가 주어진다.
 * 좌표를 x좌표가 증가하는 순으로,
 * x좌표가 같으면 y좌표가 증가하는 순서로 정렬한 다음 출력하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 점의 개수 N (1 ≤ N ≤ 100,000)이 주어진다.
 * 둘째 줄부터 N개의 줄에는 i번점의 위치 xi와 yi가 주어진다.
 * (-100,000 ≤ xi, yi ≤ 100,000) 좌표는 항상 정수이고, 위치가 같은 두 점은 없다.
 * <p>
 * 출력
 * 첫째 줄부터 N개의 줄에 점을 정렬한 결과를 출력한다.
 * <p>
 * 작성일 : 2023.08.04
 */
class B11650_CoordinateSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (e1, e2) -> {
            if (e1[0] == e2[0]) {
                return e1[1] - e2[1];
            } else {
                return e1[0] - e2[0];
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i][0]).append(" ").append(arr[i][1]).append('\n');
        }

        System.out.println(sb);
    }

    // 다른 사람의 풀이 1
    public void solution() throws Exception {
        StringBuilder sb = new StringBuilder();
        int N = nextInt();
        Coordinates[] coordinates = new Coordinates[N];

        for (int i = 0; i < N; i++)
            coordinates[i] = new Coordinates(nextInt(), nextInt());

        Arrays.sort(coordinates, (o1, o2) -> {
            if (o1.x == o2.x) return o1.y - o2.y;
            else return o1.x - o2.x;
        });

        for (int i = 0; i < N; i++)
            sb.append(coordinates[i].x).append(" ").append(coordinates[i].y).append('\n');

        System.out.println(sb);
    }

    public static void main1(String[] args) throws Exception {
        initFI();
        new B11650_CoordinateSort().solution();
    }

    private static final int DEFAULT_BUFFER_SIZE = 1 << 16;
    private static DataInputStream inputStream;
    private static byte[] buffer;
    private static int curIdx, maxIdx;

    private static void initFI() {
        inputStream = new DataInputStream(System.in);
        buffer = new byte[DEFAULT_BUFFER_SIZE];
        curIdx = maxIdx = 0;
    }

    private static int nextInt() throws IOException {
        int ret = 0;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = (c == '-');
        if (neg) c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (neg) return -ret;
        return ret;
    }

    private static byte read() throws IOException {
        if (curIdx == maxIdx) {
            maxIdx = inputStream.read(buffer, curIdx = 0, DEFAULT_BUFFER_SIZE);
            if (maxIdx == -1) buffer[0] = -1;
        }
        return buffer[curIdx++];
    }

    // 다른 사람의 풀이 2
    static class Point implements Comparable<Point> {
        int x;
        int y;

        public Point(int a, int b) {
            x = a;
            y = b;
        }

        @Override
        public int compareTo(Point o) {
            if(this.x > o.x) return 1;
            if(this.x == o.x) {
                if(this.y > o.y) return 1;

            }
            return -1;
        }
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = readInt();

        ArrayList<Point> pt = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int x = readInt();
            int y = readInt();
            pt.add(new Point(x, y));
        }

        Collections.sort(pt);

        StringBuilder sb = new StringBuilder();

        for(Point p: pt) {
            sb.append(p.x).append(" ").append(p.y).append("\n");
        }

        System.out.println(sb);
    }

    static int readInt() throws IOException {
        int sum = 0;
        boolean isNegative = false;
        while (true) {
            int a = System.in.read();

            if (a == '\n' || a == ' ') {
                return isNegative ? sum * (-1) : sum;
            } else if (a == '-') {
                isNegative = true;
            } else {
                sum = sum * 10 + a - '0';
            }
        }
    }
}

class Coordinates {
    int x;
    int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

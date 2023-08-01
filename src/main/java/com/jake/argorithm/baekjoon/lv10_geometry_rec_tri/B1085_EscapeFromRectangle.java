package com.jake.argorithm.baekjoon.lv10_geometry_rec_tri;

/**
 * [ 직사각형에서 탈출 ]
 *
 * 한수는 지금 (x, y)에 있다.
 * 직사각형은 각 변이 좌표축에 평행하고,
 * 왼쪽 아래 꼭짓점은 (0, 0), 오른쪽 위 꼭짓점은 (w, h)에 있다.
 * 직사각형의 경계선까지 가는 거리의 최솟값을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 x, y, w, h가 주어진다.
 *
 * 출력
 * 첫째 줄에 문제의 정답을 출력한다.
 *
 * 제한
 * 1 ≤ w, h ≤ 1,000
 * 1 ≤ x ≤ w-1
 * 1 ≤ y ≤ h-1
 * x, y, w, h는 정수
 *
 * 작성일 : 23.08.01
 */
class B1085_EscapeFromRectangle {
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    public static void main(String[] args) throws Exception {
        int x = read();
        int y = read();
        int w = read();
        int h = read();

        System.out.println(Math.min(Math.min(x, Math.abs(w - x)), Math.min(y, Math.abs(h - y))));
    }
}

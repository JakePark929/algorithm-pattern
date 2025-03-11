package com.jake.algorithm.baekjoon.lv17_combinatorics;

/**
 * [ 베라의 패션 ]
 * <p>
 * 베라는 상의 N 벌과 하의 N 벌이 있다.
 * i 번째 상의와 i 번째 하의는 모두 색상 i를 가진다. N 개의 색상은 모두 서로 다르다.
 * 상의와 하의가 서로 다른 색상인 조합은 총 몇 가지일까?
 * <p>
 * 입력
 * 입력은 아래와 같이 주어진다.
 * N
 * <p>
 * 출력
 * 상의와 하의가 서로 다른 색상인 조합의 가짓수를 출력한다.
 * <p>
 * 작성일 : 2023.08.25
 */
class B15439_FashionOfBera {
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3)  + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    public static void main(String[] args) throws Exception {
        int n = read();

        System.out.println(n * (n - 1));
    }
}

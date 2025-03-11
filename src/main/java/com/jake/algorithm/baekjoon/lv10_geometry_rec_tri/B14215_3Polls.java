package com.jake.algorithm.baekjoon.lv10_geometry_rec_tri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * [ 세 막대 ]
 * <p>
 * 영선이는 길이가 a, b, c인 세 막대를 가지고 있고, 각 막대의 길이를 마음대로 줄일 수 있다.
 * <p>
 * 영선이는 세 막대를 이용해서 아래 조건을 만족하는 삼각형을 만들려고 한다.
 * <p>
 * 각 막대의 길이는 양의 정수이다
 * 세 막대를 이용해서 넓이가 양수인 삼각형을 만들 수 있어야 한다.
 * 삼각형의 둘레를 최대로 해야 한다.
 * a, b, c가 주어졌을 때, 만들 수 있는 가장 큰 둘레를 구하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 a, b, c (1 ≤ a, b, c ≤ 100)가 주어진다.
 * <p>
 * 출력
 * 첫째 줄에 만들 수 있는 가장 큰 삼각형의 둘레를 출력한다.
 * <p>
 * 작성일 : 2023.08.01
 */
class B14215_3Polls {
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    public static void main(String[] args) throws Exception {
        Integer[] sides = new Integer[]{read(), read(), read()};
        HashSet<Integer> hs = new HashSet<>(Arrays.asList(sides));
        Arrays.sort(sides);

        if (hs.size() > 1) {
            if (sides[0] + sides[1] > sides[2]) {
                System.out.println(sides[0] + sides[1] + sides[2]);
            } else {
                System.out.println((sides[0] + sides[1]) * 2 - 1);
            }
        } else {
            System.out.println(sides[0] + sides[1] + sides[2]);
        }
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int sum;

        if (a + b <= c) {
            sum = a + b + (a + b - 1);
        } else if (a + c <= b) {
            sum = a + c + (a + c - 1);
        } else if (b + c <= a) {
            sum = b + c + (b + c - 1);
        } else {
            sum = a + b + c;
        }

        System.out.print(sum);
    }
}

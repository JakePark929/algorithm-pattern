package com.jake.argorithm.baekjoon.lv10_geometry_rec_tri;

import java.util.Arrays;
import java.util.HashSet;

/**
 * [ 삼각형과 세 변 ]
 *
 * 삼각형의 세 변의 길이가 주어질 때 변의 길이에 따라 다음과 같이 정의한다.
 *
 * Equilateral :  세 변의 길이가 모두 같은 경우
 * Isosceles : 두 변의 길이만 같은 경우
 * Scalene : 세 변의 길이가 모두 다른 경우
 * 단 주어진 세 변의 길이가 삼각형의 조건을 만족하지 못하는 경우에는 "Invalid" 를 출력한다.
 * 예를 들어 6, 3, 2가 이 경우에 해당한다.
 * 가장 긴 변의 길이보다 나머지 두 변의 길이의 합이 길지 않으면 삼각형의 조건을 만족하지 못한다.
 *
 * 세 변의 길이가 주어질 때 위 정의에 따른 결과를 출력하시오.
 *
 * 입력
 * 각 줄에는 1,000을 넘지 않는 양의 정수 3개가 입력된다. 마지막 줄은 0 0 0이며 이 줄은 계산하지 않는다.
 *
 * 출력
 * 각 입력에 맞는 결과 (Equilateral, Isosceles, Scalene, Invalid) 를 출력하시오.
 *
 * 작성일 : 2023.08.01
 */
class B5073_TriangleAnd3Sides {
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    public static void main(String[] args) throws Exception {
        int a, b, c;
        while ((a = read()) != 0 && (b = read()) != 0 && (c = read()) != 0) {
            Integer[] sides = new Integer[] {a, b, c};
            HashSet<Integer> hs = new HashSet<>(Arrays.asList(sides));
            Arrays.sort(sides);

            if(sides[0] + sides[1] <= sides[2]) {
                System.out.println("Invalid");
            } else {
                if (hs.size() == 1) {
                    System.out.println("Equilateral");
                } else if (hs.size() == 2) {
                    System.out.println("Isosceles");
                } else if (hs.size() == 3) {
                    System.out.println("Scalene");
                }
            }
        }
    }
}

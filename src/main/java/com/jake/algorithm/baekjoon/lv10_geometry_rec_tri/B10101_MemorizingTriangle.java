package com.jake.algorithm.baekjoon.lv10_geometry_rec_tri;

import java.util.Arrays;
import java.util.HashSet;

/**
 * [ 삼각형 외우기 ]
 * <p>
 * 창영이는 삼각형의 종류를 잘 구분하지 못한다.
 * 따라서 프로그램을 이용해 이를 외우려고 한다.
 * <p>
 * 삼각형의 세 각을 입력받은 다음,
 * <p>
 * 세 각의 크기가 모두 60이면, Equilateral
 * 세 각의 합이 180이고, 두 각이 같은 경우에는 Isosceles
 * 세 각의 합이 180이고, 같은 각이 없는 경우에는 Scalene
 * 세 각의 합이 180이 아닌 경우에는 Error
 * 를 출력하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 총 3개의 줄에 걸쳐 삼각형의 각의 크기가 주어진다. 모든 정수는 0보다 크고, 180보다 작다.
 * <p>
 * 출력
 * 문제의 설명에 따라 Equilateral, Isosceles, Scalene, Error 중 하나를 출력한다.
 * <p>
 * 작성일 : 2023.08.01
 */
class B10101_MemorizingTriangle {
    static Integer read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    public static void main(String[] args) throws Exception {
        Integer[] angles = new Integer[]{read(), read(), read()};
        HashSet<Integer> hs = new HashSet<>(Arrays.asList(angles));

        if (angles[0] + angles[1] + angles[2] == 180) {
            if (hs.size() == 1) {
                System.out.println("Equilateral");
            } else if (hs.size() == 2) {
                System.out.println("Isosceles");
            } else if (hs.size() == 3) {
                System.out.println("Scalene");
            }
        } else {
            System.out.println("Error");
        }
    }
}

package com.jake.algorithm.baekjoon.lv02_condition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ 시험 성적 ]
 *
 * 시험 점수를 입력받아
 *
 * 90 ~ 100점은 A,
 * 80 ~ 89점은 B,
 * 70 ~ 79점은 C,
 * 60 ~ 69점은 D,
 * 나머지 점수는 F를
 *
 * 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 시험 점수가 주어진다.
 * 시험 점수는 0보다 크거나 같고, 100보다 작거나 같은 정수이다.
 * ex) 100
 *
 * 출력
 * 시험 성적을 출력한다.
 * ex) A
 *
 * 작성일 : 2023.07.17
 */
class B9498_Score {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int a = Integer.parseInt(br.readLine());

        if (90 <= a && a <= 100) {
            System.out.println("A");
        } else if (80 <= a && a < 90) {
            System.out.println("B");
        } else if (70 <= a && a < 80) {
            System.out.println("C");
        } else if (60 <= a && a < 70) {
            System.out.println("D");
        } else {
            System.out.println("F");
        }
    }
}

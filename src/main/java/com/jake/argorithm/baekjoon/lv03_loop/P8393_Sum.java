package com.jake.argorithm.baekjoon.lv03_loop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ 합 ]
 *
 * n이 주어졌을 때, 1부터 n까지 합을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 n (1 ≤ n ≤ 10,000)이 주어진다.
 * ex) 3
 *
 * 출력
 * 1부터 n까지 합을 출력한다.
 * ex) 6
 *
 * 작성일 : 2023.07.17
 */
class P8393_Sum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());

        int sum = 0;
        for (int i = 1; i <= a; i++) {
            sum += i;
        }

        System.out.println(sum);
    }
}

package com.jake.algorithm.baekjoon.lv01_inout_operations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ A * B ]
 * <p>
 * 두 정수 A와 B를 입력받은 다음, A*B를 출력하는 프로그램을 작성하시오.
 * <p>
 * 입력 : 첫째 줄에 A와 B가 주어진다. (0 < A, B < 10) ex) 1 2
 * 출력 : 첫째 줄에 A*B를 출력한다. ex) 2
 * <p>
 * 작성일 : 2023.07.12
 */
class B10998_AMultiplyB {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        System.out.println(a * b);
    }
}

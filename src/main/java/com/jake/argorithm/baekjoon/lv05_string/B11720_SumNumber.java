package com.jake.argorithm.baekjoon.lv05_string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ 숫자의 합 ]
 *
 * N개의 숫자가 공백 없이 쓰여있다. 이 숫자를 모두 합해서 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 숫자의 개수 N (1 ≤ N ≤ 100)이 주어진다. 둘째 줄에 숫자 N개가 공백없이 주어진다.
 *
 * 출력
 * 입력으로 주어진 숫자 N개의 합을 출력한다.
 *
 * 작성일 : 2023.07.20
 */
class B11720_SumNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();

        int sum = 0;
        for(char c : s.toCharArray()) {
            sum += Character.getNumericValue(c);
        }

        System.out.println(sum);
    }
}

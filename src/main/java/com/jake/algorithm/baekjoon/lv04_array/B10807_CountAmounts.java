package com.jake.algorithm.baekjoon.lv04_array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 개수 세기 ]
 *
 * 총 N개의 정수가 주어졌을 때, 정수 v가 몇 개인지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 정수의 개수 N(1 ≤ N ≤ 100)이 주어진다.
 * 둘째 줄에는 정수가 공백으로 구분되어져있다.
 * 셋째 줄에는 찾으려고 하는 정수 v가 주어진다.
 * 입력으로 주어지는 정수와 v는 -100보다 크거나 같으며, 100보다 작거나 같다.
 *
 * 출력
 * 첫째 줄에 입력으로 주어진 N개의 정수 중에 v가 몇 개인지 출력한다.
 *
 * 작성일 : 2023.07.17
 */
public class B10807_CountAmounts {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int count = 0;
        int finder = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            if(Integer.parseInt(st.nextToken()) == finder) {
                count++;
            }
        }

        System.out.println(count);
    }

    // 다른 사람의 풀이1
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    public static void main1(String... args) throws Exception {
        int n = read(), cnt = 0;
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = read();
        }
        int v = read();
        for(int i = 0; i < n; i++) {
            if(arr[i] == v)
                cnt++;
        }
        System.out.println(cnt);
    }
}

package com.jake.argorithm.baekjoon.lv04_array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ X보다 작은 수 ]
 *
 * 정수 N개로 이루어진 수열 A와 정수 X가 주어진다.
 * 이때, A에서 X보다 작은 수를 모두 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N과 X가 주어진다. (1 ≤ N, X ≤ 10,000)
 *
 * 둘째 줄에 수열 A를 이루는 정수 N개가 주어진다. 주어지는 정수는 모두 1보다 크거나 같고, 10,000보다 작거나 같은 정수이다.
 *
 * 출력
 * X보다 작은 수를 입력받은 순서대로 공백으로 구분해 출력한다. X보다 작은 수는 적어도 하나 존재한다.
 *
 * 작성일 : 2023.07.19
 */
public class B10871_LessThenX {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
        int[] array = new int[Integer.parseInt(st1.nextToken())];
        int x = Integer.parseInt(st1.nextToken());
        StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(st2.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for (int j : array) {
            if (j < x) {
                sb.append(j);
                sb.append(" ");
            }
        }

        System.out.println(String.valueOf(sb).trim());
    }

    // 다른 사람의 풀이 1 - int reader 구현
    public static void main1(String[] args) throws IOException {
        StringBuffer sb = new StringBuffer();

        int N = readInt();
        int val = readInt();
        int rs = 0;
        for(int i = 0; i < N; i++){
            if( (rs = readInt()) < val) {
                sb.append(rs).append(" ");
            }
        }
        System.out.println(sb);
    }

    public static int readInt() throws IOException {
        int sum = 0;
        boolean isNegative = false;

        while(true){
            int input = System.in.read();

            System.out.println(input);

            if(input == '\n' || input == ' '){
                return isNegative ? (-1 * sum) : sum;
            } else if (input == '-') {
                isNegative = true;
            } else {
                sum = (sum * 10) + (input - '0');
            }
        }
    }
}

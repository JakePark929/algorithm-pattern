package com.jake.argorithm.baekjoon.lv04_array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [ 최소, 최대 ]
 * <p>
 * N개의 정수가 주어진다.
 * 이때, 최솟값과 최댓값을 구하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 정수의 개수 N (1 ≤ N ≤ 1,000,000)이 주어진다.
 * 둘째 줄에는 N개의 정수를 공백으로 구분해서 주어진다.
 * 모든 정수는 -1,000,000보다 크거나 같고, 1,000,000보다 작거나 같은 정수이다.
 * <p>
 * 출력
 * 첫째 줄에 주어진 정수 N개의 최솟값과 최댓값을 공백으로 구분해 출력한다.
 * <p>
 * 작성일 : 2023.07.19
 */
class B10818_MinMax {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[Integer.parseInt(br.readLine())];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        System.out.println(arr[0] + " " + arr[arr.length - 1]);
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int times = readInt();

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < times; i++) {
            int num = readInt();

            min = Math.min(num, min);
            max = Math.max(num, max);
        }
        sb.append(min).append(" ").append(max);
        System.out.println(sb);
    }


    //method
    public static int readInt() throws IOException {
        boolean numSign = false;
        int value = 0; //리턴 시킬 숫자 합계

        while (true) {
            int num = System.in.read();

            if (num == '-') { //음수라면
                numSign = true;
            } else if (num == ' ' || num == '\n') { // 숫자 끝나는 지점 인식
                return numSign ? (-1 * value) : value;
            } else {
                value = (value * 10) + (num - '0'); // 기존의 일의 자리를 다음 자리로 승격
            }
        }
    }
}

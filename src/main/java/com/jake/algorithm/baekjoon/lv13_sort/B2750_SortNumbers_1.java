package com.jake.algorithm.baekjoon.lv13_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * [ 수 정렬하기 1 ]
 * <p>
 * N개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000)이 주어진다.
 * 둘째 줄부터 N개의 줄에는 수가 주어진다.
 * 이 수는 절댓값이 1,000보다 작거나 같은 정수이다. 수는 중복되지 않는다.
 * <p>
 * 출력
 * 첫째 줄부터 N개의 줄에 오름차순으로 정렬한 결과를 한 줄에 하나씩 출력한다.
 * <p>
 * 작성일 : 2023.08.03
 */
class B2750_SortNumbers_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> nums = new ArrayList<>();

        for (int i = 0; i < n; i++) nums.add(Integer.valueOf(br.readLine()));
        Collections.sort(nums);

        for (int i : nums) System.out.println(i);
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int N = readInt();

        int[] arr = new int[N];
        int[] counting = new int[2001]; // -1000 ~ 1000
        int[] result = new int[N];

        for (int i = 0; i < N; i++)
            arr[i] = readInt();

        for (int i = 0; i < N; i++)
            counting[arr[i] + 1000]++;

        for (int i = 1; i < counting.length; i++)
            counting[i] += counting[i - 1];

        for (int i = N - 1; i >= 0; i--) {
            int value = arr[i];
            counting[value + 1000]--;
            result[counting[value + 1000]] = value;
        }

        for (int r : result) sb.append(r).append('\n');
        System.out.println(sb);
    }

    static int readInt() throws IOException {
        int n = 0;
        boolean isNegative = false;
        while (true) {
            int input = System.in.read();
            if (input <= 32) {
                return isNegative ? n * -1 : n;
            } else if (input == '-')
                isNegative = true;
            else
                n = (n << 3) + (n << 1) + (input & 15);
        }
    }

    // 다른 사람의 풀이 2 - Counting Sort
    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

		/*
		  range : -1000 ~ 1000
		  0 은 index[1000] 을 의미
		*/
        boolean[] arr = new boolean[2001];

        for(int i = 0; i < N; i++) {
            arr[Integer.parseInt(br.readLine()) + 1000] = true;
        }

        // 정렬 과정이 따로 필요 없음
        for(int i = 0; i < 2001; i++) {
            if(arr[i]) {
                sb.append(i - 1000).append('\n');
            }
        }

        System.out.println(sb);
    }

}

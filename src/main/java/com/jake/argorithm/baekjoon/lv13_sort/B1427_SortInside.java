package com.jake.argorithm.baekjoon.lv13_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * [ 소트 인사이드 ]
 *
 * 배열을 정렬하는 것은 쉽다. 수가 주어지면, 그 수의 각 자리수를 내림차순으로 정렬해보자.
 *
 * 입력
 * 첫째 줄에 정렬하려고 하는 수 N이 주어진다. N은 1,000,000,000보다 작거나 같은 자연수이다.
 *
 * 출력
 * 첫째 줄에 자리수를 내림차순으로 정렬한 수를 출력한다.
 *
 * 작성일 : 2023.08.03
 */
class B1427_SortInside {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] cs = br.readLine().toCharArray();

        int[] arr = new int[cs.length];

        for(int i = 0; i < arr.length; i++) {
            arr[i] = Character.getNumericValue(cs[i]);
        }

        int[] result = countingSort(arr, 9);

        StringBuilder sb = new StringBuilder();

        for(int i = result.length - 1; i >= 0; i--) {
            sb.append(result[i]);
        }

        System.out.println(sb);
    }

    private static int[] countingSort(int[] arr, int range) {
        int[] counting = new int[range + 1];
        int[] result = new int[arr.length];

        for (int i : arr) counting[i]++;

        for (int i = 1; i < counting.length; i++)
            counting[i] += counting[i - 1];

        for (int i = arr.length - 1; i >= 0; i--) {
            int val = arr[i];
            counting[val]--;
            result[counting[val]] = val;
        }

        return result;
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {

        InputStream in = System.in;

        int[] counting = new int[10];
        int c;
        while((c = in.read()) != '\n') {
            counting[c - '0']++;
        }

        for (int i = 9; i >= 0; i--) {
            while (counting[i]-- > 0) {
                System.out.print(i);
            }
        }
    }
}

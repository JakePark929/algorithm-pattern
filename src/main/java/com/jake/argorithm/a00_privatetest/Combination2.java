package com.jake.argorithm.a00_privatetest;

import java.util.Arrays;
import java.util.Scanner;

public class Combination2 {
    static int N, R;
    static int[] input, numbers;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input: ");
        N = sc.nextInt();
        System.out.print("Numbers: ");
        R = sc.nextInt();

        input = new int[N];
        numbers = new int[R];

        for (int i = 0; i < N; i++) {
            System.out.print("Input" + i + ": ");
            input[i] = sc.nextInt();
        }

        comb(0, 0);
    }

    public static void comb(int cnt, int start) {

        if (cnt == R) {
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for (int i = start; i < N; i++) {
            numbers[cnt] = input[i];
            comb(cnt + 1, i + 1);
        }

    }
}

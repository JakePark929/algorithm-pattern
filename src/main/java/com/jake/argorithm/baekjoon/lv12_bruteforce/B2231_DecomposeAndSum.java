package com.jake.argorithm.baekjoon.lv12_bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * [ 분해합 ]
 * <p>
 * 어떤 자연수 N이 있을 때,
 * 그 자연수 N의 분해합은 N과 N을 이루는 각 자리수의 합을 의미한다.
 * 어떤 자연수 M의 분해합이 N인 경우, M을 N의 생성자라 한다.
 * 예를 들어, 245의 분해합은 256(=245+2+4+5)이 된다.
 * 따라서 245는 256의 생성자가 된다.
 * 물론, 어떤 자연수의 경우에는 생성자가 없을 수도 있다.
 * 반대로, 생성자가 여러 개인 자연수도 있을 수 있다.
 * <p>
 * 자연수 N이 주어졌을 때, N의 가장 작은 생성자를 구해내는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 자연수 N(1 ≤ N ≤ 1,000,000)이 주어진다.
 * <p>
 * 출력
 * 첫째 줄에 답을 출력한다. 생성자가 없는 경우에는 0을 출력한다.
 * <p>
 * 작성일 : 2023.08.01
 */
class B2231_DecomposeAndSum {
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    //
    public static void main(String[] args) throws Exception {
        int n = read();
        int cons = 0;

        for (int i = 1; i <= n; i++) {
            char[] gens = String.valueOf(i).toCharArray();

            int temp = 0;

            for (char gen : gens) {
                temp += Character.getNumericValue(gen);
            }

            if (i + temp == n) {
                cons = i;
                break;
            }
        }

        System.out.println(cons);
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int len = (int) Math.log10(N) + 1;
        System.out.println(len);
        ArrayList<Integer> MList = new ArrayList<>();

        int M = N - (len * 9);
        System.out.println(M);
        while (M <= N) {
            int sum = 0;
            for (int i = len; i > 0; i--) {
                sum += ((M % Math.pow(10, i)) / Math.pow(10, i - 1));
            }
            if (N == M + sum) MList.add(M);
            M++;
        }

        System.out.println(MList);

        if (MList.isEmpty()) System.out.println(0);
        else System.out.println(Collections.min(MList));
    }

    // 다른 사람의 풀이 2
    public static void main2(String[] args) throws Exception {
        int N = read();

        int targetValue = N / 10;

        if (N > 1000) {
            targetValue = N - 100;
        }
        int result = 0;

        while (targetValue < N) {
            int targetCopyValue = targetValue;
            int sum = targetValue;

            if (targetValue > 10) {
                while (targetCopyValue > 0) {
                    sum += targetCopyValue % 10;
                    targetCopyValue /= 10;
                }
            } else {
                sum = targetValue * 2;
            }

            if (sum == N) {
                result = targetValue;
                break;
            }
            ++targetValue;
        }

        System.out.println(result);
    }
}

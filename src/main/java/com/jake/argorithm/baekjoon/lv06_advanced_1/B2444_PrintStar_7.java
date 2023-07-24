package com.jake.argorithm.baekjoon.lv06_advanced_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ 별찍기 - 7 ]
 * <p>
 * 예제를 보고 규칙을 유추한 뒤에 별을 찍어 보세요.
 * <p>
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 100)이 주어진다.
 * <p>
 * 출력
 * 첫째 줄부터 2×N-1번째 줄까지 차례대로 별을 출력한다.
 * <p>
 * 작성일 : 2023.07.24
 */
class B2444_PrintStar_7 {
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    public static void main(String[] args) throws Exception {
        int n = read();

        for (int i = 1; i <= n; i++) {
            for (int j = n - i; j > 0; j--) {
                System.out.print(" ");
            }

            for (int k = 1; k < 2 * i; k++) {
                System.out.print("*");
            }

            System.out.println();
        }

        for (int i = n - 1; i >= 1; i--) {
            for (int j = 0; j < n - i; j++) {
                System.out.print(" ");
            }
            for (int k = 2 * i; k > 1; k--) {
                System.out.print("*");
            }

            System.out.println();
        }
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            sb.append(" ".repeat(N - i));
            sb.append("*".repeat(2 * i - 1));
            sb.append("\n");
        }

        for (int i = N - 1; i > 0; i--) {
            sb.append(" ".repeat(N - i));
            sb.append("*".repeat(2 * i - 1));
            sb.append("\n");
        }

        System.out.print(sb);
    }
}

package com.jake.algorithm.baekjoon.lv15_factor_multiple_decimal_2;

import java.io.*;

/**
 * [ 베르트랑 공준 ]
 * <p>
 * 베르트랑 공준은 임의의 자연수 n에 대하여,
 * n보다 크고, 2n보다 작거나 같은 소수는 적어도 하나 존재한다는 내용을 담고 있다.
 * <p>
 * 이 명제는 조제프 베르트랑이 1845년에 추측했고, 파프누티 체비쇼프가 1850년에 증명했다.
 * <p>
 * 예를 들어, 10보다 크고, 20보다 작거나 같은 소수는 4개가 있다. (11, 13, 17, 19)
 * 또, 14보다 크고, 28보다 작거나 같은 소수는 3개가 있다. (17,19, 23)
 * <p>
 * 자연수 n이 주어졌을 때, n보다 크고, 2n보다 작거나 같은 소수의 개수를 구하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 케이스는 n을 포함하는 한 줄로 이루어져 있다.
 * 입력의 마지막에는 0이 주어진다.
 * <p>
 * 출력
 * 각 테스트 케이스에 대해서, n보다 크고, 2n보다 작거나 같은 소수의 개수를 출력한다.
 * <p>
 * 작성일 : 2023.08.08
 */
class B4948_BertrandPostulate {
    public static void main(String[] args) throws Exception {
        int n;

        StringBuilder sb = new StringBuilder();
        while ((n = read()) != 0) {
            boolean[] primes = sieveOfEratosthenes(2 * n);
            int count = 0;
            for (int i = n + 1; i <= 2 * n; i++) {
                if (primes[i]) {
                    count++;
                }
            }
            sb.append(count).append('\n');
        }

        System.out.println(sb.toString().trim());
    }

    public static boolean[] sieveOfEratosthenes(int n) {
        boolean[] primes = new boolean[n + 1];

        if (n < 2) return primes;

        for (int i = 2; i <= n; i++) {
            primes[i] = true;
        }

        for (int p = 2; p * p <= n; p++) {
            if (primes[p]) {
                for (int i = p * p; i <= n; i += p) {
                    primes[i] = false;
                }
            }
        }

        return primes;
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    // 다른 사람의 풀이 1
    static boolean[] b = new boolean[246913];
    static int[] count_arr = new int[246913];

    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        solution();
        count();
        StringBuilder sb = new StringBuilder();
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            int sum = 0;
            sb.append(count_arr[2 * n] - count_arr[n]).append("\n");
        }
        System.out.println(sb);
    }

    public static void solution() {
        b[0] = b[1] = true;
        for (int i = 2; i < Math.sqrt(b.length); i++) {
            if (b[i]) continue;
            for (int j = i * i; j < b.length; j += i) b[j] = true;
        }
    }

    public static void count() {
        int count = 0;
        for (int i = 2; i < b.length; i++) {
            if (!b[i]) count++;
            count_arr[i] = count;
        }
    }
}

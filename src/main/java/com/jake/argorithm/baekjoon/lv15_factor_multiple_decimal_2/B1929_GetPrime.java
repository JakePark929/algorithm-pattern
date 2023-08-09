package com.jake.argorithm.baekjoon.lv15_factor_multiple_decimal_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 소수 구하기 ]
 *
 * M이상 N이하의 소수를 모두 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 자연수 M과 N이 빈 칸을 사이에 두고 주어진다. (1 ≤ M ≤ N ≤ 1,000,000)
 * M이상 N이하의 소수가 하나 이상 있는 입력만 주어진다.
 *
 * 출력
 * 한 줄에 하나씩, 증가하는 순서대로 소수를 출력한다.
 *
 * 작성일 : 2023.08.08
 */
class B1929_GetPrime {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int m = Integer.parseInt(s.substring(0, s.indexOf(" ")));
        int n = Integer.parseInt(s.substring(s.indexOf(" ") + 1));

        boolean[] primes = getPrime(n);

        StringBuilder sb = new StringBuilder();
        for (int i = m; i <= n; i++) {
            if(!primes[i]) {
                sb.append(i).append('\n');
            }
        }

        System.out.println(sb.toString().trim());
    }

    private static boolean[] getPrime(int n) {
        boolean[] prime = new boolean[n + 1];
        if (n < 2) return prime;
        prime[0] = prime[1] = true;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (prime[i]) {
                continue;
            }
            for (int j = i * i; j < prime.length; j = j + i) {
                prime[j] = true;
            }
        }

        return prime;
    }

    // 다른 사람의 풀이 1
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int m, n;
    static boolean[] isDec;

    public static void main1(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        isDec = new boolean[n+1];

        getDecimal();

        System.out.println(sb);
    }

    static void getDecimal() {
        for(int i = 2; i <= n; i++) {
            if(isDec[i])
                continue;

            for(int j = i; j <= n; j += i) {
                isDec[j] = true;
            }

            if(i>=m && i<=n)
                sb.append(i).append('\n');
        }
    }
}

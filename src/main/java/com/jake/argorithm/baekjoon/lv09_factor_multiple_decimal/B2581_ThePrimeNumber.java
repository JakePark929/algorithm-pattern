package com.jake.argorithm.baekjoon.lv09_factor_multiple_decimal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ 소수 ]
 * <p>
 * 자연수 M과 N이 주어질 때 M이상 N이하의 자연수 중 소수인 것을 모두 골라 이들 소수의 합과 최솟값을 찾는 프로그램을 작성하시오.
 * <p>
 * 예를 들어 M=60,
 * N=100인 경우 60이상 100이하의 자연수 중 소수는 61, 67, 71, 73, 79, 83, 89, 97 총 8개가 있으므로,
 * 이들 소수의 합은 620이고, 최솟값은 61이 된다.
 * <p>
 * 입력
 * 입력의 첫째 줄에 M이, 둘째 줄에 N이 주어진다.
 * M과 N은 10,000이하의 자연수이며, M은 N보다 작거나 같다.
 * <p>
 * 출력
 * M이상 N이하의 자연수 중 소수인 것을 모두 찾아 첫째 줄에 그 합을, 둘째 줄에 그 중 최솟값을 출력한다.
 * 단, M이상 N이하의 자연수 중 소수가 없을 경우는 첫째 줄에 -1을 출력한다.
 * <p>
 * 작성일 : 2023.07.28
 */
class B2581_ThePrimeNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        boolean isFirst = false;
        int first = 0;
        int sum = 0;
        for (int i = m; i <= n; i++) {
            if(isPrime(i)) {
                if(!isFirst) first = i;
                isFirst = true;
                sum += i;
            }
        }

        System.out.println(sum != 0 ? sum : -1);
        System.out.println(sum != 0 ? first : "");
    }

    private static boolean isPrime(int n) {
        int cnt = 0;
        if (n == 1) return false;
        for (int i = 1; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) cnt += 1;
        }
        return cnt == 1;
    }

    // 다른 사람의 풀이 1
    public static boolean[] prime;

    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        // 배열 생성
        prime = new boolean[N + 1];
        get_prime();

        // 소수 합 및 최솟값
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = M; i <= N; i++) {
            if (!prime[i]) { // false == 소수
                sum += i;
                if (min == Integer.MAX_VALUE) { // 첫 소수가 최솟값
                    min = i;
                }
            }
        }
        if (sum == 0) System.out.println(-1); // 소수가 없다면
        else {
            System.out.println(sum);
            System.out.println(min);
        }


    }

    // 에라토스테네스 체 알고리즘
    public static void get_prime() {
        prime[0] = true;
        prime[1] = true;

        for (int i = 2; i <= Math.sqrt(prime.length); i++) {
            if (prime[i]) continue;
            for (int j = i * i; j < prime.length; j += i) {
                prime[j] = true;
            }
        }
    }
}

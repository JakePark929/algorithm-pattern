package com.jake.algorithm.baekjoon.lv15_factor_multiple_decimal_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ 골드바흐 파티션 ]
 * <p>
 * 골드바흐의 추측: 2보다 큰 짝수는 두 소수의 합으로 나타낼 수 있다.
 * <p>
 * 짝수 N을 두 소수의 합으로 나타내는 표현을 골드바흐 파티션이라고 한다.
 * 짝수 N이 주어졌을 때, 골드바흐 파티션의 개수를 구해보자.
 * 두 소수의 순서만 다른 것은 같은 파티션이다.
 * <p>
 * 입력
 * 첫째 줄에 테스트 케이스의 개수 T (1 ≤ T ≤ 100)가 주어진다.
 * 각 테스트 케이스는 한 줄로 이루어져 있고,
 * 정수 N은 짝수이고,
 * 2 < N ≤ 1,000,000을 만족한다.
 * <p>
 * 출력
 * 각각의 테스트 케이스마다 골드바흐 파티션의 수를 출력한다.
 * <p>
 * 작성일 : 2023.08.08
 */
class B17103_GoldbachPartition {
//    public static void main(String[] args) throws Exception {
//        int t = read();
//
//        StringBuilder sb = new StringBuilder();
//        while (t-- > 0) {
//            int n = read();
//
//            int count = goldbachPartitionCount(n);
//
//            sb.append(count).append('\n');
//        }
//
//        System.out.println(sb.toString().trim());
//    }
//
//    public static int goldbachPartitionCount(int n) {
//        int count = 0;
//        for (int i = 2; i <= n / 2; i++) {
//            if (isPrime(i) && isPrime(n - i)) {
//                count++;
//            }
//        }
//        return count;
//    }
//
//    public static boolean isPrime(int num) {
//        if (num <= 1) {
//            return false;
//        }
//        for (int i = 2; i * i <= num; i++) {
//            if (num % i == 0) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private static int read() throws Exception {
//        int c, n = System.in.read() & 15;
//        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
//        if (c == 13) System.in.read();
//        return n;
//    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        // 소수 구하기 = 소수 false
        boolean[] num = new boolean[1000001];
        num[0] = num[1] = true;
        for (int i = 2; i * i <= 1000000; i++) {
            if (!num[i]) {
                for (int j = i + i; j <= 1000000; j += i) {
                    num[j] = true;
                }
            }
        }

        while (t-- > 0) {
            int temp = Integer.parseInt(br.readLine());
            int ans = 0;
            for (int j = 2; j <= temp / 2; j++) {
                if (!num[j] && !num[temp - j]) ans++;
            }
            System.out.println(ans);
        }
    }
}

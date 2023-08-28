package com.jake.argorithm.baekjoon.lv18_advanced_2;

/**
 * [ 약수 ]
 *
 * 양수 A가 N의 진짜 약수가 되려면, N이 A의 배수이고, A가 1과 N이 아니어야 한다.
 * 어떤 수 N의 진짜 약수가 모두 주어질 때, N을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N의 진짜 약수의 개수가 주어진다.
 * 이 개수는 50보다 작거나 같은 자연수이다.
 * 둘째 줄에는 N의 진짜 약수가 주어진다.
 * 1,000,000보다 작거나 같고, 2보다 크거나 같은 자연수이고, 중복되지 않는다.
 *
 * 출력
 * 첫째 줄에 N을 출력한다. N은 항상 32비트 부호있는 정수로 표현할 수 있다.
 *
 * 작성일 : 2023.08.25
 */
class B1037_Divisor {
    public static void main(String[] args) throws Exception {
        int t = read();

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        while (t-- > 0) {
            int n = read();
            max = Math.max(n, max);
            min = Math.min(n, min);
        }

        System.out.println(max * min);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }
}

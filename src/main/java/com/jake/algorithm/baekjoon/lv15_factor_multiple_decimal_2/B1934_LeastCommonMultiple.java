package com.jake.algorithm.baekjoon.lv15_factor_multiple_decimal_2;

/**
 * [ 최소공배수 ]
 *
 * 두 자연수 A와 B에 대해서, A의 배수이면서 B의 배수인 자연수를 A와 B의 공배수라고 한다.
 * 이런 공배수 중에서 가장 작은 수를 최소공배수라고 한다.
 * 예를 들어, 6과 15의 공배수는 30, 60, 90등이 있으며, 최소 공배수는 30이다.
 *
 * 두 자연수 A와 B가 주어졌을 때, A와 B의 최소공배수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 테스트 케이스의 개수 T(1 ≤ T ≤ 1,000)가 주어진다.
 * 둘째 줄부터 T개의 줄에 걸쳐서 A와 B가 주어진다. (1 ≤ A, B ≤ 45,000)
 *
 * 출력
 * 첫째 줄부터 T개의 줄에 A와 B의 최소공배수를 입력받은 순서대로 한 줄에 하나씩 출력한다.
 *
 * 작성일 : 2023.08.08
 */
class B1934_LeastCommonMultiple {
    public static void main(String[] args) throws Exception {
        int t = read();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int a = read(), b = read();
            sb.append((a * b) / getGCD(a, b)).append('\n');
        }

        System.out.println(sb.toString().trim());
    }

    public static int getGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws Exception {
        int T = read();
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int a = read1();
            int b = read1();
            int lcm = a * b / gcd(a, b);
            sb.append(lcm).append('\n');
        }
        System.out.print(sb);
    }

    static int gcd(int a, int b) {
        while (b > 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    static int read1() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    // 다른 사람의 풀이 2
    static int gcd2(int n, int m){
        if(n==0) return m;
        return gcd2(m % n, n);
    }
}

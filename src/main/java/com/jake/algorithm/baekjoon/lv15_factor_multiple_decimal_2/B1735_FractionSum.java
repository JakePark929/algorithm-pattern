package com.jake.algorithm.baekjoon.lv15_factor_multiple_decimal_2;

/**
 * [ 분수 합 ]
 *
 * 분수 A/B는 분자가 A, 분모가 B인 분수를 의미한다.
 * A와 B는 모두 자연수라고 하자.
 *
 * 두 분수의 합 또한 분수로 표현할 수 있다.
 * 두 분수가 주어졌을 때, 그 합을 기약분수의 형태로 구하는 프로그램을 작성하시오.
 * 기약분수란 더 이상 약분되지 않는 분수를 의미한다.
 *
 * 입력
 * 첫째 줄과 둘째 줄에, 각 분수의 분자와 분모를 뜻하는 두 개의 자연수가 순서대로 주어진다.
 * 입력되는 네 자연수는 모두 30,000 이하이다.
 *
 * 출력
 * 첫째 줄에 구하고자 하는 기약분수의 분자와 분모를 뜻하는두 개의 자연수를
 * 빈 칸을 사이에 두고 순서대로 출력한다.
 *
 * 작성일 : 2023.08.08
 */
class B1735_FractionSum {
    public static void main(String[] args) throws Exception {
        int aChild = read(), aParent = read();
        int bChild = read(), bParent = read();

        int gcd = gcd(aParent, bParent);
        int lcm = (aParent * bParent) / gcd;
        int sumChild = aChild * (lcm / aParent) + bChild * (lcm / bParent);
        int sumParent = lcm;

        int sumGcd = gcd(sumChild, sumParent);

        sumChild /= sumGcd;
        sumParent /= sumGcd;

        System.out.println(sumChild);
        System.out.println(sumParent);
    }

    public static int gcd(int a, int b) {
        while (b > 0) {
            int r = a % b;
            a = b;
            b = r;
        }

        return a;
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }
}

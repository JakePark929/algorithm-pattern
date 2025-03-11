package com.jake.algorithm.baekjoon.lv00_sprout;

/**
 * [ 팩토리얼 ]
 *
 * 0보다 크거나 같은 정수 N이 주어진다.
 * 이때, N!을 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 정수 N(0 ≤ N ≤ 12)이 주어진다.
 *
 * 출력
 * 첫째 줄에 N!을 출력한다.
 *
 * 작성일 : 2023.07.27
 */
class B10872_Factorial {
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + c & 15;
        if(c == 13) System.in.read();
        return n;
    }

    public static void main(String[] args) throws Exception {
        int n = read();

        long factorial = n;
        for(int i = n - 1; i > 0; i--) {
            factorial *= i;
        }

        System.out.println(factorial == 0 ? 1 : factorial);
    }
}

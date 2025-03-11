package com.jake.algorithm.baekjoon.lv00_sprout;

/**
 * [ 검증수 ]
 *
 * 컴퓨터를 제조하는 회사인 KOI 전자에서는 제조하는 컴퓨터마다 6자리의 고유번호를 매긴다.
 * 고유번호의 처음 5자리에는 00000부터 99999까지의 수 중 하나가 주어지며 6번째 자리에는 검증수가 들어간다.
 * 검증수는 고유번호의 처음 5자리에 들어가는 5개의 숫자를 각각 제곱한 수의 합을 10으로 나눈 나머지이다.
 *
 * 예를 들어 고유번호의 처음 5자리의 숫자들이 04256이면,
 * 각 숫자를 제곱한 수들의 합 0+16+4+25+36 = 81 을 10으로 나눈 나머지인 1이 검증수이다.
 *
 * 입력
 * 첫째 줄에 고유번호의 처음 5자리의 숫자들이 빈칸을 사이에 두고 하나씩 주어진다.
 *
 * 출력
 * 첫째 줄에 검증수를 출력한다.
 *
 * 작성일 : 2023.07.27
 */
class B2475_ValidationNumber {
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + c & 15;
        if(c == 13) System.in.read();
        return n;
    }

    public static void main(String[] args) throws Exception {
        int num1 = read();
        int num2 = read();
        int num3 = read();
        int num4 = read();
        int num5 = read();

        int val = (int) ((Math.pow(num1, 2) + Math.pow(num2, 2) + Math.pow(num3, 2) + Math.pow(num4, 2) + Math.pow(num5, 2)) % 10);

        System.out.println(val);
    }
}

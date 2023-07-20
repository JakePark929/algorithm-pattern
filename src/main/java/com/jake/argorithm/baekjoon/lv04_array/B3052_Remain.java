package com.jake.argorithm.baekjoon.lv04_array;

import java.util.HashSet;

/**
 * [ 나머지 ]
 *
 * 두 자연수 A와 B가 있을 때, A%B는 A를 B로 나눈 나머지 이다.
 * 예를 들어, 7, 14, 27, 38을 3으로 나눈 나머지는 1, 2, 0, 2이다.
 *
 * 수 10개를 입력받은 뒤, 이를 42로 나눈 나머지를 구한다.
 * 그 다음 서로 다른 값이 몇 개 있는지 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄부터 열번째 줄 까지 숫자가 한 줄에 하나씩 주어진다.
 * 이 숫자는 1,000보다 작거나 같고, 음이 아닌 정수이다.
 *
 * 출력
 * 첫째 줄에, 42로 나누었을 때, 서로 다른 나머지가 몇 개 있는지 출력한다.
 *
 * 작성일 : 2023.07.19
 */
class B3052_Remain {
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if(c == 13) System.in.read();
        return n;
    }

    public static void main(String[] args) throws Exception {
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            int a = read();

            set.add(a % 42);
        }

        System.out.println(set.size());
    }
}

package com.jake.argorithm.baekjoon.lv08_math_1;

/**
 * [ 달팽이는 올라가고 싶다 ]
 * <p>
 * 땅 위에 달팽이가 있다. 이 달팽이는 높이가 V미터인 나무 막대를 올라갈 것이다.
 * <p>
 * 달팽이는 낮에 A미터 올라갈 수 있다. 하지만, 밤에 잠을 자는 동안 B미터 미끄러진다. 또, 정상에 올라간 후에는 미끄러지지 않는다.
 * <p>
 * 달팽이가 나무 막대를 모두 올라가려면, 며칠이 걸리는지 구하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 세 정수 A, B, V가 공백으로 구분되어서 주어진다. (1 ≤ B < A ≤ V ≤ 1,000,000,000)
 * <p>
 * 출력
 * 첫째 줄에 달팽이가 나무 막대를 모두 올라가는데 며칠이 걸리는지 출력한다.
 * <p>
 * 작성일 : 2023.07.27
 */
class B2869_SnailsWantToGoUp {
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    public static void main(String[] args) throws Exception {
        int a = read();
        int b = read();
        int v = read();

        int day = (v - b) / (a - b);

        if((v - b) % (a - b) != 0) day++;

        System.out.println(day);
    }
}

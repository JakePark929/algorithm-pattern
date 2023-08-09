package com.jake.argorithm.baekjoon.lv11_time_complexity;

/**
 * [ 알고리즘 수업 - 알고리즘 수행시간 4 ]
 * <p>
 * 오늘도 서준이는 알고리즘의 수행시간 수업 조교를 하고 있다.
 * 아빠가 수업한 내용을 학생들이 잘 이해했는지 문제를 통해서 확인해보자.
 * <p>
 * 입력의 크기 n이 주어지면 MenOfPassion 알고리즘 수행 시간을 예제 출력과 같은 방식으로 출력해보자.
 * <p>
 * MenOfPassion 알고리즘은 다음과 같다.
 * <p>
 * MenOfPassion(A[], n) {
 * sum <- 0;
 * for i <- 1 to n - 1
 * for j <- i + 1 to n
 * sum <- sum + A[i] × A[j]; # 코드1
 * return sum;
 * }
 * 입력
 * 첫째 줄에 입력의 크기 n(1 ≤ n ≤ 500,000)이 주어진다.
 * <p>
 * 출력
 * 첫째 줄에 코드1 의 수행 횟수를 출력한다.
 * <p>
 * 둘째 줄에 코드1의 수행 횟수를 다항식으로 나타내었을 때, 최고차항의 차수를 출력한다.
 * 단, 다항식으로 나타낼 수 없거나 최고차항의 차수가 3보다 크면 4를 출력한다.
 * <p>
 * 작성일 : 2023.08.01
 */
class B24265_AlgorithmRunningTime_4 {
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    public static void main(String[] args) throws Exception {
        int n = read();
        StringBuilder sb = new StringBuilder().append((long) n * (n - 1) / 2).append("\n").append(2);
        System.out.println(sb);
    }
}
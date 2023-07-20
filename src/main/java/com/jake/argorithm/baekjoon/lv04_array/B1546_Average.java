package com.jake.argorithm.baekjoon.lv04_array;

import java.util.Arrays;

/**
 * [ 평균 ]
 * <p>
 * 세준이는 기말고사를 망쳤다.
 * 세준이는 점수를 조작해서 집에 가져가기로 했다.
 * 일단 세준이는 자기 점수 중에 최댓값을 골랐다.
 * 이 값을 M이라고 한다.
 * 그리고 나서 모든 점수를 점수/M*100으로 고쳤다.
 * <p>
 * 예를 들어,
 * 세준이의 최고점이 70이고,
 * 수학점수가 50이었으면 수학점수는 50/70*100 이 되어 71.43점이 된다.
 * <p>
 * 세준이의 성적을 위의 방법대로 새로 계산했을 때, 새로운 평균을 구하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 시험 본 과목의 개수 N이 주어진다.
 * 이 값은 1000보다 작거나 같다.
 * 둘째 줄에 세준이의 현재 성적이 주어진다.
 * 이 값은 100보다 작거나 같은 음이 아닌 정수이고, 적어도 하나의 값은 0보다 크다.
 * <p>
 * 출력
 * 첫째 줄에 새로운 평균을 출력한다. 실제 정답과 출력값의 절대오차 또는 상대오차가 10-2 이하이면 정답이다.
 * <p>
 * 작성일 : 2023.07.19
 */
class B1546_Average {
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    public static void main(String[] args) throws Exception {
        final int size = read();

        int[] scores = new int[size];

        for (int i = 0; i < scores.length; i++) {
            scores[i] = read();
        }

        Arrays.sort(scores);

        int max = scores[scores.length - 1];

        double sum = 0;
        for (int score : scores) {
            sum += ((double) score / max) * 100;
        }

        System.out.println(sum / scores.length);
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws Exception {
        int N = read();
        double[] list = new double[N + 1];
        double sum = 0;
        int max = 0;

        for (int i = 0; i < N; i++) {
            list[i] = read();
            if (max < list[i]) max = (int) list[i];
            sum += list[i];
        }

        System.out.println(sum / max / N * 100);

    }
}

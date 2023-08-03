package com.jake.argorithm.baekjoon.lv13_sort;

/**
 * [ 커트라인 ]
 *
 * 2022 연세대학교 미래캠퍼스 슬기로운 코딩생활에 $N$명의 학생들이 응시했다.
 *
 * 이들 중 점수가 가장 높은 $k$명은 상을 받을 것이다. 이 때, 상을 받는 커트라인이 몇 점인지 구하라.
 *
 * 커트라인이란 상을 받는 사람들 중 점수가 가장 가장 낮은 사람의 점수를 말한다.
 *
 * 입력
 * 첫째 줄에는 응시자의 수
 * $N$과 상을 받는 사람의 수
 * $k$가 공백을 사이에 두고 주어진다.
 *
 * 둘째 줄에는 각 학생의 점수
 * $x$가 공백을 사이에 두고 주어진다.
 *
 * 출력
 * 상을 받는 커트라인을 출력하라.
 *
 * 제한
 *
 * $1 ≤ N ≤ 1,000
 *
 * $1 ≤ k ≤ N
 *
 * $0 ≤ x ≤ 10,000
 *
 * 작성일 : 2023.08.03
 */
class B25305_CutLine {
    public static void main(String[] args) throws Exception {
        int n = read();
        int k = read();

        int[] students = new int[n];

        for(int i = 0; i < students.length; i++) {
            students[i] = read();
        }

        System.out.println(countingSort(students, 10000)[students.length - k]);
    }

    private static int[] countingSort(int[] arr, int range) {
        int[] counting = new int[range + 1];
        int[] result = new int[arr.length];

        for (int i : arr) counting[i]++;

        for (int i = 1; i < counting.length; i++)
            counting[i] += counting[i - 1];

        for (int i = arr.length - 1; i >= 0; i--) {
            int val = arr[i];
            counting[val]--;
            result[counting[val]] = val;
        }

        return result;
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    // 1등 했지롱~~
}

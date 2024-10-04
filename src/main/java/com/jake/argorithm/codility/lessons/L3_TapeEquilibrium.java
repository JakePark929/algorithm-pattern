package com.jake.argorithm.codility.lessons;

/**
 * [ TapeEquilibrium ]
 *
 * 작성일 : 24.10.04
 */
public class L3_TapeEquilibrium {
    public int solution(int[] A) {
        int N = A.length;
        if (N == 2) {
            return Math.abs(A[0] - A[1]);
        }

        // 전체 배열의 합
        long totalSum = 0;
        for (int num : A) {
            totalSum += num;
        }

        // 첫 번째 부분의 합
        long leftSum = 0;
        long minDifference = Long.MAX_VALUE;

        // 배열을 순회하면서 최소 차이를 계산
        for (int P = 1; P < N; P++) {
            leftSum += A[P - 1];
            long rightSum = totalSum - leftSum;
            long difference = Math.abs(leftSum - rightSum);
            minDifference = Math.min(minDifference, difference);
        }

        return (int) minDifference;
    }

    public static void main(String[] args) {
        int[] list = {};
        final int solution = new L3_TapeEquilibrium().solution(list);

        System.out.println(solution);
    }
}

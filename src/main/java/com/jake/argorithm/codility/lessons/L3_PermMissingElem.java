package com.jake.argorithm.codility.lessons;

/**
 * [ PermMissingElem ]
 *
 * 작성일 : 24.10.04
 */
public class L3_PermMissingElem {
    public int solution(int[] A) {
        int N = A.length;
        long total = (long)(N + 1) * (N + 2) / 2;

        long sum = 0;
        for (int number : A) {
            sum += number;
        }

        return (int)(total - sum);
    }

    public static void main(String[] args) {
        int[] list = {2, 3, 1, 5};
        final int solution = new L3_PermMissingElem().solution(list);

        System.out.println(solution);
    }
}

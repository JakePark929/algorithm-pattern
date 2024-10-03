package com.jake.argorithm.codility.lessons;

import java.util.Arrays;
import java.util.LinkedList;

public class L2_CyclicRotation {
    public int[] solution(int[] A, int K) {
        LinkedList<Integer> queue = new LinkedList<>();

        if (A.length == 0 || K == 0) {
            return A;
        }

        for (int n : A) {
            queue.add(n);
        }

        int count = 0;
        while (count < K) {
            final Integer last = queue.removeLast();
            queue.addFirst(last);
            count++;
        }

        return queue.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public static void main(String[] args) {
        int[] A = {3, 8, 9, 7, 6};
        final int[] solution = new L2_CyclicRotation().solution(A, 3);

        System.out.println(Arrays.toString(solution));
    }
}

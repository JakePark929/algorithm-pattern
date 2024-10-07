package com.jake.argorithm.codility.lessons;

import java.util.HashSet;
import java.util.Set;

/**
 * [ MinAbsSum ]
 *
 * 작성일 : 24.10.05
 */
public class L17_MinAbsSum {
    public int solution(int[] A) {
        int sum = 0;
        for (int num : A) {
            sum += Math.abs(num);
        }

        int target = sum / 2; // 목표 합의 절반

        boolean[] dp = new boolean[target + 1];
        dp[0] = true; // 합이 0인 부분 집합은 항상 존재

        // DP를 통해 가능한 부분 집합의 합 계산
        for (int num : A) {
            int absNum = Math.abs(num);
            for (int j = target; j >= absNum; j--) {
                dp[j] = dp[j] || dp[j - absNum]; // 이전 상태에서 현재 수를 포함하는 경우
            }
        }

        // 가능한 가장 가까운 합을 찾기
        for (int j = target; j >= 0; j--) {
            if (dp[j]) {
                return sum - 2 * j; // 최소 절댓값 반환
            }
        }

        return sum; // 만약 어떤 부분 합도 존재하지 않는 경우
    }

    public int solution2(int[] A) {
        int N = A.length;
        if (N == 0) return 0; // 배열이 비어있을 경우

        // 배열을 두 부분으로 나누기
        int mid = N / 2;
        int[] left = new int[mid];
        int[] right = new int[N - mid];
        System.arraycopy(A, 0, left, 0, mid);
        System.arraycopy(A, mid, right, 0, N - mid);

        // 부분합 저장
        Set<Integer> leftSums = getSubsetSums(left);
        Set<Integer> rightSums = getSubsetSums(right);

        int totalSum = 0;
        for (int num : A) {
            totalSum += num;
        }

        int minAbsVal = Integer.MAX_VALUE;

        // 오른쪽의 부분합을 이용해 최적의 값 찾기
        for (int leftSum : leftSums) {
            for (int rightSum : rightSums) {
                int target = totalSum - 2 * (leftSum + rightSum); // val(A, S)의 형태로 변환
                minAbsVal = Math.min(minAbsVal, Math.abs(target));
            }
        }

        return minAbsVal;
    }

    // 주어진 배열의 가능한 부분합을 계산하는 메소드
    private Set<Integer> getSubsetSums(int[] arr) {
        Set<Integer> sums = new HashSet<>();
        int n = arr.length;
        // 가능한 모든 부분집합의 합을 계산
        for (int i = 0; i < (1 << n); i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    sum += arr[j];
                }
            }
            sums.add(sum);
        }

        return sums;
    }

    public static void main(String[] args) {
        int[] A = {1, 5, 2, -2};
        final int solution = new L17_MinAbsSum().solution2(A);

        System.out.println(solution);
    }
}

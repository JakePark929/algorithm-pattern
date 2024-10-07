package com.jake.argorithm.codility.lessons;

public class L17_NumberSolitaire {
    public int solution(int[] A) {
        int N = A.length;
        // DP 배열을 사용하여 각 칸까지 도달할 수 있는 최대 값을 저장
        int[] dp = new int[N];

        // 초기 값은 첫 번째 칸의 값으로 설정
        dp[0] = A[0];

        // 각 칸에 대해 계산
        for (int i = 1; i < N; i++) {
            int maxPrev = dp[i - 1]; // 이전 1칸의 값으로 초기화
            for (int dice = 1; dice <= 6; dice++) {
                if (i - dice >= 0) {
                    // 이전 2칸, 3칸 ... 6칸 중에서 최대 값을 선택
                    maxPrev = Math.max(maxPrev, dp[i - dice]);
                }
            }
            // 선택한 최대 값에 현재 칸의 값을 더해 DP 값을 설정
            dp[i] = maxPrev + A[i];
        }

        // 마지막 칸에 도착했을 때의 최대 값을 반환
        return dp[N - 1];
    }

    public static void main(String[] args) {
        int[] A = {1, -2, 0, 9, -1, -2};
        final int solution = new L17_NumberSolitaire().solution(A);

        System.out.println(solution);
    }
}

package com.jake.argorithm.programmers.lv0;

public class P120837 {
    public int solution(int hp) {
        int general = hp / 5;
        hp = hp % 5;

        int soldier = hp / 3;
        hp = hp % 3;

        int worker = hp;

        return general + soldier + worker;
    }

    public int dpSolution(int hp) {
        // dp 배열을 무한대로 초기화
        int[] dp = new int[hp + 1];
        for (int i = 1; i <= hp; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        // 기본적인 dp[0]은 0으로 설정 (체력 0일 때는 개미가 필요하지 않음)
        dp[0] = 0;

        // 1부터 hp 까지 최소 개미 수 계산
        for (int i = 1; i <= hp; i++) {
            if (i >= 5) dp[i] = Math.min(dp[i], dp[i - 5] + 1); // 장군개미 사용
            if (i >= 3) dp[i] = Math.min(dp[i], dp[i - 3] + 1); // 병정개미 사용
            dp[i] = Math.min(dp[i], dp[i - 1] + 1);             // 일개미 사용
        }

        return dp[hp];
    }

    public int bfSolution(int hp) {
        int minAnts = Integer.MAX_VALUE;

        // 장군개미를 사용할 수 있는 모든 경우 시도
        for (int generals = 0; generals <= hp / 5; generals++) {
            for (int soldiers = 0; soldiers <= (hp - generals * 5) / 3; soldiers++) {
                int remainingHp = hp - generals * 5 - soldiers * 3;
                int workers = remainingHp;
                int totalAnts = generals + soldiers + workers;

                // 최소 개미 수를 갱신
                if (totalAnts < minAnts) {
                    minAnts = totalAnts;
                }
            }
        }

        return minAnts;
    }

    public static class btSolution {

        // 최소 병력 수를 저장할 변수
        private int minAnts = Integer.MAX_VALUE;

        public int solution(int hp) {
            // 백트래킹 시작
            backtrack(hp, 0);

            return minAnts;
        }

        // 백트래킹 메서드
        private void backtrack(int hp, int ants) {
            // 체력이 0 이하가 되면 최소 개미 수 갱신
            if (hp <= 0) {
                if (hp == 0) {
                    minAnts = Math.min(minAnts, ants);
                }

                return;
            }

            // 가지치기: 이미 현재 사용한 개미 수가 최소 개미 수보다 크면 중단
            if (ants >= minAnts) {
                return;
            }

            // 장군개미(5 공격력) 사용
            backtrack(hp - 5, ants + 1);
            // 병정개미(3 공격력) 사용
            backtrack(hp - 3, ants + 1);
            // 일개미(1 공격력) 사용
            backtrack(hp - 1, ants + 1);
        }
    }

    public static void main(String[] args) {
        final int solution = new P120837().dpSolution(24);

        System.out.println(solution);
    }
}

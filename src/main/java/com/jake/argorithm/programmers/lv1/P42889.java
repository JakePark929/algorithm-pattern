package com.jake.argorithm.programmers.lv1;

import java.util.*;

/**
 * [ 실패율 ]
 * <p>
 * 슈퍼 게임 개발자 오렐리는 큰 고민에 빠졌다.
 * 그녀가 만든 프랜즈 오천성이 대성공을 거뒀지만,
 * 요즘 신규 사용자의 수가 급감한 것이다.
 * 원인은 신규 사용자와 기존 사용자 사이에 스테이지 차이가 너무 큰 것이 문제였다.
 * <p>
 * 이 문제를 어떻게 할까 고민 한 그녀는 동적으로 게임 시간을 늘려서 난이도를 조절하기로 했다. 역
 * 시 슈퍼 개발자라 대부분의 로직은 쉽게 구현했지만, 실패율을 구하는 부분에서 위기에 빠지고 말았다.
 * 오렐리를 위해 실패율을 구하는 코드를 완성하라.
 * <p>
 * 실패율은 다음과 같이 정의한다.
 * 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
 * 전체 스테이지의 개수 N,
 * 게임을 이용하는 사용자가 현재 멈춰있는 스테이지의 번호가 담긴 배열 stages가 매개변수로 주어질 때,
 * 실패율이 높은 스테이지부터 내림차순으로 스테이지의 번호가 담겨있는 배열을 return 하도록 solution 함수를 완성하라.
 * <p>
 * 제한사항
 * 스테이지의 개수 N은 1 이상 500 이하의 자연수이다.
 * stages 의 길이는 1 이상 200,000 이하이다.
 * stages 에는 1 이상 N + 1 이하의 자연수가 담겨있다.
 * 각 자연수는 사용자가 현재 도전 중인 스테이지의 번호를 나타낸다.
 * 단, N + 1 은 마지막 스테이지(N 번째 스테이지) 까지 클리어 한 사용자를 나타낸다.
 * 만약 실패율이 같은 스테이지가 있다면 작은 번호의 스테이지가 먼저 오도록 하면 된다.
 * 스테이지에 도달한 유저가 없는 경우 해당 스테이지의 실패율은 0 으로 정의한다.
 * <p>
 * 작성일 : 2023.06.23
 */
class P42889 {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        HashMap<Integer, Double> map = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            long fail = 0;
            long win = 0;
            for (int stage : stages) {
                if (stage == i) {
                    fail++;
                    win++;
                } else if (stage > i) {
                    win++;
                }
            }

            map.put(i, Double.isNaN((double) fail / win) ? 0 : (double) fail / win);
        }

        List<Map.Entry<Integer, Double>> entryList = new LinkedList<>(map.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for (int i = 0; i < answer.length; i++) {
            answer[i] = entryList.get(i).getKey();
        }

        return answer;
    }

    // 다른 사람의 풀이 1
    public int[] solution1(int N, int[] lastStages) {
        int nPlayers = lastStages.length;
        int[] nStagePlayers = new int[N + 2];
        for (int stage : lastStages) {
            nStagePlayers[stage] += 1;
        }

        int remainingPlayers = nPlayers;
        List<Stage> stages = new ArrayList<>();
        for (int id = 1; id <= N; id++) {
            double failure = (double) nStagePlayers[id] / remainingPlayers;
            remainingPlayers -= nStagePlayers[id];

            Stage s = new Stage(id, failure);
            stages.add(s);
        }
        Collections.sort(stages, Collections.reverseOrder());

        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = stages.get(i).id;
        }
        return answer;
    }

    class Stage implements Comparable<Stage> {
        public int id;
        public double failure;

        public Stage(int id_, double failure_) {
            id = id_;
            failure = failure_;
        }

        @Override
        public int compareTo(Stage o) {
            if (failure < o.failure) {
                return -1;
            }
            if (failure > o.failure) {
                return 1;
            }
            return 0;
        }
    }

    // 다른 사람의 풀이 2
    public int[] solution2(int N, int[] stages) {
        int[] answer = new int[N];
        double[] tempArr = new double[N];
        int idx = stages.length;
        double tempD = 0;
        int tempI = 0;

        for (int stage : stages) {
            if (stage != N + 1)
                answer[stage - 1] += 1;
        }

        for (int i = 0; i < N; i++) {
            int personNum = answer[i];
            tempArr[i] = (double) personNum / idx;
            idx -= personNum;
            answer[i] = i + 1;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N - i; j++) {
                if (tempArr[j - 1] < tempArr[j]) {
                    tempD = tempArr[j - 1];
                    tempArr[j - 1] = tempArr[j];
                    tempArr[j] = tempD;

                    tempI = answer[j - 1];
                    answer[j - 1] = answer[j];
                    answer[j] = tempI;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        P42889 problem = new P42889();
        System.out.println(Arrays.toString(problem.solution(5, new int[]{4, 4, 4, 4, 4})));
    }
}

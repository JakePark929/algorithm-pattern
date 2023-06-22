package com.jake.argorithm.programmers.lv1;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * [ 명예의 전당 1 ]
 * <p>
 * "명예의 전당"이라는 TV 프로그램에서는 매일 1명의 가수가 노래를 부르고,
 * 시청자들의 문자 투표수로 가수에게 점수를 부여합니다.
 * 매일 출연한 가수의 점수가 지금까지 출연 가수들의 점수 중 상위 k번째 이내이면
 * 해당 가수의 점수를 명예의 전당이라는 목록에 올려 기념합니다.
 * 즉 프로그램 시작 이후 초기에 k일까지는 모든 출연 가수의 점수가 명예의 전당에 오르게 됩니다.
 * k일 다음부터는 출연 가수의 점수가 기존의 명예의 전당 목록의 k번째 순위의 가수 점수보다 더 높으면,
 * 출연 가수의 점수가 명예의 전당에 오르게 되고 기존의 k번째 순위의 점수는 명예의 전당에서 내려오게 됩니다.
 * <p>
 * 이 프로그램에서는 매일 "명예의 전당"의 최하위 점수를 발표합니다.
 * 예를 들어, k = 3이고, 7일 동안 진행된 가수의 점수가 [10, 100, 20, 150, 1, 100, 200]이라면,
 * 명예의 전당에서 발표된 점수는 아래의 그림과 같이 [10, 10, 10, 20, 20, 100, 100]입니다.
 * <p>
 * 명예의 전당 목록의 점수의 개수 k,
 * 1일부터 마지막 날까지 출연한 가수들의 점수인 score가 주어졌을 때,
 * 매일 발표된 명예의 전당의 최하위 점수를 return하는 solution 함수를 완성해주세요.
 * <p>
 * 제한사항
 * 3 ≤ k ≤ 100
 * 7 ≤ score의 길이 ≤ 1,000
 * 0 ≤ score[i] ≤ 2,000
 * <p>
 * 작성일 : 2023.06.22
 */
public class P138477 {
    public LinkedList<Integer> solution(int k, int[] score) {
        LinkedList<Integer> answer = new LinkedList<>();
        LinkedList<Integer> ranking = new LinkedList<>();

        for (int i : score) {
            if (ranking.size() < k) {
                ranking.add(i);
                ranking.sort(Comparator.reverseOrder());
            } else {
                if (i > ranking.getLast()) {
                    ranking.removeLast();
                    ranking.add(i);
                    ranking.sort(Comparator.reverseOrder());
                }
            }
            answer.add(ranking.getLast());
        }

        return answer;
    }

    // 다른 사람의 풀이 1
    public int[] solution1(int k, int[] score) {
        int[] answer = new int[score.length];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int temp = 0;

        for(int i = 0; i < score.length; i++) {
            priorityQueue.add(score[i]);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
            answer[i] = priorityQueue.peek();
        }

        return answer;
    }

    public static void main(String[] args) {
        P138477 problem = new P138477();
        System.out.println(problem.solution(3, new int[]{10, 100, 20, 150, 1, 100, 200}));
    }
}

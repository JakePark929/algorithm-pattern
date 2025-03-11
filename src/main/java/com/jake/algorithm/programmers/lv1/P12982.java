package com.jake.algorithm.programmers.lv1;

import java.util.*;

/**
 * [ 예산 ]
 * <p>
 * S사에서는 각 부서에 필요한 물품을 지원해 주기 위해 부서별로 물품을 구매하는데 필요한 금액을 조사했습니다.
 * 그러나, 전체 예산이 정해져 있기 때문에 모든 부서의 물품을 구매해 줄 수는 없습니다.
 * 그래서 최대한 많은 부서의 물품을 구매해 줄 수 있도록 하려고 합니다.
 * <p>
 * 물품을 구매해 줄 때는 각 부서가 신청한 금액만큼을 모두 지원해 줘야 합니다.
 * 예를 들어 1,000원을 신청한 부서에는 정확히 1,000원을 지원해야 하며, 1,000원보다 적은 금액을 지원해 줄 수는 없습니다.
 * <p>
 * 부서별로 신청한 금액이 들어있는 배열 d와 예산 budget이 매개변수로 주어질 때,
 * 최대 몇 개의 부서에 물품을 지원할 수 있는지 return 하도록 solution 함수를 완성해주세요.
 * <p>
 * 제한사항
 * d는 부서별로 신청한 금액이 들어있는 배열이며, 길이(전체 부서의 개수)는 1 이상 100 이하입니다.
 * d의 각 원소는 부서별로 신청한 금액을 나타내며, 부서별 신청 금액은 1 이상 100,000 이하의 자연수입니다.
 * budget은 예산을 나타내며, 1 이상 10,000,000 이하의 자연수입니다.
 * <p>
 * 작성일 : 2023.06.19
 */
class P12982 {
    static int[] numbers;
    static int result = 0;

    public int solution(int[] d, int budget) {
        int answer = 0;
        Integer[] dList = Arrays.stream(d).boxed().toArray(Integer[]::new);
        Arrays.sort(dList, (i1, i2) -> i2 - i1);
        System.out.println(Arrays.toString(dList));

        for (int i = dList.length; i > 0; i--) {
            numbers = new int[i];
            comb(dList, budget, i, 0, 0);
            if (result >= 0) {
                answer = i;
                break;
            }
        }

        return answer;
    }

    public static void comb(Integer[] dList, int budget, int depth, int start, int cnt) {
        if (cnt == depth) {
            int sum = 0;
            for (int i : numbers) {
                sum += i;
            }
            result = budget - sum;
            return;
        }

        for (int i = start; i < dList.length; i++) {
            numbers[cnt] = dList[i];
            comb(dList, budget, depth, i + 1, cnt + 1);
        }
    }

    // 다른사람의 풀이 1
    public int solution2(int[] d, int budget) {
        int answer = 0;
        int result = 0;

        Arrays.sort(d);

        for (int i = 0; i < d.length; i++) {
            result += d[i];
            if (result > budget) {
                answer = i;
                break;
            }
        }

        if (result <= budget) {
            answer = d.length;
        }

        return answer;
    }

    public static void main(String[] args) {
        P12982 problem = new P12982();
        int[] numList = new int[100];

        for (int i = 0; i < 100; i++) {
            numList[i] = (int) (Math.random() * 100000) + 1;
        }

//        System.out.println(problem.solution2(numList, 10000000));
        System.out.println(problem.solution2(new int[]{1, 3, 2, 5, 4}, 9));
    }
}

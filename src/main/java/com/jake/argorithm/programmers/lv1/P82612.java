package com.jake.argorithm.programmers.lv1;

/**
 * [ 부족한 금액 계산하기 ]
 *
 * 새로 생긴 놀이기구는 인기가 매우 많아 줄이 끊이질 않습니다.
 * 이 놀이기구의 원래 이용료는 price원 인데, 놀이기구를 N 번 째 이용한다면 원래 이용료의 N배를 받기로 하였습니다.
 * 즉, 처음 이용료가 100이었다면 2번째에는 200, 3번째에는 300으로 요금이 인상됩니다.
 * 놀이기구를 count번 타게 되면 현재 자신이 가지고 있는 금액에서 얼마가 모자라는지를 return 하도록 solution 함수를 완성하세요.
 * 단, 금액이 부족하지 않으면 0을 return 하세요.
 *
 * 제한사항
 * 놀이기구의 이용료 price : 1 ≤ price ≤ 2,500, price는 자연수
 * 처음 가지고 있던 금액 money : 1 ≤ money ≤ 1,000,000,000, money는 자연수
 * 놀이기구의 이용 횟수 count : 1 ≤ count ≤ 2,500, count는 자연수
 *
 * 작성일 : 2023.06.20
 */
public class P82612 {
    public long solution(int price, int money, int count) {
        long answer = 0;

        for (int i = 1; i <= count; i++) {
            answer += (long) price * i;
        }

        return Math.max(answer - money, 0);
    }
    
    // 다른 사람의 풀이 2 - 등차 수열의 합
    public long solution(long price, long money, long count) {
        return Math.max(price * (count * (count + 1) / 2) - money, 0);
    }

    public static void main(String[] args) {
        P82612 problem = new P82612();
        System.out.println(problem.solution(3, 20, 4));
    }
}

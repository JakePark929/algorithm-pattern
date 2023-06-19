package com.jake.argorithm.programmers.lv1;

/**
 * [ 두 정수 사이의 합 ]
 *
 * 두 정수 a, b가 주어졌을 때 a와 b 사이에 속한 모든 정수의 합을 리턴하는 함수, solution을 완성하세요.
 * 예를 들어 a = 3, b = 5인 경우, 3 + 4 + 5 = 12이므로 12를 리턴합니다.
 *
 * 제한 조건
 * a와 b가 같은 경우는 둘 중 아무 수나 리턴하세요.
 * a와 b는 -10,000,000 이상 10,000,000 이하인 정수입니다.
 * a와 b의 대소관계는 정해져있지 않습니다.
 *
 * 작성일 : 2023.06.15
 */
class P12912 {
    public long solution(int a, int b) {
        long answer = 0;

        if (a < b) {
            for (int i = a; i <= b; i++) {
                answer += i;
            }
        } else if (a > b) {
            for (int i = b; i <= a; i++) {
                answer += i;
            }
        } else {
            answer = a;
        }

        return answer;
    }

    // 다른 사람의 풀이
    public long solution2(int a, int b) {
        return sumAtoB(Math.min(a, b), Math.max(b, a));
    }

    private long sumAtoB(long a, long b) {
        return (b - a + 1) * (a + b) / 2; // 등차수열 합의 공식 (a1+an)/2 * n 을 사용할 수 있다
    }

    // 다른 사람의 풀이 2
    public long solution3(int a, int b) {
        long answer = 0;
        for (int i = (Math.min(a, b)); i <= (Math.max(a, b)); i++)
            answer += i;

        return answer;
    }

    public static void main(String[] args) {
        P12912 problem = new P12912();
        System.out.println(problem.solution3(3, 3));
    }
}

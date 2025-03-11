package com.jake.algorithm.programmers.lv1;

/**
 * [ 정수 제곱근 판별 ]
 * <p>
 * 임의의 양의 정수 n에 대해, n이 어떤 양의 정수 x의 제곱인지 아닌지 판단하려 합니다.
 * n이 양의 정수 x의 제곱이라면 x+1의 제곱을 리턴하고, n이 양의 정수 x의 제곱이 아니라면 -1을 리턴하는 함수를 완성하세요.
 * <p>
 * 제한 사항
 * n은 1이상, 50000000000000 이하인 양의 정수입니다.
 * <p>
 * 작성일 : 2023.06.16
 */
class P12934 {
    public long solution(long n) {
        if (n == 1) {
            return 4;
        }
        for (long i = 2; i < n; i++) {
            if (Math.sqrt((double) n) == i) {
                return (long) Math.pow(i + 1, 2);
            }
        }
        return -1;
    }

    // 다른 사람의 풀이 1
    public long solution2(long n) {
        if (Math.pow((int) Math.sqrt(n), 2) == n) {
            return (long) Math.pow(Math.sqrt(n) + 1, 2);
        }
        return -1;
    }

    // 다른 사람의 풀이 2
    public long solution3(long n) {
        long answer = 0;
        double x = Math.sqrt(n);
        if(x == (int) x) {
            answer = (long) Math.pow(x + 1, 2);
        } else {
            return -1;
        }
        return answer;
    }

    public static void main(String[] args) {
        P12934 problem = new P12934();
        System.out.println(problem.solution(1));
    }
}

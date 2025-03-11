package com.jake.algorithm.programmers.lv1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * [ 3진법 뒤집기 ]
 *
 * 자연수 n이 매개변수로 주어집니다.
 * n을 3진법 상에서 앞뒤로 뒤집은 후, 이를 다시 10진법으로 표현한 수를 return 하도록 solution 함수를 완성해주세요.
 *
 * 제한사항
 * n은 1 이상 100,000,000 이하인 자연수입니다.
 *
 * 작성일 : 2023.06.20
 */
class P68935 {
    public int solution(int n) {
        StringBuffer sb = new StringBuffer();
        sb.append(Integer.toString(n, 3));
        sb.reverse();

        return Integer.parseInt(String.valueOf(sb), 3);
    }

    // 다른 사람의 풀이 1
    public int solution2(int n) {
        int answer = 0;

        String str = "";

        while (n > 0) {
            int r = n % 3;
            n = n / 3;
            str = r + str;
        }

        System.out.println(str);

        for (int i = 0; i < str.length(); i++) {
            answer += Math.pow(3, i) * (str.charAt(i) - '0');
        }

        return answer;
    }

    // 다른 사람의 풀이 2
    public int solution3(int n) {
        int answer = 0;

        Queue<Integer> queue = new LinkedList<>();

        while (n != 0) {
            int value = n % 3;
            queue.offer(value);
            n /= 3;
        }

        while (!queue.isEmpty()) {
            int pop = queue.poll();
            answer *= 3;
            answer += pop;
        }

        return answer;
    }

    public static void main(String[] args) {
        P68935 problem = new P68935();
        System.out.println(problem.solution(45));
    }
}

package com.jake.argorithm.programmers.lv1;

import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * [ 숫자 짝꿍 ]
 * <p>
 * 두 정수 X, Y의 임의의 자리에서 공통으로 나타나는 정수 k(0 ≤ k ≤ 9)들을 이용하여
 * 만들 수 있는 가장 큰 정수를 두 수의 짝꿍이라 합니다.
 * (단, 공통으로 나타나는 정수 중 서로 짝지을 수 있는 숫자만 사용합니다.)
 * X, Y의 짝꿍이 존재하지 않으면, 짝꿍은 -1입니다. X, Y의 짝꿍이 0으로만 구성되어 있다면, 짝꿍은 0입니다.
 * <p>
 * 예를 들어, X = 3403이고 Y = 13203이라면,
 * X와 Y의 짝꿍은 X와 Y에서 공통으로 나타나는 3, 0, 3으로 만들 수 있는 가장 큰 정수인 330입니다.
 * 다른 예시로 X = 5525이고 Y = 1255이면 X와 Y의 짝꿍은 X와 Y에서 공통으로 나타나는 2, 5, 5로 만들 수 있는 가장 큰 정수인 552입니다.
 * (X에는 5가 3개, Y에는 5가 2개 나타나므로 남는 5 한 개는 짝 지을 수 없습니다.)
 * 두 정수 X, Y가 주어졌을 때, X, Y의 짝꿍을 return하는 solution 함수를 완성해주세요.
 * <p>
 * 제한사항
 * 3 ≤ X, Y의 길이(자릿수) ≤ 3,000,000입니다.
 * X, Y는 0으로 시작하지 않습니다.
 * X, Y의 짝꿍은 상당히 큰 정수일 수 있으므로, 문자열로 반환합니다.
 * <p>
 * 작성일 : 2023.06.26
 */
class P131128 {
    public String solution(String X, String Y) {
        HashMap<String, Integer> xMap = new HashMap<>();
        HashMap<String, Integer> yMap = new HashMap<>();

        for (int i = 0; i <= 9; i++) {
            int count = 0;
            for (int j = 0; j < X.length(); j++) {
                if (i == Character.getNumericValue(X.charAt(j))) {
                    count++;
                }
            }
            xMap.put(String.valueOf(i), count);
        }

        for (int i = 0; i <= 9; i++) {
            int count = 0;
            for (int j = 0; j < Y.length(); j++) {
                if (i == Character.getNumericValue(Y.charAt(j))) {
                    count++;
                }
            }
            yMap.put(String.valueOf(i), count);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 9; i >= 0; i--) {
            if (i == 0 && sb.length() == 0 && xMap.get(String.valueOf(i)) > 0 && yMap.get(String.valueOf(i)) > 0) {
                sb.append("0");
                break;
            }
            if (xMap.get(String.valueOf(i)) > 0 && yMap.get(String.valueOf(i)) > 0) {
                int loop = Math.min(xMap.get(String.valueOf(i)), yMap.get(String.valueOf(i)));
                sb.append(String.valueOf(i).repeat(Math.max(0, loop)));
            }
        }

        if (sb.length() == 0) {
            sb.append("-1");
        }

        return sb.toString();
    }

    // 다른 사람의 풀이 1
    public String solution1(String X, String Y) {
        StringBuilder answer = new StringBuilder();
        int[] x = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] y = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = 0; i < X.length(); i++) {
            x[X.charAt(i) - 48] += 1;
        }
        for (int i = 0; i < Y.length(); i++) {
            y[Y.charAt(i) - 48] += 1;
        }

        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < Math.min(x[i], y[i]); j++) {
                answer.append(i);
            }
        }

        if ("".equals(answer.toString())) {
            return "-1";
        } else if (answer.toString().charAt(0) == 48) {
            return "0";
        } else {
            return answer.toString();
        }
    }

    // 다른 사람의 풀이 2
    public String solution2(String X, String Y) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Collections.reverseOrder());

        for (int i=0; i<X.length(); i++) {
            pq1.offer(X.charAt(i) - '0');
        }
        for (int i=0; i<Y.length(); i++) {
            pq2.offer(Y.charAt(i) - '0');
        }

        while (!pq1.isEmpty() && !pq2.isEmpty()) {
            if (pq1.peek() == pq2.peek()) {
                sb.append(String.valueOf(pq1.poll()));
                pq2.poll();
            } else if (pq1.peek() > pq2.peek()) {
                pq1.poll();
            } else {
                pq2.poll();
            }
        }

        return sb.toString().equals("") ? "-1" : sb.toString().charAt(0)=='0' ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        P131128 problem = new P131128();
        System.out.println(problem.solution("100", "2345"));
    }
}

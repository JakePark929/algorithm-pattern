package com.jake.algorithm.a01_lecture.fc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 연산자 끼워넣기 ]
 *
 * 문제
 * N개의 수로 이루어진 수열 A1, A2, ..., AN이 주어진다.
 * 또, 수와 수 사이에 끼워넣을 수 있는 N-1개의 연산자가 주어진다.
 * 연산자는 덧셈(+), 뺄셈(-), 곱셈(×), 나눗셈(÷)으로만 이루어져 있다.
 * 우리는 수와 수 사이에 연산자를 하나씩 넣어서, 수식을 하나 만들 수 있다.
 * 이때, 주어진 수의 순서를 바꾸면 안 된다.
 * 예를 들어, 6개의 수로 이루어진 수열이 1, 2, 3, 4, 5, 6이고,
 * 주어진 연산자가 덧셈(+) 2개, 뺄셈(-) 1개, 곱셈(×) 1개, 나눗셈(÷) 1개인 경우에는 총 60가지의 식을 만들 수 있다.
 * 예를 들어, 아래와 같은 식을 만들 수 있다.
 *
 * 1+2+3-4×5÷6
 * 1÷2+3+4-5×6
 * 1+2÷3×4-5+6
 * 1÷2×3-4+5+6
 *
 * 식의 계산은 연산자 우선 순위를 무시하고 앞에서부터 진행해야 한다.
 * 또, 나눗셈은 정수 나눗셈으로 몫만 취한다. 음수를 양수로 나눌 때는 C++14의 기준을 따른다.
 * 즉, 양수로 바꾼 뒤 몫을 취하고, 그 몫을 음수로 바꾼 것과 같다.
 * 이에 따라서, 위의 식 4개의 결과를 계산해보면 아래와 같다.
 *
 * 1+2+3-4×5÷6 = 1
 * 1÷2+3+4-5×6 = 12
 * 1+2÷3×4-5+6 = 5
 * 1÷2×3-4+5+6 = 7
 *
 * N개의 수와 N-1개의 연산자가 주어졌을 때,
 * 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 수의 개수 N(2 ≤ N ≤ 11)가 주어진다.
 * 둘째 줄에는 A1, A2, ..., AN이 주어진다.
 * (1 ≤ Ai ≤ 100) 셋째 줄에는 합이 N-1인 4개의 정수가 주어지는데,
 * 차례대로 덧셈(+)의 개수, 뺄셈(-)의 개수, 곱셈(×)의 개수, 나눗셈(÷)의 개수이다.
 *
 * 출력
 * 첫째 줄에 만들 수 있는 식의 결과의 최댓값을, 둘째 줄에는 최솟값을 출력한다.
 * 연산자를 어떻게 끼워넣어도 항상 -10억보다 크거나 같고, 10억보다 작거나 같은 결과가 나오는 입력만 주어진다.
 * 또한, 앞에서부터 계산했을 때, 중간에 계산되는 식의 결과도 항상 -10억보다 크거나 같고, 10억보다 작거나 같다.
 *
 * 작성일 : 2025.03.12
 */
public class P1_B14888_OperatorInsert {
    static int N, max, min;
    static int[] nums, operators, order;

    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        nums = new int[N + 1];
        operators = new int[5];
        order = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            nums[i] = scan.nextInt();
        }

        for (int i = 1; i <= 4; i++) {
            operators[i] = scan.nextInt();
        }

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
    }

    // 완성된 식에 맞게 계산을 해서 정답에 갱신하는 작업
    static int calculator() {
        // nums, order
        int value = nums[1];

        for (int i = 1; i <= N - 1; i++) {
            // value, order[i], num[i + 1]
            if (order[i] == 1) // +
                value = value + nums[i + 1];
            if (order[i] == 2) // -
                value = value - nums[i + 1];
            if (order[i] == 3) // *
                value = value * nums[i + 1];
            if (order[i] == 4) // /
                value = value / nums[i + 1];
        }

        return value;
    }

    // order[1...N-1]에 연산자들이 순서대로 저장될 것이다.
    static void recurrence(int k) {
        if (k == N) {  // 모든 연산자들을 전부 나열하는 방법을 찾은 상태
            // 완성된 식에 맞게 계산을 해서 정답에 갱신하는 작업
            int value = calculator();
            max = Math.max(max, value);
            min = Math.min(min, value);
        }  else {  // k 번째 연산자는 무엇을 선택할 것인가
            // 4가지의 연산자들 중에 뭘 쓸 것인지 선택하고 재귀호출하기
            for (int candidate = 1; candidate <= 4; candidate++) {
                if (operators[candidate] >= 1) {
                    operators[candidate]--;
                    order[k] = candidate;
                    recurrence(k + 1);
                    operators[candidate]++;
                    order[k] = candidate;
                }
            }
        }
    }

    // 피연산자 2개와 연산자가 주어졌을 때 계산해주는 함수
    static int calculatorAdvance(int operand1, int operator, int operand2) {
        if (operator == 1) {

            return operand1 + operand2;
        } else if (operator == 2) {

            return operand1 - operand2;
        } else if (operator == 3) {

            return operand1 * operand2;
        } else {

            return operand1 / operand2;
        }
    }

    static void recurrenceAdvance(int k, int value) {
        if (k == N) {  // 모든 연산자들을 전부 나열하는 방법을 찾은 상태
            max = Math.max(max, value);
            min = Math.min(min, value);
        }  else {  // k 번째 연산자는 무엇을 선택할 것인가
            // 4가지의 연산자들 중에 뭘 쓸 것인지 선택하고 재귀호출하기
            for (int candidate = 1; candidate <= 4; candidate++) {
                if (operators[candidate] >= 1) {
                    operators[candidate]--;
                    order[k] = candidate;
                    int newValue = calculatorAdvance(value, candidate, nums[k + 1]);
                    recurrenceAdvance(k + 1, newValue);
                    operators[candidate]++;
                    order[k] = candidate;
                }
            }
        }
    }

    public static void main(String[] args) {
        input();

        recurrenceAdvance(1, nums[1]);

        sb.append(max).append("\n").append(min);
        System.out.println(sb.toString());
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

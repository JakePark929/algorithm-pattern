package com.jake.algorithm.baekjoon.lv20_backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 연산자 끼워넣기 ]
 *
 * N개의 수로 이루어진 수열 A1, A2, ..., AN이 주어진다.
 * 또, 수와 수 사이에 끼워넣을 수 있는 N-1개의 연산자가 주어진다.
 * 연산자는 덧셈(+), 뺄셈(-), 곱셈(×), 나눗셈(÷)으로만 이루어져 있다.
 *
 * 우리는 수와 수 사이에 연산자를 하나씩 넣어서, 수식을 하나 만들 수 있다.
 * 이때, 주어진 수의 순서를 바꾸면 안 된다.
 *
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
 * 또, 나눗셈은 정수 나눗셈으로 몫만 취한다.
 * 음수를 양수로 나눌 때는 C++14의 기준을 따른다.
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
 * 둘째 줄에는 A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 100)
 * 셋째 줄에는 합이 N-1인 4개의 정수가 주어지는데,
 * 차례대로 덧셈(+)의 개수, 뺄셈(-)의 개수, 곱셈(×)의 개수, 나눗셈(÷)의 개수이다.
 *
 * 출력
 * 첫째 줄에 만들 수 있는 식의 결과의 최댓값을, 둘째 줄에는 최솟값을 출력한다.
 * 연산자를 어떻게 끼워넣어도 항상 -10억보다 크거나 같고, 10억보다 작거나 같은 결과가 나오는 입력만 주어진다.
 * 또한, 앞에서부터 계산했을 때, 중간에 계산되는 식의 결과도 항상 -10억보다 크거나 같고, 10억보다 작거나 같다.
 *
 * 작성일 : 2023.09.04
 */
class B14888_InsertOperatorIn {
    private static int MAX = Integer.MIN_VALUE; // 최댓값
    private static int MIN = Integer.MAX_VALUE; // 최댓값
    private static final int[] operator = new int[4]; // 연산자 개수
    private static int[] number; // 숫자
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        number = new int[n];

        // 숫자 입력
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }

        // 연산자 입력
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }

        dfs(number[0], 1);

        System.out.println(MAX);
        System.out.println(MIN);
    }

    public static void dfs(int num, int idx) {
        if (idx == n) {
            MAX = Math.max(MAX, num);
            MIN = Math.min(MIN, num);
            return;
        }

        for (int i = 0; i < 4; i++) {
            // 연산자 개수가 1개 이상인 경우
            if (operator[i] > 0) {

                // 해당 연산자 1 감소
                operator[i]--;

                switch (i) {
                    case 0: dfs(num + number[idx], idx + 1); break;
                    case 1: dfs(num - number[idx], idx + 1); break;
                    case 2: dfs(num * number[idx], idx + 1); break;
                    case 3: dfs(num / number[idx], idx + 1); break;
                }

                // 재귀호출이 종료되면 다시 해당 연산자 개수 복구
                operator[i]++;
            }
        }
    }

    // 다른 사람의 풀이 1
    static int n1;
    static int[] num;
    static int maxPlus, maxMinus, maxMultiple, maxDivide;
    static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n1 = Integer.parseInt(br.readLine());
        num = new int[n1];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n1; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        maxPlus = Integer.parseInt(st.nextToken());
        maxMinus = Integer.parseInt(st.nextToken());
        maxMultiple = Integer.parseInt(st.nextToken());
        maxDivide = Integer.parseInt(st.nextToken());

        backTracking(0, 0, 0, 0, num[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

    private static void backTracking(int plus, int minus, int multiple, int divide, int sum, int times) {
        if (times == n1) {
            min = Math.min(min, sum);
            max = Math.max(max, sum);
            return;
        }

        if (plus < maxPlus)
            backTracking(plus + 1, minus, multiple, divide, sum + num[times], times + 1);
        if (minus < maxMinus)
            backTracking(plus, minus + 1, multiple, divide, sum - num[times], times + 1);
        if (multiple < maxMultiple)
            backTracking(plus, minus, multiple + 1, divide, sum * num[times], times + 1);
        if (divide < maxDivide)
            backTracking(plus, minus, multiple, divide + 1, sum / num[times], times + 1);
    }
}

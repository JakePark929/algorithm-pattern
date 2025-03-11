package com.jake.algorithm.baekjoon.lv16_stack_queue_deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * [ 카드 2 ]
 *
 * N장의 카드가 있다.
 * 각각의 카드는 차례로 1부터 N까지의 번호가 붙어 있으며,
 * 1번 카드가 제일 위에, N번 카드가 제일 아래인 상태로 순서대로 카드가 놓여 있다.
 *
 * 이제 다음과 같은 동작을 카드가 한 장 남을 때까지 반복하게 된다.
 * 우선, 제일 위에 있는 카드를 바닥에 버린다.
 * 그 다음, 제일 위에 있는 카드를 제일 아래에 있는 카드 밑으로 옮긴다.
 *
 * 예를 들어 N=4인 경우를 생각해 보자.
 * 카드는 제일 위에서부터 1234 의 순서로 놓여있다.
 * 1을 버리면 234가 남는다.
 * 여기서 2를 제일 아래로 옮기면 342가 된다.
 * 3을 버리면 42가 되고, 4를 밑으로 옮기면 24가 된다.
 * 마지막으로 2를 버리고 나면, 남는 카드는 4가 된다.
 *
 * N이 주어졌을 때, 제일 마지막에 남게 되는 카드를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 정수 N(1 ≤ N ≤ 500,000)이 주어진다.
 *
 * 출력
 * 첫째 줄에 남게 되는 카드의 번호를 출력한다.
 *
 * 작성일 : 2023.08.24
 */
class B2164_Card_2 {
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    public static void main(String[] args) throws Exception {
        int n = read();

        Queue<Integer> cards = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            cards.offer(i);
        }

        // 카드가 한장 남을 때 까지 반복
        while (cards.size() != 1) {
            // 제일 위에 카드를 바닥에 버림
            cards.poll();
            // 제일 위에 있는 카드를 제일 아래에 있는 카드 밑으로 옮김
            Integer top = cards.poll();
            cards.offer(top);
        }

        System.out.println(cards.peek());
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(findLastCard(N));
        br.close();
    }

    public static int findLastCard(int N) {
        int M = (int) Math.pow(2, (int) (Math.log(N) / Math.log(2)));
        int lastCard = 2 * (N - M);
        return (lastCard != 0) ? lastCard : N;
    }
}

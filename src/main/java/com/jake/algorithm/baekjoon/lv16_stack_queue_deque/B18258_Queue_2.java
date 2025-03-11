package com.jake.algorithm.baekjoon.lv16_stack_queue_deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * [ 큐 2 ]
 * <p>
 * 정수를 저장하는 큐를 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.
 * <p>
 * 명령은 총 여섯 가지이다.
 * <p>
 * push X: 정수 X를 큐에 넣는 연산이다.
 * pop: 큐에서 가장 앞에 있는 정수를 빼고, 그 수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 * size: 큐에 들어있는 정수의 개수를 출력한다.
 * empty: 큐가 비어있으면 1, 아니면 0을 출력한다.
 * front: 큐의 가장 앞에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 * back: 큐의 가장 뒤에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 * <p>
 * 입력
 * 첫째 줄에 주어지는 명령의 수 N (1 ≤ N ≤ 2,000,000)이 주어진다.
 * 둘째 줄부터 N개의 줄에는 명령이 하나씩 주어진다.
 * 주어지는 정수는 1보다 크거나 같고, 100,000보다 작거나 같다.
 * 문제에 나와있지 않은 명령이 주어지는 경우는 없다.
 * <p>
 * 출력
 * 출력해야하는 명령이 주어질 때마다, 한 줄에 하나씩 출력한다.
 * <p>
 * 작성일 : 2023.08.24
 */
class B18258_Queue_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Deque<Integer> q = new ArrayDeque<>();

        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            String[] order = br.readLine().split(" ");
            int x = 0;
            if (order[0].equals("push")) x = Integer.parseInt(order[1]);

            switch (order[0]) {
                case "push":
                    q.add(x);
                    break;
                case "pop":
                    sb.append(q.isEmpty() ? "-1" : q.pollFirst()).append('\n');
                    break;
                case "size":
                    sb.append(q.size()).append('\n');
                    break;
                case "empty":
                    sb.append(q.isEmpty() ? 1 : 0).append('\n');
                    break;
                case "front":
                    sb.append(q.isEmpty() ? -1 : q.peekFirst()).append('\n');
                    break;
                case "back":
                    sb.append(q.isEmpty() ? -1 : q.peekLast()).append('\n');
                    break;
            }
        }

        System.out.println(sb);
    }

    // 다른 사람의 풀이 1
    static int[] q = new int[2000000];    // 명령의 수는 2,000,000을 안넘음

    static int size = 0;
    static int front = 0;
    static int back = 0;

    static StringBuilder sb = new StringBuilder();

    public static void main1(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());


        while (N-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");

            switch (st.nextToken()) {
                case "push":
                    push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    pop();
                    break;
                case "size":
                    size();
                    break;
                case "empty":
                    empty();
                    break;
                case "front":
                    front();
                    break;
                case "back":
                    back();
                    break;

            }
        }
        System.out.println(sb);
    }

    static void push(int n) {
        q[back] = n;
        back++;
        size++;
    }

    static void pop() {
        if (size == 0) {
            sb.append(-1).append('\n');
        } else {
            sb.append(q[front]).append('\n');    // 맨 앞의 원소를 출력
            size--;
            front++;    // front가 가리키는 위치 1 증가
        }
    }

    static void size() {
        sb.append(size).append('\n');
    }

    static void empty() {
        if (size == 0) {
            sb.append(1).append('\n');
        } else sb.append(0).append('\n');
    }

    static void front() {
        if (size == 0) {
            sb.append(-1).append('\n');
        } else {
            sb.append(q[front]).append('\n');     // 맨 앞의 원소 출력
        }
    }

    static void back() {
        if (size == 0) {
            sb.append(-1).append('\n');
        } else {
            sb.append(q[back - 1]).append('\n');    // 맨 뒤의 원소 출력
        }
    }

    // 다른 사람의 풀이 2
    static final char PUSH = 'u';
    static final char POP = 'o';
    static final char SIZE = 's';
    static final char EMPTY = 'e';
    static final char FRONT = 'f';
    static final char BACK = 'b';

    public static void main2(String[] args) throws Exception {

        int N = read();

        int[] queue = new int[N];
        int tail = 0, head = 0;

        BufferedWriter bw = new BufferedWriter();

        while (N-- > 0) {

            int op = System.in.read();
            if (op == 'p') op = System.in.read();

            read();

            switch (op) {
                case PUSH:
                    queue[tail++] = read();
                    continue;
                case POP:
                    bw.write(tail == head ? -1 : queue[head++]);
                    break;
                case SIZE:
                    bw.write(tail - head);
                    break;
                case EMPTY:
                    bw.write(tail == head ? 1 : 0);
                    break;
                case FRONT:
                    bw.write(tail == head ? -1 : queue[head]);
                    break;
                case BACK:
                    bw.write(tail == head ? -1 : queue[tail - 1]);
                    break;
            }

        }

        bw.close();

    }

    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

}

class BufferedWriter {

    java.io.BufferedWriter bw;
    char[] buffer;
    char[] temp;
    int pointer;
    final int MAX_SIZE = 1024;

    BufferedWriter() {
        bw = new java.io.BufferedWriter(new java.io.OutputStreamWriter(System.out));
        buffer = new char[MAX_SIZE];
        temp = new char[8];
    }

    void write(int num) throws Exception {
        if (pointer + 8 >= MAX_SIZE) flush();
        if (num < 0) {
            buffer[pointer++] = 45;
            buffer[pointer++] = 49;
        } else {
            int p = 0;
            do {
                temp[p++] = (char) ((num % 10) | 48);
            } while ((num /= 10) > 0);
            while (p > 0) buffer[pointer++] = temp[--p];
        }
        buffer[pointer++] = 10;
    }

    void flush() throws Exception {
        bw.write(buffer, 0, pointer);
        pointer = 0;
    }

    void close() throws Exception {
        flush();
        bw.close();
    }
}

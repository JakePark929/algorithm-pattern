package com.jake.algorithm.baekjoon.lv16_stack_queue_deque;

import java.util.Deque;
import java.util.LinkedList;

/**
 * [ 덱 2 ]
 *
 * 정수를 저장하는 덱을 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.
 *
 * 명령은 총 여덟 가지이다.
 *
 * 1 X: 정수 X를 덱의 앞에 넣는다. (1 ≤ X ≤ 100,000)
 * 2 X: 정수 X를 덱의 뒤에 넣는다. (1 ≤ X ≤ 100,000)
 * 3: 덱에 정수가 있다면 맨 앞의 정수를 빼고 출력한다. 없다면 -1을 대신 출력한다.
 * 4: 덱에 정수가 있다면 맨 뒤의 정수를 빼고 출력한다. 없다면 -1을 대신 출력한다.
 * 5: 덱에 들어있는 정수의 개수를 출력한다.
 * 6: 덱이 비어있으면 1, 아니면 0을 출력한다.
 * 7: 덱에 정수가 있다면 맨 앞의 정수를 출력한다. 없다면 -1을 대신 출력한다.
 * 8: 덱에 정수가 있다면 맨 뒤의 정수를 출력한다. 없다면 -1을 대신 출력한다.
 *
 * 입력
 * 첫째 줄에 명령의 수 N이 주어진다. (1 ≤ N ≤ 1,000,000)
 *
 * 둘째 줄부터 N개 줄에 명령이 하나씩 주어진다.
 *
 * 출력을 요구하는 명령은 하나 이상 주어진다.
 *
 * 출력
 * 출력을 요구하는 명령이 주어질 때마다 명령의 결과를 한 줄에 하나씩 출력한다.
 *
 * 작성일 : 2023.08.24
 */
class B28279_Deque_2 {
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    public static void main(String[] args) throws Exception {
        Deque<Integer> d = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        int n = read();
        while (n-- > 0) {
            int x = 0;
            int order = read();
            if (order == 1) x = read();
            if (order == 2) x = read();

            switch (order) {
                case 1 : d.offerFirst(x); break;
                case 2 : d.offerLast(x); break;
                case 3 : sb.append(d.isEmpty() ? -1 : d.pollFirst()).append('\n'); break;
                case 4 : sb.append(d.isEmpty() ? -1 : d.pollLast()).append('\n'); break;
                case 5 : sb.append(d.size()).append('\n'); break;
                case 6 : sb.append(d.isEmpty() ? 1 : 0).append('\n'); break;
                case 7 : sb.append(d.isEmpty() ? -1 : d.peekFirst()).append('\n'); break;
                case 8 : sb.append(d.isEmpty() ? -1 : d.peekLast()).append('\n'); break;
            }
        }

        System.out.println(sb);
    }

    // 다른 사람의 풀이 1
    public void main1(String[] args) throws Exception{
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        final int MAX = 2_000_000, START = 1_000_000;

        int n = in.nextInt(), order;
        int[] stack = new int[MAX];
        int lptr = START, rptr = START - 1;

        while (n-- > 0){
            order = in.nextInt();

            switch (order){
                case 1 :
                    stack[--lptr] = in.nextInt();
                    break;

                case 2 :
                    stack[++rptr] = in.nextInt();
                    break;

                case 3 :
                    if (lptr <= rptr) sb.append(stack[lptr++]).append('\n');
                    else sb.append(-1).append('\n');
                    break;

                case 4 :
                    if (lptr <= rptr) sb.append(stack[rptr--]).append('\n');
                    else sb.append(-1).append('\n');
                    break;

                case 5 :
                    sb.append(rptr - lptr + 1).append('\n');
                    break;

                case 6 :
                    if (lptr <= rptr) sb.append(0).append('\n');
                    else sb.append(1).append('\n');
                    break;

                case 7 :
                    if (lptr <= rptr) sb.append(stack[lptr]).append('\n');
                    else sb.append(-1).append('\n');
                    break;

                case 8 :
                    if (lptr <= rptr) sb.append(stack[rptr]).append('\n');
                    else sb.append(-1).append('\n');
                    break;
            }
        }

        System.out.print(sb);
    }

    class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

        char nextChar() throws Exception {
            char ch = ' ';
            byte c;
            while ((c = read()) <= 32);
            do ch = (char)c;
            while (isAlphabet(c = read()));
            return ch;
        }

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32); //{ if (size < 0) return -1; }
            if (c == 45) { c = read(); isMinus = true; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        long nextLong() throws Exception {
            long n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32);
            if (c == 45) { c = read(); isMinus = true; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        double nextDouble() throws Exception {
            double n = 0, div = 1;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32);
            if (c == 45) { c = read(); isMinus = true; }
            else if (c == 46) { c = read(); }
            do n = (n * 10) + (c & 15);
            while (isNumber(c = read()));
            if (c == 46) { while (isNumber(c = read())){ n += (c - 48) / (div *= 10); }}
            return isMinus ? -n : n;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
        }

        boolean isAlphabet(byte c){
            return 96 < c && c < 123;
        }

        byte read() throws Exception {
            if (index == size) {
                size = System.in.read(buffer, index = 0, SIZE);
                if (size < 0) buffer[0] = -1;
            }
            return buffer[index++];
        }
    }
}



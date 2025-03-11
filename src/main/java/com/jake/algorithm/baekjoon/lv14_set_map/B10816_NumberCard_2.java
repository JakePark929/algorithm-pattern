package com.jake.algorithm.baekjoon.lv14_set_map;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * [ 숫자 카드 2 ]
 *
 * 숫자 카드는 정수 하나가 적혀져 있는 카드이다.
 * 상근이는 숫자 카드 N개를 가지고 있다.
 * 정수 M개가 주어졌을 때, 이 수가 적혀있는 숫자 카드를 상근이가 몇 개 가지고 있는지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 상근이가 가지고 있는 숫자 카드의 개수 N(1 ≤ N ≤ 500,000)이 주어진다.
 * 둘째 줄에는 숫자 카드에 적혀있는 정수가 주어진다.
 * 숫자 카드에 적혀있는 수는 -10,000,000보다 크거나 같고, 10,000,000보다 작거나 같다.
 *
 * 셋째 줄에는 M(1 ≤ M ≤ 500,000)이 주어진다.
 * 넷째 줄에는 상근이가 몇 개 가지고 있는 숫자 카드인지 구해야 할 M개의 정수가 주어지며,
 * 이 수는 공백으로 구분되어져 있다. 이 수도 -10,000,000보다 크거나 같고, 10,000,000보다 작거나 같다.
 *
 * 출력
 * 첫째 줄에 입력으로 주어진 M개의 수에 대해서,
 * 각 수가 적힌 숫자 카드를 상근이가 몇 개 가지고 있는지를 공백으로 구분해 출력한다.
 *
 * 작성일 : 2023.08.07
 */
class B10816_NumberCard_2 {
    public static void main(String[] args) throws Exception {
        int n = read();
        HashMap<Integer, Integer> myCard = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int card = read();
            if(!myCard.containsKey(card)) myCard.put(card, 1);
            else myCard.put(card, myCard.get(card) + 1);
        }

        int m = read();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int check = read();
            if (myCard.containsKey(check)) sb.append(myCard.get(check));
            else sb.append(0);
            sb.append(" ");
        }

        System.out.println(sb.toString().trim());
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;
        if (isNegative) n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return isNegative ? ~n + 1 : n;
    }

    // 다른 사람의 풀이 1
    static class Reader {
        private final int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();

            while(c <= ' ')
                c = read();

            boolean neg = (c == '-');
            if(neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if(neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if(bytesRead == -1)
                buffer[0] = -1;
        }
        private byte read() throws IOException {
            if(bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
    }
    public static void main1(String[] args) throws IOException {
        Reader r = new Reader();
        int N = r.nextInt();
        int[] cards = new int[20000001];
        while(N-- > 0) {
            int number = r.nextInt();
            cards[number+10000000]++;
        }
        int M = r.nextInt();
        StringBuilder sb = new StringBuilder();

        while(M-- > 0) {
            sb.append(cards[r.nextInt()+10000000]).append(" ");

        }

        System.out.println(sb);
    }
}

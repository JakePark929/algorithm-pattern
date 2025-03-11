package com.jake.algorithm.baekjoon.lv14_set_map;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * [ 숫자 카드 ]
 * <p>
 * 숫자 카드는 정수 하나가 적혀져 있는 카드이다.
 * 상근이는 숫자 카드 N개를 가지고 있다.
 * 정수 M개가 주어졌을 때,
 * 이 수가 적혀있는 숫자 카드를 상근이가 가지고 있는지 아닌지를 구하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 상근이가 가지고 있는 숫자 카드의 개수 N(1 ≤ N ≤ 500,000)이 주어진다.
 * 둘째 줄에는 숫자 카드에 적혀있는 정수가 주어진다.
 * 숫자 카드에 적혀있는 수는 -10,000,000보다 크거나 같고,
 * 10,000,000보다 작거나 같다.
 * 두 숫자 카드에 같은 수가 적혀있는 경우는 없다.
 * <p>
 * 셋째 줄에는 M(1 ≤ M ≤ 500,000)이 주어진다.
 * 넷째 줄에는 상근이가 가지고 있는 숫자 카드인지 아닌지를 구해야 할 M개의 정수가 주어지며,
 * 이 수는 공백으로 구분되어져 있다. 이 수도 -10,000,000보다 크거나 같고, 10,000,000보다 작거나 같다
 * <p>
 * 출력
 * 첫째 줄에 입력으로 주어진 M개의 수에 대해서,
 * 각 수가 적힌 숫자 카드를 상근이가 가지고 있으면 1을, 아니면 0을 공백으로 구분해 출력한다.
 * <p>
 * 작성일 : 2023.08.07
 */
class B10815_NumberCard {
    public static void main(String[] args) throws Exception {
        int n = read();
        HashMap<Integer, Integer> hs = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int e = read();
            hs.put(e, e);
        }

        StringBuilder sb = new StringBuilder();
        int m = read();
        for (int i = 0; i < m; i++) {
            int e = read();
            if (hs.containsKey(e)) sb.append(1);
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
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

    }

    public static void main1(String[] args) throws IOException {
        Reader r = new Reader();
        int n = r.nextInt();
        boolean[] check = new boolean[20_000_001];
        for (int i = 0; i < n; i++) {
            check[r.nextInt() + 10_000_000] = true;
        }
        int m = r.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            if (check[r.nextInt() + 10_000_000]) {
                sb.append('1').append(' ');
            } else {
                sb.append('0').append(' ');
            }
        }
        System.out.print(sb);
    }

    // 다른 사람의 풀이 2 - 이분탐색 활용
    public static void main2(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 카드의 개수
        int[] cards = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards); // 이분탐색할 배열은 정렬되어 있어야 함.
        int M = Integer.parseInt(br.readLine()); // 구별할 수의 개수

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int temp = Integer.parseInt(st.nextToken());
            sb.append(binarySearch(cards, N, temp) + " ");
        }

        bw.write(sb.toString() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int binarySearch(int[] cards, int N, int search) {
        int first = 0;
        int last = N - 1;
        int mid = 0;

        while (first <= last) {
            mid = (first + last) / 2; // 중간 인덱스

            if (cards[mid] == search) { // 중간값과 찾으려는 수가 같은 경우
                return 1;
            }

            if (cards[mid] < search) { // 중간값이 찾으려는 수보다 작으면, 그 이하로는 볼 필요 없음.
                first = mid + 1;
            } else { // 중간값이 찾으려는 수보다 크면, 그 이상으로는 볼 필요 없음.
                last = mid - 1;
            }
        }

        return 0;
    }

    // 다른 사람의 풀이 3
    static int N, M;
    static int[] arr = new int[20000001];

    static int read2() throws Exception {
        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;
        if (isNegative) n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return isNegative ? ~n + 1 : n;
    }

    public static void main3(String... args) throws Exception {
        StringBuilder sb = new StringBuilder();
        N = read2();
        for(int i = 0; i < N; i++) {
            arr[read() + 10000000]++;
        }
        M = read2();
        for(int i = 0; i < M; i++) {
            if(arr[read2() + 10000000] > 0) sb.append("1").append(" ");
            else sb.append("0").append(" ");
        }
        System.out.println(sb);
    }
}

package com.jake.argorithm.baekjoon.lv03_loop;

import java.io.*;
import java.util.StringTokenizer;

/**
 * [ 빠른 A + B ]
 * <p>
 * 본격적으로 for문 문제를 풀기 전에 주의해야 할 점이 있다.
 * 입출력 방식이 느리면 여러 줄을 입력받거나 출력할 때 시간초과가 날 수 있다는 점이다.
 * <p>
 * C++을 사용하고 있고 cin/cout을 사용하고자 한다면,
 * cin.tie(NULL)과 sync_with_stdio(false)를 둘 다 적용해 주고,
 * endl 대신 개행문자(\n)를 쓰자.
 * <p>
 * 단, 이렇게 하면 더 이상 scanf/printf/puts/getchar/putchar 등 C의 입출력 방식을 사용하면 안 된다.
 * <p>
 * Java를 사용하고 있다면,
 * Scanner와 System.out.println 대신 BufferedReader와 BufferedWriter를 사용할 수 있다.
 * BufferedWriter.flush는 맨 마지막에 한 번만 하면 된다.
 * <p>
 * Python을 사용하고 있다면,
 * input 대신 sys.stdin.readline을 사용할 수 있다.
 * 단, 이때는 맨 끝의 개행문자까지 같이 입력받기 때문에 문자열을 저장하고 싶을 경우
 * .rstrip()을 추가로 해 주는 것이 좋다.
 * <p>
 * 또한 입력과 출력 스트림은 별개이므로,
 * 테스트케이스를 전부 입력받아서 저장한 뒤 전부 출력할 필요는 없다.
 * 테스트케이스를 하나 받은 뒤 하나 출력해도 된다.
 * <p>
 * 자세한 설명 및 다른 언어의 경우는 이 글에 설명되어 있다.
 * <p>
 * 이 블로그 글에서 BOJ의 기타 여러 가지 팁을 볼 수 있다.
 * <p>
 * 입력
 * 첫 줄에 테스트케이스의 개수 T가 주어진다.
 * T는 최대 1,000,000이다.
 * 다음 T줄에는 각각 두 정수 A와 B가 주어진다. A와 B는 1 이상, 1,000 이하이다.
 * <p>
 * 출력
 * 각 테스트케이스마다 A+B를 한 줄에 하나씩 순서대로 출력한다.
 * <p>
 * 작성일 : 2023.07.17
 */
class P15552_FastAPlusB {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < testCase; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            sb.append(Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()));
            sb.append('\n');
        }

        System.out.println(sb);
    }

    // 다른 사람의 풀이 1 - BufferedWriter 사용
    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            bw.write((Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken())) + "\n");
        }
        br.close();

        bw.flush();
        bw.close();
    }

    // 다른 사람의 풀이 2 - StringTokenizer X, StringBuffer 사용
    public static void main2(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            int target = str.indexOf(" ");
            int result = Integer.parseInt(str.substring(0, target)) + Integer.parseInt(str.substring(target + 1));
            sb.append(result + "\n");
        }

        br.close();
        System.out.print(sb);
    }

    // 다른 사람의 풀이 3 - 입출력 직접구현, 200 ms
    public static void main3(String[] args) throws IOException {
        P15552_FastAPlusB.MaroReader mr = new P15552_FastAPlusB.MaroReader(65536, true);
        P15552_FastAPlusB.MaroWriter mw = new P15552_FastAPlusB.MaroWriter(65536);
        int n = mr.nextInt();

        for (int i = 0; i < n; i++) {
            mw.write(mr.nextInt() + mr.nextInt());
            mw.newLine();
        }
        mw.flush();
    }

    // Main 하단에 고정.
    public static class MaroReader {
        private final static int MAX_STR = 256;
        private final InputStream in = System.in;
        private final byte[] b;
        private final boolean isNatural;
        private final int sz;
        private int s, idx, rCnt;

        public MaroReader(int sz, boolean isNatural) {
            this.b = new byte[this.sz = sz];
            this.isNatural = isNatural;
        }

        private void refill() throws IOException {
            rCnt = in.read(b, idx = 0, sz);
            if (rCnt < 0) b[0] = -1;
        }

        private byte read() throws IOException {
            if (idx >= rCnt) refill();
            return b[idx++];
        }

        public int nextInt() throws IOException {
            int a;
            byte b = read();
            if (isNatural) {
                a = b - '0';
                while ((b = read()) >= '0' && b <= '9') a = a * 10 + b - '0';
                return a;
            } else {
                boolean isNeg = b == '-';
                if (isNeg) b = read();
                a = b - '0';
                while ((b = read()) >= '0' && b <= '9') a = a * 10 + b - '0';
                return isNeg ? -a : a;
            }
        }

        public byte[] nextString() throws IOException {
            byte[] str = new byte[MAX_STR];
            s = 0;
            byte b = read();
            if (b == -1) return null;
            str[s++] = b;
            while ((b = read()) > ' ' && b < 0x7F) str[s++] = b;
            return str;
        }

        public int getS() {
            return s;
        }
    }

    public static class MaroWriter {
        private final OutputStream out = System.out;
        private final byte[] b;
        private final int sz;
        private int bufferIdx;

        MaroWriter(int sz) {
            this.b = new byte[this.sz = sz];
        }

        public void write(byte b) throws IOException {
            if (bufferIdx == sz) flushBuffer();
            this.b[bufferIdx++] = b;
        }

        public void write(int i) throws IOException {
            byte[] tmp = new byte[11];
            int idx = 0;
            boolean isNeg = i < 0;
            if (i == 0) tmp[idx++] = '0';
            else while (i != 0) {
                int t = i % 10;
                tmp[idx++] = (byte) ('0' + (t < 0 ? -t : t));
                i /= 10;
            }
            if (isNeg) tmp[idx++] = '-';
            for (int j = idx - 1; j >= 0; j--) write(tmp[j]);
        }

        public void write(long l) throws IOException {
            byte[] tmp = new byte[20];
            int idx = 0;
            boolean isNeg = l < 0;
            if (l == 0) tmp[idx++] = '0';
            else while (l != 0) {
                long t = l % 10;
                tmp[idx++] = (byte) ('0' + (t < 0 ? -t : t));
                l /= 10;
            }
            if (isNeg) tmp[idx++] = '-';
            for (int j = idx - 1; j >= 0; j--) write(tmp[j]);
        }

        public void write(String s) throws IOException {
            byte[] str = s.getBytes();
            write(str, str.length);
        }

        public void write(byte[] str, int length) throws IOException {
            for (int i = 0; i < length; i++) write(str[i]);
        }

        public void flush() throws IOException {
            if (bufferIdx > 0) flushBuffer();
            out.flush();
        }

        private void flushBuffer() throws IOException {
            out.write(b, 0, bufferIdx);
            bufferIdx = 0;
        }

        public void newLine() throws IOException {
            write((byte) '\n');
        }

        public void space() throws IOException {
            write((byte) ' ');
        }
    }
}

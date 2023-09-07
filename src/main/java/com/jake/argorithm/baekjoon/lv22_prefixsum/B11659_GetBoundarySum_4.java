package com.jake.argorithm.baekjoon.lv22_prefixsum;

/**
 * [ 구간 합 구하기 4 ]
 *
 * 수 N개가 주어졌을 때,
 * i번째 수부터 j번째 수까지 합을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 수의 개수 N과 합을 구해야 하는 횟수 M이 주어진다.
 * 둘째 줄에는 N개의 수가 주어진다.
 * 수는 1,000보다 작거나 같은 자연수이다.
 * 셋째 줄부터 M개의 줄에는 합을 구해야 하는 구간 i와 j가 주어진다.
 *
 * 출력
 * 총 M개의 줄에 입력으로 주어진 i번째 수부터 j번째 수까지 합을 출력한다.
 */
class B11659_GetBoundarySum_4 {
    public static void main(String[] args) throws Exception {
        int n = read();
        int m = read();

        int[] dp = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int num = read();

            if (i == 0) {
                dp[i + 1] = num;
            } else {
                dp[i + 1] = dp[i] + num;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
            int prefix = read();
            int suffix = read();

            sb.append(dp[suffix] - dp[prefix - 1]).append('\n');
        }

        System.out.println(sb);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    // 다른 사람의 풀이 1
//    class Main {
//        public static void main(String[] args) throws Exception {
//            Reader in = new Reader();
//            StringBuilder sb = new StringBuilder();
//
//            int n = in.nextInt();
//            int m = in.nextInt();
//            int[] array = new int[n + 1];
//
//            for(int i = 1; i <= n; i++) {
//                array[i] = array[i - 1] + in.nextInt();
//            }
//
//            for(int i = 0; i < m; i++) {
//                int a = in.nextInt();
//                int b = in.nextInt();
//                sb.append(array[b] - array[a - 1]).append('\n');
//            }
//
//            System.out.print(sb);
//        }
//    }
//
//    class Reader {
//        final int SIZE = 1 << 13;
//        byte[] buffer = new byte[SIZE];
//        int index, size;
//
//        char nextChar() throws Exception {
//            byte c;
//            while ((c = read()) <= 32);
//            return (char)c;
//        }
//
//        int nextInt() throws Exception {
//            int n = 0;
//            byte c;
//            boolean isMinus = false;
//            while ((c = read()) <= 32); //{ if (size < 0) return -1; }
//            if (c == 45) { c = read(); isMinus = true; }
//            do n = (n << 3) + (n << 1) + (c & 15);
//            while (isNumber(c = read()));
//            return isMinus ? ~n + 1 : n;
//        }
//
//        long nextLong() throws Exception {
//            long n = 0;
//            byte c;
//            boolean isMinus = false;
//            while ((c = read()) <= 32);
//            if (c == 45) { c = read(); isMinus = true; }
//            do n = (n << 3) + (n << 1) + (c & 15);
//            while (isNumber(c = read()));
//            return isMinus ? ~n + 1 : n;
//        }
//
//        double nextDouble() throws Exception {
//            double n = 0, div = 1;
//            byte c;
//            boolean isMinus = false;
//            while ((c = read()) <= 32);
//            if (c == 45) { c = read(); isMinus = true; }
//            else if (c == 46) { c = read(); }
//            do n = (n * 10) + (c & 15);
//            while (isNumber(c = read()));
//            if (c == 46) { while (isNumber(c = read())){ n += (c - 48) / (div *= 10); }}
//            return isMinus ? -n : n;
//        }
//
//        boolean isNumber(byte c) {
//            return 47 < c && c < 58;
//        }
//
//        byte read() throws Exception {
//            if (index == size) {
//                size = System.in.read(buffer, index = 0, SIZE);
//                if (size < 0) buffer[0] = -1;
//            }
//            return buffer[index++];
//        }
//    }
}

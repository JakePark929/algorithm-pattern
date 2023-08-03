package com.jake.argorithm.baekjoon.lv13_sort;

import java.io.*;

/**
 * [ 수 정렬하기 3 ]
 *
 * N개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 수의 개수 N(1 ≤ N ≤ 10,000,000)이 주어진다.
 * 둘째 줄부터 N개의 줄에는 수가 주어진다.
 * 이 수는 10,000보다 작거나 같은 자연수이다.
 *
 * 출력
 * 첫째 줄부터 N개의 줄에 오름차순으로 정렬한 결과를 한 줄에 하나씩 출력한다.
 *
 * 작성일 : 2023.08.03
 */
class B10989_SortNumbers_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] nums = new int[n];
        for(int i = 0; i < nums.length; i++) nums[i] = Integer.parseInt(br.readLine());

        int[] result = countingSort(nums, 10000);

        StringBuilder sb = new StringBuilder();
        for (int i : result) sb.append(i).append('\n');

        System.out.println(sb.toString().trim());
    }

    private static int[] countingSort(int[] arr, int range) {
        int[] counting = new int[range + 1];
        int[] result = new int[arr.length];

        for (int i : arr) counting[i]++;

        for (int i = 1; i < counting.length; i++)
            counting[i] += counting[i - 1];

        for (int i = arr.length - 1; i >= 0; i--) {
            int val = arr[i];
            counting[val]--;
            result[counting[val]] = val;
        }

        return result;
    }

    // 다른 사람의 풀이 1
//    public static void main1 (String[] args) throws IOException {
//        Main.MaroReader mr = new Main.MaroReader(4096);
//        StringBuilder sb = new StringBuilder();
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 32768);
//        int N = mr.nextInt(true);
//        int[] arr = new int[10000];
//        for(int i=0; i<N; i++)
//            arr[mr.nextInt(true)-1]++;
//        for(int i=0; i<10000; i++)
//            while(arr[i]>0) {
//                arr[i]--;
//                sb.append(i+1).append("\n");
//            }
//        bw.write(sb.toString());
//        bw.flush();
//    }
//    static class MaroReader {
//        private final InputStream in;
//        private final byte[] buffer;
//        private final int bufferSize;
//        private int bufferIdx, readCount;
//        public MaroReader() {
//            this.bufferSize = 8192;
//            this.buffer = new byte[bufferSize];
//            this.in = System.in;
//        }
//        public MaroReader(int BufferSize) {
//            this.bufferSize = BufferSize;
//            this.buffer = new byte[BufferSize];
//            this.in = System.in;
//        }
//        private void fill() throws IOException {
//            readCount = in.read(buffer, bufferIdx =0, bufferSize);
//            if(readCount == -1) buffer[0] = -1;
//        }
//        private byte read() throws IOException {
//            if(bufferIdx == readCount) fill();
//            return buffer[bufferIdx++];
//        }
//        public int nextInt(boolean isNatural) throws IOException {
//            int a;
//            byte c = read();
//            while(c <= ' ') c = read();
//
//            if(isNatural) {
//                a = c-'0';
//                while((c=read())>='0' && c<='9')
//                    a = a*10+c-'0';
//                return a;
//            } else {
//                boolean isNeg = c == '-';
//                if(isNeg) c = read();
//                a = c-'0';
//                while((c = read()) >= '0' && c <= '9')
//                    a = a*10 + c-'0';
//                return isNeg?-a:a;
//            }
//        }
//    }
}

package com.jake.argorithm.baekjoon.lv21_dynamicprogramming;

/**
 * [ 가장 긴 바이토닉 수열 ]
 *
 * 수열 S가 어떤 수 Sk를 기준으로
 *
 * S1 < S2 < ... Sk-1 < Sk > Sk+1 > ... SN-1 > SN
 *
 * 을 만족한다면,그 수열을 바이토닉 수열이라고 한다.
 *
 * 예를 들어, {10, 20, 30, 25, 20}과 {10, 20, 30, 40}, {50, 40, 25, 10} 은 바이토닉 수열이지만,
 * {1, 2, 3, 2, 1, 2, 3, 2, 1}과 {10, 20, 30, 40, 20, 30} 은 바이토닉 수열이 아니다.
 *
 * 수열 A가 주어졌을 때, 그 수열의 부분 수열 중
 * 바이토닉 수열이면서 가장 긴 수열의 길이를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 수열 A의 크기 N이 주어지고,
 * 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ N ≤ 1,000, 1 ≤ Ai ≤ 1,000)
 *
 * 출력
 * 첫째 줄에 수열 A의 부분 수열 중에서 가장 긴 바이토닉 수열의 길이를 출력한다.
 *
 * 작성일 : 2023.09.07
 */
class B11054_LongestBitonicSubsequence {
    private static int[] seq;
    private static Integer[] idp;
    private static Integer[] ddp;

    public static void main(String[] args) throws Exception {
        int n = read();

        seq = new int[n];
        idp = new Integer[n];
        ddp = new Integer[n];

        for (int i = 0; i < n; i++) {
            seq[i] = read();
        }

        for (int i = 0; i < n; i++) {
            lis(i);
            lds(i);
        }

        int max = -1;
        for (int i = 0; i < n; i++) {
            max = Math.max(idp[i] + ddp[i], max);
        }

        System.out.println(max - 1);
    }

    private static int lis(int n) {
        if (idp[n] == null) {
            idp[n] = 1;

            for (int i = n - 1; i >= 0; i--) {
                if (seq[i] < seq[n]) {
                    idp[n] = Math.max(idp[n], lis(i) + 1);
                }
            }
        }

        return idp[n];
    }

    private static int lds(int n) {
        if (ddp[n] == null) {
            ddp[n] = 1;

            for (int i = n + 1; i < ddp.length; i++) {
                if (seq[i] < seq[n]) {
                    ddp[n] = Math.max(ddp[n], lds(i) + 1);
                }
            }
        }

        return ddp[n];
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws Exception {
        int N = read();
        int[] arr = new int[N], lis = new int[N], tmp = new int[N];

        int k, l = 0;
        for (int i = 0; i < N; i++) {

            arr[i] = read();

            if ((k = binarySearch(lis, 0, l - 1, arr[i])) == l) l++;
            lis[k] = arr[i];

            tmp[i] = l;

        }

        int max = l = 0;
        for (int i = N - 1; i >= 0; i--) {

            if ((k = binarySearch(lis, 0, l - 1, arr[i])) == l) l++;
            lis[k] = arr[i];

            if (max < l + tmp[i]) max = l + tmp[i];

        }

        System.out.print(max - 1);

    }

    private static int binarySearch(int[] a, int l, int r, int v) {
        int m;

        while (l <= r)
            if (a[m = l + r >> 1] < v) l = m + 1;
            else r = m - 1;

        return l;

    }

    private static int read1() throws Exception {

        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;

    }
}

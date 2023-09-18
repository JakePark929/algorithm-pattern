package com.jake.argorithm.baekjoon.lv24_devide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 행렬 제곱 ]
 *
 * 크기가 N*N인 행렬 A가 주어진다.
 * 이때, A의 B 제곱을 구하는 프로그램을 작성하시오.
 * 수가 매우 커질 수 있으니, A^B의 각 원소를 1,000으로 나눈 나머지를 출력한다.
 *
 * 입력
 * 첫째 줄에 행렬의 크기 N과 B가 주어진다. (2 ≤ N ≤  5, 1 ≤ B ≤ 100,000,000,000)
 * 둘째 줄부터 N개의 줄에 행렬의 각 원소가 주어진다.
 * 행렬의 각 원소는 1,000보다 작거나 같은 자연수 또는 0이다.
 *
 * 출력
 * 첫째 줄부터 N개의 줄에 걸쳐 행렬 A를 B 제곱한 결과를 출력한다.
 *
 * 작성일 : 2023.09.18
 */
class B10830_MatrixPow {
    private final static int MOD = 1000;
    public static int n;
    public static int[][] matrix;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken()) % MOD;
            }
        }

        int[][] result = pow(matrix, b);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(result[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    public static int[][] pow(int[][] a, long exp) {
        if (exp == 1L) {
            return a;
        }

        int[][] ret = pow(a, exp / 2);

        ret = multiply(ret, ret);

        if (exp % 2 == 1) {
            ret = multiply(ret, matrix);
        }

        return ret;
    }

    private static int[][] multiply(int[][] o1, int[][] o2) {
        int[][] ret = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    ret[i][j] += o1[i][k] * o2[k][j];
                    ret[i][j] %= MOD;
                }
            }
        }

        return ret;
    }

    public static int[][] multiplyCashing(int[][] o1, int[][] o2) {
        int[][] ret = new int[N][N];

        int r;
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                // o1(ik) 원소를 고정시켜두고, 그에 대한 o2의 k열을 고정시켜 j행을 움직이면서 연산한다.
                r = o1[i][k];
                for (int j = 0; j < N; j++) {
                    ret[i][j] += r * o2[k][j];
                    ret[i][j] %= MOD;
                }
            }
        }
        return ret;
    }

    // 다른 사람의 풀이 1
//    static final int MOD = 1_000;
    static int N;
    static int[][] origin;

    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Long B = Long.parseLong(st.nextToken());

        initOrigin(br);

        int[][] result = pow(origin, B);

        System.out.print(matrixToString(result));
    }

    private static void initOrigin(BufferedReader br) throws IOException {
        origin = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                origin[i][j] = Integer.parseInt(st2.nextToken());
            }
        }
    }

    private static int[][] pow(int[][] A, Long exp) {
        if (exp == 1) {
            return A;
        }
        int[][] result = pow(A, exp / 2);
        result = matrixMultiply(result, result);

        if (exp % 2 == 1) {
            result = matrixMultiply(result, origin);
        }
        return result;
    }

    private static int[][] matrixMultiply(int[][] o1, int[][] o2) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    result[i][j] += (o1[i][k] * o2[k][j]) % MOD;
                }
            }
        }
        return result;
    }

    private static StringBuilder matrixToString(int[][] result) {
        StringBuilder sb = new StringBuilder();
        for (int[] ints : result) {
            for (int anInt : ints) {
                sb.append(anInt % MOD).append(" ");
            }
            sb.append(System.lineSeparator());
        }
        return sb;
    }

    // 다른 사람의 풀이 2
    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());

        int[][] origin = new int[n][n];
        int[][] result = new int[n][n];

        long b = Long.parseLong(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken());
            }

            result[i][i] = 1;
        }

        /*
         * origin 행렬은 이전 loop 에서 제곱값을 갖고있는 행렬이고,
         * result 는 지수가 홀 수 일 때, 이전 loop 에서 얻은 제곱 행렬인 origin 과
         * 현재 result 행렬을 곱해주는 방식으로 간다.
         *
         * 즉, 재귀 과정을 역순으로 거치면 된다.
         *
         * ex)
         * A^11 과정일 떄,
         *
         * B = 11 (B % 2 == 1) -> I * A^1 = A^1 (result)
         *                     -> A^1 * A^1 = A^2 (origin)
         *
         * B = 5  (B % 2 == 1) -> A^1 * A^2 = A^3 (result)
         *                     -> A^2 * A^2 = A^4 (origin)
         *
         * B = 2  (B % 2 == 0) -> A^4 * A^4 = A^8 (origin)
         *
         * B = 1  (B % 2 == 1) -> A^3 * A^8 = A^11 (result)
         *                     -> A^8 * A^8 = A^16 (origin)
         */
        while (b > 0) {
            // 지수가 홀수라면 origin 배열을 한 번 더 곱해준다
            if (b % 2 == 1) { // b % 2 == 1 을 (b & 1L) != 0L 로도 수정 가능
                result = multiply(result, origin);
            }
            origin = multiply2(origin, origin);

            b /= 2;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(result[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static int[][] multiply2(int[][] o1, int[][] o2) {
        int[][] ret = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    ret[i][j] += o1[i][k] * o2[k][j];
                    ret[i][j] %= MOD;
                }
            }
        }

        return ret;
    }
}

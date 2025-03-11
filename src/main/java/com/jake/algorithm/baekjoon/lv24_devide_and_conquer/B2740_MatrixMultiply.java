package com.jake.algorithm.baekjoon.lv24_devide_and_conquer;

/**
 * [ 행렬 곱셈 ]
 *
 * N * M 크기의 행렬 A와 M * K 크기의 행렬 B가 주어졌을 때,
 * 두 행렬을 곱하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 행렬 A의 크기 N 과 M이 주어진다.
 * 둘째 줄부터 N개의 줄에 행렬 A의 원소 M개가 순서대로 주어진다.
 * 그 다음 줄에는 행렬 B의 크기 M과 K가 주어진다.
 * 이어서 M개의 줄에 행렬 B의 원소 K개가 차례대로 주어진다.
 * N과 M, 그리고 K는 100보다 작거나 같고, 행렬의 원소는 절댓값이 100보다 작거나 같은 정수이다.
 *
 * 출력
 * 첫째 줄부터 N개의 줄에 행렬 A와 B를 곱한 행렬을 출력한다.
 * 행렬의 각 원소는 공백으로 구분한다.
 *
 * 작성일 : 2023.09.14
 */
class B2740_MatrixMultiply {
    public static void main(String[] args) throws Exception {
        int n = read();
        int m = read();

        int[][] a = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = read();
            }
        }

        m = read();
        int k = read();

        int[][] b = new int[m][k];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < k; j++) {
                b[i][j] = read();
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                int sum = 0;
                for (int l = 0; l < m; l++) {
                    sum += a[i][l] * b[l][j];
                }

                sb.append(sum).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    // 스트라젠 알고리즘 사용
//    public static void main(String[] args) throws Exception {
//        int n = read();
//        int m = read();
//
//        int[][] a = new int[128][128];
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                a[i][j] = read();
//            }
//        }
//
//        m = read();
//        int k = read();
//
//        int[][] b = new int[128][128];
//
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < k; j++) {
//                b[i][j] = read();
//            }
//        }
//
//        int big = Math.max(Math.max(n, k), m);
//
//        int size = 1;
//        while (size < big) {
//            size *= 2;
//        }
//
//        // 분할정복 메소드 호출
//        int[][] c = multiply(a, b, size);
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < k; j++) {
//                sb.append(c[i][j]).append(" ");
//            }
//            sb.append('\n');
//        }
//
//        System.out.println(sb);
//    }
//
//    private static int[][] multiply(int[][] a, int[][] b, int size) {
//        int[][] c = new int[size][size];
//
//        // size 가 1 로 가장 작게 쪼개질 경우 (0, 0) 원소 밖에 업스므로 해당 원소 곱을 반환
//        if (size == 1) {
//            c[0][0] = a[0][0] * b[0][0];
//
//            return c;
//        }
//
//        int resize = size / 2;
//
//        // a의 부분행렬
//        int[][] a11 = subArray(a, 0, 0, resize);
//        int[][] a12 = subArray(a, 0, resize, resize);
//        int[][] a21 = subArray(a, resize, 0, resize);
//        int[][] a22 = subArray(a, resize, resize, resize);
//
//        // b의 부분행렬
//        int[][] b11 = subArray(b, 0, 0, resize);
//        int[][] b12 = subArray(b, 0, resize, resize);
//        int[][] b21 = subArray(b, resize, 0, resize);
//        int[][] b22 = subArray(b, resize, resize, resize);
//
//        // m1 := (a11 + a22) * (b11 + b22)
//        int[][] m1 = multiply(add(a11, a22, resize), add(b11, b22, resize), resize);
//
//        // m2 := (a21 + a22) * b11
//        int[][] m2 = multiply(add(a21, a22, resize), b11, resize);
//
//        // m3 := a11 * (b12 - b22)
//        int[][] m3 = multiply(a11, sub(b12, b22, resize), resize);
//
//        // m4 := a22 * (b21 - b11)
//        int[][] m4 = multiply(a22, sub(b21, b11, resize), resize);
//
//        // m5 := (a11 + a12) * b22
//        int[][] m5 = multiply(add(a11, a12, resize), b22, resize);
//
//        // m6 := (a21 - a11) * (b11 + b12)
//        int[][] m6 = multiply(sub(a21, a11, resize), add(b11, b12, resize), resize);
//
//        // m7 := (a12 - a22) * (b21 - b22)
//        int[][] m7 = multiply(sub(a12, a22, resize), sub(b21, b22, resize), resize);
//
//
//        // c11 := m1 + m4 - m5 + m7
//        int[][] c11 = add(sub(add(m1, m4, resize), m5, resize), m7, resize);
//
//        // c12 := m3 + m5
//        int[][] c12 = add(m3, m5, resize);
//
//        // c21 := m2 + m4
//        int[][] c21 = add(m2, m4, resize);
//
//        // c22 := m1 - m2 + m3 + m6
//        int[][] c22 = add(add(sub(m1, m2, resize), m3, resize), m6, resize);
//
//        // 구해진 c 의 부분행렬들을 합치기
//        merge(c11, c, 0, 0, resize);
//        merge(c12, c, 0, resize, resize);
//        merge(c21, c, resize, 0, resize);
//        merge(c22, c, resize, resize, resize);
//
//        return c;
//    }
//
//    // 행렬 뺄셈
//    private static int[][] sub(int[][] a, int[][] b, int size) {
//        int[][] c = new int[size][size];
//
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                c[i][j] = a[i][j] - b[i][j];
//            }
//        }
//
//        return c;
//    }
//
//    // 행렬 덧셈
//    private static int[][] add(int[][] a, int[][] b, int size) {
//        int[][] c = new int[size][size];
//
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                c[i][j] = a[i][j] + b[i][j];
//            }
//        }
//
//        return c;
//    }
//
//    // 부분행렬 반환
//    private static int[][] subArray(int[][] src, int row, int col, int size) {
//        int[][] dest = new int[size][size];
//
//        for (int dest_i = 0, src_i = row; dest_i < size; dest_i++, src_i++) {
//            for (int dest_j = 0, src_j = col; dest_j < size; dest_j++, src_j++) {
//                dest[dest_i][dest_j] = src[src_i][src_j];
//            }
//        }
//
//        return dest;
//    }
//
//    private static void merge(int[][] src, int[][] dest, int row, int col, int size) {
//        for (int src_i = 0, dest_i = row; src_i < size; src_i++, dest_i++) {
//            for (int src_j = 0, dest_j = col; src_j < size; src_j++, dest_j++) {
//                dest[dest_i][dest_j] = src[src_i][src_j];
//            }
//        }
//    }

    // 개선된 스트라젠 알고리즘
//    private static final int threshold = 16; // 임계값
//
//    public static void main(String[] args) throws Exception {
//        int n = read();
//        int m = read();
//
//        int[][] a = new int[128][128];
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                a[i][j] = read();
//            }
//        }
//
//        m = read();
//        int k = read();
//
//        int[][] b = new int[128][128];
//
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < k; j++) {
//                b[i][j] = read();
//            }
//        }
//
//        int big = Math.max(Math.max(n, k), m);
//
//        int size = 1;
//        while (size < big) {
//            size *= 2;
//        }
//
//        // 분할정복 메소드 호출
//        int[][] c = multiply(a, b, size);
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < k; j++) {
//                sb.append(c[i][j]).append(" ");
//            }
//            sb.append('\n');
//        }
//
//        System.out.println(sb);
//    }
//
//    private static int[][] loopMultiply(int[][] a, int[][] b, int size) {
//        int[][] res = new int[size][size];
//
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                for (int k = 0; k < size; k++) {
//                    res[i][j] += a[i][k] * b[k][j];
//                }
//            }
//        }
//
//        return res;
//    }
//
//    private static int[][] multiply(int[][] a, int[][] b, int size) {
//        int[][] c = new int[size][size];
//
//        if (size <= threshold) {	// 임계값 이하가 되면 loop 로 곱셈을 하여 반환한다.
//            return c = loopMultiply(a, b, size);
//        }
//
//        int resize = size / 2;
//
//        // a의 부분행렬
//        int[][] a11 = subArray(a, 0, 0, resize);
//        int[][] a12 = subArray(a, 0, resize, resize);
//        int[][] a21 = subArray(a, resize, 0, resize);
//        int[][] a22 = subArray(a, resize, resize, resize);
//
//        // b의 부분행렬
//        int[][] b11 = subArray(b, 0, 0, resize);
//        int[][] b12 = subArray(b, 0, resize, resize);
//        int[][] b21 = subArray(b, resize, 0, resize);
//        int[][] b22 = subArray(b, resize, resize, resize);
//
//        // m1 := (a11 + a22) * (b11 + b22)
//        int[][] m1 = multiply(add(a11, a22, resize), add(b11, b22, resize), resize);
//
//        // m2 := (a21 + a22) * b11
//        int[][] m2 = multiply(add(a21, a22, resize), b11, resize);
//
//        // m3 := a11 * (b12 - b22)
//        int[][] m3 = multiply(a11, sub(b12, b22, resize), resize);
//
//        // m4 := a22 * (b21 - b11)
//        int[][] m4 = multiply(a22, sub(b21, b11, resize), resize);
//
//        // m5 := (a11 + a12) * b22
//        int[][] m5 = multiply(add(a11, a12, resize), b22, resize);
//
//        // m6 := (a21 - a11) * (b11 + b12)
//        int[][] m6 = multiply(sub(a21, a11, resize), add(b11, b12, resize), resize);
//
//        // m7 := (a12 - a22) * (b21 - b22)
//        int[][] m7 = multiply(sub(a12, a22, resize), sub(b21, b22, resize), resize);
//
//
//        // c11 := m1 + m4 - m5 + m7
//        int[][] c11 = add(sub(add(m1, m4, resize), m5, resize), m7, resize);
//
//        // c12 := m3 + m5
//        int[][] c12 = add(m3, m5, resize);
//
//        // c21 := m2 + m4
//        int[][] c21 = add(m2, m4, resize);
//
//        // c22 := m1 - m2 + m3 + m6
//        int[][] c22 = add(add(sub(m1, m2, resize), m3, resize), m6, resize);
//
//        // 구해진 c 의 부분행렬들을 합치기
//        merge(c11, c, 0, 0, resize);
//        merge(c12, c, 0, resize, resize);
//        merge(c21, c, resize, 0, resize);
//        merge(c22, c, resize, resize, resize);
//
//        return c;
//    }
//
//    // 행렬 뺄셈
//    private static int[][] sub(int[][] a, int[][] b, int size) {
//        int[][] c = new int[size][size];
//
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                c[i][j] = a[i][j] - b[i][j];
//            }
//        }
//
//        return c;
//    }
//
//    // 행렬 덧셈
//    private static int[][] add(int[][] a, int[][] b, int size) {
//        int[][] c = new int[size][size];
//
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                c[i][j] = a[i][j] + b[i][j];
//            }
//        }
//
//        return c;
//    }
//
//    // 부분행렬 반환
//    private static int[][] subArray(int[][] src, int row, int col, int size) {
//        int[][] dest = new int[size][size];
//
//        for (int dest_i = 0, src_i = row; dest_i < size; dest_i++, src_i++) {
//            for (int dest_j = 0, src_j = col; dest_j < size; dest_j++, src_j++) {
//                dest[dest_i][dest_j] = src[src_i][src_j];
//            }
//        }
//
//        return dest;
//    }
//
//    private static void merge(int[][] src, int[][] dest, int row, int col, int size) {
//        for (int src_i = 0, dest_i = row; src_i < size; src_i++, dest_i++) {
//            for (int src_j = 0, dest_j = col; src_j < size; src_j++, dest_j++) {
//                dest[dest_i][dest_j] = src[src_i][src_j];
//            }
//        }
//    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;
        if (isNegative) n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return isNegative ? ~n + 1 : n;
    }
}

package com.jake.algorithm.a01_lecture.fc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [ 먹을 것인가 먹힐 것인가 ]
 *
 * 문제
 * 심해에는 두 종류의 생명체 A와 B가 존재한다. A는 B를 먹는다.
 * A는 자기보다 크기가 작은 먹이만 먹을 수 있다.
 * 예를 들어, A의 크기가 {8, 1, 7, 3, 1}이고, B의 크기가 {3, 6, 1}인 경우에 A가 B를 먹을 수 있는 쌍의 개수는 7가지가 있다.
 * 8-3, 8-6, 8-1, 7-3, 7-6, 7-1, 3-1.
 *
 * 두 생명체 A와 B의 크기가 주어졌을 때, A의 크기가 B보다 큰 쌍이 몇 개나 있는지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 테스트 케이스의 개수 T가 주어진다.
 * 각 테스트 케이스의 첫째 줄에는 A의 수 N과 B의 수 M이 주어진다.
 * 둘째 줄에는 A의 크기가 모두 주어지며, 셋째 줄에는 B의 크기가 모두 주어진다.
 * 크기는 양의 정수이다. (1 ≤ N, M ≤ 20,000)
 *
 * 출력
 * 각 테스트 케이스마다, A가 B보다 큰 쌍의 개수를 출력한다.
 *
 */
public class P3_B7795_EatOrEaten {
    static FastReader scan = new FastReader();

    static int N, M;
    static int[] A, B;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();

        A = new int[N + 1];
        B = new int[M + 1];

        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }

        for (int i = 1; i <= M; i++) {
            B[i] = scan.nextInt();
        }
    }

    // B[L..R] 에서 X보다 작은 수 중 가장 오른쪽 인덱스 반환
    // 없으면 L - 1 반환
    static int lower_bound(int[] arr, int L, int R, int X) {
        int result = L - 1;

        while (L <= R) {
            int mid = (L + R) / 2;

            if (arr[mid] < X) {
                result = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        return result;
    }

    static void process() {
        Arrays.sort(B, 1, M + 1);

        long answer = 0;
        for (int i = 1; i <= N; i++) {
            answer += lower_bound(B, 1, M, A[i]);
        }

        System.out.println(answer);
    }

    public static void main(String[] args) {

        int T = scan.nextInt();

        while (T-- > 0) {
            input();

            process();
        }
    }

    private static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

package com.jake.algorithm.a01_lecture.fc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [ 두 용액 ]
 * <p>
 * 문제
 * KOI 부설 과학연구소에서는 많은 종류의 산성 용액과 알칼리성 용액을 보유하고 있다.
 * 각 용액에는 그 용액의 특성을 나타내는 하나의 정수가 주어져있다.
 * 산성 용액의 특성값은 1부터 1,000,000,000까지의 양의 정수로 나타내고,
 * 알칼리성 용액의 특성값은 -1부터 -1,000,000,000까지의 음의 정수로 나타낸다.
 * <p>
 * 같은 양의 두 용액을 혼합한 용액의 특성값은 혼합에 사용된 각 용액의 특성값의 합으로 정의한다.
 * 이 연구소에서는 같은 양의 두 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들려고 한다.
 * <p>
 * 예를 들어, 주어진 용액들의 특성값이 [-2, 4, -99, -1, 98]인 경우에는
 * 특성값이 -99인 용액과 특성값이 98인 용액을 혼합하면 특성값이 -1인 용액을 만들 수 있고,
 * 이 용액이 특성값이 0에 가장 가까운 용액이다.
 * 참고로, 두 종류의 알칼리성 용액만으로나 혹은 두 종류의 산성 용액만으로 특성값이 0에 가장 가까운 혼합 용액을 만드는 경우도 존재할 수 있다.
 * <p>
 * 산성 용액과 알칼리성 용액의 특성값이 주어졌을 때,
 * 이 중 두 개의 서로 다른 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액을 찾는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에는 전체 용액의 수 N이 입력된다. N은 2 이상 100,000 이하이다.
 * 둘째 줄에는 용액의 특성값을 나타내는 N개의 정수가 빈칸을 사이에 두고 주어진다.
 * 이 수들은 모두 -1,000,000,000 이상 1,000,000,000 이하이다.
 * N개의 용액들의 특성값은 모두 다르고, 산성 용액만으로나 알칼리성 용액만으로 입력이 주어지는 경우도 있을 수 있다.
 * <p>
 * 출력
 * 첫째 줄에 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액의 특성값을 출력한다.
 * 출력해야 하는 두 용액은 특성값의 오름차순으로 출력한다.
 * 특성값이 0에 가장 가까운 용액을 만들어내는 경우가 두 개 이상일 경우에는 그 중 아무것이나 하나를 출력한다.
 * <p>
 * 작성일 : 2026.03.02
 */
public class P3_B2470_Two_Solution {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] A;

    static void input() {
        N = scan.nextInt();
        A = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
    }

    static int lower_bound(int[] A, int L, int R, int X) {
        // A[L..R] 에서 X 이상의 수 중 제일 왼쪽 인덱스를 return 하는 함수
        // 그런게 없다면 R + 1 을 return 한다.
        int candidate = R + 1;
        while (L <= R) {
            int mid = (L + R) / 2;

            if (A[mid] >= X) {
                candidate = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        return candidate;
    }

    static void process() {
        // A에 대해 이분 탐색을 할 예정이니, 정렬을 미리 해주자
        Arrays.sort(A, 1, N + 1);

        int bestSum = Integer.MAX_VALUE;
        int v1 = 0, v2 = 0;
        for (int left = 1; left <= N - 1; left++) {
            // A[left] 용액을 쓸 것이다. 고로 -A[left] 와 가장 가까운 용액을 자신의 오른쪽 구간에서 찾자.
            int candidate = lower_bound(A, left + 1, N, -A[left]);

            // A[candidate - 1] 와 A[candidate] 중 A[left]와 섞었을 때의 정보를 정답에 갱신시킨다.
            if (left + 1 <= candidate - 1 && candidate - 1 <= N && Math.abs(A[candidate - 1] + A[left]) < bestSum) {
                bestSum = Math.abs(A[left] + A[candidate - 1]);

                v1 = A[left];
                v2 = A[candidate - 1];
            }

            if (left + 1 <= candidate && candidate <= N && Math.abs(A[candidate] + A[left]) < bestSum) {
                bestSum = Math.abs(A[left] + A[candidate]);

                v1 = A[left];
                v2 = A[candidate];
            }
        }

        sb.append(v1).append(' ').append(v2);
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        input();

        process();
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

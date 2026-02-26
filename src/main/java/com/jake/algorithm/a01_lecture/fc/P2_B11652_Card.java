package com.jake.algorithm.a01_lecture.fc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [ 카드 ]
 *
 * 문제
 * 준규는 숫자 카드 N장을 가지고 있다. 숫자 카드에는 정수가 하나 적혀있는데, 적혀있는 수는 -262보다 크거나 같고, 262보다 작거나 같다.
 *
 * 준규가 가지고 있는 카드가 주어졌을 때, 가장 많이 가지고 있는 정수를 구하는 프로그램을 작성하시오. 
 * 만약, 가장 많이 가지고 있는 정수가 여러 가지라면, 작은 것을 출력한다.
 *
 * 입력
 * 첫째 줄에 준규가 가지고 있는 숫자 카드의 개수 N (1 ≤ N ≤ 100,000)이 주어진다. 
 * 둘째 줄부터 N개 줄에는 숫자 카드에 적혀있는 정수가 주어진다.
 *
 * 출력
 * 첫째 줄에 준규가 가장 많이 가지고 있는 정수를 출력한다.
 *
 * 작성일 : 2026.02.25
 */
public class P2_B11652_Card {
    static FastReader scan = new FastReader();

    static int N;
    static long[] a;

    static void input() {
        N = scan.nextInt();
        a = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            a[i] = scan.nextLong();
        }
    }

    static void process() {
        // Sort 정렬 하기
        Arrays.sort(a, 1, N + 1);

        // mode: 최빈 값, modeCnt: 최빈값 등장 횟수, currentCount: 현재 값(a[1])의 등장 횟수
        long mode = a[1];
        int modeCount = 1, currentCount = 1;

        // TODO : 2번 원소부터 차례대로 보면서, 같은 숫자가 이어서 나오고 있는 지, 새로운 숫자가 나왔는지 판단해 currentCount 갱신, 최빈값 갱신
        for (int i = 2; i <= N; i++) {
            if (a[i] == a[i - 1]) {
                currentCount++;
            } else {
                currentCount = 1;
            }

            if (modeCount < currentCount) {
                modeCount = currentCount;
                mode = a[i];
            }
        }
        
        // 정답 출력하기
        System.out.println(mode);
    }

    public static void main(String[] args) {
        input();

        process();
    }

    static class FastReader {
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}

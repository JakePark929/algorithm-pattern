package com.jake.algorithm.a01_lecture.fc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [ 수열 정렬 ]
 *
 * 문제
 * P[0], P[1], ...., P[N-1]은 0부터 N-1까지(포함)의 수를 한 번씩 포함하고 있는 수열이다.
 * 수열 P를 길이가 N인 배열 A에 적용하면 길이가 N인 배열 B가 된다. 적용하는 방법은 B[P[i]] = A[i]이다.
 *
 * 배열 A가 주어졌을 때, 수열 P를 적용한 결과가 비내림차순이 되는 수열을 찾는 프로그램을 작성하시오.
 * 비내림차순이란, 각각의 원소가 바로 앞에 있는 원소보다 크거나 같을 경우를 말한다.
 * 만약 그러한 수열이 여러개라면 사전순으로 앞서는 것을 출력한다.
 *
 * 입력
 * 첫째 줄에 배열 A의 크기 N이 주어진다.
 * 둘째 줄에는 배열 A의 원소가 0번부터 차례대로 주어진다.
 * N은 50보다 작거나 같은 자연수이고, 배열의 원소는 1,000보다 작거나 같은 자연수이다.
 *
 * 출력
 * 첫째 줄에 비내림차순으로 만드는 수열 P를 출력한다.
 *
 * 작성일 : 2026.02.24
 */
public class P2_B1015_SortSequence {
    static Element[] B;
    static int N;
    static int[] P;

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static void input() {
        N = scan.nextInt();
        B = new Element[N];
        P = new int[N];

        for (int i = 0; i < N; i++) {
            B[i] = new Element();
            B[i].num = scan.nextInt();
            B[i].idx = i;
        }
    }

    static class Element implements Comparable<Element> {
        public int num, idx;

        @Override
        public int compareTo(Element o) {
            return num - o.num;
        }
    }

    // 구현 순서
    // 1) num 의 비내림차순!
    // 2) num 이 같으면 idx 오름차순!

    // 1. 배열 정렬하기
    // 2. 배열 값 이용해 P배열 채우기
    // 3. P 배열 출력하기
    static void process() {
        Arrays.sort(B);

        for (int b_idx = 0; b_idx < N; b_idx++) {
            P[B[b_idx].idx] = b_idx;
        }

        for (int i = 0; i < N; i++) {
            sb.append(P[i]).append(' ');
        }

        System.out.println(sb.toString());
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
    }
}

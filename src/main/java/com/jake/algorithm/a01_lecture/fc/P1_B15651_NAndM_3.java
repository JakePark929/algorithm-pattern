package com.jake.algorithm.a01_lecture.fc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * [ N과 M (3) ]
 * <p>
 * 문제
 * 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 * <p>
 * 1부터 N까지 자연수 중에서 M개를 고른 수열
 * 같은 수를 여러 번 골라도 된다.
 * 입력
 * 첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 7)
 * <p>
 * 출력
 * 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
 * <p>
 * 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
 *
 * 작성일 : 2025.03.11
 */
// Recurrence Function (재귀 함수)
// 만약 M 개를 전부 고름 -> 조건에 맞는 탐색을 한 가지 성공한 것!
// 아직 M 개를 고르지 않음 -> k 번째부터 M번째 원소를 조건에 맞게 고르는 모든 방법을 시도한다.
public class P1_B15651_NAndM_3 {
    static int N, M;
    static int[] selected;

    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        selected = new int[M + 1];
    }
    
    static void recurrence(int k) {
        if (k == M + 1) { // 다 골랐다!
            // selected[1...M] 배열이 새롭게 탐색된 결과
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
        } else {
            // 1~N 까지를 k 번 원소로 한 번씩 정하고,
            // 매번 k+1 번부터 M번 원소로 재귀호출 해주기
            for (int candidate = 1; candidate <= N; candidate++) {
                selected[k] = candidate;
                // k + 1번 ~ M번을 모두 탐색하는 일을 해야하는 상황
                recurrence(k + 1);
                selected[k] = 0;
            }
        }
    }

    public static void main(String[] args) {
        input();

        // 1 번째 원소부터 M번째 원소를 조건에 맞는 모든 방법을 찾아줘
        recurrence(1);

        System.out.println(sb.toString());
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

    // 다른 사람의 풀이 1 (220ms)
//    static int N, M;
    static int[] arr = new int[8];
    static char[] buffer;
    static int idx = 0;

    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int set = 1;
        for (int i = 0; i < M; i++) {
            set *= N;
        }
        buffer = new char[M*2*set];

        backtrack(0);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(buffer);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void backtrack(int step) {
        if (step == M) {
            for (int i = 0; i < M; i++) {
                buffer[idx++] = (char)(arr[i] + '0');
                if( i<M-1){
                    buffer[idx++] = ' ';
                }
            }
            buffer[idx++] = '\n';
            return;
        }

        for (int i = 1; i <= N; i++) {
            arr[step] = i;
            backtrack(step+1);
        }
    }
}

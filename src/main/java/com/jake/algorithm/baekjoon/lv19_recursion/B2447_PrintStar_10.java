package com.jake.algorithm.baekjoon.lv19_recursion;

import java.io.*;
import java.util.Arrays;

/**
 * [ 별 찍기 - 10 ]
 *
 * 재귀적인 패턴으로 별을 찍어 보자.
 * N이 3의 거듭제곱(3, 9, 27, ...)이라고 할 때,
 * 크기 N의 패턴은 N×N 정사각형 모양이다.
 * 크기 3의 패턴은 가운데에 공백이 있고, 가운데를 제외한 모든 칸에 별이 하나씩 있는 패턴이다.
 *
 * ***
 * * *
 * ***
 *
 * N이 3보다 클 경우,
 * 크기 N의 패턴은 공백으로 채워진 가운데의 (N/3)×(N/3) 정사각형을
 * 크기 N/3의 패턴으로 둘러싼 형태이다.
 * 예를 들어 크기 27의 패턴은 예제 출력 1과 같다.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. N은 3의 거듭제곱이다.
 * 즉 어떤 정수 k에 대해 N=3k이며, 이때 1 ≤ k < 8이다.
 *
 * 출력
 * 첫째 줄부터 N번째 줄까지 별을 출력한다.
 *
 * 작성일 : 2023.08.28
 */
class B2447_PrintStar_10 {
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        arr = new char[n][n];

        star(0, 0, n, false);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(arr[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(Arrays.deepToString(arr));
        System.out.println(sb);
    }

    private static void star(int x, int y, int n, boolean blank) {
        // 공백칸 일 경우
        if (blank) {
            for (int i = x; i < x + n; i++) {
                for (int j = y; j < y + n; j++) {
                    arr[i][j] = ' ';
                }
            }
            return;
        }

        // 더 이상 쪼갤 수 없는 블록일 때
        if (n == 1) {
            arr[x][y] = '*';
            return;
        }

        /*
            n = 27 일 경우 한 블록의 사이즈는 9 이고,
            n = 9 일 경우 한 블록의 사이즈는 3 이듯
            해당 블록의 한 칸을 담을 변수를 의미 size

            count 는 별 출력 누적을 의미
         */
        int size = n / 3;
        int count = 0;
        for (int i = x; i < x + n; i += size) {
            for (int j = y; j < y + n; j += size) {
                count++;
                // 공백 칸일 경우
                star(i, j, size, count == 5);
            }
        }
    }

    // 다른 사람의 풀이 1
    static char[][] arr1;

    static void star1(int x, int y, int n, boolean blank){
        // 공백칸일 경우
        if (blank) {
            for (int i = x; i < x + n; i++){
                for (int j = y; j < y + n; j++){
                    arr1[i][j] = ' ';
                }
            }
            return;
        }
        // 더이상 쪼갤 수 없는 블록일 때
        if (n == 1){
            arr1[x][y] = '*';
            return;
        }
        /*
            n=27일 경우 한 블록의 사이즈는 9이고,
            n=9일 경우 한 블록의 사이즈는 3이듯
            해당 블록의 한 칸을 담을 변수를 의미 size

            count 는 별 출력 누적을 의미
         */

        int size = n / 3;
        int count = 0;
        for (int i = x; i < x + n; i += size){
            for (int j = y; j < y + n; j += size){
                count++;
                if (count == 5){ // 공백 칸일 경우
                    star(i, j, size, true);
                } else {
                    star(i, j, size, false);
                }
            }
        }
    }

    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        arr = new char[n][n];

        star1(0, 0, n, false);

        for (int i = 0; i < n; i++){
            bw.write(arr[i]);
            bw.write("\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    // 다른 사람의 풀이 3
    static StringBuilder[] sb;

    public static void main2(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        sb = new StringBuilder[N];
        String s = String.format("%" + N + "s" , ' ');
        for(int i = 0; i < N; i++) {
            sb[i] = new StringBuilder(s);
        }

        star2(0, 0, N);
        for (int i = 0; i < N; i++) {
            System.out.println(sb[i]);
        }
    }

    static void star2(int x, int y, int N) {

        // 더이상 쪼갤 수 없는 블록일 때
        if (N == 1) {
            sb[x].setCharAt(y, '*');
            return;
        }

		/*
		   N=27 일 경우 한 블록의 사이즈는 9이고,
		   N=9 일 경우 한 블록의 사이즈는 3이듯
		   해당 블록의 한 칸을 담을 변수를 의미 size

		   count 는 별 출력 누적을 의미
		 */

        int size = N / 3;
        int count = 0;
        for (int i = x; i < x + N; i += size) {
            for (int j = y; j < y + N; j += size) {
                count++;
                if (count != 5) {
                    star2(i, j, size);
                }
            }
        }
    }
}

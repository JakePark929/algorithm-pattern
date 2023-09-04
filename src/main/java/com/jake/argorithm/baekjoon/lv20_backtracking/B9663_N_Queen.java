package com.jake.argorithm.baekjoon.lv20_backtracking;

import java.io.*;

/**
 * [ N - Queen ]
 * <p>
 * N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.
 * N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 N이 주어진다. (1 ≤ N < 15)
 * <p>
 * 출력
 * 첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.
 * <p>
 * 작성일 : 2023.09.04
 */
class B9663_N_Queen {
    public static int[] arr;
    public static int n;
    public static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];

        nQueen(0);

        System.out.println(count);
    }

    public static void nQueen(int depth) {
        // 모든 원소를 다채운 상태면 count 증가 및 return
        if (depth == n) {
            count++;
            return;
        }

        for (int i = 0; i < n; i++) {
            arr[depth] = i;
            if (isPossible(depth)) {
                nQueen(depth + 1);
            }
        }
    }

    public static boolean isPossible(int col) {
        for (int i = 0; i < col; i++) {
            // 해당 열의 행과 i 열의 행이 일치할 경우 (같은 행에 존재할 경우)
            if (arr[col] == arr[i]) {
                return false;
            } else if (Math.abs(col - i) == Math.abs(arr[col] - arr[i])) {
                /*
                 * 대각선 상에 놓여 있는 경우
                 * (열의 차와 행의 차가 같을 경우가 대각선에 놓여있는 경우임)
                 */
                return false;
            }
        }
        return true;
    }

    // 다른 사람의 풀이 1
    static int N;
    static int answer;

    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dfs(0, 0, 0, 0);

        System.out.println(answer);
    }

    private static void dfs(int r, int c, int ld, int rd) {
        // r은 위에서 아래방향으로 세는 행번호
        // c는 퀸이 놓인 모든 열의 위치를 나타냄
        // ld는 퀸에서 왼쪽으로 뻗는 대각선이 있는 모든 열
        // rd는 퀸에서 오른쪽으로 뻗는 대각선이 있는 모든 열
        // 변수에는 십진수로 저장되지만 이진수로 변환했을 때 각 비트의 상태를 활용한다
        if (r == N) {        // N 번째 까지 퀸 배치 성공한 경우
            answer++;
            return;
        }

        int bit = ~(c | ld | rd) & ((1 << N) - 1);
        // bit 는 현재 행에서 퀸이 존재할 수 있는 열을 나타내는 변수
        // ex) 1100 이면 앞 두자리에 퀸을 배치 가능

        int pos = 0;
        while (bit != 0) {
            pos = bit & -bit;
            // 자기 자신의 2의보수와 &비트연산시 1중에서 가장 작은 비트가 뽑힌다.
            // ex) 15 & -15 = 00001111 & 11110001 = 0001
            // 현재 행에서 nQueen 이 배치 가능한 위치를 우측에서부터 차례로 뽑아내는 변수

            bit -= pos;        // 가능한 모든 위치를 백트래킹하기 위해 bit값 변경
            dfs(r + 1, c | pos, (ld | pos) << 1, (rd | pos) >> 1);
            // r+1은 다음행으로
            // c|pos 는 퀸을 배치 한 열 비트를 추가
            // 대각선 방향은 아래 행으로 갈때 좌우로 한칸씩 멀어지므로 현재 배치한 퀸 비트 추가 후
            // 방향에 맞게 <<, >> 시프트 연산으로 다음 행에서 배치 불가능한 위치를 반영한다.
        }
    }

    // 다른 사람의 풀이 2
    public static int n2;
    public static int cnt = 0;


    public static void queen(int y, int[][] check) {
        //y축 위치를 증가 시키며 한 개씩 퀸을 놓는다.
        if (y == n2) {
            //n-1 까지 끝내고 n이 입력된다면 cnt++
            cnt++;
        } else {
            for (int i = 0; i < n2; i++) {
                if (check[y][i] == 0) {
                    signX(y, i, check);
                    sign(y, i, check);
                    queen(y + 1, check);
                    unSignX(y, i, check);
                    unSign(y, i, check);
                }
            }
        }
    }

    public static void signX(int y, int x, int[][] check) {
        int xl = x;
        int xr = x;
        for (int i = y + 1; i < n2; i++) {
            if (xl > 0) check[i][--xl]++;
            if (xr < n2 - 1) check[i][++xr]++;
        }
    }

    public static void unSignX(int y, int x, int[][] check) {
        int xl = x;
        int xr = x;
        for (int i = y + 1; i < n2; i++) {
            if (xl > 0) check[i][--xl]--;
            if (xr < n2 - 1) check[i][++xr]--;
        }
    }

    public static void sign(int y, int x, int[][] check) {
        for (int i = y + 1; i < n2; i++) {
            check[i][x]++;
        }
    }

    public static void unSign(int y, int x, int[][] check) {
        for (int i = y + 1; i < n2; i++) {
            check[i][x]--;
        }
    }


    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n2 = Integer.parseInt(br.readLine());
        int[][] check = new int[n2][n2];

        int r = n2 / 2;
        for (int i = 0; i <= r - 1; i++) {
            //반나눠서 x2(좌우 대칭상황) 체스판 가장 윗 라인의 퀸 위치(i,0) >편의상 0을 위라고 생각
            sign(0, i, check);
            signX(0, i, check);
            queen(1, check);
            unSign(0, i, check);
            unSignX(0, i, check);

        }

        if (n2 != 1) cnt *= 2;

        if (n2 % 2 == 1) {
            //n이 홀수일 때, 퀸이 가장 윗칸 가운데에 있을 경우를 따로 더해줌
            sign(0, r, check);
            signX(0, r, check);
            queen(1, check);
            unSign(0, r, check);
            unSignX(0, r, check);
        }

        bw.write(cnt + "");
        bw.flush();
    }
}

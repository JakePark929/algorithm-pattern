package com.jake.argorithm.baekjoon.lv20_backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 스도쿠 ]
 * <p>
 * 스도쿠는 18세기 스위스 수학자가 만든 '라틴 사각형'이랑 퍼즐에서 유래한 것으로 현재 많은 인기를 누리고 있다.
 * 이 게임은 아래 그림과 같이 가로, 세로 각각 9개씩 총 81개의 작은 칸으로 이루어진 정사각형 판 위에서 이뤄지는데,
 * 게임 시작 전 일부 칸에는 1부터 9까지의 숫자 중 하나가 쓰여 있다.
 * <p>
 * 나머지 빈 칸을 채우는 방식은 다음과 같다.
 * <p>
 * 1. 각각의 가로줄과 세로줄에는 1부터 9까지의 숫자가 한 번씩만 나타나야 한다.
 * 2 .굵은 선으로 구분되어 있는 3x3 정사각형 안에도 1부터 9까지의 숫자가 한 번씩만 나타나야 한다.
 * <p>
 * 위의 예의 경우, 첫째 줄에는 1을 제외한 나머지 2부터 9까지의 숫자들이 이미 나타나 있으므로 첫째 줄 빈칸에는 1이 들어가야 한다.
 * <p>
 * 또한 위쪽 가운데 위치한 3x3 정사각형의 경우에는 3을 제외한 나머지 숫자들이 이미 쓰여있으므로 가운데 빈 칸에는 3이 들어가야 한다.
 * <p>
 * 이와 같이 빈 칸을 차례로 채워 가면 다음과 같은 최종 결과를 얻을 수 있다.
 * <p>
 * 게임 시작 전 스도쿠 판에 쓰여 있는 숫자들의 정보가 주어질 때 모든 빈 칸이 채워진 최종 모습을 출력하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 아홉 줄에 걸쳐 한 줄에 9개씩 게임 시작 전 스도쿠판 각 줄에 쓰여 있는 숫자가 한 칸씩 띄워서 차례로 주어진다.
 * 스도쿠 판의 빈 칸의 경우에는 0이 주어진다. 스도쿠 판을 규칙대로 채울 수 없는 경우의 입력은 주어지지 않는다.
 * <p>
 * 출력
 * 모든 빈 칸이 채워진 스도쿠 판의 최종 모습을 아홉 줄에 걸쳐 한 줄에 9개씩 한 칸씩 띄워서 출력한다.
 * <p>
 * 스도쿠 판을 채우는 방법이 여럿인 경우는 그 중 하나만을 출력한다.
 * <p>
 * 작성일 : 2023.09.04
 */
class B2580_Sudoku {
    private static final int[][] board = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sudoku(0, 0);
    }

    private static void sudoku(int row, int col) {
        // 해당 행이 다 채워졌을 경우 다음 행의 첫 번째 열부터 시작
        if (col == 9) {
            sudoku(row + 1, 0);
            return;
        }

        // 행과 열이 모두 채워졌을 경우 출력 후 종료
        if (row == 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(board[i][j]).append(' ');
                }
                sb.append('\n');
            }
            System.out.println(sb);

            // 시스템 종료
            System.exit(0);
        }

        // 만약 해당 위치의 값이 0 이라면 1부터 9까지 중 가능한 수 탐색
        if (board[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                // i 값이 중복되지 않는지 검사
                if (isPossible(row, col, i)) {
                    board[row][col] = i;
                    sudoku(row, col + 1);
                }
            }

            board[row][col] = 0;
            return;
        }

        sudoku(row, col + 1);
    }

    private static boolean isPossible(int row, int col, int value) {
        // 같은 행에 있는 원소들 중 겹치는 열 원소가 있는지 검사
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == value) {
                return false;
            }
        }

        // 같은 열에 있는 원소들 중 겹치는 행 원소가 있는지 검사
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == value) {
                return false;
            }
        }

        // 3 * 3 칸에 중복되는 원소가 있는지 검사
        int set_row = (row / 3) * 3; // value 가 속한 3x3의 행의 첫 위치
        int set_col = (col / 3) * 3; // value 가 속한 3x3의 열의 첫 위치
        for (int i = set_row; i < set_row + 3; i++) {
            for (int j = set_col; j < set_col + 3; j++) {
                if (board[i][j] == value) {
                    return false;
                }
            }
        }

        // 중복 안되면 true 반환
        return true;
    }

    // 다른 사람의 풀이 1
    static int[][] sudoku;

    static int[] secSet;
    static int[] rowSet;
    static int[] colSet;

    static StringBuilder sb;

    public static void main1(String[] args) throws Exception {
        sudoku = new int[9][9];

        secSet = new int[9];
        rowSet = new int[9];
        colSet = new int[9];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int r = 0; r < 9; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < 9; c++) {
                sudoku[r][c] = Integer.parseInt(st.nextToken());
                secSet[(r / 3) * 3 + c / 3] |= 1 << sudoku[r][c];
                rowSet[r] |= 1 << sudoku[r][c];
                colSet[c] |= 1 << sudoku[r][c];
            }
        }

        backTrack(0, 0);
        System.out.println(sb);
        br.close();
    }

    private static boolean backTrack(int r, int c) {
        if (r == 9) {
            if (sb == null) {
                sb = new StringBuilder();
                for (int[] sudo : sudoku) {
                    for (int i : sudo) {
                        sb.append(i).append(" ");
                    }
                    sb.append("\n");
                }
            }
            return true;
        }

        if (sudoku[r][c] != 0) {
            if (c < 8)
                return backTrack(r, c + 1);
            else
                return backTrack(r + 1, 0);
        } else {
            for (int i = 1; i <= 9; i++) {
                if (
                        (secSet[(r / 3) * 3 + c / 3] & (1 << i)) != 0
                        || (rowSet[r] & (1 << i)) != 0
                        || (colSet[c] & (1 << i)) != 0
                ) {
                    continue; // 섹션, 가로, 세로에 겹치는 수가 있으면 스킵
                }
                sudoku[r][c] = i;
                secSet[(r / 3) * 3 + c / 3] |= (1 << i);
                rowSet[r] |= (1 << i);
                colSet[c] |= (1 << i);
                if (c < 8) {
                    if (backTrack(r, c + 1)) return true;
                } else {
                    if (backTrack(r + 1, 0)) return true;
                }
                sudoku[r][c] = 0;
                secSet[(r / 3) * 3 + c / 3] &= ~(1 << i);
                rowSet[r] &= ~(1 << i);
                colSet[c] &= ~(1 << i);
            }
        }

        return false;
    }
}

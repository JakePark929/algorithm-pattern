package com.jake.algorithm.programmers.lv1;

import java.util.LinkedList;
import java.util.Stack;

/**
 * [ 크레인 인형뽑기 게임 ]
 * <p>
 * 게임개발자인 "죠르디"는 크레인 인형뽑기 기계를 모바일 게임으로 만들려고 합니다.
 * "죠르디"는 게임의 재미를 높이기 위해 화면 구성과 규칙을 다음과 같이 게임 로직에 반영하려고 합니다.
 * <p>
 * 게임 화면은 "1 x 1" 크기의 칸들로 이루어진 "N x N" 크기의 정사각 격자이며 위
 * 쪽에는 크레인이 있고 오른쪽에는 바구니가 있습니다.
 * (위 그림은 "5 x 5" 크기의 예시입니다).
 * 각 격자 칸에는 다양한 인형이 들어 있으며 인형이 없는 칸은 빈칸입니다.
 * 모든 인형은 "1 x 1" 크기의 격자 한 칸을 차지하며
 * 격자의 가장 아래 칸부터 차곡차곡 쌓여 있습니다.
 * 게임 사용자는 크레인을 좌우로 움직여서 멈춘 위치에서 가장 위에 있는 인형을 집어 올릴 수 있습니다.
 * 집어 올린 인형은 바구니에 쌓이게 되는 데,
 * 이때 바구니의 가장 아래 칸부터 인형이 순서대로 쌓이게 됩니다.
 * 다음 그림은 [1번, 5번, 3번] 위치에서 순서대로 인형을 집어 올려 바구니에 담은 모습입니다.
 * <p>
 * 만약 같은 모양의 인형 두 개가 바구니에 연속해서 쌓이게 되면 두 인형은 터뜨려지면서 바구니에서 사라지게 됩니다.
 * 위 상태에서 이어서 [5번] 위치에서 인형을 집어 바구니에 쌓으면 같은 모양 인형 두 개가 없어집니다.
 * <p>
 * 크레인 작동 시 인형이 집어지지 않는 경우는 없으나
 * 만약 인형이 없는 곳에서 크레인을 작동시키는 경우에는 아무런 일도 일어나지 않습니다.
 * 또한 바구니는 모든 인형이 들어갈 수 있을 만큼 충분히 크다고 가정합니다.
 * (그림에서는 화면표시 제약으로 5칸만으로 표현하였음)
 * <p>
 * 게임 화면의 격자의 상태가 담긴 2차원 배열 board와
 * 인형을 집기 위해 크레인을 작동시킨 위치가 담긴 배열 moves가 매개변수로 주어질 때,
 * 크레인을 모두 작동시킨 후 터트려져 사라진 인형의 개수를 return 하도록 solution 함수를 완성해주세요.
 * <p>
 * [제한사항]
 * board 배열은 2차원 배열로 크기는 "5 x 5" 이상 "30 x 30" 이하입니다.
 * board의 각 칸에는 0 이상 100 이하인 정수가 담겨있습니다.
 * 0은 빈 칸을 나타냅니다.
 * 1 ~ 100의 각 숫자는 각기 다른 인형의 모양을 의미하며 같은 숫자는 같은 모양의 인형을 나타냅니다.
 * moves 배열의 크기는 1 이상 1,000 이하입니다.
 * moves 배열 각 원소들의 값은 1 이상이며 board 배열의 가로 크기 이하인 자연수입니다.
 * <p>
 * 작성일 : 2023.06.27
 */
class P64061 {
    public int solution(int[][] board, int[] moves) {
        int[][] dolls = new int[board.length][board[0].length];
        LinkedList<Integer> basket = new LinkedList<>();
        LinkedList<Integer> original = new LinkedList<>();

        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                dolls[i][j] = board[j][i];
            }
        }

        for (int move : moves) {
            for (int i = 0; i < dolls.length; i++) {
                if (dolls[move - 1][i] > 0) {
                    original.add(dolls[move - 1][i]);
                    if (basket.size() > 0) {
                        if (basket.get(basket.size() - 1) == dolls[move - 1][i]) {
                            basket.removeLast();
                        } else {
                            basket.add(dolls[move - 1][i]);
                        }
                    } else {
                        basket.add(dolls[move - 1][i]);
                    }
                    dolls[move - 1][i] = 0;
                    break;
                }
            }
        }

        return original.size() - basket.size();
    }
    
    // 다른 사람의 풀이 1 - 스택사용
    public int solution1(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for (int move : moves) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][move - 1] != 0) {
                    if (stack.isEmpty()) {
                        stack.push(board[j][move - 1]);
                        board[j][move - 1] = 0;
                        break;
                    }
                    if (board[j][move - 1] == stack.peek()) {
                        stack.pop();
                        answer += 2;
                    } else
                        stack.push(board[j][move - 1]);
                    board[j][move - 1] = 0;
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        P64061 problem = new P64061();
        System.out.println(problem.solution(
                new int[][]{
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {10, 10, 10, 10, 10, 10},
                },
                new int[]{1, 3})
        );
    }
}
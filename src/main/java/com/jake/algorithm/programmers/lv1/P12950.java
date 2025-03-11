package com.jake.algorithm.programmers.lv1;

import java.util.Arrays;

/**
 * [ 행렬의 덧셈 ]
 *
 * 행렬의 덧셈은 행과 열의 크기가 같은 두 행렬의 같은 행, 같은 열의 값을 서로 더한 결과가 됩니다. 2개의 행렬 arr1과 arr2를 입력받아, 행렬 덧셈의 결과를 반환하는 함수, solution을 완성해주세요.
 *
 * 제한 조건
 * 행렬 arr1, arr2의 행과 열의 길이는 500을 넘지 않습니다.
 *
 * 작성일 : 2023.06.18
 */
class P12950 {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr1[0].length];
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[0].length; j++) {
                answer[i][j] = arr1[i][j] + arr2[i][j];
            }
        }
        return answer;
    }

    // 다른 사람의 풀이 1
    public int[][] solution2(int[][] arr1, int[][] arr2) {
        int[][] answer = {};
        answer = arr1;
        for(int i=0; i<arr1.length; i++){
            for(int j=0; j<arr1[0].length; j++){
                answer[i][j] += arr2[i][j];
            }
        }
        return answer;
    }

    // 다른 사람의 풀이 2
    int[][] sumMatrix(int[][] A, int[][] B) {

        int rowLength = Math.max(A.length, B.length);
        int colLength = Math.max(A[0].length, B[0].length);

        int[][] answer = new int[rowLength][colLength];

        for(int i=0; i<rowLength; i++) {
            for(int j=0; j<colLength; j++) {
                answer[i][j] = A[i][j]+B[i][j];
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        P12950 problem = new P12950();
        System.out.println(Arrays.deepToString(problem.solution(new int[][]{{1}, {2}}, new int[][]{{3}, {4}})));
    }
}

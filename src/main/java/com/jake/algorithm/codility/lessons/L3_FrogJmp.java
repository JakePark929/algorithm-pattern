package com.jake.algorithm.codility.lessons;

/**
 * [ FrogJmp ]
 *
 * 작성일 : 24.10.04
 */
public class L3_FrogJmp {
    public int solution(int X, int Y, int D) {
        int answer;

        if ((Y - X) % D == 0) {
            answer = (Y - X) / D;
        } else {
            answer = ((Y - X) / D) + 1;
        }

        return answer;
    }

    public static void main(String[] args) {

    }
}

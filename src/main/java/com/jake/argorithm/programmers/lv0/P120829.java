package com.jake.argorithm.programmers.lv0;

public class P120829 {
    public int solution(int angle) {
        int answer = 4;

        if (0 < angle && angle < 90) {
            answer = 1;
        } else if (angle == 90) {
            answer = 2;
        } else if (90 < angle && angle < 180) {
            answer = 3;
        }

        return answer;
    }

    public static void main(String[] args) {
        final P120829 p120829 = new P120829();

        System.out.println(p120829.solution(180));
    }
}

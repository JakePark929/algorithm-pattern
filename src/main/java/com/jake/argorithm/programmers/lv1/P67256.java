package com.jake.argorithm.programmers.lv1;

import java.util.HashMap;

/**
 * [ 키패드 누르기 ]
 * <p>
 * 스마트폰 전화 키패드의 각 칸에 다음과 같이 숫자들이 적혀 있습니다.
 * <p>
 * 이 전화 키패드에서 왼손과 오른손의 엄지손가락만을 이용해서 숫자만을 입력하려고 합니다.
 * 맨 처음 왼손 엄지손가락은 * 키패드에 오른손 엄지손가락은 # 키패드 위치에서 시작하며, 엄지손가락을 사용하는 규칙은 다음과 같습니다.
 * <p>
 * 엄지손가락은 상하좌우 4가지 방향으로만 이동할 수 있으며 키패드 이동 한 칸은 거리로 1에 해당합니다.
 * 왼쪽 열의 3개의 숫자 1, 4, 7을 입력할 때는 왼손 엄지손가락을 사용합니다.
 * 오른쪽 열의 3개의 숫자 3, 6, 9를 입력할 때는 오른손 엄지손가락을 사용합니다.
 * 가운데 열의 4개의 숫자 2, 5, 8, 0을 입력할 때는 두 엄지손가락의 현재 키패드의 위치에서 더 가까운 엄지손가락을 사용합니다.
 * 4-1. 만약 두 엄지손가락의 거리가 같다면, 오른손잡이는 오른손 엄지손가락, 왼손잡이는 왼손 엄지손가락을 사용합니다.
 * 순서대로 누를 번호가 담긴 배열 numbers, 왼손잡이인지 오른손잡이인 지를 나타내는 문자열 hand가 매개변수로 주어질 때,
 * 각 번호를 누른 엄지손가락이 왼손인 지 오른손인 지를 나타내는 연속된 문자열 형태로 return 하도록 solution 함수를 완성해주세요.
 * <p>
 * [제한사항]
 * numbers 배열의 크기는 1 이상 1,000 이하입니다.
 * numbers 배열 원소의 값은 0 이상 9 이하인 정수입니다.
 * hand는 "left" 또는 "right" 입니다.
 * "left"는 왼손잡이, "right"는 오른손잡이를 의미합니다.
 * 왼손 엄지손가락을 사용한 경우는 L, 오른손 엄지손가락을 사용한 경우는 R을 순서대로 이어붙여 문자열 형태로 return 해주세요.
 * <p>
 * 작성일 : 2023.06.29
 */
class P67256 {
    public String solution(int[] numbers, String hand) {
        StringBuilder result = new StringBuilder();
        int thumbLeft = 10;
        int thumbRight = 12;
        HashMap<String, int[]> keypad = new HashMap<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                keypad.put(String.valueOf(3 * i + j + 1), new int[]{i, j});
            }
        }

        for (int number : numbers) {
            if (number == 1 || number == 4 || number == 7) {
                thumbLeft = number;
                result.append("L");
            } else if (number == 3 || number == 6 || number == 9) {
                thumbRight = number;
                result.append("R");
            } else {
                if (number == 0) number = 11;
                int[] thumbLeftPos = keypad.get(String.valueOf(thumbLeft));
                int[] thumbRightPos = keypad.get(String.valueOf(thumbRight));
                int[] targetPos = keypad.get(String.valueOf(number));
                int instanceLeft = Math.abs(targetPos[0] - thumbLeftPos[0]) + Math.abs(targetPos[1] - thumbLeftPos[1]);
                int instanceRight = Math.abs(targetPos[0] - thumbRightPos[0]) + Math.abs(targetPos[1] - thumbRightPos[1]);

                if (instanceLeft < instanceRight) {
                    thumbLeft = number;
                    result.append("L");
                } else if (instanceRight < instanceLeft) {
                    thumbRight = number;
                    result.append("R");
                } else {
                    if (hand.equals("left")) {
                        thumbLeft = number;
                        result.append("L");
                    } else {
                        thumbRight = number;
                        result.append("R");
                    }
                }
            }
        }

        return result.toString();
    }

    // 다른 사람의 풀이 1
    int[][] numpadPos = {
            {3, 1}, //0
            {0, 0}, //1
            {0, 1}, //2
            {0, 2}, //3
            {1, 0}, //4
            {1, 1}, //5
            {1, 2}, //6
            {2, 0}, //7
            {2, 1}, //8
            {2, 2}  //9
    };
    //초기 위치
    int[] leftPos = {3, 0};
    int[] rightPos = {3, 2};
    String hand;

    public String solution1(int[] numbers, String hand) {
        this.hand = (hand.equals("right")) ? "R" : "L";

        String answer = "";
        for (int num : numbers) {
            String Umji = pushNumber(num);
            answer += Umji;

            if (Umji.equals("L")) {
                leftPos = numpadPos[num];
                continue;
            }
            if (Umji.equals("R")) {
                rightPos = numpadPos[num];
                continue;
            }
        }
        return answer;
    }

    //num버튼을 누를 때 어디 손을 사용하는가
    private String pushNumber(int num) {
        if (num == 1 || num == 4 || num == 7) return "L";
        if (num == 3 || num == 6 || num == 9) return "R";

        // 2,5,8,0 일때 어디 손가락이 가까운가
        if (getDist(leftPos, num) > getDist(rightPos, num)) return "R";
        if (getDist(leftPos, num) < getDist(rightPos, num)) return "L";

        //같으면 손잡이
        return this.hand;
    }

    //해당 위치와 번호 위치의 거리
    private int getDist(int[] pos, int num) {
        return Math.abs(pos[0] - numpadPos[num][0]) + Math.abs(pos[1] - numpadPos[num][1]);
    }

    // 다른 사람의 풀이 2
    private static final String LEFT = "L";
    private static final String RIGHT = "R";
    private static final int[][] KEYPAD = new int[][]{
            {3, 1}, {0, 0}, {0, 1}, {0, 2}
            , {1, 0}, {1, 1}, {1, 2}
            , {2, 0}, {2, 1}, {2, 2}
    };

    public String solution2(int[] numbers, String hand) {
        int[] leftXY = {3, 0};
        int[] rightXY = {3, 2};

        StringBuilder sb = new StringBuilder();
        for (int i : numbers) {
            switch (i) {
                case 1: case 4: case 7:
                    leftXY = KEYPAD[i];
                    sb.append(LEFT);
                    break;
                case 3: case 6: case 9:
                    rightXY = KEYPAD[i];
                    sb.append(RIGHT);
                    break;
                case 2: case 5: case 8: case 0:
                    String finger = getSuitableFinger(leftXY, rightXY, i, hand);
                    sb.append(finger);

                    if (finger.equalsIgnoreCase(LEFT)) {
                        leftXY = KEYPAD[i];
                    } else {
                        rightXY = KEYPAD[i];
                    }

                    break;
            }
        }

        return sb.toString();
    }

    private int getDifference(int[] xy, int[] keypad) {
        return Math.abs(xy[0] - keypad[0]) + Math.abs(xy[1] - keypad[1]);
    }

    private String getSuitableFinger(int[] leftXY, int[] rightXY, int number, String hand) {
        int leftDifference = getDifference(leftXY, KEYPAD[number]);
        int rightDifference = getDifference(rightXY, KEYPAD[number]);

        String finger;
        if (leftDifference > rightDifference) {
            finger = RIGHT;
        } else if (leftDifference < rightDifference) {
            finger = LEFT;
        } else {
            finger = ("right".equals(hand) ? RIGHT : LEFT);
        }

        return finger;
    }

    public static void main(String[] args) {
        P67256 problem = new P67256();
        System.out.println(problem.solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, "right"));
    }
}

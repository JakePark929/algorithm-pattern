package com.jake.algorithm.programmers.lv1;

import java.util.Arrays;

/**
 * [ 공원 산책 ]
 * <p>
 * 지나다니는 길을 'O', 장애물을 'X'로 나타낸 직사각형 격자 모양의 공원에서 로봇 강아지가 산책을 하려합니다.
 * 산책은 로봇 강아지에 미리 입력된 명령에 따라 진행하며, 명령은 다음과 같은 형식으로 주어집니다.
 * <p>
 * ["방향 거리", "방향 거리" … ]
 * 예를 들어 "E 5"는 로봇 강아지가 현재 위치에서 동쪽으로 5칸 이동했다는 의미입니다.
 * 로봇 강아지는 명령을 수행하기 전에 다음 두 가지를 먼저 확인합니다.
 * <p>
 * 주어진 방향으로 이동할 때 공원을 벗어나는지 확인합니다.
 * 주어진 방향으로 이동 중 장애물을 만나는지 확인합니다.
 * 위 두 가지중 어느 하나라도 해당된다면, 로봇 강아지는 해당 명령을 무시하고 다음 명령을 수행합니다.
 * 공원의 가로 길이가 W, 세로 길이가 H라고 할 때, 공원의 좌측 상단의 좌표는 (0, 0), 우측 하단의 좌표는 (H - 1, W - 1) 입니다.
 * <p>
 * image
 * <p>
 * 공원을 나타내는 문자열 배열 park,
 * 로봇 강아지가 수행할 명령이 담긴 문자열 배열 routes가 매개변수로 주어질 때,
 * 로봇 강아지가 모든 명령을 수행 후 놓인 위치를 [세로 방향 좌표, 가로 방향 좌표] 순으로
 * 배열에 담아 return 하도록 solution 함수를 완성해주세요.
 * <p>
 * 제한사항
 * 3 ≤ park의 길이 ≤ 50
 * 3 ≤ park[i]의 길이 ≤ 50
 * park[i]는 다음 문자들로 이루어져 있으며 시작지점은 하나만 주어집니다.
 * S : 시작 지점
 * O : 이동 가능한 통로
 * X : 장애물
 * park는 직사각형 모양입니다.
 * 1 ≤ routes의 길이 ≤ 50
 * routes의 각 원소는 로봇 강아지가 수행할 명령어를 나타냅니다.
 * 로봇 강아지는 routes의 첫 번째 원소부터 순서대로 명령을 수행합니다.
 * routes의 원소는 "op n"과 같은 구조로 이루어져 있으며, op는 이동할 방향, n은 이동할 칸의 수를 의미합니다.
 * op는 다음 네 가지중 하나로 이루어져 있습니다.
 * N : 북쪽으로 주어진 칸만큼 이동합니다.
 * S : 남쪽으로 주어진 칸만큼 이동합니다.
 * W : 서쪽으로 주어진 칸만큼 이동합니다.
 * E : 동쪽으로 주어진 칸만큼 이동합니다.
 * 1 ≤ n ≤ 9
 * <p>
 * 작성일 : 2023.07.06
 */
class P172928 {
    public int[] solution(String[] park, String[] routes) {
        int positionCol = 0;
        int positionRow = 0;

        start:
        for (int row = 0; row < park.length; row++) {
            for (int col = 0; col < park[0].length(); col++) {
                if (park[row].charAt(col) == 'S') {
                    positionRow = row;
                    positionCol = col;
                    break start;
                }
            }
        }

        for (String route : routes) {
            String direction = route.split(" ")[0];
            int move = Integer.parseInt(route.split(" ")[1]);

            switch (direction) {
                case "N":
                    if (0 <= positionRow - move && positionRow - move < park.length) {
                        if (!hasX(park, direction, positionRow, positionCol, move)) {
                            positionRow = positionRow - move;
                        }
                    }
                    break;
                case "S":
                    if (0 <= positionRow + move && positionRow + move < park.length) {
                        if (!hasX(park, direction, positionRow, positionCol, move)) {
                            positionRow = positionRow + move;
                        }
                    }
                    break;
                case "W":
                    if (0 <= positionCol - move && positionCol - move < park[0].length()) {
                        if (!hasX(park, direction, positionRow, positionCol, move)) {
                            positionCol = positionCol - move;
                        }
                    }
                    break;
                case "E":
                    if (0 <= positionCol + move && positionCol + move < park[0].length()) {
                        if (!hasX(park, direction, positionRow, positionCol, move)) {
                            positionCol = positionCol + move;
                        }
                    }
                    break;
            }
        }

        return new int[]{positionRow, positionCol};
    }

    public boolean hasX(String[] park, String direction, int positionRow, int positionCol, int move) {
        switch (direction) {
            case "N":
                for (int i = positionRow - 1; i >= positionRow - move; i--) {
                    if (park[i].charAt(positionCol) == 'X') return true;
                }
                break;
            case "S":
                for (int i = positionRow + 1; i <= positionRow + move; i++) {
                    if (park[i].charAt(positionCol) == 'X') return true;
                }
                break;
            case "W":
                for (int i = positionCol - 1; i >= positionCol - move; i--) {
                    if (park[positionRow].charAt(i) == 'X') return true;
                }
                break;
            case "E":
                for (int i = positionCol + 1; i <= positionCol + move; i++) {
                    if (park[positionRow].charAt(i) == 'X') return true;
                }
                break;
        }

        return false;
    }

    // 다른 사람의 풀이 1
    public static int[] solution1(String[] park, String[] routes) {
        int x = 0;
        int y = 0;
        int[][] arr = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean start = false;

        for (int i = 0; i < park.length; i++) {
            if (start) {
                break;
            }
            for (int j = 0; j < park[i].length(); j++) {
                if (park[i].charAt(j) == 'S') {
                    x = i;
                    y = j;
                    start = true;
                    break;
                }
            }
        }

        for (String route : routes) {
            String[] routeArr = route.split(" ");
            String direction = routeArr[0];
            int distance = Integer.parseInt(routeArr[1]);

            int index = getDirectionIndex(direction);

            if (isWalk(park, x, y, distance, arr[index])) {
                x += distance * arr[index][0];
                y += distance * arr[index][1];
            }
        }

        return new int[]{x, y};
    }

    private static boolean isWalk(String[] park, int x, int y, int distance, int[] arr) {
        for (int i = 0; i < distance; i++) {
            x += arr[0];
            y += arr[1];

            if (y < 0 || y > park[0].length() - 1 || x < 0 || x > park.length - 1 || park[x].charAt(y) == 'X') {
                return false;
            }
        }
        return true;
    }

    private static int getDirectionIndex(String direction) {
        int index = 0;
        switch (direction) {
            case "N":
                break;
            case "S":
                index = 1;
                break;
            case "W":
                index = 2;
                break;
            case "E":
                index = 3;
                break;
        }
        return index;
    }

    public static void main(String[] args) {
        P172928 problem = new P172928();
        System.out.println(Arrays.toString(problem.solution(
                new String[]{
                        "OOXO",
                        "OXOO",
                        "OOSO",
                        "OOXO"
                }
                , new String[]{"N 1", "E 1", "S 1", "W 1"}
        )));
    }
}

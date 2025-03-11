package com.jake.algorithm.baekjoon.lv07_2darray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 색종이 ]
 * <p>
 * 가로, 세로의 크기가 각각 100인 정사각형 모양의 흰색 도화지가 있다.
 * 이 도화지 위에 가로, 세로의 크기가 각각 10인 정사각형 모양의 검은색 색종이를 색종이의 변과 도화지의 변이 평행하도록 붙인다.
 * 이러한 방식으로 색종이를 한 장 또는 여러 장 붙인 후 색종이가 붙은 검은 영역의 넓이를 구하는 프로그램을 작성하시오.
 * <p>
 * 예를 들어 흰색 도화지 위에 세 장의 검은색 색종이를 그림과 같은 모양으로 붙였다면 검은색 영역의 넓이는 260이 된다.
 * <p>
 * 입력
 * 첫째 줄에 색종이의 수가 주어진다.
 * 이어 둘째 줄부터 한 줄에 하나씩 색종이를 붙인 위치가 주어진다.
 * 색종이를 붙인 위치는 두 개의 자연수로 주어지는데 첫 번째 자연수는 색종이의 왼쪽 변과 도화지의 왼쪽 변 사이의 거리이고,
 * 두 번째 자연수는 색종이의 아래쪽 변과 도화지의 아래쪽 변 사이의 거리이다.
 * 색종이의 수는 100 이하이며, 색종이가 도화지 밖으로 나가는 경우는 없다
 * <p>
 * 출력
 * 첫째 줄에 색종이가 붙은 검은 영역의 넓이를 출력한다.
 * <p>
 * 작성일 : 2023.07.25
 */
class B2563_ColorPaper {
//        public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int piece = Integer.parseInt(br.readLine());
//        int[][] papers = new int[piece][4];
//
//        int minX = Integer.MAX_VALUE;
//        int minY = Integer.MAX_VALUE;
//        int maxX = Integer.MIN_VALUE;
//        int maxY = Integer.MIN_VALUE;
//
//        for (int i = 0; i < papers.length; i++) {
//            String s = br.readLine();
//            papers[i][0] = Integer.parseInt(s.substring(0, s.indexOf(" ")));
//            papers[i][1] = Integer.parseInt(s.substring(s.indexOf(" ") + 1));
//            papers[i][2] = papers[i][0] + 10;
//            papers[i][3] = papers[i][1] + 10;
//            minX = Math.min(papers[i][0], minX);
//            minY = Math.min(papers[i][1], minY);
//            maxX = Math.max(papers[i][2], maxX);
//            maxY = Math.max(papers[i][3], maxY);
//        }
//
//        int[][] walls;
//        if (piece > 0) {
//            walls = new int[maxY][maxX];
//        } else {
//            walls = new int[][]{};
//        }
//
//        int count = 0;
//        for (int i = 0; i < walls.length; i++) {
//            for (int j = 0; j < walls[0].length; j++) {
//                for (int[] paper : papers) {
//                    if (
//                            paper[0] <= j && j < paper[2]
//                                    && paper[1] <= i && i < paper[3]
//                    ) {
//                        walls[i][j]++;
//                    }
//                }
//
//                if (walls[i][j] > 1) {
//                    count++;
//                }
//            }
//        }
//
//        for (int[] wall : walls) {
//            System.out.println(Arrays.toString(wall));
//        }
//
//        System.out.println(count);
//
//        System.out.println(100 * piece - count);
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = 0;  //검은 영역의 넓이
        int n = Integer.parseInt(br.readLine());  //색종이 개수
        int[][] arr = new int[101][101];  //도화지

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            //(x,y)부터 (x+9, y+9)까지의 영역을 하나씩 체크한 후 총 넓이에 더해준다
            for (int j = x; j < x + 10; j++) {
                for (int k = y; k < y + 10; k++) {
                    if (arr[j][k] == 0) {
                        arr[j][k] = 1;
                        total++;
                    }
                }
            }
        }

        System.out.print(total);
    }
}

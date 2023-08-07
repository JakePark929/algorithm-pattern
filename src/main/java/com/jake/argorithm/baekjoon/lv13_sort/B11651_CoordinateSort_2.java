package com.jake.argorithm.baekjoon.lv13_sort;

import java.util.Arrays;

/**
 * [ 좌표 정렬하기 2 ]
 * <p>
 * 2차원 평면 위의 점 N개가 주어진다.
 * 좌표를 y좌표가 증가하는 순으로, y좌표가 같으면 x좌표가 증가하는 순서로 정렬한 다음 출력하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 점의 개수 N (1 ≤ N ≤ 100,000)이 주어진다.
 * 둘째 줄부터 N개의 줄에는 i번점의 위치 xi와 yi가 주어진다.
 * (-100,000 ≤ xi, yi ≤ 100,000) 좌표는 항상 정수이고, 위치가 같은 두 점은 없다.
 * <p>
 * 출력
 * 첫째 줄부터 N개의 줄에 점을 정렬한 결과를 출력한다.
 * <p>
 * 작성일 : 2023.08.04
 */
class B11651_CoordinateSort_2 {
    public static void main(String[] args) throws Exception {
        int n = read();
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = read();
            arr[i][1] = read();
        }

        Arrays.sort(arr, (e1, e2) -> {
            if (e1[1] == e2[1]) return e1[0] - e2[0];
            else return e1[1] - e2[1];
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i][0]).append(" ").append(arr[i][1]).append('\n');
        }

        System.out.println(sb.toString().trim());
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;
        if (isNegative) n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return isNegative ? ~n + 1 : n;
    }

    // 다른 사람의 풀이 1
//    static int read() throws Exception {
//        boolean nega = false;
//        int i, j = System.in.read() & 15;
//        if (j == 13) {
//            nega = true;
//            j = System.in.read() & 15;
//        }
//        while ((i = System.in.read()) > 32) j = (j << 3) + (j << 1) + (i & 15);
//        if (i == 13) System.in.read();
//        if (nega) return -j;
//        else return j;
//    }

    public static void main1(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int n = read(), maxY = -100000, minY = 100000, maxX = -100000, minX = 100000;
        int adjY = 0, adjX = 0, rangeY = 0, rangeX = 0;
        int[][] nums = new int[n][2];
        int[][] xSorted = new int[n][2];

        for (int i = 0; i < n; i++) {
            nums[i][0] = read();
            nums[i][1] = read();
            if (nums[i][0] > maxX) maxX = nums[i][0];
            if (nums[i][0] < minX) minX = nums[i][0];
            if (nums[i][1] > maxY) maxY = nums[i][1];
            if (nums[i][1] < minY) minY = nums[i][1];
        }

        if (minX > 0) rangeX = maxX + 1;
        else rangeX = maxX - minX + 1;

        if (minY > 0) rangeY = maxY + 1;
        else rangeY = maxY - minY + 1;

        int prefX[] = new int[rangeX];
        int prefY[] = new int[rangeY];

        if (minY < 0) adjY = -minY;
        else adjY = 0;

        if (minX < 0) adjX = -minX;
        else adjX = 0;

        for (int i = 0; i < n; i++) ++prefX[nums[i][0] + adjX];
        for (int i = 1; i < rangeX; i++) prefX[i] += prefX[i - 1];
        for (int i = n - 1; i >= 0; i--) xSorted[--prefX[nums[i][0] + adjX]] = nums[i];
        for (int i = 0; i < n; i++) ++prefY[xSorted[i][1] + adjY];
        for (int i = 1; i < rangeY; i++) prefY[i] += prefY[i - 1];
        for (int i = n - 1; i >= 0; i--) nums[--prefY[xSorted[i][1] + adjY]] = xSorted[i];
        for (int i = 0; i < n; i++) sb.append(nums[i][0] + " " + nums[i][1]).append("\n");

        System.out.print(sb);
    }
}

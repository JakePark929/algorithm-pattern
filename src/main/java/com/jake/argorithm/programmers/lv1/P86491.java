package com.jake.argorithm.programmers.lv1;

import java.util.Collections;
import java.util.LinkedList;

public class P86491 {
    public int solution(int[][] sizes) {
        LinkedList<Integer> wList = new LinkedList<>();
        LinkedList<Integer> hList = new LinkedList<>();
        for (int[] size : sizes) {
            if (size[0] < size[1]) {
                int temp = size[0];
                size[0] = size[1];
                size[1] = temp;
            }
            wList.add(size[0]);
            hList.add(size[1]);
        }
        Collections.sort(wList);
        Collections.sort(hList);
        return wList.getLast() * hList.getLast();
    }

    // 다른 사람의 풀이 1
    public int solution2(int[][] sizes) {
        int length = 0, height = 0;
        for (int[] card : sizes) {
            length = Math.max(length, Math.max(card[0], card[1]));
            height = Math.max(height, Math.min(card[0], card[1]));
        }
        return length * height;
    }

    public static void main(String[] args) {
        P86491 problem = new P86491();
        System.out.println(problem.solution(new int[][]{{60, 50}, {30, 70}, {60, 30}, {80, 40}}));
    }
}

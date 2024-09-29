package com.jake.argorithm.programmers.lv0;

import java.util.Arrays;

public class P120819 {
    public int[] solution(int money) {

        return new int[]{money / 5500, money % 5500};
    }

    public static void main(String[] args) {
        final int[] solution = new P120819().solution(15000);

        System.out.println(Arrays.toString(solution));
    }
}

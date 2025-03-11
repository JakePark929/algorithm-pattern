package com.jake.algorithm.programmers.lv0;

import java.util.Arrays;

public class P120817 {
    public double solution(int[] numbers) {

        return  Arrays.stream(numbers)
                .average()
                .orElse(0);
    }

    public static void main(String[] args) {
        final P120817 p120817 = new P120817();
        int[] numbers = {89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99};

        System.out.println(p120817.solution(numbers));
    }
}

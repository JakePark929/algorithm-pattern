package com.jake.argorithm.a00_privatetest.util;

import java.util.Arrays;
import java.util.LinkedList;

public class CombinationReuse {
    int[] numbers;

    public int solution() {
        int[] number = new int[5];
        int depth = 3;
        numbers = new int[depth];

        LinkedList<Integer> list = new LinkedList<>();
        for (int num : number) {
            list.add(num);
        }

        comb(list, depth, 0, 0);

        return 0;
    }

    public void comb(LinkedList<Integer> list, int depth, int cnt, int start) {
        if (cnt == depth) {
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for (int i = start; i < list.size(); i++) {
            numbers[cnt] = list.get(i);
            comb(list, depth, cnt + 1, i + 1);
        }
    }
}

package com.jake.algorithm.baekjoon.lv04_array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * [ 최대값 ]
 *
 * 9개의 서로 다른 자연수가 주어질 때,
 * 이들 중 최댓값을 찾고 그 최댓값이 몇 번째 수인지를 구하는 프로그램을 작성하시오.
 * <p>
 * 예를 들어, 서로 다른 9개의 자연수
 * <p>
 * 3, 29, 38, 12, 57, 74, 40, 85, 61
 * <p>
 * 이 주어지면, 이들 중 최댓값은 85이고, 이 값은 8번째 수이다.
 * <p>
 * 입력
 * 첫째 줄부터 아홉 번째 줄까지 한 줄에 하나의 자연수가 주어진다. 주어지는 자연수는 100 보다 작다.
 * <p>
 * 출력
 * 첫째 줄에 최댓값을 출력하고, 둘째 줄에 최댓값이 몇 번째 수인지를 출력한다.
 *
 * 작성일 : 2023.07.19
 */
class B2562_MaxValue {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int max = 0;
        int index = 0;
        for (int i = 0; i < 9; i++) {
            int val = Integer.parseInt(br.readLine());

            if (val > max) {
                max = val;
                index = i + 1;
            }
        }

        System.out.println(max);
        System.out.println(index);
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws NumberFormatException, IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < 9; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }

        int max = list.get(0);

        for (int j = 1; j < list.size(); j++) {
            if(list.get(j) > max)
                max = list.get(j);
        }

        System.out.println(max);
        System.out.println(list.indexOf(max) + 1);

    }
}

package com.jake.argorithm.baekjoon.lv13_sort;

import java.io.IOException;

/**
 * [ 대표값 2 ]
 *
 * 어떤 수들이 있을 때,
 * 그 수들을 대표하는 값으로 가장 흔하게 쓰이는 것은 평균이다.
 * 평균은 주어진 모든 수의 합을 수의 개수로 나눈 것이다.
 * 예를 들어 10, 40, 30, 60, 30의 평균은 (10 + 40 + 30 + 60 + 30) / 5 = 170 / 5 = 34가 된다.
 *
 * 평균 이외의 또 다른 대표값으로 중앙값이라는 것이 있다.
 * 중앙값은 주어진 수를 크기 순서대로 늘어 놓았을 때 가장 중앙에 놓인 값이다.
 * 예를 들어 10, 40, 30, 60, 30의 경우, 크기 순서대로 늘어 놓으면
 *
 * 10 30 30 40 60
 *
 * 이 되고 따라서 중앙값은 30이 된다.
 *
 * 다섯 개의 자연수가 주어질 때 이들의 평균과 중앙값을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄부터 다섯 번째 줄까지 한 줄에 하나씩 자연수가 주어진다. 주어지는 자연수는 100 보다 작은 10의 배수이다.
 *
 * 출력
 * 첫째 줄에는 평균을 출력하고, 둘째 줄에는 중앙값을 출력한다. 평균과 중앙값은 모두 자연수이다.
 *
 * 작성일 : 2023.08.03
 */
class B2587_RepresentationValue_2 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int[] nums = new int[5];
        int[] counting = new int[101]; // -100 ~ 100
        int[] result = new int[5];

        for (int i = 0; i < 5; i++)
            nums[i] = readInt();

        for (int i = 0; i < 5; i++)
            counting[nums[i]]++;

        for (int i = 1; i < counting.length; i++)
            counting[i] += counting[i - 1];

        for (int i = 4; i >= 0; i--) {
            int val = nums[i];
            counting[val]--;
            result[counting[val]] = val;
        }

        int sum = 0;
        for(int i : result) {
            sum += i;
        }

        sb.append(sum / result.length).append('\n').append(result[2]);
        System.out.println(sb);
     }

    static int readInt() throws IOException {
        int n = 0;
        boolean isNegative = false;
        while (true) {
            int input = System.in.read();
            if (input <= 32) {
                return isNegative ? n * -1 : n;
            } else if (input == '-')
                isNegative = true;
            else
                n = (n << 3) + (n << 1) + (input & 15);
        }
    }
}

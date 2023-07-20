package com.jake.argorithm.baekjoon.lv04_array;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * [ 바구니 뒤집기 ]
 * <p>
 * 도현이는 바구니를 총 N개 가지고 있고,
 * 각각의 바구니에는 1번부터 N번까지 번호가 순서대로 적혀져 있다.
 * 바구니는 일렬로 놓여져 있고,
 * 가장 왼쪽 바구니를 1번째 바구니,
 * 그 다음 바구니를 2번째 바구니, ..., 가장 오른쪽 바구니를 N번째 바구니라고 부른다.
 * <p>
 * 도현이는 앞으로 M번 바구니의 순서를 역순으로 만들려고 한다.
 * 도현이는 한 번 순서를 역순으로 바꿀 때,
 * 순서를 역순으로 만들 범위를 정하고, 그 범위에 들어있는 바구니의 순서를 역순으로 만든다.
 * <p>
 * 바구니의 순서를 어떻게 바꿀지 주어졌을 때,
 * M번 바구니의 순서를 역순으로 만든 다음,
 * 바구니에 적혀있는 번호를 가장 왼쪽 바구니부터 출력하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 N (1 ≤ N ≤ 100)과 M (1 ≤ M ≤ 100)이 주어진다.
 * <p>
 * 둘째 줄부터 M개의 줄에는 바구니의 순서를 역순으로 만드는 방법이 주어진다.
 * 방법은 i j로 나타내고, 왼쪽으로부터 i번째 바구니부터 j번째 바구니의 순서를 역순으로 만든다는 뜻이다. (1 ≤ i ≤ j ≤ N)
 * <p>
 * 도현이는 입력으로 주어진 순서대로 바구니의 순서를 바꾼다.
 * <p>
 * 출력
 * 모든 순서를 바꾼 다음에, 가장 왼쪽에 있는 바구니부터 바구니에 적혀있는 순서를 공백으로 구분해 출력한다.
 * <p>
 * 작성일 : 2023.07.19
 */
class B10811_ReverseBasket {
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    public static void main(String[] args) throws Exception {
        final int size = read();
        final int loop = read();

        String[] baskets = new String[size];

        for (int i = 0; i < baskets.length; i++) baskets[i] = String.valueOf(i + 1);

        for (int i = 0; i < loop; i++) {
            int start = read() - 1;
            int end = read() - 1;

            ArrayList<String> copy = new ArrayList<>();

            for (int j = start; j <= end; j++) {
                copy.add(baskets[j]);
            }

            Collections.reverse(copy);

            int id = 0;
            for (int k = start; k <= end; k++) {
                baskets[k] = copy.get(id++);
            }
        }

        System.out.println(String.join(" ", baskets));
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int n = read2();
        int[] lst = new int[n];
        for (int i = 0; i < n; i++) lst[i] = i + 1;
        int m = read2();
        for (int j = 0; j < m; j++) {
            int a = read2() - 1;
            int b = read2() - 1;
            for (int l = 0; l < (b - a + 1) / 2; l++) {
                int tmp = lst[a + l];
                lst[a + l] = lst[b - l];
                lst[b - l] = tmp;
            }

        }
        for (int k = 0; k < n; k++) sb.append(lst[k]).append(' ');
        System.out.print(sb);

    }
    static int read2() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}

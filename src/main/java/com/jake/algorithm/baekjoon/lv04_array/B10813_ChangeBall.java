package com.jake.algorithm.baekjoon.lv04_array;

import java.io.IOException;
import java.util.Arrays;

/**
 * [ 공 바꾸기 ]
 * <p>
 * 도현이는 바구니를 총 N개 가지고 있고,
 * 각각의 바구니에는 1번부터 N번까지 번호가 매겨져 있다.
 * 바구니에는 공이 1개씩 들어있고, 처음에는 바구니에 적혀있는 번호와 같은 번호가 적힌 공이 들어있다.
 * <p>
 * 도현이는 앞으로 M번 공을 바꾸려고 한다. 도현이는 공을 바꿀 바구니 2개를 선택하고, 두 바구니에 들어있는 공을 서로 교환한다.
 * <p>
 * 공을 어떻게 바꿀지가 주어졌을 때, M번 공을 바꾼 이후에 각 바구니에 어떤 공이 들어있는지 구하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 N (1 ≤ N ≤ 100)과 M (1 ≤ M ≤ 100)이 주어진다.
 * <p>
 * 둘째 줄부터 M개의 줄에 걸쳐서 공을 교환할 방법이 주어진다.
 * 각 방법은 두 정수 i j로 이루어져 있으며, i번 바구니와 j번 바구니에 들어있는 공을 교환한다는 뜻이다. (1 ≤ i ≤ j ≤ N)
 * <p>
 * 도현이는 입력으로 주어진 순서대로 공을 교환한다.
 * <p>
 * 출력
 * 1번 바구니부터 N번 바구니에 들어있는 공의 번호를 공백으로 구분해 출력한다.
 * <p>
 * 작성일 : 2023.07.19
 */
class B10813_ChangeBall {
    public static void main(String[] args) throws IOException {
        final int size = readInt();
        final int loop = readInt();

        String[] basket = new String[size];

        Arrays.setAll(basket, i -> String.valueOf(i + 1));

        for (int i = 0; i < loop; i++) {
            String temp;
            final int first = readInt() - 1;
            final int second = readInt() - 1;

            temp = basket[first];
            basket[first] = basket[second];
            basket[second] = temp;
        }

        System.out.println(String.join(" ", basket));
    }

//    public static void main(String[] args) throws IOException {
//        final int size = readInt();
//        final int loop = readInt();
//
//        int[] basket = new int[size];
//
//        Arrays.setAll(basket, i -> i + 1);
//
//        for (int i = 0; i < loop; i++) {
//            int temp;
//            final int first = readInt() - 1;
//            final int second = readInt() - 1;
//
//            temp = basket[first];
//            basket[first] = basket[second];
//            basket[second] = temp;
//        }
//
//        StringBuilder sb = new StringBuilder();
//
//        for(int e : basket) {
//            sb.append(e).append(" ");
//        }
//
//        System.out.println(sb);
//    }

    private static int readInt() throws IOException {
        int val;
        int total = 0;
        while ((val = System.in.read()) != '\n' && val != ' ') {
            total = total * 10 + (val - '0');
        }

        return total;
    }

    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    public static void main1(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int n = read(), m = read();
        int[] arr = new int[n + 1];
        int temp = 0;

        for (int i = 1; i <= n; i++) arr[i] = i;
        while (m-- > 0) {
            int a = read();
            int b = read();
            temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
        for (int i = 1; i <= n; i++) sb.append(arr[i]).append(" ");
        System.out.print(sb);
    }
}

package com.jake.argorithm.baekjoon.lv04_array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 공넣기 ]
 * <p>
 * 도현이는 바구니를 총 N개 가지고 있고,
 * 각각의 바구니에는 1번부터 N번까지 번호가 매겨져 있다.
 * 또, 1번부터 N번까지 번호가 적혀있는 공을 매우 많이 가지고 있다.
 * 가장 처음 바구니에는 공이 들어있지 않으며, 바구니에는 공을 1개만 넣을 수 있다.
 * <p>
 * 도현이는 앞으로 M번 공을 넣으려고 한다.
 * 도현이는 한 번 공을 넣을 때, 공을 넣을 바구니 범위를 정하고, 정한 바구니에 모두 같은 번호가 적혀있는 공을 넣는다.
 * 만약, 바구니에 공이 이미 있는 경우에는 들어있는 공을 빼고, 새로 공을 넣는다. 공을 넣을 바구니는 연속되어 있어야 한다.
 * <p>
 * 공을 어떻게 넣을지가 주어졌을 때, M번 공을 넣은 이후에 각 바구니에 어떤 공이 들어 있는지 구하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 N (1 ≤ N ≤ 100)과 M (1 ≤ M ≤ 100)이 주어진다.
 * <p>
 * 둘째 줄부터 M개의 줄에 걸쳐서 공을 넣는 방법이 주어진다.
 * 각 방법은 세 정수 i j k로 이루어져 있으며, i번 바구니부터 j번 바구니까지에 k번 번호가 적혀져 있는 공을 넣는다는 뜻이다.
 * 예를 들어, 2 5 6은 2번 바구니부터 5번 바구니까지에 6번 공을 넣는다는 뜻이다. (1 ≤ i ≤ j ≤ N, 1 ≤ k ≤ N)
 * <p>
 * 도현이는 입력으로 주어진 순서대로 공을 넣는다.
 * <p>
 * 출력
 * 1번 바구니부터 N번 바구니에 들어있는 공의 번호를 공백으로 구분해 출력한다. 공이 들어있지 않은 바구니는 0을 출력한다.
 * <p>
 * 작성일 : 2023.07.19
 */
class B10810_PutInBall {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] basket = new int[Integer.parseInt(st.nextToken())];
        int loop = Integer.parseInt(st.nextToken());

        for (int i = 0; i < loop; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());

            for (int j = start - 1; j <= end - 1; j++) {
                basket[j] = ball;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int e : basket) {
            sb.append(e).append(" ");
        }

        System.out.println(sb.toString().trim());
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        final int size = readInt();
        final int count = readInt();

        int[] arr = new int[size];
        for (int i = 0; i < count; i++) {
            final int from = readInt();
            final int until = readInt();
            final int number = readInt();
            for (int j = from - 1; j < until; j++) {
                arr[j] = number;
            }
        }

        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arr[i]).append(" ");
        }

        System.out.print(sb);
    }

    private static int readInt() throws IOException {
        int val = 0;
        int total = 0;

        while ((val = System.in.read()) != '\n' && val != ' ') {
            total = total * 10 + (val - '0');
        }

        return total;
    }
}

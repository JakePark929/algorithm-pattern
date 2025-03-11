package com.jake.algorithm.baekjoon.lv23_greedy_algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 동전 0 ]
 * <p>
 * 준규가 가지고 있는 동전은 총 N 종류이고,
 * 각각의 동전을 매우 많이 가지고 있다.
 * 동전을 적절히 사용해서 그 가치의 합을 K로 만들려고 한다.
 * 이때 필요한 동전 개수의 최솟값을 구하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 N과 K가 주어진다. (1 ≤ N ≤ 10, 1 ≤ K ≤ 100,000,000)
 * 둘째 줄부터 N개의 줄에 동전의 가치 Ai가 오름차순으로 주어진다.
 * (1 ≤ Ai ≤ 1,000,000, A1 = 1, i ≥ 2인 경우에 Ai는 Ai-1의 배수)
 * <p>
 * 출력
 * 첫째 줄에 K원을 만드는데 필요한 동전 개수의 최솟값을 출력한다.
 * <p>
 * 작성일 : 2023.09.12
 */
class B11047_Coin0 {
    public static void main(String[] args) throws Exception {
        int n = read();
        int k = read();

        int[] coin = new int[n];

        for (int i = 0; i < n; i++) {
            coin[i] = read();
        }

        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            // 현재 동전의 가치가 K 보다 작거나 같아야 구성 가능
            if (coin[i] <= k) {
                // 현재 가치의 동전으로 구성할 수 있는 개수를 더해 줌
                count += k / coin[i];
                k = k % coin[i];
            }
        }

        System.out.println(count);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int N = Integer.parseInt(st.nextToken()); // 10
        int money = Integer.parseInt(st.nextToken()); // 4200

        int[] unit = new int[N];
        for (int i = 0; i < N; i++) {
            unit[i] = Integer.parseInt(br.readLine()); // 1,5,10,...
        }

        int n = N - 1;
        int ans = 0;
        while (n >= 0) {
            ans += money / unit[n];
            money %= unit[n--];
        }

        System.out.println(ans);


    }
}

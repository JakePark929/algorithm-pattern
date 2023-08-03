package com.jake.argorithm.baekjoon.lv12_bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * [ 설탕 배달 ]
 *
 * 상근이는 요즘 설탕공장에서 설탕을 배달하고 있다.
 * 상근이는 지금 사탕가게에 설탕을 정확하게 N킬로그램을 배달해야 한다.
 * 설탕공장에서 만드는 설탕은 봉지에 담겨져 있다.
 * 봉지는 3킬로그램 봉지와 5킬로그램 봉지가 있다.
 *
 * 상근이는 귀찮기 때문에, 최대한 적은 봉지를 들고 가려고 한다.
 * 예를 들어,
 * 18킬로그램 설탕을 배달해야 할 때,
 * 3킬로그램 봉지 6개를 가져가도 되지만,
 * 5킬로그램 3개와 3킬로그램 1개를 배달하면,
 * 더 적은 개수의 봉지를 배달할 수 있다.
 *
 * 상근이가 설탕을 정확하게 N킬로그램 배달해야 할 때,
 * 봉지 몇 개를 가져가면 되는지 그 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. (3 ≤ N ≤ 5000)
 *
 * 출력
 * 상근이가 배달하는 봉지의 최소 개수를 출력한다. 만약, 정확하게 N킬로그램을 만들 수 없다면 -1을 출력한다.
 *
 * 작성일 : 2023.08.03
 */
class B2839_SugarDelivery {
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    public static void main(String[] args) throws Exception {
        int n = read();

        ArrayList<Integer> cases = new ArrayList<>();

        int remain;

        for(int i = 0; i <= n / 5; i++) {
            int count3 = 0;
            remain = n - (5 * i);

            if (remain == 0) cases.add(i);

            while (remain >= 3) {
                count3++;
                remain = remain - 3;
                if (remain == 0) {
                    cases.add(i + count3);
                }
            }
        }

        if (cases.size() > 0) {
            System.out.println(Collections.min(cases));
        } else {
            System.out.println(-1);
        }
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        int n = readInt();
        int r = n % 5;
        while (r <= n) {
            if (r % 3 == 0) {    // 3으로 나누어 떨어지면 성공
                System.out.print((n - r) / 5 + r / 3);
                return;
            } else r += 5;
        }
        System.out.print(-1);
    }

    static int readInt() throws IOException {
        int n = 0;
        while (true) {
            int input = System.in.read();
            if (input <= 32)
                return n;
            else
                n = (n << 3) + (n << 1) + (input & 15);
        }
    }
    
    // 다른 사람의 풀이 2 - "강한 골드바흐의 추측 : 2보다 큰 모든 짝수는 두 소수의 합으로 표현 가능하다"
    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        if (N == 4 || N == 7) {
            System.out.println(-1);
        }
        else if (N % 5 == 0) {
            System.out.println(N / 5);
        }
        else if (N % 5 == 1 || N % 5 == 3) {
            System.out.println((N / 5) + 1);
        }
        else if (N % 5 == 2 || N % 5 == 4) {
            System.out.println((N / 5) + 2);
        }
    }
}

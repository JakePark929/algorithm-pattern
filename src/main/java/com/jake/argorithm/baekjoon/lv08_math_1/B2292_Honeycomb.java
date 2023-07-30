package com.jake.argorithm.baekjoon.lv08_math_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ 벌집 ]
 *
 * 위의 그림과 같이 육각형으로 이루어진 벌집이 있다.
 * 그림에서 보는 바와 같이 중앙의 방 1부터 시작해서 이웃하는 방에 돌아가면서 1씩 증가하는 번호를 주소로 매길 수 있다.
 * 숫자 N이 주어졌을 때,
 * 벌집의 중앙 1에서 N번 방까지 최소 개수의 방을 지나서 갈 때 몇 개의 방을 지나가는지(시작과 끝을 포함하여)를 계산하는 프로그램을 작성하시오.
 * 예를 들면, 13까지는 3개, 58까지는 5개를 지난다.
 *
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 1,000,000,000)이 주어진다.
 *
 * 출력
 * 입력으로 주어진 방까지 최소 개수의 방을 지나서 갈 때 몇 개의 방을 지나는지 출력한다.
 *
 * 작성일 : 2023.07.27
 */
class B2292_Honeycomb {
    static long read() throws Exception {
        long c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    public static void main(String[] args) throws Exception {
        long n = read();

        long a = 2;
        long c = 7;
        long count = 1;

        while (a <= n) {
//            System.out.println(a + " ~ " + c + " | " + (count + 1));
            a = a + 6 * count;
            c = c + 6 * (count + 1);
            count++;
        }

        System.out.println(count);
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        int count = 1;
        int tmp = 2;

        while (tmp <= num) {
            tmp += 6 * count;
            count++;
        }

        System.out.println(count);
    }
}

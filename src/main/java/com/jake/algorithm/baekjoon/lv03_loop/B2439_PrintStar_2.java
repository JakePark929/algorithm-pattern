package com.jake.algorithm.baekjoon.lv03_loop;

import java.io.*;

/**
 * [ 별찍기 - 2 ]
 *
 * 첫째 줄에는 별 1개, 둘째 줄에는 별 2개, N번째 줄에는 별 N개를 찍는 문제
 *
 * 하지만, 오른쪽을 기준으로 정렬한 별(예제 참고)을 출력하시오.
 *
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 100)이 주어진다.
 *
 * 출력
 * 첫째 줄부터 N번째 줄까지 차례대로 별을 출력한다.
 *
 * 작성일 : 2023.07.17
 */
public class B2439_PrintStar_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i = 1; i <= n; i++) {
            for(int j = n - i - 1; j >= 0; j--) {
                System.out.print(" ");
            }
            for(int k = 1; k <= i; k++) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int num = Integer.parseInt(br.readLine());
        char[] stars = new char[num];
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < num; i++) {
            stars[i] = ' ';
        }
        for (int i = 0; i < num; i++) {
            stars[num - i - 1] = '*';
            res.append(stars).append('\n');
        }
        bw.write(res.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

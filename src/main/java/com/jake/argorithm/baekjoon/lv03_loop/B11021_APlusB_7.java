package com.jake.argorithm.baekjoon.lv03_loop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ A + B - 7 ]
 * <p>
 * 두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 테스트 케이스의 개수 T가 주어진다.
 * <p>
 * 각 테스트 케이스는 한 줄로 이루어져 있으며, 각 줄에 A와 B가 주어진다. (0 < A, B < 10)
 * <p>
 * 출력
 * 각 테스트 케이스마다 "Case #x: "를 출력한 다음, A+B를 출력한다. 테스트 케이스 번호는 1부터 시작한다.
 * <p>
 * 작성일 : 2023.07.17
 */
class B11021_APlusB_7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < testCase; i++) {
            String str = br.readLine();
            int target = str.indexOf(" ");
            int result = Integer.parseInt(str.substring(0, target)) + Integer.parseInt(str.substring(target + 1));
            sb.append("Case #").append(i + 1).append(": ").append(result).append("\n");
        }

        br.close();

        System.out.println(sb);
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스의 수 T를 받아서 tc = 1부터 T까지 for 문을 돌도록 하자.
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            char[] info = br.readLine().toCharArray();

            sb.append("Case #").append(tc).append(": ");
            sb.append((info[0] + info[2] - 96));
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}

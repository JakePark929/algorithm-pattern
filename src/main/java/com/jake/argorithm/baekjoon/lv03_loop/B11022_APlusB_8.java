package com.jake.argorithm.baekjoon.lv03_loop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ A + B - 8 ]
 * <p>
 * 두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 테스트 케이스의 개수 T가 주어진다.
 * <p>
 * 각 테스트 케이스는 한 줄로 이루어져 있으며, 각 줄에 A와 B가 주어진다. (0 < A, B < 10)
 * <p>
 * 출력
 * 각 테스트 케이스마다 "Case #x: A + B = C" 형식으로 출력한다. x는 테스트 케이스 번호이고 1부터 시작하며, C는 A+B이다.
 * <p>
 * 작성일 : 2023.07.17
 */
class B11022_APlusB_8 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        for (int i = 1; i <= tc; i++) {
            char[] info = br.readLine().toCharArray();
            sb.append("Case #").append(i).append(": ")
                    .append(info[0]).append(" + ").append(info[2]).append(" = ")
                    .append(Character.getNumericValue(info[0]) + Character.getNumericValue(info[2]))
                    .append('\n');
        }

        System.out.println(sb);
        br.close();
    }

    // 다른 사람의 풀이 1
    public static final String NEW_LINE = "\n";

    public static void main1(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int times = Integer.parseInt(br.readLine());

        for (int i = 0; i < times; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int sum = a + b;
            sb.append("Case #").append(i + 1).append(": ").append(a).append(" + ").append(b).append(" = ").append(sum).append(NEW_LINE);
        }

        System.out.println(sb.toString());

    }
}

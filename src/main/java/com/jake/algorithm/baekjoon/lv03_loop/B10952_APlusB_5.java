package com.jake.algorithm.baekjoon.lv03_loop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ A + B - 5]
 * <p>
 * 두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 입력은 여러 개의 테스트 케이스로 이루어져 있다.
 * <p>
 * 각 테스트 케이스는 한 줄로 이루어져 있으며, 각 줄에 A와 B가 주어진다. (0 < A, B < 10)
 * <p>
 * 입력의 마지막에는 0 두 개가 들어온다.
 * <p>
 * 출력
 * 각 테스트 케이스마다 A+B를 출력한다.
 * <p>
 * 작성일 : 2023.07.17
 */
class B10952_APlusB_5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String str = br.readLine();
            int target = str.indexOf(" ");
            int a = Integer.parseInt(str.substring(0, target));
            int b = Integer.parseInt(str.substring(target + 1));
            if (a == 0 && b == 0) {
                break;
            }
            int result = a + b;
            sb.append(result).append('\n');
        }

        System.out.println(sb);
        br.close();
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        int a, b;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            if (line.length() == 0)
                break;
            a = line.charAt(0) - '0';
            b = line.charAt(2) - '0';
            if (a == 0 && b == 0)
                break;
            sb.append(a + b).append("\n");
        }
        System.out.print(sb);
    }
}

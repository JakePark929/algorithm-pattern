package com.jake.algorithm.baekjoon.lv05_string;

import java.io.*;

/**
 * [ 문자열 반복 ]
 * <p>
 * 문자열 S를 입력받은 후에,
 * 각 문자를 R번 반복해 새 문자열 P를 만든 후 출력하는 프로그램을 작성하시오.
 * 즉, 첫 번째 문자를 R번 반복하고, 두 번째 문자를 R번 반복하는 식으로 P를 만들면 된다.
 * S에는 QR Code "alphanumeric" 문자만 들어있다.
 * <p>
 * QR Code "alphanumeric" 문자는 0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ\$%*+-./: 이다.
 * <p>
 * 입력
 * 첫째 줄에 테스트 케이스의 개수 T(1 ≤ T ≤ 1,000)가 주어진다.
 * 각 테스트 케이스는 반복 횟수 R(1 ≤ R ≤ 8), 문자열 S가 공백으로 구분되어 주어진다.
 * S의 길이는 적어도 1이며, 20글자를 넘지 않는다.
 * <p>
 * 출력
 * 각 테스트 케이스에 대해 P를 출력한다.
 * <p>
 * 작성일 : 2023.07.20
 */
class B2675_StringLoop {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String s = br.readLine();
            int repeat = Integer.parseInt(s.substring(0, s.indexOf(" ")));
            char[] cs = s.substring(s.indexOf(" ") + 1).toCharArray();

            StringBuilder sb = new StringBuilder();

            for (char c : cs) {
                sb.append(String.valueOf(c).repeat(Math.max(0, repeat)));
            }

            System.out.println(sb);
        }
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter print = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(input.readLine());

        for (int i = 0; i < T; i++) {
            String[] str = input.readLine().split(" ");
            int R = Integer.parseInt(str[0]);
            char[] ch = str[1].toCharArray();

            for (char c : ch) {
                for (int k = 0; k < R; k++) {
                    print.write(c);
                }
            }
            print.write("\n");
        }

        print.flush();
    }
}

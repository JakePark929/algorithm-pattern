package com.jake.algorithm.baekjoon.lv19_recursion;

import java.io.*;
import java.util.Arrays;

/**
 * [ 칸토어 집합 ]
 * <p>
 * 칸토어 집합은 0과 1사이의 실수로 이루어진 집합으로,
 * 구간 [0, 1]에서 시작해서 각 구간을 3등분하여 가운데 구간을 반복적으로 제외하는 방식으로 만든다.
 * 전체 집합이 유한이라고 가정하고, 다음과 같은 과정을 통해서 칸토어 집합의 근사를 만들어보자.
 * <p>
 * 1. -가 3N개 있는 문자열에서 시작한다.
 * 2. 문자열을 3등분 한 뒤, 가운데 문자열을 공백으로 바꾼다. 이렇게 하면, 선(문자열) 2개가 남는다.
 * 3. 이제 각 선(문자열)을 3등분 하고, 가운데 문자열을 공백으로 바꾼다. 이 과정은 모든 선의 길이가 1일때 까지 계속 한다.
 * <p>
 * 예를 들어, N=3인 경우, 길이가 27인 문자열로 시작한다.
 * ---------------------------
 * <p>
 * 여기서 가운데 문자열을 공백으로 바꾼다.
 * ---------         ---------
 * <p>
 * 남은 두 선의 가운데 문자열을 공백으로 바꾼다.
 * ---   ---         ---   ---
 * <p>
 * 한번 더
 * - -   - -         - -   - -
 * <p>
 * 모든 선의 길이가 1이면 멈춘다. N이 주어졌을 때, 마지막 과정이 끝난 후 결과를 출력하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 입력을 여러 줄로 이루어져 있다.
 * 각 줄에 N이 주어진다.
 * 파일의 끝에서 입력을 멈춘다.
 * N은 0보다 크거나 같고, 12보다 작거나 같은 정수이다.
 * <p>
 * 출력
 * 입력으로 주어진 N에 대해서, 해당하는 칸토어 집합의 근사를 출력한다.
 * <p>
 * 작성일 : 2023.08.28
 */
public class B4779_CantorianSet {
    static int n;
    static StringBuilder answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str;
        while ((str = br.readLine()) != null) {
            n = Integer.parseInt(str);
            answer = new StringBuilder();
            int length = (int) Math.pow(3, n);
            answer.append("-".repeat(length));

            cantorianSet(0, length);

            System.out.println(answer);
        }
    }

    private static void cantorianSet(int start, int size) {
        if (size == 1) {
            return;
        }

        int newSize = size / 3;

        for (int i = start + newSize; i < start + (newSize * 2); i++) {
            answer.setCharAt(i, ' ');
        }

        cantorianSet(start, newSize);
        cantorianSet(start + (newSize * 2), newSize);
    }

    // 다른 사람의 풀이 1
    private static String[] memo;

    public static void main1(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        memo = new String[13];
        memo[0] = "-";
        StringBuilder sb = new StringBuilder();

        for (String s = br.readLine(); s != null && !s.isEmpty(); s = br.readLine()) {
            int n = Integer.parseInt(s);
            sb.append(recurse(n)).append("\n");
        }

        br.close();
        System.out.print(sb);
    }

    private static String recurse(int n) {
        if (memo[n] != null) {
            return memo[n];
        }

        String previous = recurse(n - 1);
        String current = previous.concat(" ".repeat(previous.length())).concat(previous);
        memo[n] = current;
        return current;
    }

    // 다른 사람의 풀이 2
    public static void main2(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input;
        String str;
        StringBuilder sb;
        StringBuilder result = new StringBuilder();
        while ((input = bf.readLine()) != null) {
            int N = Integer.parseInt(input);
            if (N == -1)
                break;
            sb = new StringBuilder();
            sb.append("-");
            str = sb.toString();
            for (int j = 0; j < N; j++) {
                sb.append(" ".repeat(str.length())).append(str);
                str = sb.toString();
            }
            result.append(str).append("\n");
        }
        bf.close();
        System.out.print(result);
    }

    // 다른 사람의 풀이 3
    public static int n1;
    public static int length;
    public static char[] chars;
    public static String input;

    public static void main3(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while ((input = br.readLine()) != null) {
            n1 = Integer.parseInt(input);
            length = (int) Math.pow(3, n1);
            chars = new char[length];
            Arrays.fill(chars, '-');
            cantor(0, length);
            bw.write(chars);
            bw.write('\n');
            bw.flush();
        }
    }

    public static void cantor(int start, int length) {
        if (length == 1) {
            return;
        }
        length /= 3;
        Arrays.fill(chars, start + length, start + length * 2, ' ');
        cantor(start, length);
        cantor(start + length * 2, length);
    }
}

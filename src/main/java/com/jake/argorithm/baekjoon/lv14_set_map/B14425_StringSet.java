package com.jake.argorithm.baekjoon.lv14_set_map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * [ 문자열 집합 ]
 *
 * 총 N개의 문자열로 이루어진 집합 S가 주어진다.
 * 입력으로 주어지는 M개의 문자열 중에서 집합 S에 포함되어 있는 것이 총 몇 개인지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 문자열의 개수 N과 M (1 ≤ N ≤ 10,000, 1 ≤ M ≤ 10,000)이 주어진다.
 * 다음 N개의 줄에는 집합 S에 포함되어 있는 문자열들이 주어진다.
 * 다음 M개의 줄에는 검사해야 하는 문자열들이 주어진다.
 * 입력으로 주어지는 문자열은 알파벳 소문자로만 이루어져 있으며, 길이는 500을 넘지 않는다.
 * 집합 S에 같은 문자열이 여러 번 주어지는 경우는 없다.
 *
 * 출력
 * 첫째 줄에 M개의 문자열 중에 총 몇 개가 집합 S에 포함되어 있는지 출력한다.
 *
 * 작성일 : 2023.08.07
 */
class B14425_StringSet {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s.substring(0, s.indexOf(" ")));
        int m = Integer.parseInt(s.substring(s.indexOf(" ") + 1));

        HashMap<String, String> strings = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String e = br.readLine();
            strings.put(e, e);
        }

        int count = 0;

        for(int i = 0; i < m; i++) {
            String e = br.readLine();

            if(strings.containsKey(e)) count++;
        }

        System.out.println(count);
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws Exception {

        int N = nextInt();
        int M = nextInt();

        Set<String> set = new HashSet<>();
        while (N-- > 0) set.add(next());

        int count = 0;
        while (M-- > 0) if (set.contains(next())) count++;

        System.out.print(count);

    }

    static final int SIZE = 1 << 13;
    static byte[] buffer = new byte[SIZE];
    static byte[] string = new byte[500];
    static int index, size;

    static int nextInt() throws Exception {
        int n = 0;
        byte c;
        while ((c = read()) <= 32);
        do n = (n << 3) + (n << 1) + (c & 15);
        while ((c = read()) > 47 && c < 58);
        return n;
    }

    static String next() throws Exception {
        while (read() <= 32);
        int i = 0;
        do string[i++] = buffer[index - 1];
        while (read() > 64);
        return new String(string, 0, i);
    }

    static byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}

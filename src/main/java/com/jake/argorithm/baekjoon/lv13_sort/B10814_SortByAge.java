package com.jake.argorithm.baekjoon.lv13_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * [ 나이순 정렬 ]
 * <p>
 * 온라인 저지에 가입한 사람들의 나이와 이름이 가입한 순서대로 주어진다.
 * 이때, 회원들을 나이가 증가하는 순으로,
 * 나이가 같으면 먼저 가입한 사람이 앞에 오는 순서로 정렬하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 온라인 저지 회원의 수 N이 주어진다. (1 ≤ N ≤ 100,000)
 * <p>
 * 둘째 줄부터 N개의 줄에는 각 회원의 나이와 이름이 공백으로 구분되어 주어진다.
 * 나이는 1보다 크거나 같으며, 200보다 작거나 같은 정수이고,
 * 이름은 알파벳 대소문자로 이루어져 있고, 길이가 100보다 작거나 같은 문자열이다.
 * 입력은 가입한 순서로 주어진다.
 * <p>
 * 출력
 * 첫째 줄부터 총 N개의 줄에 걸쳐 온라인 저지 회원을 나이 순,
 * 나이가 같으면 가입한 순으로 한 줄에 한 명씩 나이와 이름을 공백으로 구분해 출력한다.
 * <p>
 * 작성일 : 2023.08.04
 */
class B10814_SortByAge {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[][] members = new String[n][3];

        StringTokenizer st;
        int count = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            members[i][0] = st.nextToken();
            members[i][1] = st.nextToken();
            members[i][2] = String.valueOf(++count);
        }

        Arrays.sort(members, (e1, e2) -> {
            if (Objects.equals(e1[0], e2[0])) {
                return Integer.parseInt(e1[2]) - Integer.parseInt(e2[2]);
            } else {
                return Integer.parseInt(e1[0]) - Integer.parseInt(e2[0]);
            }
        });

        StringBuilder sb = new StringBuilder();
        for (String[] member : members) {
            sb.append(member[0]).append(" ").append(member[1]).append('\n');
        }

        System.out.println(sb.toString().trim());
    }

    // 다른 사람의 풀이 1
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            if (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        Integer nextInt() {
            return Integer.parseInt(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        public void close() {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static FastReader sc = new FastReader();

    public static void main1(String... args) throws IOException {
        StringBuilder[] sbs = new StringBuilder[201];
        int n = sc.nextInt();
        String s;

        for (int i = 0; i < 201; i++) {
            sbs[i] = new StringBuilder();
        }

        while (n-- > 0) {
            s = sc.nextLine();
            sbs[Integer.parseInt(new StringTokenizer(s).nextToken())].append(s).append("\n");
        }
        sc.close();

        StringBuilder sb = new StringBuilder();
        for (StringBuilder i : sbs) {
            sb.append(i);
        }
        System.out.println(sb);
    }

    // 다른 사람의 풀이 2
    public static void main2(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 입력되는 나이의 범위 : 1 ~ 200
        StringBuilder[] p = new StringBuilder[201];

        //객체배열의 인덱스에 각 StringBuilder 객체를 생성해준다.
        for(int i = 0; i < p.length; i++) {
            p[i] = new StringBuilder();
        }

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            // 카운팅 정렬 : 나이를 index 로 하여 해당 배열에 나이와 이름을 append() 한다
            p[age].append(age).append(' ').append(name).append('\n');
        }

        StringBuilder sb = new StringBuilder();
        for(StringBuilder val : p) {
            sb.append(val);
        }

        System.out.println(sb);
    }
}

package com.jake.algorithm.a01_lecture.fc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 고냥이 ]
 *
 * 문제
 * 고양이는 너무 귀엽다.
 * 사람들은 고양이를 너무 귀여워했고, 결국 고양이와 더욱 가까워지고 싶어 고양이와의 소통을 위한 고양이 말 번역기를 발명하기로 했다.
 * 이 번역기는 사람의 언어를 고양이의 언어로, 고양이의 언어를 사람의 언어로 바꾸어 주는 희대의 발명품이 될 것이다.
 *
 * 현재 고양이말 번역기의 베타버전이 나왔다.
 * 그러나 이 베타버전은 완전 엉망진창이다.
 * 베타버전의 번역기는 문자열을 주면 그 중에서 최대 N개의 종류의 알파벳을 가진 연속된 문자열밖에 인식하지 못한다.
 * 굉장히 별로지만 그나마 이게 최선이라고 사람들은 생각했다.
 * 그리고 문자열이 주어졌을 때 이 번역기가 인식할 수 있는 최대 문자열의 길이는 얼마인지가 궁금해졌다.
 * 고양이와 소통할 수 있도록 우리도 함께 노력해보자.
 *
 * 입력
 * 첫째 줄에는 인식할 수 있는 알파벳의 종류의 최대 개수 N이 입력된다. (1 < N ≤ 26)
 *
 * 둘째 줄에는 문자열이 주어진다. (1 ≤ 문자열의 길이 ≤ 100,000) 단, 문자열에는 알파벳 소문자만이 포함된다.
 *
 * 출력
 * 첫째 줄에 번역기가 인식할 수 있는 문자열의 최대길이를 출력한다.
 *
 * 작성일 : 2026.03.03
 */
public class P3_B16472_Meow {
    static FastReader scan = new FastReader();

    static int N, kind;
    static String A;
    static int[] count;

    static void input() {
        N = scan.nextInt();
        A = scan.nextLine();
        count = new int[26];
    }

    static void add(char x) {
        count[x - 'a']++;

        if (count[x - 'a'] == 1) {
            kind++;
        }
    }

    static void erase(char x) {
        count[x - 'a']--;

        if (count[x - 'a'] == 0) {
            kind--;
        }
    }

    static void process() {
        int length = A.length(), answer = 0;

        for (int R = 0, L = 0; R < length; R++) {
            // R 번째 문자를 오른쪽에 추가
            add(A.charAt(R));

            // 불가능하면, 가능할 때 까지 L을 이동
            while (kind > N) {
                erase(A.charAt(L++));
            }

            answer = Math.max(answer, R - L + 1);
        }

        System.out.println(answer);
    }

    public static void main(String[] args) {
        input();

        process();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
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
    }
}

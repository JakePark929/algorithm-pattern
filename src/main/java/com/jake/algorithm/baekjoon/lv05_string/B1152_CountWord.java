package com.jake.algorithm.baekjoon.lv05_string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 단어의 개수 ]
 *
 * 영어 대소문자와 공백으로 이루어진 문자열이 주어진다.
 * 이 문자열에는 몇 개의 단어가 있을까? 이를 구하는 프로그램을 작성하시오.
 * 단, 한 단어가 여러 번 등장하면 등장한 횟수만큼 모두 세어야 한다.
 *
 * 입력
 * 첫 줄에 영어 대소문자와 공백으로 이루어진 문자열이 주어진다.
 * 이 문자열의 길이는 1,000,000을 넘지 않는다.
 * 단어는 공백 한 개로 구분되며, 공백이 연속해서 나오는 경우는 없다.
 * 또한 문자열은 공백으로 시작하거나 끝날 수 있다.
 *
 * 출력
 * 첫째 줄에 단어의 개수를 출력한다.
 *
 * 작성일 : 2023.07.20
 */
class B1152_CountWord {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        System.out.println(st.countTokens());
    }

    // 다른 사람의 풀이 1
    static final byte A32 = (byte)32;
    public static void main1(String[] args) throws IOException{
        InputStream is = System.in;
        byte[] buf = new byte[1000002];
        is.read(buf);

        int cnt = 0;

        for(int i=1; ; i++){
            if(buf[i]==A32) cnt++;
            else if(buf[i]==10) {
                if(buf[i-1]!=32) cnt++;
                break;
            }
        }

        System.out.print(cnt);
    }

    // 다른 사람의 풀이 2
    public static void main2(String[] args) throws IOException {
        int a = 0;
        int b = 32;
        int i;

        while(true) {
            i = System.in.read();

            if(i == 32) {
                if(b != 32) a++;
            }

            else if(i == 10) {
                if(b != 32) a++;
                break;
            }

            b = i;
        }

        System.out.println(a);
    }
}

package com.jake.algorithm.baekjoon.lv23_greedy_algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 잃어버린 괄호 ]
 *
 * 세준이는 양수와 +, -, 그리고 괄호를 가지고 식을 만들었다.
 * 그리고 나서 세준이는 괄호를 모두 지웠다.
 * 그리고 나서 세준이는 괄호를 적절히 쳐서 이 식의 값을 최소로 만들려고 한다.
 * 괄호를 적절히 쳐서 이 식의 값을 최소로 만드는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 식이 주어진다. 식은 ‘0’~‘9’, ‘+’, 그리고 ‘-’만으로 이루어져 있고, 가장 처음과 마지막 문자는 숫자이다.
 * 그리고 연속해서 두 개 이상의 연산자가 나타나지 않고, 5자리보다 많이 연속되는 숫자는 없다.
 * 수는 0으로 시작할 수 있다.
 * 입력으로 주어지는 식의 길이는 50보다 작거나 같다.
 *
 * 출력
 * 첫째 줄에 정답을 출력한다.
 *
 * 작성일 : 2023.09.12
 */
class B1541_LostBracket {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sum = Integer.MAX_VALUE;
        StringTokenizer subtraction = new StringTokenizer(br.readLine(), "-");

        while (subtraction.hasMoreTokens()) {
            int temp = 0;

            StringTokenizer addition = new StringTokenizer(subtraction.nextToken(), "+");

            while (addition.hasMoreTokens()) {
                temp += Integer.parseInt(addition.nextToken());
            }

            if (sum == Integer.MAX_VALUE) {
                sum = temp;
            } else {
                sum -= temp;
            }
        }

        System.out.println(sum);
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sum = Integer.MAX_VALUE;	// 초기 상태 여부 확인을 위한 값으로 설정
        String[] subtraction = br.readLine().split("-");

        for (String value : subtraction) {
            int temp = 0;

            // 뺄셈으로 나뉜 토큰을 덧셈으로 분리하여 해당 토큰들을 더한다.
            String[] addition = value.split("\\+");

            // 덧셈으로 나뉜 토큰들을 모두 더한다.
            for (String s : addition) {
                temp += Integer.parseInt(s);
            }

            // 첫 번째토큰인 경우 temp 값이 첫 번째 수가 됨
            if (sum == Integer.MAX_VALUE) {
                sum = temp;
            } else {
                sum -= temp;
            }
        }
        System.out.println(sum);
    }

    // 다른 사람의 풀이 2
    static int[] arr = new int[25];
    static int cnt = 0, idx = 25;

    public static void main2(String[] args) throws Exception {
        read();

        int pos = 0, neg = 0;
        for(int i = 0; i < cnt; i++) {
            if(i >= idx) neg += arr[i];
            else pos += arr[i];
        }

        System.out.println(pos - neg);
    }

    static void read() throws Exception {
        int c, n = System.in.read() & 15;

        while(true) {
            if((c = System.in.read()) > 45) {
                n = (n << 3) + (n << 1) + (c & 15);
            } else {
                arr[cnt++] = n;
                if(c < 43) break;

                if((c & 15) == 13) idx = Math.min(cnt, idx);
                n = System.in.read() & 15;
            }
        }
    }
}

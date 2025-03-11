package com.jake.algorithm.baekjoon.lv11_time_complexity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 알고리즘 수업 - 점근적 표기 1 ]
 * <p>
 * 오늘도 서준이는 점근적 표기 수업 조교를 하고 있다.
 * 아빠가 수업한 내용을 학생들이 잘 이해했는지 문제를 통해서 확인해보자.
 * <p>
 * 알고리즘의 소요 시간을 나타내는 O-표기법(빅-오)을 다음과 같이 정의하자.
 * <p>
 * O(g(n)) = {f(n) | 모든 n ≥ n0에 대하여 f(n) ≤ c × g(n)인 양의 상수 c와 n0가 존재한다}
 * <p>
 * 이 정의는 실제 O-표기법(<a href="https://en.wikipedia.org/wiki/Big_O_notation">...</a>)과 다를 수 있다.
 * 함수 f(n) = a1n + a0, 양의 정수 c, n0가 주어질 경우 O(n) 정의를 만족하는지 알아보자.
 * <p>
 * 입력
 * 첫째 줄에 함수 f(n)을 나타내는 정수 a1, a0가 주어진다. (0 ≤ |ai| ≤ 100)
 * 다음 줄에 양의 정수 c가 주어진다. (1 ≤ c ≤ 100)
 * 다음 줄에 양의 정수 n0가 주어진다. (1 ≤ n0 ≤ 100)
 * <p>
 * 출력
 * f(n), c, n0가 O(n) 정의를 만족하면 1, 아니면 0을 출력한다.
 * <p>
 * 작성일 : 2023.08.01
 */
class B24313_AsymptoticNotation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int a1 = Integer.parseInt(st.nextToken());
        int a0 = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        boolean isSatisfy = false;

        for(int i = n; i <= 100; i++) {
            if (a1 * i + a0 <= c * i) {
                isSatisfy = true;
            } else {
                isSatisfy = false;
                break;
            }
        }

        System.out.println(isSatisfy ? 1 : 0);
    }
}

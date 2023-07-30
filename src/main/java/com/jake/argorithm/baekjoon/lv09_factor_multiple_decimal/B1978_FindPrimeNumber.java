package com.jake.argorithm.baekjoon.lv09_factor_multiple_decimal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 소수 찾기 ]
 *
 * 주어진 수 N개 중에서 소수가 몇 개인지 찾아서 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫 줄에 수의 개수 N이 주어진다.
 * N은 100이하이다.
 * 다음으로 N개의 수가 주어지는데 수는 1,000 이하의 자연수이다.
 *
 * 출력
 * 주어진 수들 중 소수의 개수를 출력한다.
 *
 * 작성일 : 2023.07.28
 */
class B1978_FindPrimeNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int count = 0;
        for(int i = 0; i < n; i++) {
            int check = Integer.parseInt(st.nextToken());
            if(isPrime(check)) {
                count++;
            }
        }

        System.out.println(count);
    }

    private static Boolean isPrime(int n){
        int cnt = 0;
        if(n == 1) {	// 1 인경우 다음 반복문으로
            return false;
        }
        for(int i = 1; i <= (int)Math.sqrt(n); i ++){
            if(n % i == 0) cnt += 1;
        }
        return cnt == 1;
    }
}

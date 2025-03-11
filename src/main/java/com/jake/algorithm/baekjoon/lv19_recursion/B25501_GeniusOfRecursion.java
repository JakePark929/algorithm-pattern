package com.jake.algorithm.baekjoon.lv19_recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ 재귀의 귀재 ]
 * <p>
 * 정휘는 후배들이 재귀 함수를 잘 다루는 재귀의 귀재인지 알아보기 위해 재귀 함수와 관련된 문제를 출제하기로 했다.
 * 팰린드롬이란, 앞에서부터 읽었을 때와 뒤에서부터 읽었을 때가 같은 문자열을 말한다.
 * 팰린드롬의 예시로 AAA, ABBA, ABABA 등이 있고, 팰린드롬이 아닌 문자열의 예시로 ABCA, PALINDROME 등이 있다.
 * <p>
 * 어떤 문자열이 팰린드롬인지 판별하는 문제는 재귀 함수를 이용해 쉽게 해결할 수 있다.
 * 아래 코드의 isPalindrome 함수는 주어진 문자열이 팰린드롬이면 1, 팰린드롬이 아니면 0을 반환하는 함수다.
 * <p>
 * #include <stdio.h>
 * #include <string.h>
 * <p>
 * int recursion(const char *s, int l, int r){
 * if(l >= r) return 1;
 * else if(s[l] != s[r]) return 0;
 * else return recursion(s, l+1, r-1);
 * }
 * <p>
 * int isPalindrome(const char *s){
 * return recursion(s, 0, strlen(s)-1);
 * }
 * <p>
 * int main(){
 * printf("ABBA: %d\n", isPalindrome("ABBA")); // 1
 * printf("ABC: %d\n", isPalindrome("ABC"));   // 0
 * }
 * <p>
 * 정휘는 위에 작성된 isPalindrome 함수를 이용하여 어떤 문자열이 팰린드롬인지 여부를 판단하려고 한다.
 * <p>
 * 구체적으로는, 문자열 $S$를 isPalindrome 함수의 인자로 전달하여 팰린드롬 여부를 반환값으로 알아낼 것이다.
 * 더불어 판별하는 과정에서 recursion 함수를 몇 번 호출하는지 셀 것이다.
 * 정휘를 따라 여러분도 함수의 반환값과 recursion 함수의 호출 횟수를 구해보자.
 * <p>
 * 입력
 * 첫째 줄에 테스트케이스의 개수 $T$가 주어진다.
 * ($1 \leq T \leq 1\,000$)
 * 둘째 줄부터 $T$개의 줄에 알파벳 대문자로 구성된 문자열 $S$가 주어진다.
 * ($1 \leq \vert S\vert \leq 1\,000$)
 * <p>
 * 출력
 * 각 테스트케이스마다, isPalindrome 함수의 반환값과
 * recursion 함수의 호출 횟수를 한 줄에 공백으로 구분하여 출력한다.
 * <p>
 * 작성일 : 2023.08.28
 */
class B25501_GeniusOfRecursion {
    static int count = 0;
    public static int recursion(String s, int l, int r) {
        count++;
        if (l >= r) return 1;
        else if (s.charAt(l) != s.charAt(r)) return 0;
        else return recursion(s, l + 1, r - 1);
    }

    public static int isPalindrome(String s) {
        return recursion(s, 0, s.length() - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            String s = br.readLine();
            sb.append(isPalindrome(s)).append(" ").append(count).append('\n');
            count = 0;
        }

        System.out.println(sb);
    }
}

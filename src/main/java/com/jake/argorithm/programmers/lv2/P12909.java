package com.jake.argorithm.programmers.lv2;

import java.util.Stack;

/**
 * [ 올바른 괄호 ]
 * <p>
 * 괄호가 바르게 짝지어졌다는 것은 '(' 문자로 열렸으면 반드시 짝지어서 ')' 문자로 닫혀야 한다는 뜻입니다.
 * <p>
 * 예를 들어
 * <p>
 * "()()" 또는 "(())()" 는 올바른 괄호입니다.
 * ")()(" 또는 "(()(" 는 올바르지 않은 괄호입니다.
 * '(' 또는 ')' 로만 이루어진 문자열 s가 주어졌을 때,
 * 문자열 s가 올바른 괄호이면 true를 return 하고,
 * 올바르지 않은 괄호이면 false를 return 하는 solution 함수를 완성해 주세요.
 * <p>
 * 제한사항
 * 문자열 s의 길이 : 100,000 이하의 자연수
 * 문자열 s는 '(' 또는 ')' 로만 이루어져 있습니다.
 * <p>
 * 작성일 : 2023.07.07
 */
class P12909 {
    boolean solution(String s) {
        int leftBracket = 0;
        int rightBracket = 0;

        if (s.lastIndexOf("(") > s.lastIndexOf(")")) {
            return false;
        } else if (s.indexOf("(") > s.indexOf(")")) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            if (leftBracket == rightBracket) {
                if (s.charAt(i) == ')') {
                    return false;
                }
            }
            if (s.charAt(i) == '(') {
                leftBracket++;
            } else if (s.charAt(i) == ')') {
                rightBracket++;
            }
        }

        return leftBracket == rightBracket;
    }

    // 다른 사람의 풀이 1
    boolean solution1(String s) {
        boolean answer = false;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            }
            if (s.charAt(i) == ')') {
                count--;
            }
            if (count < 0) {
                break;
            }
        }
        if (count == 0) {
            answer = true;
        }

        return answer;
    }

    // 다른 사람의 풀이 2 - 스택사용!
    boolean solution2(String s) {
        boolean answer = true;
        String res = "YES";
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push(1);
            } else if (s.charAt(i) == ')') {
                if (st.isEmpty()) {
                    answer = false;
                    break;
                } else {
                    st.pop();
                }
            }
        }

        if(!st.isEmpty()) {

            answer = false;

        }

        return answer;
    }


    public static void main(String[] args) {
        P12909 problem = new P12909();
        System.out.println(problem.solution("(()))(()"));
    }
}

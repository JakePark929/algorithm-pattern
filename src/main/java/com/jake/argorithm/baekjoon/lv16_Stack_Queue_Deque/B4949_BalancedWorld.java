package com.jake.argorithm.baekjoon.lv16_Stack_Queue_Deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * [ 균형잡힌 세상 ]
 * <p>
 * 세계는 균형이 잘 잡혀있어야 한다.
 * 양과 음, 빛과 어둠 그리고 왼쪽 괄호와 오른쪽 괄호처럼 말이다.
 * <p>
 * 정민이의 임무는 어떤 문자열이 주어졌을 때, 괄호들의 균형이 잘 맞춰져 있는지 판단하는 프로그램을 짜는 것이다.
 * <p>
 * 문자열에 포함되는 괄호는 소괄호("()") 와 대괄호("[]")로 2종류이고, 문자열이 균형을 이루는 조건은 아래와 같다.
 * <p>
 * 모든 왼쪽 소괄호("(")는 오른쪽 소괄호(")")와만 짝을 이뤄야 한다.
 * 모든 왼쪽 대괄호("[")는 오른쪽 대괄호("]")와만 짝을 이뤄야 한다.
 * 모든 오른쪽 괄호들은 자신과 짝을 이룰 수 있는 왼쪽 괄호가 존재한다.
 * 모든 괄호들의 짝은 1:1 매칭만 가능하다. 즉, 괄호 하나가 둘 이상의 괄호와 짝지어지지 않는다.
 * 짝을 이루는 두 괄호가 있을 때, 그 사이에 있는 문자열도 균형이 잡혀야 한다.
 * 정민이를 도와 문자열이 주어졌을 때 균형잡힌 문자열인지 아닌지를 판단해보자.
 * <p>
 * 입력
 * 각 문자열은 마지막 글자를 제외하고 영문 알파벳, 공백, 소괄호("( )"), 대괄호("[ ]")로 이루어져 있으며,
 * 온점(".")으로 끝나고, 길이는 100글자보다 작거나 같다.
 * 입력의 종료조건으로 맨 마지막에 온점 하나(".")가 들어온다.
 * <p>
 * 출력
 * 각 줄마다 해당 문자열이 균형을 이루고 있으면 "yes"를, 아니면 "no"를 출력한다.
 * <p>
 * 작성일 : 2023.08.23
 */
class B4949_BalancedWorld {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String sentence;
        while (!(sentence = br.readLine()).equals(".")) {
            sb.append(solution(sentence)).append('\n');
        }

        System.out.println(sb);
    }

    public static String solution(String sentence) {
        Stack<Character> pss = new Stack<>();

        for (char c : sentence.toCharArray()) {
            if (pss.size() == 0 && c == ')' || pss.size() == 0 && c == ']') {
                return "no";
            }

            if (c == ')') {
                if (pss.peek() != '(') {
                    return "no";
                }
                pss.pop();
            }

            if (c == ']') {
                if (pss.peek() != '[') {
                    return "no";
                }
                pss.pop();
            }

            if (c == '(') {
                pss.push(c);
            }

            if (c == '[') {
                pss.push(c);
            }
        }

        if (pss.size() == 0) {
            return "yes";
        } else return "no";
    }

    // 다른 사람의 풀이 1
    static final int POINT = 46;
    static final int LEFT_PARENTHESIS = 40, RIGHT_PARENTHESIS = 41;
    static final int LEFT_BRACE = 91, RIGHT_BRACE = 93;
    static final String YES = "yes\n", NO = "no\n";

    public static void main1(String[] args) throws Exception {
        int[] input = new int[100];
        int[] stack = new int[101];
        StringBuilder sb = new StringBuilder();

        while (true) {
            int c, len = 0, top = 0;

            while ((c = System.in.read()) > 13) input[len++] = c;

            if (len == 1 && input[0] == POINT) break;

            for (int i = 0; i < len; i++) {
                if (input[i] == LEFT_PARENTHESIS || input[i] == LEFT_BRACE)
                    stack[++top] = input[i];
                else if (input[i] == RIGHT_PARENTHESIS)
                    if (stack[top] == LEFT_PARENTHESIS) top--;
                    else top = -1;
                else if (input[i] == RIGHT_BRACE)
                    if (stack[top] == LEFT_BRACE) top--;
                    else top = -1;
                if (top < 0) break;
            }

            sb.append(top == 0 ? YES : NO);
        }

        System.out.print(sb);
    }
}

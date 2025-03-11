package com.jake.algorithm.programmers.lv1;

/**
 * [ 시저 암호 ]
 *
 * 어떤 문장의 각 알파벳을 일정한 거리만큼 밀어서 다른 알파벳으로 바꾸는 암호화 방식을 시저 암호라고 합니다.
 * 예를 들어 "AB"는 1만큼 밀면 "BC"가 되고, 3만큼 밀면 "DE"가 됩니다. "z"는 1만큼 밀면 "a"가 됩니다.
 * 문자열 s와 거리 n을 입력받아 s를 n만큼 민 암호문을 만드는 함수, solution을 완성해 보세요.
 *
 * 제한 조건
 * 공백은 아무리 밀어도 공백입니다.
 * s는 알파벳 소문자, 대문자, 공백으로만 이루어져 있습니다.
 * s의 길이는 8000이하입니다.
 * n은 1 이상, 25이하인 자연수입니다.
 *
 * 작성일 : 2023.06.15
 */
class P12926 {
    public String solution(String s, int n) {
        char[] ch = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : ch) {
            if (c == ' ') {
                sb.append(c);
                continue;
            }
            if (65 <= (int) c && (int) c <= 90) {
                if (c + n > 90) {
                    sb.append((char) (c - 26 + n));
                } else {
                    sb.append((char) (c + n));
                }
            } else if (97 <= (int) c && (int) c <= 122) {
                if (c + n > 122) {
                    sb.append((char) (c - 26 + n));
                } else {
                    sb.append((char) (c + n));
                }
            }
        }

        return sb.toString();
    }

    // 다른 사람의 풀이
    String caesar(String s, int n) {
        String result = "";
        n = n % 26;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLowerCase(ch)) {
                ch = (char) ((ch - 'a' + n) % 26 + 'a');
            } else if (Character.isUpperCase(ch)) {
                ch = (char) ((ch - 'A' + n) % 26 + 'A');
            }
            result += ch;
        }
        return result;
    }

    public static void main(String[] args) {
        P12926 problem = new P12926();
        System.out.println(problem.solution("a B z", 4));
    }
}

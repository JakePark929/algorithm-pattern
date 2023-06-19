package com.jake.argorithm.programmers.lv1;

/**
 * [ 문자열 다루기 기본 ]
 * <p>
 * 문자열 s의 길이가 4 혹은 6이고, 숫자로만 구성돼있는지 확인해주는 함수, solution을 완성하세요.
 * 예를 들어 s가 "a234"이면 False를 리턴하고 "1234"라면 True를 리턴하면 됩니다.
 * <p>
 * 제한 사항
 * s는 길이 1 이상, 길이 8 이하인 문자열입니다.
 * s는 영문 알파벳 대소문자 또는 0부터 9까지 숫자로 이루어져 있습니다.
 * <p>
 * 작성일 : 2023.06.15
 */
class P12918 {
    public boolean solution(String s) {
        if (s.length() == 4 || s.length() == 6) {
            return !hasChar(s);
        } else {
            return false;
        }
    }

    private boolean hasChar(String s) {
        for (char c : s.toCharArray()) {
            if ((65 <= c && c <= 90) || (97 <= c && c <= 122)) {
                return true;
            }
        }
        return false;
    }

    // 다른 사람의 풀이 1
    public boolean solution2(String s) {
        if(s.length() == 4 || s.length() == 6){
            try{
                int x = Integer.parseInt(s);
                return true;
            } catch(NumberFormatException e){
                return false; // try-catch 사용, 흐름제어를 위한 예외사용은 금지하고 있음..
            }
        }
        else return false;
    }

    // 다른 사람의 풀이 2 - 정규표현식 사용
    public boolean solution3(String s) {
        return s.matches("[0-9]{4}|[0-9]{6}");
    }

    public static void main(String[] args) {
        P12918 problem = new P12918();
        System.out.println(problem.solution3("1234"));
    }
}

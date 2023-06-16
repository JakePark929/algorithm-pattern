package com.jake.argorithm.programmers.lv1;

/**
 * [ 이상한 문자 만들기 ]
 * <p>
 * 문자열 s는 한 개 이상의 단어로 구성되어 있습니다.
 * 각 단어는 하나 이상의 공백문자로 구분되어 있습니다.
 * 각 단어의 짝수번째 알파벳은 대문자로, 홀수번째 알파벳은 소문자로 바꾼 문자열을 리턴하는 함수, solution을 완성하세요.
 * <p>
 * 제한 사항
 * 문자열 전체의 짝/홀수 인덱스가 아니라, 단어(공백을 기준)별로 짝/홀수 인덱스를 판단해야합니다.
 * 첫 번째 글자는 0번째 인덱스로 보아 짝수번째 알파벳으로 처리해야 합니다.
 * <p>
 * 작성일: 2023.06.15
 */
class P12930 {
    public String solution(String s) {
        int count = 0;
        char[] ch = s.toCharArray();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ch.length; i++) {
            if (ch[i] != ' ') {
                if (count % 2 == 0) {
                    ch[i] = Character.toUpperCase(ch[i]);
                    count++;
                } else {
                    ch[i] = Character.toLowerCase(ch[i]);
                    count++;
                }
            } else {
                count = 0;
            }
            sb.append(ch[i]);
        }

        return sb.toString();
    }

    // 다른 사람의 풀이 1
    public String solution2(String s) {
        String answer = "";
        int cnt = 0;
        String[] array = s.split("");

        for(String ss : array) {
            cnt = ss.contains(" ") ? 0 : cnt + 1;
            answer += cnt%2 == 0 ? ss.toLowerCase() : ss.toUpperCase();
        }
        return answer;
    }

    public static void main(String[] args) {
        P12930 problem = new P12930();
        System.out.println(problem.solution("try hello world"));
    }
}

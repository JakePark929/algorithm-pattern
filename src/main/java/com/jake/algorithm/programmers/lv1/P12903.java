package com.jake.algorithm.programmers.lv1;

/**
 * [ 가운데 글자 가져오기 ]
 *
 * 단어 s의 가운데 글자를 반환하는 함수, solution 을 만들어 보세요. 단어의 길이가 짝수라면 가운데 두글자를 반환하면 됩니다.
 *
 * 제한사항
 * s는 길이가 1 이상, 100이하인 스트링입니다.
 *
 * 작성일 : 2023.06.15
 */
class P12903 {
    String solution(String s) {
        String[] list = s.split("");

        if (list.length % 2 == 1) {
            return list[list.length / 2];
        } else {
            return list[(list.length / 2) - 1] + list[(list.length / 2)];
        }
    }

    // 다른 사람의 풀이
    String getMiddle(String word){
        return word.substring((word.length()-1)/2, word.length()/2 + 1);
    }

    public static void main(String[] args) {
        P12903 problem1 = new P12903();
        System.out.println(problem1.solution("abcde"));
    }
}

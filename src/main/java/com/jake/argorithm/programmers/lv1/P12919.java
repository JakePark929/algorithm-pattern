package com.jake.argorithm.programmers.lv1;

import java.util.Arrays;

/**
 * [ 서울에서 김서방 찾기 ]
 *
 * String형 배열 seoul의 element중 "Kim"의 위치 x를 찾아, "김서방은 x에 있다"는 String을 반환하는 함수, solution을 완성하세요.
 * seoul에 "Kim"은 오직 한 번만 나타나며 잘못된 값이 입력되는 경우는 없습니다.
 *
 * 제한 사항
 * seoul은 길이 1 이상, 1000 이하인 배열입니다.
 * seoul의 원소는 길이 1 이상, 20 이하인 문자열입니다.
 * "Kim"은 반드시 seoul 안에 포함되어 있습니다.
 *
 * 작성일: 2023.06.15
 */
class P12919 {
    public String solution(String[] seoul) {
        String answer = "";
        int count = 0;
        for (String name : seoul) {
            if (name.equals("Kim")) break;
            count++;
        }

        return "김서방은" + count + "에 있다";
    }

    // 다른 사람의 풀이
    public String findKim(String[] seoul){
        //x에 김서방의 위치를 저장하세요.
        int x = Arrays.asList(seoul).indexOf("Kim");
        return new StringBuffer("김서방은 ").append(x).append("에 있다").toString(); // 스트링버퍼를 사용하면 연산속도를 높일 수 있다.
    }

    public static void main(String[] args) {
        P12919 problem = new P12919();
        System.out.println(problem.solution(new String[]{"Jane", "Kim"}));
    }
}

package com.jake.algorithm.programmers.lv2;

import java.util.Arrays;

/**
 * [ 이진 변환 반복하기 ]
 * <p>
 * 0과 1로 이루어진 어떤 문자열 x에 대한 이진 변환을 다음과 같이 정의합니다.
 * <p>
 * x의 모든 0을 제거합니다.
 * x의 길이를 c라고 하면, x를 "c를 2진법으로 표현한 문자열"로 바꿉니다.
 * 예를 들어, x = "0111010"이라면, x에 이진 변환을 가하면 x = "0111010" -> "1111" -> "100" 이 됩니다.
 * <p>
 * 0과 1로 이루어진 문자열 s가 매개변수로 주어집니다.
 * s가 "1"이 될 때까지 계속해서 s에 이진 변환을 가했을 때,
 * 이진 변환의 횟수와 변환 과정에서 제거된 모든 0의 개수를 각각 배열에 담아 return 하도록 solution 함수를 완성해주세요.
 * <p>
 * 제한사항
 * s의 길이는 1 이상 150,000 이하입니다.
 * s에는 '1'이 최소 하나 이상 포함되어 있습니다.
 * <p>
 * 작성일 : 2023.07.10
 */
class P70129 {
    public int[] solution(String s) {
        int count = 0;
        int zeroCount = 0;

        while (!s.equals("1")) {
            // step.1 모든 0 제거
            for (char c : s.toCharArray()) {
                if (c == '0') {
                    zeroCount++;
                }
            }
            s = s.replaceAll("0", "");

            // step.2 길이 c 를 2진법으로 표현
            s = Integer.toString(s.length(), 2);

            count++;
        }

        return new int[]{count, zeroCount};
    }

    // 다른 사람의 풀이 1
    public int[] solution1(String s) {
        int[] answer = new int[2];
        int temp;
        while (!s.equals("1")) {
            answer[1] += s.length();
            s = s.replaceAll("0", "");
            temp = s.length();
            s = Integer.toBinaryString(temp);
            answer[0]++;
            answer[1] -= temp;
        }
        return answer;
    }

    // 다른 사람의 풀이 2
//    class Solution {
//        public static int count;
//        public static int zeroCount;
//        public int[] solution(String s) {
//            int[] answer = new int[2];
//            count =0;
//            zeroCount=0;
//            recursive(s);
//            answer[0] = count;
//            answer[1] = zeroCount;
//            return answer;
//        }
//        public static void recursive(String s){
//            if(s.equals("1"))
//                return;
//            count++;
//            StringBuilder sb = new StringBuilder();
//            for(char c : s.toCharArray()){
//                if(c=='0'){
//                    zeroCount++;
//                    continue;
//                }
//                sb.append(c);
//            }
//            recursive(Integer.toBinaryString(sb.length()));
//        }
//    }

    public static void main(String[] args) {
        P70129 problem = new P70129();
        System.out.println(Arrays.toString(problem.solution("1111111")));
    }
}

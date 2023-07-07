package com.jake.argorithm.programmers.lv1;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 문자열 s가 주어졌을 때, s의 각 위치마다 자신보다 앞에 나왔으면서,
 * 자신과 가장 가까운 곳에 있는 같은 글자가 어디 있는지 알고 싶습니다.
 * 예를 들어, s="banana"라고 할 때,
 * 각 글자들을 왼쪽부터 오른쪽으로 읽어 나가면서 다음과 같이 진행할 수 있습니다.
 * <p>
 * b는 처음 나왔기 때문에 자신의 앞에 같은 글자가 없습니다. 이는 -1로 표현합니다.
 * a는 처음 나왔기 때문에 자신의 앞에 같은 글자가 없습니다. 이는 -1로 표현합니다.
 * n은 처음 나왔기 때문에 자신의 앞에 같은 글자가 없습니다. 이는 -1로 표현합니다.
 * a는 자신보다 두 칸 앞에 a가 있습니다. 이는 2로 표현합니다.
 * n도 자신보다 두 칸 앞에 n이 있습니다. 이는 2로 표현합니다.
 * a는 자신보다 두 칸, 네 칸 앞에 a가 있습니다. 이 중 가까운 것은 두 칸 앞이고, 이는 2로 표현합니다.
 * 따라서 최종 결과물은 [-1, -1, -1, 2, 2, 2]가 됩니다.
 * <p>
 * 문자열 s이 주어질 때, 위와 같이 정의된 연산을 수행하는 함수 solution을 완성해주세요.
 * <p>
 * 제한사항
 * 1 ≤ s의 길이 ≤ 10,000
 * s은 영어 소문자로만 이루어져 있습니다.
 * <p>
 * 작성일 : 2023.06.22
 */
class P142086 {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        char[] chars = s.toCharArray();
        for (int i = 0; i < answer.length; i++) {
            int pos = s.substring(0, i).lastIndexOf(chars[i]);
            answer[i] = pos;
            if (answer[i] >= 0) {
                answer[i] = s.substring(0, i).length() - pos;
            }
        }
        return answer;
    }

    // 다른 사람의 풀이 1
    public int[] solution1(String s) {
        int[] answer = new int[s.length()];
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            answer[i] = i - map.getOrDefault(ch, i + 1);
            map.put(ch, i);
        }
        return answer;
    }

    // 다른 사람의 풀이 2
    public int[] solution2(String str) {
        int[] result = new int[str.length()];

        for (int i = 0; i < str.length(); i++) {
            String subStr = str.substring(0, i);
            if (subStr.indexOf(str.charAt(i)) == -1) {
                result[i] = -1;
            } else {
                result[i] = i - subStr.lastIndexOf(str.charAt(i));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        P142086 problem = new P142086();
        System.out.println(Arrays.toString(problem.solution("sdfasdfasdfasdf")));
    }
}

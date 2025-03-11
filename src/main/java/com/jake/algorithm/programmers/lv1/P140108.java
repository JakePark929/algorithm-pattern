package com.jake.algorithm.programmers.lv1;

/**
 * [ 문자열 나누기 ]
 * <p>
 * 문자열 s가 입력되었을 때 다음 규칙을 따라서 이 문자열을 여러 문자열로 분해하려고 합니다.
 * <p>
 * 먼저 첫 글자를 읽습니다. 이 글자를 x라고 합시다.
 * 이제 이 문자열을 왼쪽에서 오른쪽으로 읽어나가면서,
 * x와 x가 아닌 다른 글자들이 나온 횟수를 각각 셉니다.
 * 처음으로 두 횟수가 같아지는 순간 멈추고, 지금까지 읽은 문자열을 분리합니다.
 * s에서 분리한 문자열을 빼고 남은 부분에 대해서 이 과정을 반복합니다. 남은 부분이 없다면 종료합니다.
 * 만약 두 횟수가 다른 상태에서 더 이상 읽을 글자가 없다면, 역시 지금까지 읽은 문자열을 분리하고, 종료합니다.
 * 문자열 s가 매개변수로 주어질 때, 위 과정과 같이 문자열들로 분해하고, 분해한 문자열의 개수를 return 하는 함수 solution을 완성하세요.
 * <p>
 * 제한사항
 * 1 ≤ s의 길이 ≤ 10,000
 * s는 영어 소문자로만 이루어져 있습니다.
 * <p>
 * 작성일 : 2023.06.27
 */

class P140108 {
    public int solution(String s) {
        int answer = 0;

        while (s.length() != 0) {
            int yes = 0;
            int no = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(0) == s.charAt(i)) {
                    yes++;
                } else {
                    no++;
                }
                if (yes == no) {
                    s = s.substring(i + 1);
                    answer++;
                    break;
                }
            }
            if (yes != no) {
                answer++;
                break;
            }
        }

        return answer;
    }

    // 다른 사람의 풀이 1
    public int solution1(String s) {
        char prev = '1';
        int same = 0, different = 0, answer = 0;
        for (char c : s.toCharArray()) {
            if (prev == '1') {
                prev = c;
                same++;
                answer++;
            } else if (prev == c) {
                same++;
            } else {
                different++;
            }

            if (same == different) {
                prev = '1';
                same = 0;
                different = 0;
            }
        }

        return answer;
    }

    // 다른 사람의 풀이 2
    public int solution2(String s) {
        int answer = 0;
        char init = s.charAt(0);
        int count = 0;
        for (char c : s.toCharArray()) {
            if (count == 0) {
                init = c;
            }
            if (init == c) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                answer++;
            }
        }

        if(count > 0) {
            answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        P140108 problem = new P140108();
        System.out.println(problem.solution("abracadabra"));
    }
}

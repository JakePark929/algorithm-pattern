package com.jake.algorithm.programmers.lv1;

/**
 * [ 옹알이 (2) ]
 * <p>
 * 머쓱이는 태어난 지 11개월 된 조카를 돌보고 있습니다.
 * 조카는 아직 "aya", "ye", "woo", "ma" 네 가지 발음과
 * 네 가지 발음을 조합해서 만들 수 있는 발음밖에 하지 못하고 연속해서 같은 발음을 하는 것을 어려워합니다.
 * 문자열 배열 babbling이 매개변수로 주어질 때, 머쓱이의 조카가 발음할 수 있는 단어의 개수를 return하도록 solution 함수를 완성해주세요.
 * <p>
 * 제한사항
 * 1 ≤ babbling의 길이 ≤ 100
 * 1 ≤ babbling[i]의 길이 ≤ 30
 * 문자열은 알파벳 소문자로만 이루어져 있습니다.
 * <p>
 * 작성일 : 2023.06.26
 */
class P133499 {
    public int solution(String[] babbling) {
        int answer = 0;

        for (String s : babbling) {
            String bab = parseNum(s);

            if (!checkDuple(bab) && !checkChar(bab)) {
                answer++;
            }
        }

        return answer;
    }

    String parseNum(String bab) {
        String[] can = {"aya", "ye", "woo", "ma"};

        for (int j = 0; j < can.length; j++) {
            bab = bab.replace(can[j], String.valueOf(j));
        }

        return bab;
    }

    boolean checkDuple(String bab) {
        for (int i = 0; i < bab.length(); i++) {
            if (i < bab.length() - 1) {
                if (bab.charAt(i) == bab.charAt(i + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean checkChar(String bab) {
        for (int i = 0; i < bab.length(); i++) {
            if (Character.isLetter(bab.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    // 다른 사람의 풀이 1
    public int solution1(String[] babblings) {
        // "aya", "ye", "woo", "ma" 4가지 발음만 가능
        int answer = 0;
        for (int i = 0; i < babblings.length; i++) {
            if (babblings[i].contains("ayaaya") || babblings[i].contains("yeye") || babblings[i].contains("woowoo") || babblings[i].contains("mama")) {
                continue;
            }

            babblings[i] = babblings[i].replace("aya", " ");
            babblings[i] = babblings[i].replace("ye", " ");
            babblings[i] = babblings[i].replace("woo", " ");
            babblings[i] = babblings[i].replace("ma", " ");
            babblings[i] = babblings[i].replace(" ", "");

            if (babblings[i].length() == 0) answer++;

        }
        return answer;
    }

    public static void main(String[] args) {
        P133499 problem = new P133499();
        System.out.println(problem.solution(new String[]{"ayaye", "uuu", "yeye", "yemawoo", "ayaayaa"}));
    }
}

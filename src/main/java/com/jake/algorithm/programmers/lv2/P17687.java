package com.jake.algorithm.programmers.lv2;

/**
 * [ n진수 게임 ]
 *
 * 튜브가 활동하는 코딩 동아리에서는 전통적으로 해오는 게임이 있다.
 * 이 게임은 여러 사람이 둥글게 앉아서 숫자를 하나씩 차례대로 말하는 게임인데, 규칙은 다음과 같다.
 *
 * 숫자를 0부터 시작해서 차례대로 말한다. 첫 번째 사람은 0, 두 번째 사람은 1, … 열 번째 사람은 9를 말한다.
 * 10 이상의 숫자부터는 한 자리씩 끊어서 말한다. 즉 열한 번째 사람은 10의 첫 자리인 1, 열두 번째 사람은 둘째 자리인 0을 말한다.
 * 이렇게 게임을 진행할 경우,
 * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 0, 1, 1, 1, 2, 1, 3, 1, 4, …
 * 순으로 숫자를 말하면 된다.
 *
 * 한편 코딩 동아리 일원들은 컴퓨터를 다루는 사람답게 이진수로 이 게임을 진행하기도 하는데, 이 경우에는
 * 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, …
 * 순으로 숫자를 말하면 된다.
 *
 * 이진수로 진행하는 게임에 익숙해져 질려가던 사람들은 좀 더 난이도를 높이기 위해
 * 이진법에서 십육진법까지 모든 진법으로 게임을 진행해보기로 했다.
 * 숫자 게임이 익숙하지 않은 튜브는 게임에 져서 벌칙을 받는 굴욕을 피하기 위해,
 * 자신이 말해야 하는 숫자를 스마트폰에 미리 출력해주는 프로그램을 만들려고 한다.
 * 튜브의 프로그램을 구현하라.
 *
 * 작성일 : 24.09.29
 */
public class P17687 {
    public String solution(int n, int t, int m, int p) {
        StringBuilder result = new StringBuilder();

        int currentNumber = 0;
        int currentTurn = 0;

        while (result.length() < t) {
            String convertedNumber = convertNumToBase(currentNumber, n);
            for (char c : convertedNumber.toCharArray()) {
                if (currentTurn == p - 1) {
                    result.append(c);
                }

                if (result.length() == t) {
                    return result.toString();
                }
                currentTurn = (currentTurn + 1) % m;
            }
            currentNumber++;
        }

        return result.toString();
    }

    private String convertNumToBase(int num, int base) {
        StringBuilder result = new StringBuilder();
        String[] alpha = {"A", "B", "C", "D", "E", "F"};

        if (num == 0) {
            return "0";
        }

        while (num > 0) {
            int remainder = num % base;

            if (remainder >= 10) {
                result.append(alpha[remainder - 10]);
            } else {
                result.append(remainder);
            }

            num = num / base;
        }

        return result.reverse().toString();
    }

    public String solution2(int n, int t, int m, int p) {
        String answer;
        int startNum = 0;

        StringBuilder targetString = new StringBuilder();
        StringBuilder retString = new StringBuilder();

        while (targetString.length() < m * t) {
            targetString.append(Integer.toString(startNum++, n));
        }

        System.out.println(targetString);

        for (int i = 0; i < t; i++) {
            retString.append(targetString.charAt(p - 1 + i * m));
        }

        answer = retString.toString().toUpperCase();

        return answer;
    }

    public static void main(String[] args) {
        final String solution = new P17687().solution2(2, 4, 2, 1);

        System.out.println(solution);
    }
}

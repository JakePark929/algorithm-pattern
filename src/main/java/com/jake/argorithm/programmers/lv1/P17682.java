package com.jake.argorithm.programmers.lv1;

import java.util.LinkedList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 카카오톡 게임별의 하반기 신규 서비스로 다트 게임을 출시하기로 했다. 다트 게임은 다트판에 다트를 세 차례 던져 그 점수의 합계로 실력을 겨루는 게임으로, 모두가 간단히 즐길 수 있다.
 * 갓 입사한 무지는 코딩 실력을 인정받아 게임의 핵심 부분인 점수 계산 로직을 맡게 되었다. 다트 게임의 점수 계산 로직은 아래와 같다.
 * <p>
 * [ 요구사항 ]
 * 1. 다트 게임은 총 3번의 기회로 구성된다.
 * 2. 각 기회마다 얻을 수 있는 점수는 0점에서 10점까지이다.
 * 3. 점수와 함께 Single(S), Double(D), Triple(T) 영역이 존재하고 각 영역 당첨 시 점수에서 1제곱, 2제곱, 3제곱 (점수1 , 점수2 , 점수3 )으로 계산된다.
 * 4. 옵션으로 스타상(*) , 아차상(#)이 존재하며 스타상(*) 당첨 시 해당 점수와 바로 전에 얻은 점수를 각 2배로 만든다. 아차상(#) 당첨 시 해당 점수는 마이너스된다.
 * 5. 스타상(*)은 첫 번째 기회에서도 나올 수 있다. 이 경우 첫 번째 스타상(*)의 점수만 2배가 된다. (예제 4번 참고)
 * 6. 스타상(*)의 효과는 다른 스타상(*)의 효과와 중첩될 수 있다. 이 경우 중첩된 스타상(*) 점수는 4배가 된다. (예제 4번 참고)
 * 7. 스타상(*)의 효과는 아차상(#)의 효과와 중첩될 수 있다. 이 경우 중첩된 아차상(#)의 점수는 -2배가 된다. (예제 5번 참고)
 * 8. Single(S), Double(D), Triple(T)은 점수마다 하나씩 존재한다.
 * 9. 스타상(*), 아차상(#)은 점수마다 둘 중 하나만 존재할 수 있으며, 존재하지 않을 수도 있다.
 * 10. 0~10의 정수와 문자 S, D, T, *, #로 구성된 문자열이 입력될 시 총점수를 반환하는 함수를 작성하라.
 * <p>
 * 작성일 : 2023.06.19 (2시간)
 */
class P17682 {
    public int solution(String dartResult) {
        String[] number = dartResult.split("[SDT#*]");
        String[] grade = dartResult.split("[0-9]");
        LinkedList<Integer> newNumber = new LinkedList<>();
        LinkedList<String> newGrade = new LinkedList<>();

        for (String s : number) {
            if (!s.equals("")) newNumber.add((Integer.parseInt(s)));
        }

        for (String s : grade) {
            if (!s.equals("")) newGrade.add(s);
        }

        for (int i = 0; i < 3; i++) {
            switch (newGrade.get(i)) {
                case "S":
                    newNumber.set(i, newNumber.get(i));
                    break;
                case "D":
                    newNumber.set(i, (int) Math.pow(newNumber.get(i), 2));
                    break;
                case "T":
                    newNumber.set(i, (int) Math.pow(newNumber.get(i), 3));
                    break;
                case "S*":
                    if (i - 1 >= 0) {
                        newNumber.set(i - 1, newNumber.get(i - 1) * 2);
                    }
                    newNumber.set(i, newNumber.get(i) * 2);
                    break;
                case "D*":
                    if (i - 1 >= 0) {
                        newNumber.set(i - 1, newNumber.get(i - 1) * 2);
                    }
                    newNumber.set(i, (int) Math.pow(newNumber.get(i), 2) * 2);
                    break;
                case "T*":
                    if (i - 1 >= 0) {
                        newNumber.set(i - 1, newNumber.get(i - 1) * 2);
                    }
                    newNumber.set(i, (int) Math.pow(newNumber.get(i), 3) * 2);
                    break;
                case "S#":
                    newNumber.set(i, newNumber.get(i) * -1);
                    break;
                case "D#":
                    newNumber.set(i, (int) Math.pow(newNumber.get(i), 2) * -1);
                    break;
                case "T#":
                    newNumber.set(i, (int) Math.pow(newNumber.get(i), 3) * -1);
                    break;
            }
        }

        return newNumber.get(0) + newNumber.get(1) + newNumber.get(2);
    }

    // 다른 사람의 풀이 1
    static int[] sum = new int[3];

    public int solution2(String msg) {
        String reg = "([0-9]{1,2}[S|T|D][*|#]{0,1})";
        Pattern p = Pattern.compile(reg + reg + reg);
        Matcher m = p.matcher(msg);
        m.find();
        for (int i = 1; i <= m.groupCount(); i++) {
            Pattern p1 = Pattern.compile("([0-9]{1,2})([S|T|D])([*|#]{0,1})");
            Matcher m1 = p1.matcher(m.group(i));
            m1.find();
            sum[i - 1] = (int) Math.pow(Integer.parseInt(m1.group(1)), getPow(m1.group(2)));
            setting(i, m1.group(3));
        }
        return (sum[0] + sum[1] + sum[2]);
    }

    static void setting(int idx, String msg) {
        if (msg.equals("*")) {
            sum[idx - 1] *= 2;
            if (idx > 1) {
                sum[idx - 2] *= 2;
            }
        } else if (msg.equals("#")) {
            sum[idx - 1] *= -1;
        }
    }

    static int getPow(String mag) {
        if (mag.equals("S")) {
            return 1;
        } else if (mag.equals("D")) {
            return 2;
        } else {
            return 3;
        }
    }

    // 다른 사람의 풀이 2
    public int solution3(String dartResult) {
        Stack<Integer> stack = new Stack<>();
        int sum = 0;

        for (int i = 0; i < dartResult.length(); i++) {
            char c = dartResult.charAt(i);
            if (Character.isDigit(c)) {
                sum = (c - '0');
                if (sum == 1 && i < dartResult.length() - 1 && dartResult.charAt(i + 1) == '0') {
                    sum = 10;
                    i++;
                }
                stack.push(sum);
            } else {
                int prev = stack.pop();
                if (c == 'D') {
                    prev *= prev;
                } else if (c == 'T') {
                    prev = prev * prev * prev;
                } else if (c == '*') {
                    if (!stack.isEmpty()) {
                        int val = stack.pop() * 2;
                        stack.push(val);
                    }
                    prev *= 2;
                } else if (c == '#') {
                    prev *= (-1);
                }
                // System.out.println(prev);
                stack.push(prev);
            }
        }
        int totalScore = 0;
        while (!stack.isEmpty()) {
            totalScore += stack.pop();
        }
        return totalScore;
    }

    public static void main(String[] args) {
        P17682 problem = new P17682();
        System.out.println(problem.solution("1D2S3T*"));
    }
}

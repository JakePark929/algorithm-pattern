package com.jake.argorithm.programmers.lv1;

import java.util.LinkedList;

/**
 * [ 삼총사 ]
 * <p>
 * 한국중학교에 다니는 학생들은 각자 정수 번호를 갖고 있습니다.
 * 이 학교 학생 3명의 정수 번호를 더했을 때 0이 되면 3명의 학생은 삼총사라고 합니다.
 * 예를 들어, 5명의 학생이 있고, 각각의 정수 번호가 순서대로 -2, 3, 0, 2, -5일 때,
 * 첫 번째, 세 번째, 네 번째 학생의 정수 번호를 더하면 0이므로 세 학생은 삼총사입니다.
 * 또한, 두 번째, 네 번째, 다섯 번째 학생의 정수 번호를 더해도 0이므로 세 학생도 삼총사입니다.
 * 따라서 이 경우 한국중학교에서는 두 가지 방법으로 삼총사를 만들 수 있습니다.
 * <p>
 * 한국중학교 학생들의 번호를 나타내는 정수 배열 number가 매개변수로 주어질 때,
 * 학생들 중 삼총사를 만들 수 있는 방법의 수를 return 하도록 solution 함수를 완성하세요.
 * <p>
 * 제한사항
 * 3 ≤ number의 길이 ≤ 13
 * -1,000 ≤ number의 각 원소 ≤ 1,000
 * 서로 다른 학생의 정수 번호가 같을 수 있습니다.
 * <p>
 * 작성일 : 2023.06.20
 */
public class P131705 {
    int[] combination;
    int result = 0;

    public int solution(int[] number) {
        int depth = 3;
        combination = new int[depth];

        LinkedList<Integer> list = new LinkedList<>();

        for (int num : number) {
            list.add(num);
        }

        comb(list, depth, 0, 0);

        return result;
    }

    public void comb(LinkedList<Integer> list, int depth, int cnt, int start) {
        if (cnt == depth) {
            int sum = 0;
            for (int i : combination) {
                sum += i;
            }
            if (sum == 0) {
                result++;
            }
            return;
        }

        for (int i = start; i < list.size(); i++) {
            combination[cnt] = list.get(i);
            comb(list, depth, cnt + 1, i + 1);
        }
    }

    // 다른 사람의 풀이 1
    public int solution2(int[] number) {
        int answer = 0;

        for (int i = 0; i < number.length - 2; i++) {
            for (int j = i + 1; j < number.length - 1; j++) {
                for (int k = j + 1; k < number.length; k++) {
                    if (number[i] + number[j] + number[k] == 0) answer++;
                }
            }
        }

        return answer;
    }

    // 다른 사람의 풀이 2 - 재귀함수 제대로
    static int[] triple;
    static int good = 0;

    // 조합 함수에 가지고 들어갈 input 복사
    static int[] input;
    static int inputLen;

    public int solution3(int[] number) {
        // 삼총사 저장 배열
        triple = new int[3];
        good = 0;

        input = number;
        inputLen = number.length;

        // 조합 구하기
        combination(0, 0);
        return good;
    }

    private static void combination(int cnt, int start) {
        // 기저조건
        if (cnt == 3) {
            int sum = 0;
            for (int val : triple) {
                sum += val;
            }
            if (sum == 0) good++;
            return;
        }

        // 유도파트
        for (int i = start; i < inputLen; i++) {
            triple[cnt] = input[i];
            combination(cnt + 1, i + 1);
        }
    }

    public static void main(String[] args) {
        P131705 problem = new P131705();
        System.out.println(problem.solution(new int[]{-1, 1, -1, 1}));
    }
}

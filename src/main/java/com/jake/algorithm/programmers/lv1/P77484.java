package com.jake.algorithm.programmers.lv1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.LongStream;

/**
 * [ 로또의 최고 순위와 최저 순위 ]
 * 
 * 로또 6/45(이하 '로또'로 표기)는 1부터 45까지의 숫자 중 6개를 찍어서 맞히는 대표적인 복권입니다.
 * 아래는 로또의 순위를 정하는 방식입니다.
 *
 * 순위	당첨 내용
 * 1	6개 번호가 모두 일치
 * 2	5개 번호가 일치
 * 3	4개 번호가 일치
 * 4	3개 번호가 일치
 * 5	2개 번호가 일치
 * 6(낙첨)	그 외
 *
 * 로또를 구매한 민우는 당첨 번호 발표일을 학수고대하고 있었습니다.
 * 하지만, 민우의 동생이 로또에 낙서를 하여, 일부 번호를 알아볼 수 없게 되었습니다.
 * 당첨 번호 발표 후, 민우는 자신이 구매했던 로또로 당첨이 가능했던 최고 순위와 최저 순위를 알아보고 싶어 졌습니다.
 * 알아볼 수 없는 번호를 0으로 표기하기로 하고, 민우가 구매한 로또 번호 6개가 44, 1, 0, 0, 31 25라고 가정해보겠습니다.
 * 당첨 번호 6개가 31, 10, 45, 1, 6, 19라면, 당첨 가능한 최고 순위와 최저 순위의 한 예는 아래와 같습니다.
 *
 * 당첨 번호	31	10	45	1	6	19	결과
 * 최고 순위 번호	31	0→10	44	1	0→6	25	4개 번호 일치, 3등
 * 최저 순위 번호	31	0→11	44	1	0→7	25	2개 번호 일치, 5등
 *
 * 순서와 상관없이, 구매한 로또에 당첨 번호와 일치하는 번호가 있으면 맞힌 걸로 인정됩니다.
 * 알아볼 수 없는 두 개의 번호를 각각 10, 6이라고 가정하면 3등에 당첨될 수 있습니다.
 * 3등을 만드는 다른 방법들도 존재합니다. 하지만, 2등 이상으로 만드는 것은 불가능합니다.
 * 알아볼 수 없는 두 개의 번호를 각각 11, 7이라고 가정하면 5등에 당첨될 수 있습니다.
 * 5등을 만드는 다른 방법들도 존재합니다. 하지만, 6등(낙첨)으로 만드는 것은 불가능합니다.
 * 민우가 구매한 로또 번호를 담은 배열 lottos, 당첨 번호를 담은 배열 win_nums가 매개변수로 주어집니다.
 * 이때, 당첨 가능한 최고 순위와 최저 순위를 차례대로 배열에 담아서 return 하도록 solution 함수를 완성해주세요.
 *
 * 제한사항
 * lottos는 길이 6인 정수 배열입니다.
 * lottos의 모든 원소는 0 이상 45 이하인 정수입니다.
 * 0은 알아볼 수 없는 숫자를 의미합니다.
 * 0을 제외한 다른 숫자들은 lottos에 2개 이상 담겨있지 않습니다.
 * lottos의 원소들은 정렬되어 있지 않을 수도 있습니다.
 * win_nums은 길이 6인 정수 배열입니다.
 * win_nums의 모든 원소는 1 이상 45 이하인 정수입니다.
 * win_nums에는 같은 숫자가 2개 이상 담겨있지 않습니다.
 * win_nums의 원소들은 정렬되어 있지 않을 수도 있습니다.
 *
 * 작성일 : 2023.06.26
 */
class P77484 {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];

        int count = 0;
        for (int i : lottos) {
            if(i == 0) {
                count++;
            }
        }

        answer[0] = getRank(lottos, win_nums, count);
        answer[1] = getRank(lottos, win_nums, 0);

        return answer;
    }

    int getRank(int[] lottos, int[] win_nums, int count) {
        int hit = 0;
        for(int lotto : lottos) {
            for (int win_num : win_nums) {
                if (lotto == win_num) {
                    hit++;
                }
            }
        }

        return hit + count >= 2 ? 7 - hit - count : 6;
    }

    // 다른 사람의 풀이 1
    public int[] solution1(int[] lottos, int[] win_nums) {
        Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        int zeroCount = 0;

        for(int lotto : lottos) {
            if(lotto == 0) {
                zeroCount++;
                continue;
            }
            map.put(lotto, true);
        }


        int sameCount = 0;
        for(int winNum : win_nums) {
            if(map.containsKey(winNum)) sameCount++;
        }

        int maxRank = 7 - (sameCount + zeroCount);
        int minRank = 7 - sameCount;
        if(maxRank > 6) maxRank = 6;
        if(minRank > 6) minRank = 6;

        return new int[] {maxRank, minRank};
    }

    // 다른 사람의 풀이 2
    public int[] solution2(int[] lottos, int[] winNums) {
        return LongStream.of(
                        (lottos.length + 1) - Arrays.stream(lottos).filter(l -> Arrays.stream(winNums).anyMatch(w -> w == l) || l == 0).count(),
                        (lottos.length + 1) - Arrays.stream(lottos).filter(l -> Arrays.stream(winNums).anyMatch(w -> w == l)).count()
                )
                .mapToInt(op -> (int) (op > 6 ? op - 1 : op))
                .toArray();
    }

    public static void main(String[] args) {
        P77484 problem = new P77484();
        System.out.println(Arrays.toString(problem.solution(new int[]{45, 4, 35, 20, 3, 9}, new int[]{20, 9, 3, 45, 4, 35})));
    }
}

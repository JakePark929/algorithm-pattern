package com.jake.argorithm.programmers.lv1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * [ 폰켓몬 ]
 *
 * 당신은 폰켓몬을 잡기 위한 오랜 여행 끝에, 홍 박사님의 연구실에 도착했습니다. 홍 박사님은 당신에게 자신의 연구실에 있는 총 N 마리의 폰켓몬 중에서 N/2마리를 가져가도 좋다고 했습니다.
 * 홍 박사님 연구실의 폰켓몬은 종류에 따라 번호를 붙여 구분합니다. 따라서 같은 종류의 폰켓몬은 같은 번호를 가지고 있습니다.
 * 예를 들어 연구실에 총 4마리의 폰켓몬이 있고, 각 폰켓몬의 종류 번호가 [3번, 1번, 2번, 3번]이라면 이는 3번 폰켓몬 두 마리, 1번 폰켓몬 한 마리, 2번 폰켓몬 한 마리가 있음을 나타냅니다.
 * 이때, 4마리의 폰켓몬 중 2마리를 고르는 방법은 다음과 같이 6가지가 있습니다.
 *
 * 작성일 : 2023.06.14
 */

class P1845 {
    int solution(int[] nums) {
        int answer = 0;

        int[] newNums = Arrays.stream(nums).distinct().toArray();

        answer = newNums.length;

        if (answer > nums.length / 2) {
            answer = nums.length / 2;
        }

        return answer;
    }

    // 다른 사람의 풀이
    int solution2(int[] nums) {
        int answer = 0;
        ArrayList<Integer> array = new ArrayList<Integer>();

        for (int i = 1; i < nums.length; i++) {
            if (!array.contains(nums[i])) {
                array.add(nums[i]);
            }
        }

        answer = array.size();

        if (answer > nums.length / 2) {
            answer = nums.length / 2;
        }

        return answer;
    }

    // 다른 사람의 풀이2
    int solution3 (int[] nums) {
        return Arrays.stream(nums)
                .boxed()
                .collect(Collectors.collectingAndThen(Collectors.toSet(),
                        phonekemons -> Integer.min(phonekemons.size(), nums.length / 2)));
    }

    public static void main(String[] args) {
        P1845 p1845 = new P1845();
        System.out.println(p1845.solution(new int[]{3, 1, 2, 3}));
    }
}

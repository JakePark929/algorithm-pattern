package com.jake.algorithm.codility.lessons;

import java.util.HashSet;
import java.util.Set;

/**
 * [ FrogRiverOne]
 *
 * 작성일 : 24.10.04
 */
public class L4_FrogRiverOne {
    public int solution(int X, int[] A) {
        Set<Integer> leafPositions = new HashSet<>();

        for (int time = 0; time < A.length; time++) {
            leafPositions.add(A[time]);  // 잎이 떨어진 위치를 기록
            if (leafPositions.size() == X) {  // 모든 위치에 잎이 떨어졌다면
                return time;  // 현재 시간을 반환
            }
        }

        return -1;  // 모든 위치에 잎이 다 떨어지지 않았다면 -1 반환
    }
}

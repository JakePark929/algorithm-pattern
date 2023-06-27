package com.jake.argorithm.programmers.lv1;

import java.util.LinkedHashMap;

/**
 * [ 체육복 ]
 * <p>
 * 점심시간에 도둑이 들어, 일부 학생이 체육복을 도난당했습니다.
 * 다행히 여벌 체육복이 있는 학생이 이들에게 체육복을 빌려주려 합니다.
 * 학생들의 번호는 체격 순으로 매겨져 있어, 바로 앞번호의 학생이나 바로 뒷번호의 학생에게만 체육복을 빌려줄 수 있습니다.
 * 예를 들어, 4번 학생은 3번 학생이나 5번 학생에게만 체육복을 빌려줄 수 있습니다.
 * 체육복이 없으면 수업을 들을 수 없기 때문에 체육복을 적절히 빌려 최대한 많은 학생이 체육수업을 들어야 합니다.
 * <p>
 * 전체 학생의 수 n, 체육복을 도난당한 학생들의 번호가 담긴 배열 lost,
 * 여벌의 체육복을 가져온 학생들의 번호가 담긴 배열 reserve가 매개변수로 주어질 때,
 * 체육수업을 들을 수 있는 학생의 최댓값을 return 하도록 solution 함수를 작성해주세요.
 * <p>
 * 제한사항
 * 전체 학생의 수는 2명 이상 30명 이하입니다.
 * 체육복을 도난당한 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
 * 여벌의 체육복을 가져온 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
 * 여벌 체육복이 있는 학생만 다른 학생에게 체육복을 빌려줄 수 있습니다.
 * 여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다.
 * 이때 이 학생은 체육복을 하나만 도난당했다고 가정하며, 남은 체육복이 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다.
 * <p>
 * 작성일 : 2023.06.27
 */
class P42862 {
    public int solution(int n, int[] lost, int[] reserve) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(String.valueOf(i), 1);
            for (int l : lost) {
                if (i == l) {
                    map.put(String.valueOf(i), map.get(String.valueOf(i)) - 1);
                }
            }
            for (int r : reserve) {
                if (i == r) {
                    map.put(String.valueOf(i), map.get(String.valueOf(i)) + 1);
                }
            }
        }

        LinkedHashMap<String, Integer> leftFirst = new LinkedHashMap<>(map);
        LinkedHashMap<String, Integer> rightFirst = new LinkedHashMap<>(map);

        getLeft(leftFirst);
        getRight(leftFirst);

        getRight(rightFirst);
        getLeft(rightFirst);

        return Math.max(getCount(leftFirst), getCount(rightFirst));
    }

    public void getLeft(LinkedHashMap<String, Integer> map) {
        for (int i = 1; i <= map.size(); i++) {
            if (i > 1) {
                if (map.get(String.valueOf(i)) == 0 && map.get(String.valueOf(i - 1)) > 1) {
                    map.put(String.valueOf(i - 1), map.get(String.valueOf(i - 1)) - 1);
                    map.put(String.valueOf(i), map.get(String.valueOf(i)) + 1);
                }
            }
        }
    }

    public void getRight(LinkedHashMap<String, Integer> map) {
        for (int i = 1; i <= map.size(); i++) {
            if (i < map.size()) {
                if (map.get(String.valueOf(i)) == 0 && map.get(String.valueOf(i + 1)) > 1) {
                    map.put(String.valueOf(i + 1), map.get(String.valueOf(i + 1)) - 1);
                    map.put(String.valueOf(i), map.get(String.valueOf(i)) + 1);
                }
            }
        }
    }

    public int getCount(LinkedHashMap<String, Integer> map) {
        int count = 0;
        for (int i = 1; i <= map.size(); i++) {
            if (map.get(String.valueOf(i)) > 0) {
                count++;
            }
        }
        return count;
    }

    // 다른 사람의 풀이 1
    public int solution1(int n, int[] lost, int[] reserve) {
        int[] people = new int[n];
        int answer = n;

        for (int l : lost)
            people[l - 1]--;
        for (int r : reserve)
            people[r - 1]++;

        for (int i = 0; i < people.length; i++) {
            if (people[i] == -1) {
                if (i - 1 >= 0 && people[i - 1] == 1) {
                    people[i]++;
                    people[i - 1]--;
                } else if (i + 1 < people.length && people[i + 1] == 1) {
                    people[i]++;
                    people[i + 1]--;
                } else
                    answer--;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        P42862 problem = new P42862();
        System.out.println(problem.solution(5, new int[]{4, 2}, new int[]{3, 5}));
    }
}

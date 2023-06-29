package com.jake.argorithm.programmers.lv1;

import java.util.*;

/**
 * [ 대충 만든 자판 ]
 * <p>
 * 휴대폰의 자판은 컴퓨터 키보드 자판과는 다르게 하나의 키에 여러 개의 문자가 할당될 수 있습니다.
 * 키 하나에 여러 문자가 할당된 경우, 동일한 키를 연속해서 빠르게 누르면 할당된 순서대로 문자가 바뀝니다.
 * <p>
 * 예를 들어, 1번 키에 "A", "B", "C" 순서대로 문자가 할당되어 있다면
 * 1번 키를 한 번 누르면 "A", 두 번 누르면 "B", 세 번 누르면 "C"가 되는 식입니다.
 * <p>
 * 같은 규칙을 적용해 아무렇게나 만든 휴대폰 자판이 있습니다.
 * 이 휴대폰 자판은 키의 개수가 1개부터 최대 100개까지 있을 수 있으며,
 * 특정 키를 눌렀을 때 입력되는 문자들도 무작위로 배열되어 있습니다.
 * 또, 같은 문자가 자판 전체에 여러 번 할당된 경우도 있고,
 * 키 하나에 같은 문자가 여러 번 할당된 경우도 있습니다.
 * 심지어 아예 할당되지 않은 경우도 있습니다.
 * 따라서 몇몇 문자열은 작성할 수 없을 수도 있습니다.
 * <p>
 * 이 휴대폰 자판을 이용해 특정 문자열을 작성할 때,
 * 키를 최소 몇 번 눌러야 그 문자열을 작성할 수 있는지 알아보고자 합니다.
 * <p>
 * 1번 키부터 차례대로 할당된 문자들이 순서대로 담긴 문자열배열 keymap과
 * 입력하려는 문자열들이 담긴 문자열 배열 targets가 주어질 때,
 * 각 문자열을 작성하기 위해 키를 최소 몇 번씩 눌러야 하는지 순서대로 배열에 담아 return 하는 solution 함수를 완성해 주세요.
 * <p>
 * 단, 목표 문자열을 작성할 수 없을 때는 -1을 저장합니다.
 * <p>
 * 제한사항
 * 1 ≤ keymap의 길이 ≤ 100
 * 1 ≤ keymap의 원소의 길이 ≤ 100
 * keymap[i]는 i + 1번 키를 눌렀을 때 순서대로 바뀌는 문자를 의미합니다.
 * 예를 들어 keymap[0] = "ABACD" 인 경우 1번 키를 한 번 누르면 A, 두 번 누르면 B, 세 번 누르면 A 가 됩니다.
 * keymap의 원소의 길이는 서로 다를 수 있습니다.
 * keymap의 원소는 알파벳 대문자로만 이루어져 있습니다.
 * 1 ≤ targets의 길이 ≤ 100
 * 1 ≤ targets의 원소의 길이 ≤ 100
 * targets의 원소는 알파벳 대문자로만 이루어져 있습니다.
 * <p>
 * 작성일 : 2023.06.29
 */
class P160586 {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        for (int i = 0; i < targets.length; i++) {
            String[] list = targets[i].split("");
            int count = 0;
            for (String e : list) {
                LinkedList<Integer> indexes = new LinkedList<>();
                for (String key : keymap) {
                    if (key.contains(e)) {
                        indexes.add(key.indexOf(e) + 1);
                    }
                    Collections.sort(indexes);
                }
                if (indexes.size() > 0) {
                    count += indexes.getFirst();
                } else {
                    count = 0;
                    break;
                }
            }
            if (count == 0) {
                answer[i] = -1;
            } else {
                answer[i] = count;
            }
        }

        return answer;
    }

    // 다른 사람의 풀이 1
    public int[] solution1(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        for (int i = 0; i < targets.length; i++) { //타겟 중 1개
            int result = 0;
            for (int j = 0; j < targets[i].length(); j++) { // 타켓의 문자
                List<Integer> list = new ArrayList<>();
                for (int k = 0; k < keymap.length; k++) { // keyMap 개수 비교
                    if (keymap[k].contains(String.valueOf(targets[i].charAt(j)))) {
                        list.add(keymap[k].indexOf(String.valueOf(targets[i].charAt(j))) + 1);
                    }
                }
                if (list.isEmpty()) {
                    result = -1;
                } else {
                    result += Collections.min(list);
                }
            }
            answer[i] += result;
        }
        return answer;
    }

    // 다른 사람의 풀이 2
    public int[] solution2(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];

        Set<String> set = new HashSet<>(Arrays.asList(keymap));
        set.remove("");

        for (int i = 0; i < targets.length; i++) { // targets

            for (int l = 0; l < targets[i].length(); l++) { // char
                int k = 101;

                for (String j : set) { // set
                    int index = j.indexOf(targets[i].charAt(l));

                    if (index == -1) continue;
                    else if (index < k) k = index;
                }


                if (k == 101) {
                    answer[i] = -1;
                    break;
                } else answer[i] += (k + 1);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        P160586 problem = new P160586();
        System.out.println(Arrays.toString(problem.solution(new String[]{"ABDE"}, new String[]{"ABCE"})));
    }
}

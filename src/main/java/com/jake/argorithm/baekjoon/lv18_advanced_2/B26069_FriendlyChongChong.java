package com.jake.argorithm.baekjoon.lv18_advanced_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * [ 붙임성 좋은 총총이 ]
 * <p>
 * 무지개 댄스를 추는 총총과 그걸 보는 총총
 * <p>
 * 총총이는 친구 곰곰이의 소개로 제2회 곰곰컵에 출연할 기회를 얻었다!
 * 총총이는 자신의 묘기인 무지개 댄스를 선보여, 여러분의 환심을 사려 한다.
 * 이 댄스는 중독성이 강하기 때문에, 한번 보게 된 사람은 모두 따라 하게 돼버린다.
 * <p>
 * 무지개 댄스를 추는 총총 2마리
 * <p>
 * 사람들이 만난 기록이 시간 순서대로 $N$개 주어진다. (총총이는 토끼이지만 이 문제에서는 편의상 사람이라고 가정한다.)
 * <p>
 * 무지개 댄스를 추지 않고 있던 사람이 무지개 댄스를 추고 있던 사람을 만나게 된다면,
 * 만난 시점 이후로 무지개 댄스를 추게 된다.
 * <p>
 * 기록이 시작되기 이전 무지개 댄스를 추고 있는 사람은 총총이 뿐이라고 할 때,
 * 마지막 기록 이후 무지개 댄스를 추는 사람이 몇 명인지 구해보자!
 * <p>
 * 입력
 * 첫번째 줄에는 사람들이 만난 기록의 수 $N\ (1 \le N \le 1\ 000)$이 주어진다.
 * 두번째 줄부터 $N$개의 줄에 걸쳐 사람들이 만난 기록이 주어진다.
 * $i + 1$번째 줄에는 $i$번째로 만난 사람들의 이름 $A_i$와 $B_i$가 공백을 사이에 두고 주어진다.
 * $A_i$와 $B_i$는 숫자와 영문 대소문자로 이루어진 최대 길이 $20$의 문자열이며, 서로 같지 않다.
 * 총총이의 이름은 ChongChong으로 주어지며, 기록에서 1회 이상 주어진다.
 * 동명이인은 없으며, 사람의 이름은 대소문자를 구분한다. (ChongChong과 chongchong은 다른 이름이다.)
 * <p>
 * 출력
 * 마지막 기록 이후 무지개 댄스를 추는 사람의 수를 출력하라.
 * <p>
 * 작성일 : 2023.08.25
 */
class B26069_FriendlyChongChong {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> meeters = new HashMap<>();

        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            String meet = br.readLine();
            String a = meet.substring(0, meet.indexOf(" "));
            String b = meet.substring(meet.indexOf(" ") + 1);

            if (a.equals("ChongChong") || b.equals("ChongChong")) {
                meeters.put(a, 1);
                meeters.put(b, 1);
            }

            if (meeters.get(a) != null && meeters.get(a) == 1) {
                meeters.put(a, 1);
                meeters.put(b, 1);
            }

            if (meeters.get(b) != null && meeters.get(b) == 1) {
                meeters.put(a, 1);
                meeters.put(b, 1);
            }

            meeters.putIfAbsent(a, 0);
            meeters.putIfAbsent(b, 0);
        }

        int count = 0;
        for (Map.Entry<String, Integer> meeter : meeters.entrySet()) {
            if (meeter.getValue() == 1) {
                count++;
            }
        }

        System.out.println(count);
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        HashMap<String, Integer> map = new HashMap<>();

        int N = Integer.parseInt(br.readLine());

        String input, input2;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            input = st.nextToken();
            input2 = st.nextToken();

            if (("ChongChong").equals(input)) {
                map.put(input, 0);
                map.put(input2, 0);
            } else if (map.containsKey(input)) map.put(input2, 0);

            if (("ChongChong").equals(input2)) {
                map.put(input, 0);
                map.put(input2, 0);
            } else if (map.containsKey(input2)) map.put(input, 0);
        }

        System.out.println(map.size());
    }
}

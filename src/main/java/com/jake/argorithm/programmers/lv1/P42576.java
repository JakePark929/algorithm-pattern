package com.jake.argorithm.programmers.lv1;

import java.util.Arrays;
import java.util.HashMap;

/**
 * [ 완주하지 못한 선수 ]
 *
 * 수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.
 *
 * 마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때, 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한사항
 * 마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
 * completion의 길이는 participant의 길이보다 1 작습니다.
 * 참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
 * 참가자 중에는 동명이인이 있을 수 있습니다.
 *
 * 작성일 : 2023.06.19
 */
public class P42576 {
    public String solution(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);

        for (int i = 0; i < completion.length; i++) {
            if(participant[i].hashCode() != completion[i].hashCode()) {
                return participant[i];
            }
        }

        return participant[participant.length - 1];
    }

    // 다른 사람의 풀이 1
    public String solution2(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> hm = new HashMap<>();
        for (String player : participant) hm.put(player, hm.getOrDefault(player, 0) + 1);
        for (String player : completion) hm.put(player, hm.get(player) - 1);

        System.out.println(hm);

        for (String key : hm.keySet()) {
            if (hm.get(key) != 0){
                answer = key;
            }
        }
        return answer;
    }

    // 다른 사람의 풀이 2 - HashMap 직접구현
    int bucketSize;
    List<Entry>[] bucket;

    public String solution3(String[] participant, String[] completion) {
        bucketSize = (completion.length / 5)+1;
        bucket = new List[bucketSize];

        for (int i = 0; i < completion.length; i++) {
            Entry entry = get(completion[i]);
            entry.value += 1;
        }

        for (int i = 0; i < participant.length; i++) {
            Entry entry = get(participant[i]);
            if (entry != null && entry.value > 0) {
                entry.value -= 1;
            } else {
                return entry.key;
            }
        }
        throw new RuntimeException("error");
    }

    private Entry get(String s) {
        int idx = hash(s);
        List<Entry> list = bucket[idx];
        if (list == null) {
            list = new List<Entry>();
            Entry entry = new Entry(s, 0);
            list.add(entry);
            bucket[idx] = list;
            return entry;
        } else {
            Entry entry = list.get(s);
            if (entry == null) {
                entry = new Entry(s, 0);
                list.add(entry);
            }
            return entry;
        }
    }


    private int hash(String s) {
        int num = 0;
        for(int i=0; i<s.length(); i++) {
            num += s.codePointAt(i) * 31 + s.codePointAt(i);
        }
        return num % bucketSize;
    }

    class Entry {
        String key;
        int value;

        public Entry(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    class List<T extends Entry> {
        Node head;

        public void add(T entry) {
            Node nn = new Node(entry, null);

            if (head == null) {
                head = nn;
            } else {
                Node last = head;
                while (last.next != null) {
                    last = last.next;
                }
                last.next = nn;
            }
        }

        public <T extends Entry> T get(String s) {
            Node node = head;
            while (node != null) {
                if (node.data.key.equals(s)) {
                    return (T) node.data;
                }
                node = node.next;
            }

            return null;
        }

        class Node<T extends Entry> {
            T data;
            Node next;

            public Node(T data, Node next) {
                this.data = data;
                this.next = next;
            }
        }
    }


    public static void main(String[] args) {
        P42576 problem = new P42576();
        System.out.println(problem.solution2(new String[]{"mislav", "stanko", "mislav", "ana"}, new String[]{"stanko", "ana", "mislav"}));
    }
}

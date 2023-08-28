package com.jake.argorithm.baekjoon.lv18_advanced_2;

import java.io.*;
import java.util.*;

/**
 * [ 영단어 암기는 괴로워 ]
 *
 * 화은이는 이번 영어 시험에서 틀린 문제를 바탕으로 영어 단어 암기를 하려고 한다.
 * 그 과정에서 효율적으로 영어 단어를 외우기 위해 영어 단어장을 만들려 하고 있다.
 * 화은이가 만들고자 하는 단어장의 단어 순서는 다음과 같은 우선순위를 차례로 적용하여 만들어진다.
 *
 * 자주 나오는 단어일수록 앞에 배치한다.
 * 해당 단어의 길이가 길수록 앞에 배치한다.
 * 알파벳 사전 순으로 앞에 있는 단어일수록 앞에 배치한다
 *
 * $M$보다 짧은 길이의 단어의 경우 읽는 것만으로도 외울 수 있기 때문에 길이가 $M$이상인 단어들만 외운다고 한다.
 * 화은이가 괴로운 영단어 암기를 효율적으로 할 수 있도록 단어장을 만들어 주자.
 *
 * 입력
 * 첫째 줄에는 영어 지문에 나오는 단어의 개수 $N$과
 * 외울 단어의 길이 기준이 되는 $M$이 공백으로 구분되어 주어진다. ($1 \leq N \leq 100\,000$, $1 \leq M \leq 10$)
 * 둘째 줄부터 $N+1$번째 줄까지 외울 단어를 입력받는다.
 * 이때의 입력은 알파벳 소문자로만 주어지며 단어의 길이는 $10$을 넘지 않는다.
 * 단어장에 단어가 반드시 1개 이상 존재하는 입력만 주어진다.
 *
 * 출력
 * 화은이의 단어장에 들어 있는 단어를 단어장의 앞에 위치한 단어부터 한 줄에 한 단어씩 순서대로 출력한다.
 *
 * 작성일 : 2023.08.25
 */
class B20920_ItsHardMemorizeEngWord {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s.substring(0, s.indexOf(" ")));
        int m = Integer.parseInt(s.substring(s.indexOf(" ") + 1));

        LinkedHashMap<String, Integer> notes = new LinkedHashMap<>();

        while (n-- > 0) {
            String word = br.readLine();
            if (notes.get(word) != null) {
                notes.put(word, notes.get(word) + 1);
            } else {
                if (word.length() >= m) notes.put(word, 0);
            }
        }

        List<Map.Entry<String, Integer>> entryList = new LinkedList<>(notes.entrySet());

        Comparator<Map.Entry<String, Integer>> valueComparator = (entry1, entry2) -> {
            int valueCompare = entry2.getValue().compareTo(entry1.getValue());
            if (valueCompare == 0) {
                int lengthCompare = Integer.compare(entry2.getKey().length(), entry1.getKey().length());
                if (lengthCompare == 0) {
                    return entry1.getKey().compareTo(entry2.getKey());
                }
                return lengthCompare;
            }
            return valueCompare;
        };

        entryList.sort(valueComparator);

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> map : entryList) {
            sb.append(map.getKey()).append('\n');
        }

        System.out.println(sb.toString().trim());
    }

    // 다른 사람의 풀이 1
    static final char LF = '\n';

    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        HashMap <String, Word> map = new HashMap <>();

        String [] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int minimumLength = Integer.parseInt(input[1]);
        String name;
        Word w;

        while (n-- > 0) {
            name = br.readLine();
            w = map.get(name);

            if (name.length() < minimumLength)
                continue;

            if (w == null)
                map.put(name, new Word(name));
            else
                w.setFrequency();
        }
        br.close();

        TreeSet <Word> set = new TreeSet <>(map.values());
        Iterator <Word> it = set.iterator();

        while (it.hasNext())
            sb.append(it.next().word).append(LF);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // 다른 사람의 풀이 2
    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        TreeMap<String, Integer> map = new TreeMap<>((o1, o2) -> {
            if (o1.length() != o2.length()) return -(o1.length() - o2.length());
            else return o1.compareTo(o2);
        });

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            if (word.length() < M) continue;
            if (map.containsKey(word)) map.put(word, map.get(word) + 1);
            else map.put(word, 0);
        }

        List<String> result = new ArrayList<>(map.keySet());
        result.sort((o1, o2) -> -(map.get(o1) - map.get(o2)));

        for (String s : result) sb.append(s).append("\n");
        System.out.println(sb);
    }
}

class Word implements Comparable <Word> {
    int frequency;
    int wordLength;
    String word;

    Word(String word) {
        this.word = word;
        wordLength = word.length();
        frequency = 1;
    }

    public void setFrequency() { this.frequency++; }

    @Override
    public int compareTo(Word w) {
        if (frequency < w.frequency)
            return 1;
        else if (frequency == w.frequency) {
            if (wordLength == w.wordLength)
                return word.compareTo(w.word);
            else
                return w.wordLength - wordLength;
        }
        else
            return -1;
    }
}



package com.jake.algorithm.baekjoon.lv14_set_map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [ 듣보잡 ]
 *
 * 김진영이 듣도 못한 사람의 명단과,
 * 보도 못한 사람의 명단이 주어질 때,
 * 듣도 보도 못한 사람의 명단을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 듣도 못한 사람의 수 N,
 * 보도 못한 사람의 수 M이 주어진다.
 * 이어서 둘째 줄부터 N개의 줄에 걸쳐 듣도 못한 사람의 이름과,
 * N+2째 줄부터 보도 못한 사람의 이름이 순서대로 주어진다.
 * 이름은 띄어쓰기 없이 알파벳 소문자로만 이루어지며, 그 길이는 20 이하이다.
 * N, M은 500,000 이하의 자연수이다.
 *
 * 듣도 못한 사람의 명단에는 중복되는 이름이 없으며, 보도 못한 사람의 명단도 마찬가지이다.
 *
 * 출력
 * 듣보잡의 수와 그 명단을 사전순으로 출력한다.
 *
 * 작성일 : 2023.08.07
 */
class B1764_DudBoJob {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s.substring(0 , s.indexOf(" ")));
        int m = Integer.parseInt(s.substring(s.indexOf(" ") + 1));

        HashMap<String, Integer> dudBoJobs = new HashMap<>();

        for (int i = 0; i < n; i++) {
            dudBoJobs.put(br.readLine(), 0);
        }

        for (int i = 0; i < m; i++) {
            String man = br.readLine();
            if (dudBoJobs.containsKey(man)) dudBoJobs.put(man, dudBoJobs.get(man) + 1);
            else dudBoJobs.put(man, 0);
        }

        int count = 0;
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : dudBoJobs.entrySet()) {
            if (entry.getValue() > 0) {
                list.add(entry.getKey());
                count++;
            }
        }

        Collections.sort(list);

        StringBuilder sb = new StringBuilder().append(count).append('\n');
        for (String man : list) {
            sb.append(man).append('\n');
        }

        System.out.println(sb.toString().trim());
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] nArr = new String[n];
        String[] mArr = new String[m];

        for (int i = 0; i < n; i++)
            nArr[i] = br.readLine();
        for (int i = 0; i < m; i++)
            mArr[i] = br.readLine();

        Arrays.sort(nArr);
        Arrays.sort(mArr);

        StringBuilder sb = new StringBuilder();
        int nIdx = 0, mIdx = 0;
        int count = 0;
        while (nIdx < n && mIdx < m) {
            int cmp = nArr[nIdx].compareTo(mArr[mIdx]);
            if (cmp < 0) {
                nIdx++;
            }
            else if (cmp > 0) {
                mIdx++;
            }
            else {
                sb.append(nArr[nIdx]).append('\n');
                count++;
                nIdx++;
                mIdx++;
            }
        }

        System.out.println(count);
        System.out.print(sb);
    }
}

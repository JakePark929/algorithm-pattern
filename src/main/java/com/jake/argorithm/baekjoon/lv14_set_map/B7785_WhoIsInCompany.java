package com.jake.argorithm.baekjoon.lv14_set_map;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [ 회사에 있는 사람 ]
 *
 * 상근이는 세계적인 소프트웨어 회사 기글에서 일한다.
 * 이 회사의 가장 큰 특징은 자유로운 출퇴근 시간이다.
 * 따라서, 직원들은 반드시 9시부터 6시까지 회사에 있지 않아도 된다.
 * 각 직원은 자기가 원할 때 출근할 수 있고, 아무때나 퇴근할 수 있다.
 * 상근이는 모든 사람의 출입카드 시스템의 로그를 가지고 있다.
 * 이 로그는 어떤 사람이 회사에 들어왔는지, 나갔는지가 기록되어져 있다.
 * 로그가 주어졌을 때, 현재 회사에 있는 모든 사람을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 로그에 기록된 출입 기록의 수 n이 주어진다. (2 ≤ n ≤ 106)
 * 다음 n개의 줄에는 출입 기록이 순서대로 주어지며,
 * 각 사람의 이름이 주어지고 "enter"나 "leave"가 주어진다.
 * "enter"인 경우는 출근, "leave"인 경우는 퇴근이다.
 *
 * 회사에는 동명이인이 없으며, 대소문자가 다른 경우에는 다른 이름이다.
 * 사람들의 이름은 알파벳 대소문자로 구성된 5글자 이하의 문자열이다.
 *
 * 출력
 * 현재 회사에 있는 사람의 이름을 사전 순의 역순으로 한 줄에 한 명씩 출력한다.
 *
 * 작성일 : 2023.08.07
 */
class B7785_WhoIsInCompany {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashMap<String, String> employees = new HashMap<>();

        while (n-- > 0) {
            String s = br.readLine();
            employees.put(s.substring(0, s.indexOf(" ")), s.substring(s.indexOf(" ") + 1));
        }

        ArrayList<String> enters = new ArrayList<>();

        for (Map.Entry<String, String> entry : employees.entrySet()) {
            if (entry.getValue().equals("enter")) {
                enters.add(entry.getKey());
            }
        }

        enters.sort(Collections.reverseOrder());

        StringBuilder sb = new StringBuilder();
        for (String enter : enters) {
            sb.append(enter).append('\n');
        }

        System.out.println(sb.toString().trim());
    }

    // 다른 사람의 풀이 1
    static class Reader {
        private final int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private byte[] string;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            string = new byte[10];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();

            while(c <= ' ')
                c = read();

            boolean neg = (c == '-');
            if(neg)
                c = read();

            do{
                ret = ret * 10 + c - '0';
            } while((c = read())>='0' && c <= '9');

            if(neg)
                return -ret;
            return ret;
        }

        public String next() throws IOException {
            int i = 0;
            int c = read();

            while(c <= ' ')
                c = read();

            do {
                string[i++] = buffer[bufferPointer-1];
            } while((c = read()) >= 'A' && c <= 'z');

            return new String(string, 0, i);

        }
        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if(bytesRead == -1)
                buffer[0] = -1;
        }
        private byte read() throws IOException {
            if(bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
    }
    public static void main1(String[] args) throws IOException {
        Reader r = new Reader();
        int N = r.nextInt();

        TreeSet<String> treeSet = new TreeSet<>();
        while(N-- > 0) {
            String name = r.next();
            String enter = r.next();
            if(enter.equals("enter")) {
                treeSet.add(name);
            } else {
                treeSet.remove(name);
            }
        }

        StringBuilder sb = new StringBuilder();


        for(Iterator<String> itr = treeSet.descendingIterator(); itr.hasNext();) {
            sb.append(itr.next()).append("\n");
        }

        System.out.println(sb);
    }
}

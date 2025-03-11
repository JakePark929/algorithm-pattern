package com.jake.algorithm.baekjoon.lv22_prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 인간-컴퓨터 상호작용 ]
 *
 * 승재는 인간-컴퓨터 상호작용에서 생체공학 설계를 공부하다가 키보드 자판이 실용적인지 궁금해졌다.
 * 이를 알아보기 위해 승재는 다음과 같은 생각을 했다.
 *
 * '문자열에서 특정 알파벳이 몇 번 나타나는지 알아봐서
 * 자주 나타나는 알파벳이 중지나 검지 위치에 오는 알파벳인지 확인하면 실용적인지 확인할 수 있을 것이다.'
 *
 * 승재를 도와 특정 문자열 $S$, 특정 알파벳 $\alpha$와 문자열의 구간 $[l,r]$이 주어지면
 * $S$의 $l$번째 문자부터 $r$번째 문자 사이에 $\alpha$가 몇 번 나타나는지 구하는 프로그램을 작성하여라.
 * 승재는 문자열의 문자는 $0$번째부터 세며, $l$번째와 $r$번째 문자를 포함해서 생각한다.
 * 주의할 점은 승재는 호기심이 많기에 (통계적으로 크게 무의미하지만) 같은 문자열을 두고 질문을 $q$번 할 것이다.
 *
 * 입력
 * 첫 줄에 문자열 $S$가 주어진다.
 * 문자열의 길이는 $200,000$자 이하이며 알파벳 소문자로만 구성되었다.
 * 두 번째 줄에는 질문의 수 $q$가 주어지며, 문제의 수는 $1\leq q\leq 200,000$을 만족한다.
 * 세 번째 줄부터 $(q+2)$번째 줄에는 질문이 주어진다.
 * 각 질문은 알파벳 소문자 $\alpha_i$와 $0\leq l_i\leq r_i<|S|$를 만족하는 정수 $l_i,r_i$가
 * 공백으로 구분되어 주어진다.
 *
 * 출력
 * 각 질문마다 줄을 구분해 순서대로 답변한다.
 * $i$번째 줄에 $S$의 $l_i$번째 문자부터 $r_i$번째 문자 사이에
 * $\alpha_i$가 나타나는 횟수를 출력한다.
 *
 * 서브태스크 1 (50점)
 * 문자열의 길이는 $2,000$자 이하, 질문의 수는 $2,000$개 이하이다.
 *
 * 서브태스크 2 (50점)
 * 추가 제한 조건이 없다.
 *
 * 작성일 : 2023.09.11
 */
class B16139_Human_Computer_Interaction {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int q = Integer.parseInt(br.readLine());

        int[][] memos = new int[s.length() + 1][26];

        memos[1][s.charAt(0) - 'a']++;

        for (int i = 2; i <= s.length(); i++) {
            int idx = s.charAt(i - 1) - 'a';
            for (int j = 0; j < 26; j++) {
                int before = memos[i - 1][j];
                memos[i][j] = j == idx ? before + 1 : before;
            }
        }

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = st.nextToken().charAt(0) - 'a';
            int prefix = Integer.parseInt(st.nextToken());
            int suffix = Integer.parseInt(st.nextToken()) + 1;

            sb.append(memos[suffix][idx] - memos[prefix][idx]).append('\n');
        }

        System.out.println(sb);
    }

    // 다른 사람의 풀이 1
    static final int SIZE = 200_000;
    static byte[] S = new byte[SIZE];
    static int stringLength = 0;

    public static void main1(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();

        readString();
        int[][] prefixSum = new int[27][stringLength + 1];

        for (int i = 1; i <= stringLength; i++) {
            for (int alphabet = 1; alphabet < 27; alphabet++) {
                prefixSum[alphabet][i] = prefixSum[alphabet][i - 1];
            }
            prefixSum[S[i - 1] & 31][i]++;
        }

        int Q = nextInt();

        while (Q-- > 0) {
            int alphabet = nextAlphabet();
            int start = nextInt();
            int end = nextInt() + 1;

            sb
                    .append(prefixSum[alphabet][end] - prefixSum[alphabet][start])
                    .append('\n');
        }

        System.out.println(sb);

    }

    static byte[] buffer = new byte[SIZE];
    static int index, size;
    static byte c;

    static void readString() throws Exception {
        while ((c = read()) > 32) S[stringLength++] = c;
    }

    static int nextAlphabet() throws Exception {
        while ((c = read()) <= 32);
        return c & 31;
    }

    static int nextInt() throws Exception {
        int n = 0;
        while ((c = read()) <= 32);
        do n = (n << 3) + (n << 1) + (c & 15);
        while ((c = read()) > 47 && c < 58);
        return n;
    }

    static byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}

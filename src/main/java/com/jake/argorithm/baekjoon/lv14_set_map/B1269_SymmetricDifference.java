package com.jake.argorithm.baekjoon.lv14_set_map;

import java.util.HashSet;

/**
 * [ 대칭 차집합 ]
 * <p>
 * 자연수를 원소로 갖는 공집합이 아닌 두 집합 A와 B가 있다.
 * 이때, 두 집합의 대칭 차집합의 원소의 개수를 출력하는 프로그램을 작성하시오.
 * 두 집합 A와 B가 있을 때, (A-B)와 (B-A)의 합집합을 A와 B의 대칭 차집합이라고 한다.
 * <p>
 * 예를 들어, A = { 1, 2, 4 } 이고,
 * B = { 2, 3, 4, 5, 6 } 라고 할 때,
 * A-B = { 1 } 이고, B-A = { 3, 5, 6 } 이므로,
 * 대칭 차집합의 원소의 개수는 1 + 3 = 4개이다.
 * <p>
 * 입력
 * 첫째 줄에 집합 A의 원소의 개수와 집합 B의 원소의 개수가 빈 칸을 사이에 두고 주어진다.
 * 둘째 줄에는 집합 A의 모든 원소가, 셋째 줄에는 집합 B의 모든 원소가 빈 칸을 사이에 두고 각각 주어진다.
 * 각 집합의 원소의 개수는 200,000을 넘지 않으며, 모든 원소의 값은 100,000,000을 넘지 않는다.
 * <p>
 * 출력
 * 첫째 줄에 대칭 차집합의 원소의 개수를 출력한다.
 * <p>
 * 작성일 : 2023.08.07
 */
class B1269_SymmetricDifference {
    public static void main(String[] args) throws Exception {
        int aNum = read();
        int bNum = read();

        HashSet<Integer> as = new HashSet<>();
        HashSet<Integer> bs = new HashSet<>();

        while (aNum-- > 0) as.add(read());
        while (bNum-- > 0) bs.add(read());

        int count = 0;

        for (int i : as) {
            if (!bs.contains(i)) count++;
        }

        for (int i : bs) {
            if (!as.contains(i)) count++;
        }

        System.out.println(count);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    // 다른 사람의 풀이 1
    static int read2() throws Exception {
        int i, j = System.in.read() & 15;
        boolean nega = false;
        if (j == 13) {
            nega = true;
            j = System.in.read() & 15;
        }
        while ((i = System.in.read()) > 32) j = (j << 3) + (j << 1) + (i & 15);
        if (i == 13) System.in.read();
        if (nega) return -j;
        else return j;
    }

    public static void main1(String[] args) throws Exception {
        int n = read2(), m = read2(), max = 0, num = 0, dupCount = 0;
        boolean[] aCount = new boolean[100_000_000];
        for (int i = 0; i < n; i++) {
            num = read2();
            if (max < num) max = num;
            aCount[num] = true;
        }
        for (int i = 0; i < m; i++) if (aCount[read2()]) dupCount++;
        System.out.print((m + n - 2 * dupCount));
    }
}

package com.jake.algorithm.baekjoon.lv10_geometry_rec_tri;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [ 네번째 점 ]
 *
 * 세 점이 주어졌을 때, 축에 평행한 직사각형을 만들기 위해서 필요한 네 번째 점을 찾는 프로그램을 작성하시오.
 *
 * 입력
 * 세 점의 좌표가 한 줄에 하나씩 주어진다. 좌표는 1보다 크거나 같고, 1000보다 작거나 같은 정수이다.
 *
 * 출력
 * 직사각형의 네 번째 점의 좌표를 출력한다.
 *
 * 작성일 : 23.08.01
 */
class B3009_ForthPoint {
    public static void main(String[] args) throws Exception {
        HashMap<String, Integer> xs = new HashMap<>();
        HashMap<String, Integer> ys = new HashMap<>();

        for(int i = 0; i < 3; i++) {
            String x = String.valueOf(read());
            String y = String.valueOf(read());

            xs.merge(x, 1, Integer::sum);
            ys.merge(y, 1, Integer::sum);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getMinKey(xs)).append(" ").append(getMinKey(ys));

        System.out.println(sb);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    private static String getMinKey(HashMap<String, Integer> hashMap) {
        int minVal = Integer.MAX_VALUE;
        String minKey = null;

        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            int currentValue = entry.getValue();
            if (currentValue < minVal) {
                minVal = currentValue;
                minKey = entry.getKey();
            }
        }

        return minKey;
    }

    // 다른 사람의 풀이 1
    static List<Integer> xList = new ArrayList<>();
    static List<Integer> yList = new ArrayList<>();

    public static void main1(String[] args) throws Exception {
        int n = 3;

        while(n-- > 0) {
            int x = read(), y = read();
            if(!xList.contains(x)) xList.add(x);
            else xList.remove((Integer) x);
            if(!yList.contains(y)) yList.add(y);
            else yList.remove((Integer) y);
        }

        StringBuilder sb = new StringBuilder().append(xList.get(0)).append(" ").append(yList.get(0));

        System.out.print(sb);
    }
}

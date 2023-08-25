package com.jake.argorithm.baekjoon.lv16_stack_queue_deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [ 풍선 터트리기 ]
 * <p>
 * 1번부터 N번까지 N개의 풍선이 원형으로 놓여 있고.
 * i번 풍선의 오른쪽에는 i+1번 풍선이 있고, 왼쪽에는 i-1번 풍선이 있다.
 * 단, 1번 풍선의 왼쪽에 N번 풍선이 있고, N번 풍선의 오른쪽에 1번 풍선이 있다.
 * 각 풍선 안에는 종이가 하나 들어있고, 종이에는 -N보다 크거나 같고, N보다 작거나 같은 정수가 하나 적혀있다.
 * 이 풍선들을 다음과 같은 규칙으로 터뜨린다.
 * <p>
 * 우선, 제일 처음에는 1번 풍선을 터뜨린다.
 * 다음에는 풍선 안에 있는 종이를 꺼내어 그 종이에 적혀있는 값만큼 이동하여 다음 풍선을 터뜨린다.
 * 양수가 적혀 있을 경우에는 오른쪽으로, 음수가 적혀 있을 때는 왼쪽으로 이동한다.
 * 이동할 때에는 이미 터진 풍선은 빼고 이동한다.
 * <p>
 * 예를 들어 다섯 개의 풍선 안에 차례로 3, 2, 1, -3, -1이 적혀 있었다고 하자.
 * 이 경우 3이 적혀 있는 1번 풍선,
 * -3이 적혀 있는 4번 풍선,
 * -1이 적혀 있는 5번 풍선,
 * 1이 적혀 있는 3번 풍선,
 * 2가 적혀 있는 2번 풍선의 순서대로 터지게 된다.
 * <p>
 * 입력
 * 첫째 줄에 자연수 N(1 ≤ N ≤ 1,000)이 주어진다.
 * 다음 줄에는 차례로 각 풍선 안의 종이에 적혀 있는 수가 주어진다. 종이에 0은 적혀있지 않다.
 * <p>
 * 출력
 * 첫째 줄에 터진 풍선의 번호를 차례로 나열한다.
 * <p>
 * 작성일 : 2023.08.24
 */
class B2346_PopBalloon {
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;
        if (isNegative) n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return isNegative ? ~n + 1 : n;
    }

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();

        int n = read();
        Deque<int[]> balloons = new ArrayDeque<>();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = read();
        }

        sb.append("1 ");
        int in = arr[0];

        for (int i = 1; i < n; i++) {
            balloons.add(new int[]{(i + 1), arr[i]});
        }

        while (!balloons.isEmpty()) {
            if (in > 0) {
                for (int i = 1; i < in; i++) {
                    balloons.add(balloons.poll());
                }

                int[] next = balloons.poll();
                assert next != null;
                in = next[1];
                sb.append(next[0]).append(" ");
            } else {
                for (int i = 1; i < -in; i++) {
                    balloons.addFirst(balloons.pollLast());
                }

                int[] next = balloons.pollLast();
                assert next != null;
                in = next[1];
                sb.append(next[0]).append(" ");
            }
        }

        System.out.println(sb.toString().trim());
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        //백준 2346 풍선 터뜨리기

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<int[]> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String[] s = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            list.add(new int[]{i + 1, Integer.parseInt(s[i])});
        }

        int idx = 0;
        while (list.size() != 1) {
            int move = list.get(idx)[1];
            int size = list.size() - 1;

            sb.append(list.get(idx)[0]).append(" ");
            list.remove(idx);

            if (move > 0) {
                move--;
            }

            idx = (idx + move) % size;

            if (idx < 0) {
                idx += size;
            }
        }

        sb.append(list.get(0)[0]);
        System.out.println(sb);
    }

    // 다른사람의 풀이 2
    public static void main2(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int[] list = new int[n];

        ArrayList<Balloon> arrayList = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            list[i] = Integer.parseInt(st.nextToken());
            arrayList.add(new Balloon(i + 1, list[i]));
        }

        int point = 0;
        while (arrayList.size() != 1) {
            Balloon b = arrayList.get(point);
            sb.append(b.num).append(" ");
            arrayList.remove(point);

            if (b.value >= 0) {
                point += b.value - 1;
                while (point >= arrayList.size()) {
                    point -= arrayList.size();
                }
            } else {
                point += b.value;
                while (point < 0) {
                    point += arrayList.size();
                }
            }
        }

        sb.append(arrayList.get(0).num);
        System.out.println(sb);
    }
}

class Balloon {
    int num;
    int value;

    public Balloon(int num, int value) {
        this.num = num;
        this.value = value;
    }
}

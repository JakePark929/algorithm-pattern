package com.jake.argorithm.baekjoon.lv16_stack_queue_deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [ 요세푸스 문제 0 ]
 * <p>
 * 요세푸스 문제는 다음과 같다.
 * 1번부터 N번까지 N명의 사람이 원을 이루면서 앉아있고, 양의 정수 K(≤ N)가 주어진다.
 * 이제 순서대로 K번째 사람을 제거한다.
 * 한 사람이 제거되면 남은 사람들로 이루어진 원을 따라 이 과정을 계속해 나간다.
 * 이 과정은 N명의 사람이 모두 제거될 때까지 계속된다.
 * 원에서 사람들이 제거되는 순서를 (N, K)-요세푸스 순열이라고 한다.
 * 예를 들어 (7, 3)-요세푸스 순열은 <3, 6, 2, 7, 5, 1, 4>이다.
 * <p>
 * N과 K가 주어지면 (N, K)-요세푸스 순열을 구하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 N과 K가 빈 칸을 사이에 두고 순서대로 주어진다. (1 ≤ K ≤ N ≤ 1,000)
 * <p>
 * 출력
 * 예제와 같이 요세푸스 순열을 출력한다.
 * <p>
 * 작성일 : 2023.08.24
 */
class B11866_JosephusProblem_0 {
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    public static void main(String[] args) throws Exception {
        int n = read(), k = read();

        int[] josephs = new int[n];
        ArrayList<Integer> lists = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            josephs[i - 1] = i;
        }

        int counter = -1;
        while (!areAllElementsNull(josephs)) {
            for (int i = 0; i < k; i++) {
                counter += 1;
                while (josephs[counter % n] < 0) counter += 1;
            }
            lists.add(josephs[counter % n]);
            josephs[counter % n] = -1;
        }

        StringBuilder sb = new StringBuilder("<");
        int max = lists.size();
        for (int i = 0; i < lists.size(); i++) {
            sb.append(lists.get(i));
            if (i + 1 == max) {
                sb.append(">");
                break;
            }
            sb.append(", ");
        }

        System.out.println(sb);
    }

    public static boolean areAllElementsNull(int[] array) {
        for (int element : array) {
            if (element != -1) {
                return false;
            }
        }
        return true;
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(0);

        for (int i = 1; i <= n; i++) {
            arr.add(i);
        }

        int k = Integer.parseInt(st.nextToken());
        int index = k;

        sb.append("<");

        while (true) {
            sb.append(arr.get(index));
            arr.remove(index);
            if (arr.size() == 1) {
                sb.append(">");
                break;
            }
            sb.append(", ");
            index += k - 1;
            while (true) {
                if (index > arr.size() - 1) {
                    index -= (arr.size() - 1);
                } else {
                    break;
                }
            }
        }

        System.out.println(sb);
    }

    // 다른 사람의 풀이 2
    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<Integer> list = new LinkedList<Integer>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= N; i++) {
            list.add(i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append('<');

        int index = 0;	// 리스트에서 삭제할 요소를 참조할 인덱스 변수

        while(N > 1) {
            index = (index + (K - 1)) % N;

            // index위치에 있는 요소를 삭제함과 동시에 출력
            sb.append(list.remove(index)).append(", ");
            N--;
        }

        // 마지막으로 남은 요소 삭제함과 동시에 출력
        sb.append(list.remove()).append('>');
        System.out.println(sb);
    }

    // 다른 사람의 풀이 3
    public static void main3(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();

        for(int i = 1; i <= N; i++) {
            q.add(i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append('<');

        /*
         *  마지막 부분의 출력은 > 괄호 전에 공백이 없기 때문에
         *  일괄적으로 출력하기 위해 마지막 원소만 남겨질 때까지만
         *  반복하고 마지막 원소는 그대로 출력한다.
         */
        while(q.size() > 1) {
            for(int i = 0; i < K - 1; i++) {
                q.offer(q.poll());
            }

            sb.append(q.poll()).append(", ");
        }

        // 마지막 원소 출력한 뒤 > 도 붙여준다.
        sb.append(q.poll()).append('>');
        System.out.println(sb);
    }
}

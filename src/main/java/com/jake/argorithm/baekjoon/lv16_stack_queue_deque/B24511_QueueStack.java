package com.jake.argorithm.baekjoon.lv16_stack_queue_deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * [ queuestack ]
 * <p>
 * 한가롭게 방학에 놀고 있던 도현이는 갑자기 재밌는 자료구조를 생각해냈다. 그 자료구조의 이름은 queuestack이다.
 * <p>
 * queuestack의 구조는 다음과 같다. $1$번, $2$번, ... , $N$번의 자료구조(queue 혹은 stack)가 나열되어있으며,
 * 각각의 자료구조에는 한 개의 원소가 들어있다.
 * queuestack의 작동은 다음과 같다.
 * <p>
 * <p>
 * $x_0$을 입력받는다.
 * <p>
 * $x_0$을 $1$번 자료구조에 삽입한 뒤 $1$번 자료구조에서 원소를 pop한다. 그때 pop된 원소를 $x_1$이라 한다.
 * <p>
 * $x_1$을 $2$번 자료구조에 삽입한 뒤 $2$번 자료구조에서 원소를 pop한다. 그때 pop된 원소를 $x_2$이라 한다.
 * ...
 * <p>
 * $x_{N-1}$을 $N$번 자료구조에 삽입한 뒤 $N$번 자료구조에서 원소를 pop한다. 그때 pop된 원소를 $x_N$이라 한다.
 * <p>
 * $x_N$을 리턴한다.
 * <p>
 * <p>
 * 도현이는 길이 $M$의 수열 $C$를 가져와서 수열의 원소를 앞에서부터 차례대로 queuestack에 삽입할 것이다.
 * 이전에 삽입한 결과는 남아 있다. (예제 $1$ 참고)
 * <p>
 * queuestack에 넣을 원소들이 주어졌을 때, 해당 원소를 넣은 리턴값을 출력하는 프로그램을 작성해보자.
 * <p>
 * 입력
 * 첫째 줄에 queuestack을 구성하는 자료구조의 개수 $N$이 주어진다. ($1 \leq N \leq 100\,000$)
 * 둘째 줄에 길이 $N$의 수열 $A$가 주어진다.
 * $i$번 자료구조가 큐라면 $A_i = 0$, 스택이라면 $A_i = 1$이다.
 * 셋째 줄에 길이$N$의 수열 $B$가 주어진다.
 * $B_i$는 $i$번 자료구조에 들어 있는 원소이다. ($1 \leq B_i \leq 1\,000\,000\,000$)
 * 넷째 줄에 삽입할 수열의 길이 $M$이 주어진다. ($1 \leq M \leq 100\,000$)
 * 다섯째 줄에 queuestack에 삽입할 원소를 담고 있는 길이 $M$의 수열 $C$가 주어진다. ($1 \leq C_i \leq 1\,000\,000\,000$)
 * 입력으로 주어지는 모든 수는 정수이다.
 * <p>
 * 출력
 * 수열 $C$의 원소를 차례대로 queuestack에 삽입했을 때의 리턴값을 공백으로 구분하여 출력한다.
 * <p>
 * 작성일 : 2023.08.24
 */
class B24511_QueueStack {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int qsVolume = Integer.parseInt(br.readLine());
        int[][] queueStack = new int[qsVolume][1];
        StringTokenizer types = new StringTokenizer(br.readLine(), " ");
        StringTokenizer values = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < qsVolume; i++) {
            int t = Integer.parseInt(types.nextToken());
            int v = Integer.parseInt(values.nextToken());
            if (t == 0) queueStack[i] = new int[]{v};
            else queueStack[i] = null;
        }

        int m = Integer.parseInt(br.readLine());
        StringTokenizer xs = new StringTokenizer(br.readLine(), " ");
        while (m-- > 0) {
            int x = Integer.parseInt(xs.nextToken());

            for (int i = 0; i < qsVolume; i++) {
                if (queueStack[i] != null) {
                    int temp = queueStack[i][0];
                    queueStack[i][0] = x;
                    x = temp;
                }
            }

            sb.append(x).append(" ");
        }

        System.out.println(sb.toString().trim());
    }

    // 시간초과
//    public static void main(String[] args) throws Exception {
//        ArrayList<Object> queueStack = new ArrayList<>();
//        StringBuilder sb = new StringBuilder();
//
//        int qsVolume = read();
//
//        for (int i = 0; i < qsVolume; i++) {
//            int type = read();
//            if (type == 0) queueStack.add(new LinkedList<>());
//            else queueStack.add(new Stack<>());
//        }
//
//        for (int i = 0; i < qsVolume; i++) {
//            Integer val = read();
//            if (queueStack.get(i) instanceof LinkedList) {
//                ((LinkedList<Integer>) queueStack.get(i)).add(val);
//            } else if (queueStack.get(i) instanceof Stack) {
//                ((Stack<Integer>) queueStack.get(i)).push(val);
//            }
//        }
//
//        int m = read();
//        Queue<Integer> c = new LinkedList<>();
//        while (m-- > 0) {
//            int val = read();
//            c.offer(val);
//        }
//
//        while (!c.isEmpty()) {
//            Integer x = c.poll();
//
//            for (int i = 0; i < qsVolume; i++) {
//                if (queueStack.get(i) instanceof LinkedList) {
//                    ((LinkedList<Integer>) queueStack.get(i)).add(x);
//                    x = ((LinkedList<Integer>) queueStack.get(i)).removeFirst();
//                } else if (queueStack.get(i) instanceof Stack) {
//                    ((Stack<Integer>) queueStack.get(i)).push(x);
//                    x = ((Stack<Integer>) queueStack.get(i)).pop();
//                }
//            }
//
//            sb.append(x).append(" ");
//        }
//
//        System.out.println(sb.toString().trim());
//    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] typeArr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            typeArr[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> deque = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (typeArr[i] == 0) {
                deque.addLast(num);
            }
        }

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        br.close();
        while (M-- > 0) {
            int moveValue = Integer.parseInt(st.nextToken());
            deque.addFirst(moveValue);
            sb.append(deque.pollLast()).append(" ");
        }

        System.out.println(sb);
    }

    // 다른 사람의 풀이 2
    static int read() throws Exception {
        int i, j = System.in.read() & 15;
        boolean nega = false;
        if (j == 13) {
            nega = true;
            j = System.in.read() & 15;
        }
        while ((i = System.in.read()) > 32) j = (j << 3) + (j << 1) + (i & 15);
        if (i == 13) System.in.read();
        if (!nega) return j;
        else return -j;
    }

    public static void main2(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        Deque<Integer> dq = new ArrayDeque<>();
        int n1 = read();
        boolean chkS[] = new boolean[n1 + 1];

        for (int i = 1; i <= n1; i++) if (read() == 1) chkS[i] = true;
        for (int i = 1; i <= n1; i++)
            if (!chkS[i]) dq.offerLast(read());
            else read();

        int n2 = read();
        for (int i = 0; i < n2; i++) {
            dq.offerFirst(read());
            sb.append(dq.pollLast()).append(" ");
        }
        System.out.println(sb);
    }
}

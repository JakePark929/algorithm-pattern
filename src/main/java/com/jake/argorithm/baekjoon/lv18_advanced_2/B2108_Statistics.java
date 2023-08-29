package com.jake.argorithm.baekjoon.lv18_advanced_2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [ 통계학 ]
 * <p>
 * 수를 처리하는 것은 통계학에서 상당히 중요한 일이다.
 * 통계학에서 N개의 수를 대표하는 기본 통계값에는 다음과 같은 것들이 있다.
 * 단, N은 홀수라고 가정하자.
 * <p>
 * 산술평균 : N개의 수들의 합을 N으로 나눈 값
 * 중앙값 : N개의 수들을 증가하는 순서로 나열했을 경우 그 중앙에 위치하는 값
 * 최빈값 : N개의 수들 중 가장 많이 나타나는 값
 * 범위 : N개의 수들 중 최댓값과 최솟값의 차이
 * N개의 수가 주어졌을 때, 네 가지 기본 통계값을 구하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 수의 개수 N(1 ≤ N ≤ 500,000)이 주어진다.
 * 단, N은 홀수이다. 그 다음 N개의 줄에는 정수들이 주어진다.
 * 입력되는 정수의 절댓값은 4,000을 넘지 않는다.
 * <p>
 * 출력
 * 첫째 줄에는 산술평균을 출력한다. 소수점 이하 첫째 자리에서 반올림한 값을 출력한다.
 * 둘째 줄에는 중앙값을 출력한다.
 * 셋째 줄에는 최빈값을 출력한다. 여러 개 있을 때에는 최빈값 중 두 번째로 작은 값을 출력한다.
 * 넷째 줄에는 범위를 출력한다.
 * <p>
 * 작성일 : 2023.08.25
 */
class B2108_Statistics {
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;
        if (isNegative) n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return isNegative ? ~n + 1 : n;
    }

    public static void main(String[] args) throws Exception {
        PriorityQueue<Integer> stats = new PriorityQueue<>();
        HashMap<Integer, Integer> statsMap = new HashMap<>();
        ArrayList<Integer> l = new ArrayList<>();
        long sum = 0;

        int n = read();

        while (n-- > 0) {
            int e = read();
            sum += e;
            stats.add(e);
            if (statsMap.get(e) != null) {
                statsMap.put(e, statsMap.get(e) + 1);
            } else {
                statsMap.put(e, 0);
            }
        }

        int size = stats.size();

        // 산술평균
        double avg = ((double) sum) / size;
        int floor = (int) Math.rint(avg);
        System.out.println(floor); // 산술평균

        // 중앙값
        while (!stats.isEmpty()) {
            l.add(stats.poll());
        }

        int index = (size / 2);
        System.out.println(l.get(index)); // 중앙값

        // 최빈값
        List<Map.Entry<Integer, Integer>> entryList = new LinkedList<>(statsMap.entrySet());
        Comparator<Map.Entry<Integer, Integer>> valueComparator = (entry1, entry2) -> {
            int valueCompare = entry2.getValue().compareTo(entry1.getValue());
            if (valueCompare == 0) {
                return entry1.getKey().compareTo(entry2.getKey());
            }
            return valueCompare;
        };

        entryList.sort(valueComparator);

        boolean isFirst = false;
        int firstVal = 0;
        Stack<Integer> s = new Stack<>();
        for (Map.Entry<Integer, Integer> entry : entryList) {
            if (!isFirst) {
                firstVal = entry.getValue();
                s.push(entry.getKey());
                isFirst = true;
            } else {
                int nextVal = entry.getValue();
                if (nextVal == firstVal) {
                    s.push(entry.getKey());
                }
            }

            if (s.size() == 2) {
                System.out.println(s.peek());
                break;
            }
        }

        if (s.size() == 1) {
            System.out.println(s.peek());
        }

        // 범위
        System.out.println(Math.abs(l.get(l.size() - 1) - l.get(0))); // 범위
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        Reader r = new Reader();
        int n = r.nextInt();
        int[] counter = new int[8001];

        int max = -4001;
        int min = 4001;
        int sum = 0;
        int value;
        for (int i = 0; i < n; i++) {
            value = r.nextInt();
            counter[value + 4000]++;
            if (value > max) {
                max = value;
            }

            if (value < min) {
                min = value;
            }
            sum += value;
        }
        int mode = -4001;
        int modeCount = 0;
        boolean modeDuplicated = false;

        int count = 0;
        int median = 4001;
        for (int i = min + 4000; i < max + 4001; i++) {
            if (counter[i] > 0) {
                count += counter[i];
                if (median == 4001 && count > n / 2) {
                    median = i - 4000;
                }
                if (counter[i] > modeCount) {
                    mode = i - 4000;
                    modeCount = counter[i];
                    modeDuplicated = false;
                } else if (counter[i] == modeCount && !modeDuplicated) {
                    mode = i - 4000;
                    modeDuplicated = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(Math.round(sum / (double) n))
                .append('\n')
                .append(median)
                .append('\n')
                .append(mode)
                .append('\n')
                .append(max - min);
        System.out.print(sb);
    }


    static class Reader {
        private final int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0,
                    BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

    }

    // 다른 사람의 풀이 2
    public static void main2(String[] args) throws IOException {
        int[] counts = new int[8001];
        StringBuilder sb = new StringBuilder();

        int N = readInt();

        double sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int num = readInt();
            sum += num;
            counts[num + 4000]++;

            if (num > max) {
                max = num;
            }

            if (num < min) {
                min = num;
            }
        }

        int middleCount = 0;
        int middle = 0;
        int maxCount = 0;
        boolean isSecond = false;
        int maxCountValue = 0;
        for (int i = min + 4000; i <= max + 4000; i++) {
            if (counts[i] > 0) {

                if (middleCount < N / 2 + 1) {
                    middleCount += counts[i];
                    middle = i - 4000;
                }

                if (counts[i] > maxCount) {
                    maxCount = counts[i];
                    maxCountValue = i - 4000;
                    isSecond = true;
                } else if (counts[i] == maxCount && isSecond) {
                    maxCountValue = i - 4000;
                    isSecond = false;
                }
            }
        }

        System.out.println((int) Math.round(sum / N));
        System.out.println(middle);
        System.out.println(maxCountValue);
        System.out.println(max - min);
    }

    static int readInt() throws IOException {
        boolean isNegative = false;
        int sum = 0;
        while (true) {
            int input = System.in.read();
            if (input == '\n' || input == ' ') {
                return isNegative ? -1 * sum : sum;
            } else if (input == '-') {
                isNegative = true;
            } else {
                sum = (sum * 10) + (input - '0');
            }
        }
    }

    // 다른 사람의 풀이 3
    public static void main3(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 입력값의 범위 : -4000 ~ 4000
        int[] arr = new int[8001];

        /*
         *  sum = 총 합계
         *  max = 최댓값
         *  min = 최솟값
         *  median = 중앙값
         *  mode = 최빈값
         */
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        // median 과 mode 는 -4000~4000 을 제외한 수로 초기화하면 된다.
        int median = 10000;
        int mode = 10000;

        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(br.readLine());
            sum += value;
            arr[value + 4000]++;

            if (max < value) {
                max = value;
            }
            if (min > value) {
                min = value;
            }
        }


        // 순회
        int count = 0;    // 중앙값 빈도 누적 수
        int mode_max = 0;    // 최빈값의 최댓값

        // 이전의 동일한 최빈값이 1번만 등장했을경우 true, 아닐경우 false
        boolean flag = false;

        for (int i = min + 4000; i <= max + 4000; i++) {

            if (arr[i] > 0) {

                /*
                 * <중앙값 찾기>
                 * 누적횟수가 전체 전체 길이의 절반에 못 미친다면
                 */
                if (count < (N + 1) / 2) {
                    count += arr[i];    // i값의 빈도수를 count 에 누적
                    median = i - 4000;
                }

                /*
                 * <최빈값 찾기>
                 * 이전 최빈값보다 현재 값의 빈도수가 더 높을 경우
                 */
                if (mode_max < arr[i]) {
                    mode_max = arr[i];
                    mode = i - 4000;
                    flag = true;    // 첫 등장이므로 true 로 변경
                }
                // 이전 최빈값 최댓값과 동일한 경우면서 한 번만 중복되는 경우
                else if (mode_max == arr[i] && flag) {
                    mode = i - 4000;
                    flag = false;
                }
            }
        }

        System.out.println((int) Math.round((double) sum / N));    // 산술평균
        System.out.println(median);    // 중앙값
        System.out.println(mode);    // 최빈값
        System.out.println(max - min);    // 범위
    }
}

package com.jake.argorithm.baekjoon.lv19_recursion;

/**
 * [ 알고리즘 수업 - 병합 정렬 1 ]
 * <p>
 * 오늘도 서준이는 병합 정렬 수업 조교를 하고 있다.
 * 아빠가 수업한 내용을 학생들이 잘 이해했는지 문제를 통해서 확인해보자.
 * N개의 서로 다른 양의 정수가 저장된 배열 A가 있다.
 * 병합 정렬로 배열 A를 오름차순 정렬할 경우 배열 A에 K 번째 저장되는 수를 구해서 우리 서준이를 도와주자.
 * <p>
 * 크기가 N인 배열에 대한 병합 정렬 의사 코드는 다음과 같다.
 * <p>
 * merge_sort(A[p..r]) { # A[p..r]을 오름차순 정렬한다.
 * if (p < r) then {
 * q <- ⌊(p + r) / 2⌋;       # q는 p, r의 중간 지점
 * merge_sort(A, p, q);      # 전반부 정렬
 * merge_sort(A, q + 1, r);  # 후반부 정렬
 * merge(A, p, q, r);        # 병합
 * }
 * }
 * <p>
 * # A[p..q]와 A[q+1..r]을 병합하여 A[p..r]을 오름차순 정렬된 상태로 만든다.
 * # A[p..q]와 A[q+1..r]은 이미 오름차순으로 정렬되어 있다.
 * merge(A[], p, q, r) {
 * i <- p; j <- q + 1; t <- 1;
 * while (i ≤ q and j ≤ r) {
 * if (A[i] ≤ A[j])
 * then tmp[t++] <- A[i++]; # tmp[t] <- A[i]; t++; i++;
 * else tmp[t++] <- A[j++]; # tmp[t] <- A[j]; t++; j++;
 * }
 * while (i ≤ q)  # 왼쪽 배열 부분이 남은 경우
 * tmp[t++] <- A[i++];
 * while (j ≤ r)  # 오른쪽 배열 부분이 남은 경우
 * tmp[t++] <- A[j++];
 * i <- p; t <- 1;
 * while (i ≤ r)  # 결과를 A[p..r]에 저장
 * A[i++] <- tmp[t++];
 * }
 * 입력
 * 첫째 줄에 배열 A의 크기 N(5 ≤ N ≤ 500,000), 저장 횟수 K(1 ≤ K ≤ 108)가 주어진다.
 * <p>
 * 다음 줄에 서로 다른 배열 A의 원소 A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 109)
 * <p>
 * 출력
 * 배열 A에 K 번째 저장 되는 수를 출력한다. 저장 횟수가 K 보다 작으면 -1을 출력한다.
 * <p>
 * 작성일 : 2023.08.28
 */
class B24060_AlgorithmClass_MergeSort_1 {
    static int count = 0;
    static int finder = 0;

    public static void main(String[] args) throws Exception {
        int n = read();
        finder = read();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = read();
        }

        mergeSort(arr);

        if (count < finder) {
            System.out.println(-1);
        }
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // 배열의 크기가 1 이하면 정렬할 필요가 없음
        }
        int n = arr.length;
        mergeSortInner(arr, 0, n - 1);
    }

    private static void mergeSortInner(int[] arr, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSortInner(arr, p, q);
            mergeSortInner(arr, q + 1, r);
            merge(arr, p, q, r);
        }
    }

    private static void merge(int[] arr, int p, int q, int r) {
        int i = p;
        int j = q + 1;
        int[] tmp = new int[r - p + 1];
        int t = 0;

        while (i <= q && j <= r) {
            if (arr[i] <= arr[j]) {
                tmp[t++] = arr[i++];
            } else {
                tmp[t++] = arr[j++];
            }
        }

        while (i <= q) {
            tmp[t++] = arr[i++];
        }

        while (j <= r) {
            tmp[t++] = arr[j++];
        }

        i = p;
        t = 0;

        while (i <= r) {
            count++;
            int test = tmp[t++];
            if (count == finder) {
                System.out.println(test);
            }

            arr[i++] = test;
        }
    }

    // 다른 사람의 풀이 1
    private static int[] A;
    private static int[] tmp;
    private static int count1, k;

    public static void main1(String[] args) throws Exception {
        int n = read();
        k = read();
        A = new int[n];
        tmp = new int[n];
        count1 = 0;
        for (int i = 0; i < n; i++) A[i] = read1();
        mergeSort(0, n - 1);
        System.out.print(-1);
    }

    public static void mergeSort(int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(p, q);
            mergeSort(q + 1, r);
            merge(p, q, r);
        }
    }

    private static void merge(int p, int q, int r) {
        int i = p, j = q + 1, t = 0;
        while (i <= q && j <= r) tmp[t++] = A[i] <= A[j] ? A[i++] : A[j++];
        while (i <= q) tmp[t++] = A[i++];
        while (j <= r) tmp[t++] = A[j++];
        i = p;
        t = 0;
        while (i <= r) {
            A[i++] = tmp[t++];
            count1++;
            if (count1 == k) {
                System.out.print(A[i - 1]);
                System.exit(0);
            }
        }
    }

    private static int read1() throws Exception {
        int i, j = System.in.read() & 15;
        boolean neg = false;
        if (j == 13) neg = true;
        while ((i = System.in.read()) > 32) j = (j << 3) + (j << 1) + (i & 15);
        if (i == 13) System.in.read();
        if (neg) return -j;
        else return j;
    }
}

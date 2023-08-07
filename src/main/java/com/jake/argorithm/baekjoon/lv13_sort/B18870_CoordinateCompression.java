package com.jake.argorithm.baekjoon.lv13_sort;

import java.io.*;
import java.util.*;

/**
 * [ 좌표 압축 ]
 * <p>
 * 수직선 위에 N개의 좌표 X1, X2, ..., XN이 있다. 이 좌표에 좌표 압축을 적용하려고 한다.
 * <p>
 * Xi를 좌표 압축한 결과 X'i의 값은 Xi > Xj를 만족하는 서로 다른 좌표 Xj의 개수와 같아야 한다.
 * <p>
 * X1, X2, ..., XN에 좌표 압축을 적용한 결과 X'1, X'2, ..., X'N를 출력해보자.
 * <p>
 * 입력
 * 첫째 줄에 N이 주어진다.
 * <p>
 * 둘째 줄에는 공백 한 칸으로 구분된 X1, X2, ..., XN이 주어진다.
 * <p>
 * 출력
 * 첫째 줄에 X'1, X'2, ..., X'N을 공백 한 칸으로 구분해서 출력한다.
 * <p>
 * 제한
 * 1 ≤ N ≤ 1,000,000
 * -109 ≤ Xi ≤ 109
 * <p>
 * 작성일 : 2023.08.07
 */
class B18870_CoordinateCompression {
//    public static void main1(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(br.readLine());
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//
//        int[] arr = new int[n];
//        HashSet<Integer> hs = new HashSet<>();
//        HashMap<Integer, Integer> ranking = new HashMap<>();
//
//        for (int i = 0; i < n; i++) {
//            int e = Integer.parseInt(st.nextToken());
//            arr[i] = e;
//            hs.add(e);
//        }
//
//        List<Integer> list = new ArrayList<>(hs);
//        Collections.sort(list);
//
//        int rank = 0;
//        for (int v : list) {
//            if (!ranking.containsKey(v)) {
//                ranking.put(v, rank);
//                rank++;
//            }
//        }
//
//        StringBuilder sb = new StringBuilder();
//
//        for (int i : arr) {
//            sb.append(ranking.get(i)).append(" ");
//        }
//
//        System.out.println(sb.toString().trim());
//    }

    // 다른 사람의 풀이 1 - 병합정렬
    static int[] tmp;
    static int[] tmp2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int[] arr = new int[T];
        int[] idx = new int[T];
        tmp = new int[T];
        tmp2 = new int[T];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < T; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            idx[i] = i;
        }

        mergeSort(arr, idx, 0, T - 1);

        System.out.println(Arrays.toString(arr));

        int cnt = 0;
        int num = arr[0];
        int[] res = new int[T];

        for (int i = 0; i < T; i++) {
            if (arr[i] == num) {
                res[idx[i]] = cnt;
            } else {
                num = arr[i];
                res[idx[i]] = ++cnt;
            }
        }

        for (int i = 0; i < T; i++) {
            bw.append(String.valueOf(res[i])).append(" ");
        }

        bw.flush();
    }

    public static void mergeSort(int[] arr, int[] arr2, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, arr2, start, mid);
            mergeSort(arr, arr2, mid + 1, end);
            merge(arr, arr2, start, mid, end);
        }
    }

    public static void merge(int[] arr, int[] arr2, int start, int mid, int end) {
        int left = start;
        int right = mid + 1;
        int k = start;

        System.out.println(Arrays.toString(tmp));
        System.out.println(Arrays.toString(tmp2));

        while (left <= mid && right <= end) {
            if (arr[left] < arr[right]) {
                tmp[k] = arr[left];
                tmp2[k] = arr2[left];
                left++;
            } else {
                tmp[k] = arr[right];
                tmp2[k] = arr2[right];
                right++;
            }
            k++;
        }

        while (left <= mid) {
            tmp[k] = arr[left];
            tmp2[k] = arr2[left];
            left++;
            k++;
        }

        while (right <= end) {
            tmp[k] = arr[right];
            tmp2[k] = arr2[right];
            right++;
            k++;
        }

        for (int i = start; i <= end; i++) {
            arr[i] = tmp[i];
            arr2[i] = tmp2[i];
        }
    }
}

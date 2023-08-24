package com.jake.argorithm.a00_privatetest.util;

import java.util.PriorityQueue;

public class SortUtils {
    // 계수 정렬(카운팅 정렬)
    private static int[] countingSort(int[] arr, int range) {
        int[] counting = new int[range + 1];
        int[] result = new int[arr.length];

        for (int i : arr) counting[i]++;

        for (int i = 1; i < counting.length; i++)
            counting[i] += counting[i - 1];

        for (int i = arr.length - 1; i >= 0; i--) {
            int val = arr[i];
            counting[val]--;
            result[counting[val]] = val;
        }

        return result;
    }
    
    // 힙정렬 - by chat gpt
    public static void heapSort(int[] arr) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // 배열의 모든 원소를 우선순위 큐에 추가
        for (int num : arr) {
            minHeap.add(num);
        }

        // 우선순위 큐에서 원소를 하나씩 꺼내서 배열에 넣으면 정렬된 상태가 됨
        int i = 0;
        while (!minHeap.isEmpty()) {
            arr[i++] = minHeap.poll();
        }
    }

    // 합병 정렬 - by chat gpt
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // 배열의 크기가 1 이하면 정렬할 필요가 없음
        }
        int n = arr.length;
        int[] temp = new int[n]; // 임시 배열 생성
        mergeSortHelper(arr, temp, 0, n - 1);
    }

    // 합병 정렬의 보조 메소드
    private static void mergeSortHelper(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2; // 중간 지점 계산
            mergeSortHelper(arr, temp, left, mid); // 왼쪽 반 정렬
            mergeSortHelper(arr, temp, mid + 1, right); // 오른쪽 반 정렬
            merge(arr, temp, left, mid, right); // 정렬된 두 반 병합
        }
    }

    // 정렬된 두 반을 병합하는 메소드
    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left; // 왼쪽 반의 시작 인덱스
        int j = mid + 1; // 오른쪽 반의 시작 인덱스
        int k = left; // 임시 배열에 저장할 인덱스

        // 왼쪽 반과 오른쪽 반을 비교하여 임시 배열에 정렬하며 병합
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // 남은 요소들을 임시 배열에 복사
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // 임시 배열의 결과를 원래 배열에 복사
        for (int p = left; p <= right; p++) {
            arr[p] = temp[p];
        }
    }
}

package com.jake.argorithm.programmers.lv2;

import java.util.Arrays;
import java.util.Comparator;

/**
 * [ 최솟값 만들기 ]
 * <p>
 * 길이가 같은 배열 A, B 두개가 있습니다. 각 배열은 자연수로 이루어져 있습니다.
 * 배열 A, B에서 각각 한 개의 숫자를 뽑아 두 수를 곱합니다.
 * 이러한 과정을 배열의 길이만큼 반복하며,
 * 두 수를 곱한 값을 누적하여 더합니다.
 * 이때 최종적으로 누적된 값이 최소가 되도록 만드는 것이 목표입니다.
 * (단, 각 배열에서 k번째 숫자를 뽑았다면 다음에 k번째 숫자는 다시 뽑을 수 없습니다.)
 * <p>
 * 예를 들어 A = [1, 4, 2] , B = [5, 4, 4] 라면
 * <p>
 * A에서 첫번째 숫자인 1, B에서 첫번째 숫자인 5를 뽑아 곱하여 더합니다. (누적된 값 : 0 + 5(1x5) = 5)
 * A에서 두번째 숫자인 4, B에서 세번째 숫자인 4를 뽑아 곱하여 더합니다. (누적된 값 : 5 + 16(4x4) = 21)
 * A에서 세번째 숫자인 2, B에서 두번째 숫자인 4를 뽑아 곱하여 더합니다. (누적된 값 : 21 + 8(2x4) = 29)
 * 즉, 이 경우가 최소가 되므로 29를 return 합니다.
 * <p>
 * 배열 A, B가 주어질 때 최종적으로 누적된 최솟값을 return 하는 solution 함수를 완성해 주세요.
 * <p>
 * 제한사항
 * 배열 A, B의 크기 : 1,000 이하의 자연수
 * 배열 A, B의 원소의 크기 : 1,000 이하의 자연수
 * <p>
 * 작성일 : 2023.07.07
 */
class P12941 {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Integer[] newA = new Integer[A.length];
        Integer[] newB = new Integer[B.length];

        for (int i = 0; i < newA.length; i++) newA[i] = A[i];
        for (int i = 0; i < newB.length; i++) newB[i] = B[i];

        Arrays.sort(newA);
        Arrays.sort(newB, Comparator.reverseOrder());

        for (int i = 0; i < newA.length; i++) answer += newA[i] * newB[i];

        return answer;
    }

    // 다른 사람의 풀이 1
    public int getMinSum(int[] A, int[] B) {
        int answer = 0;
        int length = A.length;


        quickSort(A, 0, A.length - 1);
        quickSort(B, 0, B.length - 1);

        //System.out.println(A[0]);
        //System.out.println(B[0]);

        for (int i = 0; i < length; i++) {
            answer += A[i] * B[length - 1 - i];
        }

        return answer;
    }

    public static void quickSort(int[] arr, int left, int right) {
        int i, j, pivot, tmp;

        if (left < right) {
            i = left;
            j = right;
            pivot = arr[left];
            //분할 과정
            while (i < j) {
                while (arr[j] > pivot) j--;
                while (i < j && arr[i] <= pivot) i++;

                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
            arr[left] = arr[i];
            arr[i] = pivot;
            //정렬 과정
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }
    }

    // 다른 사람의 풀이 2
    public int getMinSum2(int[] A, int[] B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        for (int i = 0; i < A.length; i++) {
            answer += A[i] * B[B.length - i - 1];
        }

        return answer;
    }

    public static void main(String[] args) {
        P12941 problem = new P12941();
        System.out.println(problem.solution(new int[]{1, 2}, new int[]{3, 4}));
    }
}

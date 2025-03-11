package com.jake.algorithm.programmers.lv2;

/**
 * [ 다음 큰 숫자 ]
 * <p>
 * 자연수 n이 주어졌을 때, n의 다음 큰 숫자는 다음과 같이 정의 합니다.
 * <p>
 * 조건 1. n의 다음 큰 숫자는 n보다 큰 자연수 입니다.
 * 조건 2. n의 다음 큰 숫자와 n은 2진수로 변환했을 때 1의 갯수가 같습니다.
 * 조건 3. n의 다음 큰 숫자는 조건 1, 2를 만족하는 수 중 가장 작은 수 입니다.
 * 예를 들어서 78(1001110)의 다음 큰 숫자는 83(1010011)입니다.
 * <p>
 * 자연수 n이 매개변수로 주어질 때, n의 다음 큰 숫자를 return 하는 solution 함수를 완성해주세요.
 * <p>
 * 제한 사항
 * n은 1,000,000 이하의 자연수 입니다.
 * <p>
 * 작성일 : 2023.07.12
 */
class P12911 {
    public int solution(int n) {
        int next = n + 1;
        int nCount = 0;
        int nextCount = 0;

        for (char c : Integer.toString(n, 2).toCharArray()) {
            if (c == '1') {
                nCount++;
            }
        }

        while (true) {
            for (char c : Integer.toString(next, 2).toCharArray()) {
                if (c == '1') {
                    nextCount++;
                }
            }

            if (nCount == nextCount) break;

            nextCount = 0;
            next++;
        }

        return next;
    }

    // 다른 사람의 풀이 1
    public int nextBigNumber(int n) {
        int postPattern = n & -n, smallPattern = ((n ^ (n + postPattern)) / postPattern) >> 2;
        return n + postPattern | smallPattern;
    }

    // 다른 사람의 풀이 2
    public int nextBigNumber2(int n) {
        int a = Integer.bitCount(n);
        int compare = n + 1;
        while (Integer.bitCount(compare) != a) {
            compare++;
        }
        return compare;
    }

    public static void main(String[] args) {
        P12911 problem = new P12911();
        System.out.println(problem.solution(78));
    }
}

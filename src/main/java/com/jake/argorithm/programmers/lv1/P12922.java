package com.jake.argorithm.programmers.lv1;

/**
 * [수박수박수박수박수박수?]
 * <p>
 * 길이가 n이고, "수박수박수박수...."와 같은 패턴을 유지하는 문자열을 리턴하는 함수, solution을 완성하세요.
 * 예를들어 n이 4이면 "수박수박"을 리턴하고 3이라면 "수박수"를 리턴하면 됩니다.
 * <p>
 * 제한 조건
 * n은 길이 10,000이하인 자연수입니다.
 * <p>
 * 작성일: 2023.06.15
 */
class P12922 {
    public String solution(int n) {
        StringBuilder watermelon = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                watermelon.append("수");
            } else if (i % 2 == 1) {
                watermelon.append("박");
            } else {
                watermelon.append("수");
            }
        }

        return watermelon.toString();
    }

    // 다른 사람의 풀이
    public String watermelon(int n) {
        return new String(new char[n / 2 + 1]).replace("\0", "수박").substring(0, n);
    }

    // 다른 사람의 풀이
    public String watermelon2(int n) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < n; i++) {
            result.append((i % 2 == 0) ? "수" : "박");
        }

        return result.toString();
    }

    public static void main(String[] args) {
        P12922 problem = new P12922();
        System.out.println(problem.solution(10));
    }
}

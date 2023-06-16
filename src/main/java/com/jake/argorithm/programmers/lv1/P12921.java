package com.jake.argorithm.programmers.lv1;

import java.util.LinkedList;

/**
 * [ 소수 찾기 ]
 * <p>
 * 1부터 입력받은 숫자 n 사이에 있는 소수의 개수를 반환하는 함수, solution을 만들어 보세요.
 * <p>
 * 소수는 1과 자기 자신으로만 나누어지는 수를 의미합니다.
 * (1은 소수가 아닙니다.)
 * <p>
 * 제한 조건
 * n은 2이상 1000000이하의 자연수입니다.
 * <p>
 * 작성일: 2023.06.15
 */
class P12921 {
    public int solution(int n) {
        boolean[] isPrime = getPrime(n);
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i <= n; i++) {
            if (!isPrime[i]) {
                list.add(i);
            }
        }
        return list.size();
    }

    private boolean[] getPrime(int n) {
        boolean[] prime = new boolean[n + 1];
        if (n < 2) return prime;
        prime[0] = prime[1] = true;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (prime[i]) {
                continue;
            }
            for (int j = i * i; j < prime.length; j = j + i) {
                prime[j] = true;
            }
        }

        return prime;
    }

    // 다른 사람의 풀이 1
    int numberOfPrime(int n) {
        int result = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= i; j++) {
                if (j == i) {
                    ++result;
                } else if (i % j == 0) {
                    break;
                }
            }
        }

        return result;
    }

    // 다른 사람의 풀이 2
    public int solution2(int n) {
        int answer = 0;
        boolean[] check = new boolean[n + 1];

        check[0] = check[1] = true;
        for (int i = 2; i * i < n + 1; i++) {
            for (int j = i * i; j < n + 1; j += i)
                check[j] = true;
        }
        for (int i = 2; i < n + 1; i++) {
            if (!check[i])
                answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        P12921 problem = new P12921();
        System.out.println(problem.solution(11));
    }
}

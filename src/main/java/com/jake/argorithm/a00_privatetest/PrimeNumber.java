package com.jake.argorithm.a00_privatetest;

import java.util.LinkedList;

public class PrimeNumber {
    public int solution(int n) {
        boolean[] isPrimes = getPrimes(n);
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i <= n; i++) {
            if (!isPrimes[i]) {
                list.add(i);
            }
        }
        return list.size();
    }

    private Boolean isPrime(int n) {
        int cnt = 0;
        if (n == 1) return false;
        for (int i = 1; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) cnt += 1;
        }
        return cnt == 1;
    }
    
    // 에라토스테네스의 체
    private boolean[] getPrimes(int n) {
        boolean[] primes = new boolean[n + 1];
        if (n < 2) return primes;
        primes[0] = primes[1] = true;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (primes[i]) {
                continue;
            }
            for (int j = i * i; j < primes.length; j = j + i) {
                primes[j] = true;
            }
        }

        return primes;
    }

    // 에라토스테네스의 채 with gpt
    public static boolean[] sieveOfEratosthenes(int n) {
        boolean[] primes = new boolean[n + 1];

        if (n < 2) return primes;

        for (int i = 2; i <= n; i++) primes[i] = true;


        for (int p = 2; p * p <= n; p++) {
            if (primes[p]) {
                for (int i = p * p; i <= n; i += p) {
                    primes[i] = false;
                }
            }
        }

        return primes;
    }
}

package com.jake.argorithm.a00_privatetest.util;

public class MathUtils {
    // 최대 공약수 - 유클리드 알고리즘
    public static int getGCD(int a, int b) {
        while (b > 0) {
            int r = a % b;
            a = b;
            b = r;
        }

        return a;
    }

    // 최소 공배수 - (a * b) / gcd(a, b)
    public static int getLCM(int a, int b) {
        return (a * b) / getGCD(a, b);
    }
    
    // 팩토리얼 재귀함수
    private static int factorial(int n) {
        // factorial(0) == 1
        if (n <= 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }
    
    // 조합 수
    private static int combination(int n, int r) {
        if (n == r || r == 0) {
            return 1;
        }

        return combination(n - 1, r - 1) + combination(n - 1, r);
    }

    // 피보나치 수
    private static int fibonacci(int n) {
        if (n == 0)	return 0;
        if (n == 1)	return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        int a = 36;
        int b = 48;

        int gcd = getGCD(a, b);
        int lcm = getLCM(a, b);
        System.out.println(gcd);
        System.out.println(lcm);
    }
}

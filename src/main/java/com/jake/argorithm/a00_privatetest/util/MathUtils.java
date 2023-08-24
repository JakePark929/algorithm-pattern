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

    public static void main(String[] args) {
        int a = 36;
        int b = 48;

        int gcd = getGCD(a, b);
        int lcm = getLCM(a, b);
        System.out.println(gcd);
        System.out.println(lcm);
    }
}

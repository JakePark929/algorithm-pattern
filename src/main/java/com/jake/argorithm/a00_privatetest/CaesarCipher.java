package com.jake.argorithm.a00_privatetest;

public class CaesarCipher {
    public char caesarCipher(char c, int n) {
        n = n % 26;
        if (Character.isLowerCase(c)) {
            c = (char) ((c - 'a' + n) % 26 + 'a');
        } else if (Character.isUpperCase(c)) {
            c = (char) ((c - 'A' + n) % 26 + 'A');
        }
        System.out.println("+" + n + ":" + c);
        return c;
    }
}

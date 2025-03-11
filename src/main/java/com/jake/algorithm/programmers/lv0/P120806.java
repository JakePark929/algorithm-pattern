package com.jake.algorithm.programmers.lv0;

public class P120806 {
    public int solution(int num1, int num2) {
        float divide = ((float) num1) / ((float) num2);

        return (int) (divide * 1000);
    }

    public static void main(String[] args) {
        final P120806 p120806 = new P120806();

        System.out.println(p120806.solution(1, 16));
    }
}

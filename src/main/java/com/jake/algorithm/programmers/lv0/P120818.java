package com.jake.algorithm.programmers.lv0;

public class P120818 {
    public int solution(int price) {
        float rate = 0;
        if (100000 <= price && price < 300000) {
            rate = 0.05f;
        } else if (300000 <= price && price < 500000) {
            rate = 0.10f;
        } else if (500000 <= price) {
            rate = 0.20f;
        }

        return price - (int) Math.ceil(price * rate);
    }

    public static void main(String[] args) {
        final P120818 p120818 = new P120818();

        System.out.println(p120818.solution(580000));
    }
}

package com.jake.algorithm.programmers.lv2;

/**
 * [ 피보나치 수 ]
 * <p>
 * 피보나치 수는 F(0) = 0, F(1) = 1일 때,
 * 1 이상의 n에 대하여 F(n) = F(n-1) + F(n-2) 가 적용되는 수 입니다.
 * <p>
 * 예를들어
 * <p>
 * F(2) = F(0) + F(1) = 0 + 1 = 1
 * F(3) = F(1) + F(2) = 1 + 1 = 2
 * F(4) = F(2) + F(3) = 1 + 2 = 3
 * F(5) = F(3) + F(4) = 2 + 3 = 5
 * 와 같이 이어집니다.
 * <p>
 * 2 이상의 n이 입력되었을 때,
 * n번째 피보나치 수를 1234567으로 나눈 나머지를 리턴하는 함수, solution을 완성해 주세요.
 * <p>
 * 제한 사항
 * n은 2 이상 100,000 이하인 자연수입니다.
 * <p>
 * 작성일 : 2023.07.12
 */
class P12945 {
    public int solution(int n) {
        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        for (int i = 2; i <= n; i++) {
            nums[i] = (nums[i-1] + nums[i-2]) % 1234567;
        }

        return nums[n];
    }
    
    // 나머지로 나눈 수의 수열도 같음

    public static void main(String[] args) {
        P12945 problem = new P12945();
        System.out.println(problem.solution(5));
    }
}

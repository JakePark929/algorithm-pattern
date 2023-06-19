package com.jake.argorithm.programmers.lv1;

import java.util.*;

/**
 * [ 소수 만들기 ]
 *
 * 주어진 숫자 중 3개의 수를 더했을 때 소수가 되는 경우의 개수를 구하려고 합니다.
 * 숫자들이 들어있는 배열 nums가 매개변수로 주어질 때,
 * nums에 있는 숫자들 중 서로 다른 3개를 골라 더했을 때
 * 소수가 되는 경우의 개수를 return 하도록 solution 함수를 완성해주세요.
 *
 * 제한사항
 * nums에 들어있는 숫자의 개수는 3개 이상 50개 이하입니다.
 * nums의 각 원소는 1 이상 1,000 이하의 자연수이며, 중복된 숫자가 들어있지 않습니다.
 *
 * 작성일: 2023.06.19
 */
class P12977 {
    public int solution(int[] nums) {
        int count = 0;
        LinkedList<Integer> primeList = new LinkedList<>();
        LinkedList<Integer> numList = new LinkedList<>();
        LinkedList<Integer> sumList = new LinkedList<>();
        boolean[] isPrime = getPrime(10000);

        for (int i = 0; i <= 10000; i++) {
            if (!isPrime[i]) {
                primeList.add(i);
            }
        }

        for (int i : nums) {
            numList.add(i);
        }

        List<List<Integer>> combinations = generateCombinations(numList, 3);
        for (List<Integer> combination : combinations) {
            System.out.print(combination + " ");
            int sum = 0;
            for (int i : combination) {
                sum += i;
            }
            sumList.add(sum);
        }

        System.out.println();
        System.out.println(sumList);

        for (int i : sumList) {
            if (primeList.contains(i)) {
                count++;
            }
        }

        return count;
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

    private List<List<Integer>> generateCombinations(List<Integer> elements, int r) {
        List<List<Integer>> combinations = new ArrayList<>();
        generateCombinationsRecursive(elements, r, new ArrayList<>(), combinations, 0);
        return combinations;
    }

    private void generateCombinationsRecursive(
            List<Integer> elements,
            int r, List<Integer> currentCombination,
            List<List<Integer>> combinations, int start
    ) {
        if (r == 0) {
            combinations.add(new ArrayList<>(currentCombination));
            return;
        }

        for (int i = start; i < elements.size(); i++) {
            currentCombination.add(elements.get(i));
            generateCombinationsRecursive(elements, r - 1, currentCombination, combinations, i + 1);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }

    // 다른 사람의 풀이
    public int solution2(int[] nums) {
        int ans = 0;

        for(int i = 0; i < nums.length - 2; i ++){
            for(int j = i + 1; j < nums.length - 1; j ++){
                for(int k = j + 1; k < nums.length; k ++){
                    if(isPrime(nums[i] + nums[j] + nums[k])){
                        ans += 1;
                    }
                }
            }
        }
        return ans;
    }

    public Boolean isPrime(int num){
        int cnt = 0;
        for(int i = 1; i <= (int)Math.sqrt(num); i ++){
            if(num % i == 0) cnt += 1;
        }
        return cnt == 1;
    }

    public static void main(String[] args) {
        P12977 problem = new P12977();
        System.out.println(problem.solution(new int[]{1, 2, 3}));
    }
}

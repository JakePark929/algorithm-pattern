package com.jake.argorithm.a00_privatetest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Combination {
    public void solution() {
        LinkedList<Integer> numList = new LinkedList<>();
        for(int i = 1; i <= 5; i++) {
            numList.add(i);
        }
        List<List<Integer>> combinations = generateCombinations(numList, 4);
        for (List<Integer> combination : combinations) {
            System.out.print(combination + " ");
        }
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

    public static void main(String[] args) {
        Combination combination = new Combination();
        combination.solution();
    }
}

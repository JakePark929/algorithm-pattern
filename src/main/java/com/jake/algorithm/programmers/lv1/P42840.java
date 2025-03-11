package com.jake.algorithm.programmers.lv1;

import java.util.*;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * [ 모의고사 ]
 * <p>
 * 수포자는 수학을 포기한 사람의 준말입니다.
 * 수포자 삼인방은 모의고사에 수학 문제를 전부 찍으려 합니다.
 * 수포자는 1번 문제부터 마지막 문제까지 다음과 같이 찍습니다.
 * <p>
 * 1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
 * 2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
 * 3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...
 * <p>
 * 1번 문제부터 마지막 문제까지의 정답이 순서대로 들은 배열 answers가 주어졌을 때,
 * 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return 하도록 solution 함수를 작성해주세요.
 * <p>
 * 제한 조건
 * 시험은 최대 10,000 문제로 구성되어있습니다.
 * 문제의 정답은 1, 2, 3, 4, 5중 하나입니다.
 * 가장 높은 점수를 받은 사람이 여럿일 경우, return하는 값을 오름차순 정렬해주세요.
 * <p>
 * 작성일 : 2023.06.20
 */
class P42840 {
    public LinkedList<Integer> solution(int[] answers) {
        int[] number1 = {1, 2, 3, 4, 5};
        int[] number2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] number3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        LinkedList<Integer> supoja1 = new LinkedList<>();
        LinkedList<Integer> supoja2 = new LinkedList<>();
        LinkedList<Integer> supoja3 = new LinkedList<>();

        Integer[] count = {0, 0, 0};

        LinkedList<Integer> complete = new LinkedList<>();

        for (int i = 0; i < answers.length; i++) {
            supoja1.add(number1[i % number1.length]);
            supoja2.add(number2[i % number2.length]);
            supoja3.add(number3[i % number3.length]);
            if (answers[i] == supoja1.get(i)) {
                count[0]++;
            }
            if (answers[i] == supoja2.get(i)) {
                count[1]++;
            }
            if (answers[i] == supoja3.get(i)) {
                count[2]++;
            }
        }

        Integer[] copy = new Integer[count.length];
        System.arraycopy(count, 0, copy, 0, copy.length);
        Arrays.sort(copy, Comparator.reverseOrder());

        for (int i = 0; i < count.length; i++) {
            if (count[i] >= copy[0]) {
                complete.add(i + 1);
            }
        }

        return complete;
    }

    // 다른 사람의 풀이 1
    public int[] solution2(int[] answers) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] c = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] score = new int[3];
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == a[i % a.length]) { score[0]++; }
            if (answers[i] == b[i % b.length]) { score[1]++; }
            if (answers[i] == c[i % c.length]) { score[2]++; }
        }
        int maxScore = Math.max(score[0], Math.max(score[1], score[2]));
        ArrayList<Integer> list = new ArrayList<>();
        if (maxScore == score[0]) { list.add(1); }
        if (maxScore == score[1]) { list.add(2); }
        if (maxScore == score[2]) { list.add(3); }
        System.out.println(list);
        int[] answer = new int[list.size()];
        for(int i = 0; i < answer.length; i++) { answer[i] = list.get(i); }

        return answer;
//        return list.stream().mapToInt(i -> i.intValue()).toArray();
    }

    // 다른 사람의 풀이 2 - 객체지향
    public int[] solution3(int[] answers) {
        return new Students().getBestStudents(
                        Arrays.stream(answers)
                                .boxed()
                                .map(Answer::new)
                                .collect(Collectors.toList()))
                .stream()
                .mapToInt(i -> i)
                .toArray();
    }

    private static class Students {
        private final List<Student> students;

        public Students() {
            this.students = new ArrayList<>();
            this.students.add(Student.of(1, Arrays.asList(1, 2, 3, 4, 5)));
            this.students.add(Student.of(2, Arrays.asList(2, 1, 2, 3, 2, 4, 2, 5)));
            this.students.add(Student.of(3, Arrays.asList(3, 3, 1, 1, 2, 2, 4, 4, 5, 5)));
        }

        public List<Integer> getBestStudents(List<Answer> answers) {
            return this.students.stream()
                    .map(student -> student.getResult(answers))
                    .collect(Collectors.collectingAndThen(Collectors.toList(), Results::new))
                    .getBestResults().stream()
                    .map(Result::getStudentId)
                    .collect(Collectors.toList());
        }
    }

    private static class Student {
        private final Integer id;
        private final List<Answer> answerPattern;

        public Student(Integer id, List<Answer> answerPattern) {
            this.id = id;
            this.answerPattern = answerPattern;
        }

        public static Student of(Integer id, List<Integer> answerPattern) {
            return new Student(id, answerPattern.stream()
                    .map(Answer::new)
                    .collect(Collectors.toList()));
        }

        public Result getResult(List<Answer> answers) {
            Long correctCount = IntStream.range(0, answers.size())
                    .filter(isCorrect(answers))
                    .count();

            return new Result(this.id, correctCount.intValue());
        }

        private IntPredicate isCorrect(List<Answer> answers) {
            return index -> {
                int patternIndex = index % answerPattern.size();
                Answer answer = answerPattern.get(patternIndex);
                Answer correctAnswer = answers.get(index);

                return answer.isCorrect(correctAnswer);
            };
        }
    }

    private static class Results {
        private final List<Result> results;

        public Results(List<Result> results) {
            this.results = results;
        }

        public List<Result> getBestResults() {
            Result bestResult = Collections.max(results);

            return results.stream()
                    .filter(result -> result.isCorrectCountEqualTo(bestResult))
                    .collect(Collectors.toList());
        }
    }

    private static class Result implements Comparable<Result> {
        private final Integer studentId;
        private final Integer correctCount;

        public Result(Integer studentId, Integer correctCount) {
            this.studentId = studentId;
            this.correctCount = correctCount;
        }

        public boolean isCorrectCountEqualTo(Result result) {
            return this.correctCount.equals(result.correctCount);
        }

        public Integer getStudentId() {
            return studentId;
        }

        @Override
        public int compareTo(Result o) {
            return this.correctCount.compareTo(o.correctCount);
        }

    }

    private static class Answer {
        private final Integer answer;

        public Answer(Integer answer) {
            this.answer = answer;
        }

        public boolean isCorrect(Answer answer) {
            return this.answer.equals(answer.answer);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Answer answer1 = (Answer) o;
            return Objects.equals(answer, answer1.answer);
        }

        @Override
        public int hashCode() {
            return Objects.hash(answer);
        }
    }

    public static void main(String[] args) {
        P42840 problem = new P42840();
//        System.out.println(problem.solution(new int[]{2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5}));
        System.out.println(Arrays.toString(problem.solution2(new int[]{1, 3, 2, 4, 2})));
    }
}

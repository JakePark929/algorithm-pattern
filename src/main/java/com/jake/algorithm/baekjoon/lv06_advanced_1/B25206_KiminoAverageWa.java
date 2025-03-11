package com.jake.algorithm.baekjoon.lv06_advanced_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * [ 너의 평점은 ]
 *
 * 인하대학교 컴퓨터공학과를 졸업하기 위해서는,
 * 전공평점이 3.3 이상이거나 졸업고사를 통과해야 한다.
 * 그런데 아뿔싸, 치훈이는 깜빡하고 졸업고사를 응시하지 않았다는 사실을 깨달았다!
 *
 * 치훈이의 전공평점을 계산해주는 프로그램을 작성해보자.
 *
 * 전공평점은 전공과목별 (학점 × 과목평점)의 합을 학점의 총합으로 나눈 값이다.
 *
 * 인하대학교 컴퓨터공학과의 등급에 따른 과목평점은 다음 표와 같다.
 *
 * A+	4.5
 * A0	4.0
 * B+	3.5
 * B0	3.0
 * C+	2.5
 * C0	2.0
 * D+	1.5
 * D0	1.0
 * F	0.0
 *
 * P/F 과목의 경우 등급이 P또는 F로 표시되는데, 등급이 P인 과목은 계산에서 제외해야 한다.
 *
 * 과연 치훈이는 무사히 졸업할 수 있을까?
 *
 * 입력
 * 20줄에 걸쳐 치훈이가 수강한 전공과목의 과목명, 학점, 등급이 공백으로 구분되어 주어진다.
 *
 * 출력
 * 치훈이의 전공평점을 출력한다.
 *
 * 정답과의 절대오차 또는 상대오차가
 * \(10^{-4}\) 이하이면 정답으로 인정한다.
 *
 * 제한
 * 1 ≤ 과목명의 길이 ≤ 50
 * 과목명은 알파벳 대소문자 또는 숫자로만 이루어져 있으며, 띄어쓰기 없이 주어진다.
 * 입력으로 주어지는 모든 과목명은 서로 다르다.
 * 학점은 1.0,2.0,3.0,4.0중 하나이다.
 * 등급은 A+,A0,B+,B0,C+,C0,D+,D0,F,P중 하나이다.
 * 적어도 한 과목은 등급이 P가 아님이 보장된다.
 *
 * 작성일 : 2023.07.24
 */
class B25206_KiminoAverageWa {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        ArrayList<Subject> subjects = new ArrayList<>();

        for(int i = 0; i < 20; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String name = st.nextToken();
            Double score = Double.parseDouble(st.nextToken());
            String grade = st.nextToken();

            subjects.add(new Subject(name, score, grade));
        }

        double mySum = 0;
        double sum = 0;

        for(Subject s : subjects) {
            if(!Objects.equals(s.getGrade(), "P")) {
                mySum += s.getScore() * s.getMyScore();
                sum += s.getScore();
            }
        }

        System.out.println(mySum / sum);
    }

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] arr1 = {"A+", "A0", "B+", "B0", "C+", "C0", "D+", "D0", "F"};
        double[] arr2 = {4.5, 4.0, 3.5, 3.0, 2.5, 2.0, 1.5, 1.0, 0.0};

        double total = 0;
        double totalGrade=0;

        for(int k=0; k<20; k++) {
            String[] str = br.readLine().split(" ");
            if(!str[2].equals("P")) {
                int idx = Arrays.binarySearch(arr1, str[2]);
                totalGrade += arr2[idx] * Double.parseDouble(str[1]);
                total += Double.parseDouble(str[1]);
            }
        }

        System.out.println(totalGrade / total);

    }
}

class Subject {
     private String name;
     private Double score;
     private String grade;
     private Double myScore;

    public Subject() {}

    public Subject(String name, Double score, String grade) {
        this.name = name;
        this.score = score;
        this.grade = grade;

        switch (grade) {
            case "A+" : this.myScore = 4.5; break;
            case "A0" : this.myScore = 4.0; break;
            case "B+" : this.myScore = 3.5; break;
            case "B0" : this.myScore = 3.0; break;
            case "C+" : this.myScore = 2.5; break;
            case "C0" : this.myScore = 2.0; break;
            case "D+" : this.myScore = 1.5; break;
            case "D0" : this.myScore = 1.0; break;
            case "F" : this.myScore = 0.0; break;
        }
    }

    public String getName() {
        return name;
    }

    public Double getScore() {
        return score;
    }

    public String getGrade() {
        return grade;
    }

    public Double getMyScore() {
        return myScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        Subject subject = (Subject) o;
        return Objects.equals(name, subject.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", grade='" + grade + '\'' +
                ", myScore=" + myScore +
                '}';
    }
}

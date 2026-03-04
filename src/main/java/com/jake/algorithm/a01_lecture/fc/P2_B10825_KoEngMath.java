package com.jake.algorithm.a01_lecture.fc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [ 국영수 ]
 *
 * 문제
 * 도현이네 반 학생 N명의 이름과 국어, 영어, 수학 점수가 주어진다.
 * 이때, 다음과 같은 조건으로 학생의 성적을 정렬하는 프로그램을 작성하시오.
 *
 * 국어 점수가 감소하는 순서로
 * 국어 점수가 같으면 영어 점수가 증가하는 순서로
 * 국어 점수와 영어 점수가 같으면 수학 점수가 감소하는 순서로
 * 모든 점수가 같으면 이름이 사전 순으로 증가하는 순서로
 * (단, 아스키 코드에서 대문자는 소문자보다 작으므로 사전순으로 앞에 온다.)
 *
 * 입력
 * 첫째 줄에 도현이네 반의 학생의 수 N (1 ≤ N ≤ 100,000)이 주어진다.
 * 둘째 줄부터 한 줄에 하나씩 각 학생의 이름, 국어, 영어, 수학 점수가 공백으로 구분해 주어진다.
 * 점수는 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.
 * 이름은 알파벳 대소문자로 이루어진 문자열이고, 길이는 10자리를 넘지 않는다.
 *
 * 출력
 * 문제에 나와있는 정렬 기준으로 정렬한 후 첫째 줄부터 N개의 줄에 걸쳐 각 학생의 이름을 출력한다.
 *
 * 작성일 : 2025.03.12
 */
public class P2_B10825_KoEngMath {
    static int N;
    static Elem[] elems;

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static class Elem implements Comparable<Elem> {
        public String name;
        public int  korean, english, math;

        @Override
        public int compareTo(Elem other) {
            if (korean != other.korean) {
                return other.korean - korean;
            }

            if (english != other.english) {
                return english - other.english;
            }

            if (math != other.math) {
                return other.math - math;
            }

            return name.compareTo(other.name);
        }
    }

    static void input() {
        N = scan.nextInt();
        elems = new Elem[N];

        for (int i = 0; i < N; i++) {
            elems[i] = new Elem();
            elems[i].name = scan.next();
            elems[i].korean = scan.nextInt();
            elems[i].english = scan.nextInt();
            elems[i].math = scan.nextInt();
        }
    }

    static void progress() {
        Arrays.sort(elems);

        for (final Elem elem : elems) {
            sb.append(elem.name).append("\n");
        }
    }

    public static void main(String[] args) {
        input();

        progress();

        System.out.println(sb.toString());
    }

    private static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    // 다른 사람 풀이
    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Student> q = new PriorityQueue<>((a, b) ->{
            if(a.KoreanScore != b.KoreanScore)
                return -Integer.compare(a.KoreanScore, b.KoreanScore);

            if(a.EnglishScore != b.EnglishScore)
                return Integer.compare(a.EnglishScore, b.EnglishScore);

            if(a.MathScore != b.MathScore)
                return -Integer.compare(a.MathScore, b.MathScore);

            return a.name.compareTo(b.name);
        });

        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int KoreanScore = Integer.parseInt(st.nextToken());
            int EnglishScore = Integer.parseInt(st.nextToken());
            int MathScore = Integer.parseInt(st.nextToken());

            q.add(new Student(name, KoreanScore, EnglishScore, MathScore));
        }

        while(!q.isEmpty()) sb.append(q.poll().name).append('\n');

        System.out.print(sb);
    }

    static class Student{
        String name;
        int KoreanScore;
        int EnglishScore;
        int MathScore;

        Student(String name, int KoreanScore, int EnglishScore, int MathScore){
            this.name = name;
            this.KoreanScore = KoreanScore;
            this.EnglishScore = EnglishScore;
            this.MathScore = MathScore;
        }
    }
}

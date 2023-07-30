package com.jake.argorithm.baekjoon.lv08_math_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ 분수찾기 ]
 * <p>
 * 무한히 큰 배열에 다음과 같이 분수들이 적혀있다.
 * <p>
 * 1/1	1/2	1/3	1/4	1/5	…
 * 2/1	2/2	2/3	2/4	…	…
 * 3/1	3/2	3/3	…	…	…
 * 4/1	4/2	…	…	…	…
 * 5/1	…	…	…	…	…
 * …	…	…	…	…	…
 * 이와 같이 나열된 분수들을 1/1 → 1/2 → 2/1 → 3/1 → 2/2 → … 과 같은
 * 지그재그 순서로 차례대로 1번, 2번, 3번, 4번, 5번, … 분수라고 하자.
 * <p>
 * X가 주어졌을 때, X번째 분수를 구하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 X(1 ≤ X ≤ 10,000,000)가 주어진다.
 * <p>
 * 출력
 * 첫째 줄에 분수를 출력한다.
 * <p>
 * 작성일 : 2023.07.27
 */
class B1193_FindFraction {
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    public static void main(String[] args) throws Exception {
        long n = read();

        long start = 2;
        long count = 2;

        while (start <= n) {
            start = start + count;
            count++;
        }

        String[] s = new String[(int) count - 1];

        if ((count - 1) % 2 == 1) {
            for (int i = 1; i <= count - 1; i++) {
                s[i - 1] = (count - i) + "/" + i;
            }
        } else {
            for (int i = 1; i <= count - 1; i++) {
                s[i - 1] = i + "/" + (count - i);
            }
        }

        System.out.println(s[(int) (n - start + count - 1)]);
    }

    // 다른 사람의 풀이 1
    public static void main1(String... args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        br.close();

        int i = 1;
        while (i * (i + 1) < X * 2) {
            i++;
        }

        X -= ((i - 1) * i / 2);

        StringBuilder sb = new StringBuilder();
        if (i % 2 == 0) {
            sb.append(X).append("/").append(i + 1 - X);
        } else {
            sb.append(i + 1 - X).append("/").append(X);
        }

        System.out.println(sb);
    }
}

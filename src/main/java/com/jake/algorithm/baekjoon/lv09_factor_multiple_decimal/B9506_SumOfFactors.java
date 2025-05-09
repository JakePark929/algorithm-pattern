package com.jake.algorithm.baekjoon.lv09_factor_multiple_decimal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * [ 약수들의 합 ]
 * <p>
 * 어떤 숫자 n이 자신을 제외한 모든 약수들의 합과 같으면, 그 수를 완전수라고 한다.
 * 예를 들어 6은 6 = 1 + 2 + 3 으로 완전수이다.
 * n이 완전수인지 아닌지 판단해주는 프로그램을 작성하라.
 * <p>
 * 입력
 * 입력은 테스트 케이스마다 한 줄 간격으로 n이 주어진다. (2 < n < 100,000)
 * 입력의 마지막엔 -1이 주어진다.
 * <p>
 * 출력
 * 테스트케이스 마다 한줄에 하나씩 출력해야 한다.
 * n이 완전수라면, n을 n이 아닌 약수들의 합으로 나타내어 출력한다(예제 출력 참고).
 * 이때, 약수들은 오름차순으로 나열해야 한다.
 * n이 완전수가 아니라면 n is NOT perfect. 를 출력한다.
 * <p>
 * 작성일 : 2023.07.28
 */
class B9506_SumOfFactors {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == -1) break;

            ArrayList<Integer> factors = new ArrayList<>();
            for (int i = 1; i < n; i++) {
                if (n % i == 0) {
                    factors.add(i);
                }
            }

            int sum = 0;

            for(int factor : factors) {
                sum += factor;
            }

            StringBuilder sb = new StringBuilder();

            if(n == sum) {
                sb.append(n).append(" = ");
                for(int i = 0; i < factors.size(); i++) {
                    if(i == factors.size() - 1) {
                        sb.append(factors.get(i));
                    } else {
                        sb.append(factors.get(i)).append(" + ");
                    }
                }
            } else  {
                sb.append(n).append(" is NOT perfect.");
            }

            System.out.println(sb);
        }
    }
}

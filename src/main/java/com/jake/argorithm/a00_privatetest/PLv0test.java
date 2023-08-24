package com.jake.argorithm.a00_privatetest;

public class PLv0test {
    public String p181943(String my_string, String overwrite_string, int s) {
        StringBuilder sb = new StringBuilder();
        sb.append(my_string, 0, s);
        sb.append(overwrite_string);
        sb.append(my_string.substring(sb.length()));
        return sb.toString();
    }

    public String p181942(String str1, String str2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str1.length(); i++) {
            sb.append(str1.charAt(i));
            sb.append(str2.charAt(i));
        }
        return sb.toString();
    }

    // 230618 더 크게 합치기
    public int p181939(int a, int b) {
        return Math.max(
                Integer.parseInt(Integer.toString(Math.max(a, b)) + Math.min(a, b)),
                Integer.parseInt(Integer.toString(Math.min(a, b)) + Math.max(a, b))
        );
    }

    // 230618 홀짝에 따라 다른 값 반환하기
    public int p181935(int n) {
        int answer = 0;
        if (n % 2 == 1) {
            for (int i = 0; i <= n; i++) {
                if (i % 2 == 1) {
                    answer += i;
                }
            }
        } else {
            for (int i = 0; i <= n; i++) {
                if (i % 2 == 0) {
                    answer += i * i;
                }
            }
        }
        return answer;
    }

    // 수열로 풀기
    public int p181935_2(int n) {
        if (n % 2 == 1) {
            return (n + 1) * (n + 1) / 2 / 2;
        } else {
            return 4 * n/2 * (n/2 + 1) * (2 * n/2 + 1) / 6;
        }
    }

    public int p181934(String ineq, String eq, int n, int m) {
        if ((ineq + eq).equals("<=")) return n <= m ? 1 : 0;
        if ((ineq + eq).equals(">=")) return n >= m ? 1 : 0;
        if ((ineq + eq).equals("<!")) return n < m ? 1 : 0;
        if ((ineq + eq).equals(">!")) return n > m ? 1 : 0;
        return 0;
    }

    public static void main(String[] args) {
        PLv0test problem = new PLv0test();
//        // !@#$%^&*(\'"<>?:;
//        System.out.println("!@#$%^&*(\\\'\"<>?:;");
//        System.out.println(problem.p181943("He11oWor1d", "lloWorl", 2));
//        System.out.println(problem.p181942("aaaa", "bbbb"));
//        System.out.println(problem.p181939(9, 91));
//        System.out.println(problem.p181935(7));
        System.out.println(problem.p181934(">", "!", 41, 78));

    }
}

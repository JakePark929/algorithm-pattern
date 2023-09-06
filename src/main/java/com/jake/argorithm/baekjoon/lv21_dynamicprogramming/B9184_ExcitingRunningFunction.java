package com.jake.argorithm.baekjoon.lv21_dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [ 신나는 함수 실행 ]
 * <p>
 * 재귀 호출만 생각하면 신이 난다! 아닌가요?
 * <p>
 * 다음과 같은 재귀함수 w(a, b, c)가 있다.
 * <p>
 * if a <= 0 or b <= 0 or c <= 0, then w(a, b, c) returns:
 * 1
 * <p>
 * if a > 20 or b > 20 or c > 20, then w(a, b, c) returns:
 * w(20, 20, 20)
 * <p>
 * if a < b and b < c, then w(a, b, c) returns:
 * w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c)
 * <p>
 * otherwise it returns:
 * w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1)
 * <p>
 * 위의 함수를 구현하는 것은 매우 쉽다.
 * 하지만, 그대로 구현하면 값을 구하는데 매우 오랜 시간이 걸린다. (예를 들면, a=15, b=15, c=15)
 * <p>
 * a, b, c가 주어졌을 때, w(a, b, c)를 출력하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 입력은 세 정수 a, b, c로 이루어져 있으며, 한 줄에 하나씩 주어진다.
 * 입력의 마지막은 -1 -1 -1로 나타내며, 세 정수가 모두 -1인 경우는 입력의 마지막을 제외하면 없다.
 * <p>
 * 출력
 * 입력으로 주어진 각각의 a, b, c에 대해서, w(a, b, c)를 출력한다.
 * <p>
 * 제한
 * -50 ≤ a, b, c ≤ 50
 * <p>
 * 작성일 : 2023.09.08
 */
class B9184_ExcitingRunningFunction {
    /*
     * 함수 w 에서 a, b, c 중 20이 넘어가게 되면 w(20, 20, 20)을 호출하고,
     * 0 이하일 경우는 1을 반환하기 때문에 각 배열의 크기가 21(0 ~ 20)을 넘기지 않는다.
     */
    private static final int[][][] dp = new int[21][21][21];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int a, b, c;
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1 && c == -1) break;

            sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ")
                    .append(w(a, b, c)).append('\n');
        }

        System.out.println(sb);
    }

    // Dynamic Programming
    private static int w(int a, int b, int c) {
        // a, b, c 가 범위를 벗어나지 않으면서 메모이제이션이 되어 있는 경우
        if (inRange(a, b, c) && dp[a][b][c] != 0) {
            return dp[a][b][c];
        }

        if (a <= 0 || b <= 0 || c <= 0) {
            return 1;
        }

        if (a > 20 || b > 20 || c > 20) {
            return dp[20][20][20] = w(20, 20, 20);
        }

        if (a < b && b < c) {
            return dp[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
        }

        return dp[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
    }

    /*
     * 배열을 참조하려 할 때
     * a, b, c 중 하나라도 범위 밖의 수가 나올 수 있기 때문에
     * 이를 체크해주기 위한 함수
     */
    private static boolean inRange(int a, int b, int c) {
        return 0 <= a && a <= 20 && 0 <= b && b <= 20 && 0 <= c && c <= 20;
    }

    // Recursive Function
//    private static int w(int a, int b, int c) {
//        if (a <= 0 || b <= 0 || c <= 0) return 1;
//        if (a > 20 || b > 20 || c > 20) return w(20, 20, 20);
//
//        if (a < b && b < c) {
//            return w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
//        } else {
//            return w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
//        }
//    }

    // 다른 사람의 풀이 1
    private static final String head = "w(", split = ", ", tail = ") = ";
    private static final int SIZE = 20;

    public static void main1(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();
        int[][][] W = new int[SIZE + 1][SIZE + 1][SIZE + 1];

        for (int i = 0; i <= SIZE; i++)
            for (int j = 0; j <= SIZE; j++)
                W[0][i][j] = W[i][0][j] = W[i][j][0] = 1;

        for (int a = 1; a <= SIZE; a++)
            for (int b = 1; b <= SIZE; b++)
                for (int c = 1; c <= SIZE; c++)
                    if (a < b && b < c)
                        W[a][b][c] = W[a][b][c - 1] + W[a][b - 1][c - 1] - W[a][b - 1][c];
                    else
                        W[a][b][c] = W[a - 1][b][c] + W[a - 1][b - 1][c] + W[a - 1][b][c - 1] - W[a - 1][b - 1][c - 1];

        while (true) {
            int ans, a = read(), b = read(), c = read();

            if (a == -1 && b == -1 && c == -1) break;

            if (a < 1 || b < 1 || c < 1)
                ans = 1;
            else if (a > SIZE || b > SIZE || c > SIZE)
                ans = W[SIZE][SIZE][SIZE];
            else
                ans = W[a][b][c];

            sb.append(head)
                    .append(a).append(split)
                    .append(b).append(split)
                    .append(c).append(tail)
                    .append(ans).append('\n');

        }

        System.out.print(sb);

    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;
        if (isNegative) n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return isNegative ? ~n + 1 : n;
    }

    // 다른 사람의 풀이 2
//    static int dp[][][]=new int[21][21][21];
//    static int read() throws Exception{
//        int i,j=System.in.read()&15;
//        boolean nega=false;
//        if(j==13){nega=true;j=System.in.read()&15;}
//        while((i=System.in.read())>32)j=(j<<3)+(j<<1)+(i&15);
//        if(i==13)System.in.read();
//        if(nega)return -j; else return j;
//    }

//    static int w(int a,int b,int c){
//        if(a<=0||b<=0||c<=0)return 1;
//        if(a>20||b>20||c>20)return w(20,20,20);
//        if(a<b&&b<c){
//            if(dp[a][b][c]==0)dp[a][b][c]=w(a,b,c-1)+w(a,b-1,c-1)-w(a,b-1,c);
//            return dp[a][b][c];
//        }
//        if(dp[a][b][c]==0)dp[a][b][c]=w(a-1,b,c)+w(a-1,b-1,c)+w(a-1,b,c-1)-w(a-1,b-1,c-1);
//        return dp[a][b][c];
//    }
//    public static void main(String[] args) throws Exception{
//        StringBuilder sb=new StringBuilder();
//        int a=read(),b=read(),c=read();
//        dp[0][0][0]=1;
//        while(a!=-1||b!=-1||c!=-1){
//            sb.append("w(").append(a).append(", ").append(b).append(", ").
//                    append(c).append(") = ").append(w(a,b,c)).append("\n");
//            a=read();b=read();c=read();
//        }
//        System.out.print(sb);
//    }
}

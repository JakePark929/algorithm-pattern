package com.jake.algorithm.baekjoon.lv15_factor_multiple_decimal_2;

import java.io.*;
import java.math.BigInteger;

/**
 * [ 다음 소수 ]
 * <p>
 * 정수 n(0 ≤ n ≤ 4*109)가 주어졌을 때,
 * n보다 크거나 같은 소수 중 가장 작은 소수 찾는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 테스트 케이스의 개수가 주어진다.
 * 각 테스트 케이스는 한 줄로 이루어져 있고, 정수 n이 주어진다.
 * <p>
 * 출력
 * 각각의 테스트 케이스에 대해서 n보다 크거나 같은 소수 중 가장 작은 소수를 한 줄에 하나씩 출력한다.
 * <p>
 * 작성일 : 2023.08.08
 */
class B4134_NextPrime {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

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

    // 다른 사람의 풀이 1
    public static void main1(String[] args) throws IOException {
        final int ARR_SIZE = 63300;
        int[] primeNum = new int[ARR_SIZE];

        int T;
        long n;
        boolean isPrime = false;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < ARR_SIZE; i++) {
            primeNum[i] = 1;
        }
        // 소수 배열 구하기 - 에라스토테네스의 체
        // 1이면 소수이고, 0이면 소수가 아니다.
        primeNum[0] = 0;
        primeNum[1] = 0;

        for (int i = 2; i < ARR_SIZE; i++) {
            for (int j = 2; i * j < ARR_SIZE; j++) {
                primeNum[i * j] = 0;
            }
        }

        // 입력 받은 수 n에 대하여 n보다 크거나 같은 소수 중 가장 작은 소수를 구해보자.
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            n = Long.parseLong(br.readLine());

            if (n <= 1) {
                System.out.println(2);
            } else {
                for (long i = n; ; i++) {
                    int m = (int) Math.sqrt(i);
                    isPrime = true;

                    for (int j = 1; j <= m; j++) {
                        if (primeNum[j] == 1) {
                            if (i % j == 0) {
                                isPrime = false;
                                break;
                            }
                        }
                    }

                    if (isPrime) {
                        System.out.println(i);
                        break;
                    }
                }
            }
        }
    }

    // 다른 사람의 풀이 2
    static boolean arr[] = new boolean[65000];
    static long prime[];

    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        arr[0] = true;
        arr[1] = true;

        jump1:
        for (int i = 2; i < 253; i++) {
            if (!arr[i]) {
                for (int j = 2; j < 32500; j++) {
                    if (i * j > 64999) {
                        continue jump1;
                    } else {
                        arr[i * j] = true;
                    }
                }
            }
        }

        int count = 0;
        for (int i = 0; i < 65000; i++) {
            if (!arr[i]) {
                count++;
            }
        }

        prime = new long[count];
        int index = 0;
        for (int i = 0; i < 65000; i++) {
            if (!arr[i]) {
                prime[index] = i;
                index++;
            }
        }

        int N = Integer.parseInt(br.readLine());
        long num;
        for (int i = 0; i < N; i++) {
            num = Long.parseLong(br.readLine());
            if (num == 0 || num == 1) {
                sb.append(2).append('\n');
            } else {
                jump:
                while (true) {
                    for (int j = 0; Math.sqrt(num) >= prime[j]; j++) {
                        if (num % prime[j] == 0) {
                            num++;
                            continue jump;
                        }
                    }
                    sb.append(num).append('\n');
                    break;
                }
            }
        }
        System.out.println(sb);
    }

    // 다른 사람의 풀이 3
    public static void main3(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            long quest = Long.parseLong(br.readLine());
            long ans = findPrime(quest);
            sb.append(ans).append('\n');
        }

        System.out.println(sb.toString());
    }

    public static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        long sqrtN = (long) Math.sqrt(n) + 1;
        for (long i = 6L; i <= sqrtN; i += 6) {
            if (n % (i - 1) == 0 || n % (i + 1) == 0) return false;
        }
        return true;
    }

    public static long findPrime(long n) {
        if (n <= 2) return 2;
        long index = n % 2 == 0 ? n + 1 : n;
        while (!isPrime(index)) {
            index += 2;
        }
        return index;
    }

    // 다른 사람의 풀이 4
//    // https://blog.naver.com/jihogrammer/222314445259
//    static long read() throws Exception {
//        long c, n = System.in.read() & 15;
//        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
//
//        if (c == 13) System.in.read();
//
//        return n;
//    }
//
//    private static int readInt() throws Exception {
//        int c, n = System.in.read() & 15; // 음수일 경우 '-' 문자를 읽게 됨
//        boolean isNegative = n == 13;     // '-'는 45이지만 비트연산으로 인해 13으로 값 비교
//        if (isNegative) n = System.in.read() & 15; // 음수이면 n에 숫자 부분으로 덮어쓰기
//        // 숫자 부분은 양수와 동일하게 처리
//        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
//
//        if (c == 13) System.in.read();
//
//        return isNegative ? ~n + 1 : n; // 음수일 경우 2의 보수 취하고 반환
//
//    }

    // https://stack07142.tistory.com/32

    /*
     * 소수 정리에 따르면
     * 1. N이 충분히 큰 수라면 N 근처에 존재하는 소수들 사이의 평균 간격은 대략적으로 lnN이다
     * 2. 어떤 큰 수 N에 가까운 정수 하나를 무작위로 고를때, 그 정수가 소수일 확률은 1/lnN에 근사
     * 3. 소수의 분포는 더 큰 수로 갈수록 적어진다
     */

    // "n이 소수인지 판별하려는 수 일때, n의 양의 제곱근 보다 작은 소수로 나누어 떨어지지 않으면 n은 소수이다."
    // 따라서, 4*10^9 이상의 수에 대해 소수임을 판별하려면 sqrt(4*10^9) = 63,245 보다 작은 소수들로 나누자.
    // 소수 정리에 따라 4*10^9 근처의 소수의 간격은 ln(4*10^9)이므로
    // 즉 4*10^9이 소수가 아니라 해도 4*10^9 + ln(4*10^9) 이내에는 소수가 존재한다.
    // 넉넉잡아 63,300 크기의 배열을 선언하자.

//    public void solution() throws Exception {
//        long N = read();
//        StringBuilder sb = new StringBuilder();
//
//        int maxLength = 63300;
//        boolean[] primes = new boolean[maxLength];
//        primes[0] = true;
//        primes[1] = true;
//
//        for (int i = 2; i < Math.sqrt(maxLength); i++) {
//            if (!primes[i]) {
//                for (int j = i * i; j < maxLength; j += i) {
//                    primes[j] = true;
//                }
//            }
//        }
//
//        while (N-- > 0) {
//            long num = read();
//
//            if (num < 2) {
//                sb.append(2).append("\n");
//                continue;
//            }
//
//            while (true) {
//                boolean flag = true;
//
//                int sqrt = (int) Math.sqrt(num);
//                for (int i = 2; i <= sqrt; i++) {
//                    if (!primes[i] && num % i == 0) {
//                        flag = false;
//                        break;
//                    }
//                }
//
//                if (flag) {
//                    break;
//                }
//
//                num++;
//            }
//
//            sb.append(num).append("\n");
//        }
//
//        System.out.println(sb);
//    }
//
//    public static void main(String[] args) throws Exception {
//        new Main().solution();
//    }

    // 다른 사람의 풀이 5
    public static void main5(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); // 테스트 수

        long[] numbers = new long[T]; //정수들을 압력받아 저장할 배열

        for (int i = 0; i < T; i++) {
            numbers[i] = Long.parseLong(br.readLine()); //정수 입력 받고
        }

        br.close(); //입력 버퍼 닫기

        printSection(numbers);
    }

    //소수 찾는 메소드
    static BigInteger findPrimeNumber(BigInteger num) {

        if (num.isProbablePrime(10)) { //만약 지금 받은 매개변수가 소수이면
            return num; //그대로 반환
        } else {
            return num.nextProbablePrime(); //다음 소수 반환
        }
    }

    //출력 부분
    static void printSection(long[] array) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < array.length; i++) {
            //isProbablePrime()랑 nextProbablePrime()가 BigInteger의 형태에서만 지원함.
            BigInteger bigNumber = new BigInteger(String.valueOf(array[i]));
            bw.write(findPrimeNumber(bigNumber) + "\n");
        }

        bw.flush();
        bw.close();
    }
}

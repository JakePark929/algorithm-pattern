package com.jake.algorithm.programmers.lv1;

/**
 * [ 비밀지도 ]
 * <p>
 * 네오는 평소 프로도가 비상금을 숨겨놓는 장소를 알려줄 비밀지도를 손에 넣었다.
 * 그런데 이 비밀지도는 숫자로 암호화되어 있어 위치를 확인하기 위해서는 암호를 해독해야 한다.
 * 다행히 지도 암호를 해독할 방법을 적어놓은 메모도 함께 발견했다.
 * <p>
 * 지도는 한 변의 길이가 n인 정사각형 배열 형태로,
 * 각 칸은 "공백"(" ") 또는 "벽"("#") 두 종류로 이루어져 있다.
 * 전체 지도는 두 장의 지도를 겹쳐서 얻을 수 있다.
 * 각각 "지도 1"과 "지도 2"라고 하자.
 * 지도 1 또는 지도 2 중 어느 하나라도 벽인 부분은 전체 지도에서도 벽이다.
 * 지도 1과 지도 2에서 모두 공백인 부분은 전체 지도에서도 공백이다.
 * "지도 1"과 "지도 2"는 각각 정수 배열로 암호화되어 있다.
 * 암호화된 배열은 지도의 각 가로줄에서 벽 부분을 1, 공백 부분을 0으로 부호화했을 때
 * 얻어지는 이진수에 해당하는 값의 배열이다.
 * <p>
 * 작성일 : 2023.06.19
 */
class P17681 {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        StringBuilder sb = new StringBuilder();
        sb.append("%").append(n).append("s");
        for (int i = 0; i < answer.length; i++) {
            char[] points1 = String.format(sb.toString(), Integer.toBinaryString(arr1[i])).replaceAll(" ", "0").toCharArray();
            char[] points2 = String.format(sb.toString(), Integer.toBinaryString(arr2[i])).replaceAll(" ", "0").toCharArray();
            StringBuilder map = new StringBuilder();
            for (int j = 0; j < points1.length; j++) {
                if (points1[j] + points2[j] == 96) {
                    map.append(" ");
                } else {
                    map.append("#");
                }
            }
            answer[i] = String.valueOf(map);
        }

        return answer;
    }

    // 다른 사람의 풀이 1
    public String[] solution2(int n, int[] arr1, int[] arr2) {
        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            result[i] = Integer.toBinaryString(arr1[i] | arr2[i]); // or 연산으로 2진수 만들기
        }

        for (int i = 0; i < n; i++) {
            result[i] = String.format("%" + n + "s", result[i]);
            result[i] = result[i].replaceAll("1", "#");
            result[i] = result[i].replaceAll("0", " ");
        }

        return result;
    }

    // 다른 사람의 풀이 2
    public String[] solution3(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        String temp;

        for(int i = 0 ; i < n ; i++){
            temp = String.format("%16s", Integer.toBinaryString(arr1[i] | arr2[i]));
            temp = temp.substring(temp.length() - n);
            temp = temp.replaceAll("1", "#");
            temp = temp.replaceAll("0", " ");
            answer[i] = temp;
        }

        return answer;
    }

    // 다른 사람의 풀이 3 - 재귀함수
    public String[] solution4(int n, int [] arr1, int [] arr2) {
        String [] answer = new String[n];
        int [] secretMap = new int[n];
        for(int i = 0; i < n; i++) {
            secretMap[i] = arr1[i] | arr2[i];
            answer[i] = makeSharp(secretMap[i], n);
        }
        return answer;
    }

    public String makeSharp(int n, int m) {
        if(n == 0) {
            if( m > 0) {
                String str = "";
                for(int i = 0; i < m; i++) {
                    str += " ";
                }
                return str;
            }
            else return "";
        }
        else {
            return n % 2 == 0 ? makeSharp(n/2, m-1) + " " : makeSharp(n/2, m-1) + "#";
        }
    }

    public static void main(String[] args) {
        P17681 problem = new P17681();
        int n = 5;
        String[] answer = problem.solution(n, new int[]{9, 20, 28, 18, 11}, new int[]{30, 1, 21, 17, 28});
        for (String s : answer) {
            System.out.println(s);
        }
    }
}

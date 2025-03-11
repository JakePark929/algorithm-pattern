package com.jake.algorithm.programmers.lv1;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;

/**
 *[ 2016년 ]
 *
 * 2016년 1월 1일은 금요일입니다. 2016년 a월 b일은 무슨 요일일까요?
 * 두 수 a ,b를 입력받아 2016년 a월 b일이 무슨 요일인지 리턴하는 함수, solution을 완성하세요.
 *
 * 요일의 이름은 일요일부터 토요일까지 각각 SUN,MON,TUE,WED,THU,FRI,SAT 입니다.
 *
 * 예를 들어 a=5, b=24라면 5월 24일은 화요일이므로 문자열 "TUE"를 반환하세요.
 *
 * 제한 조건
 * 2016년은 윤년입니다.
 * 2016년 a월 b일은 실제로 있는 날입니다. (13월 26일이나 2월 45일같은 날짜는 주어지지 않습니다)
 *
 * 작성일 : 2023.06.15
 */
class P12901 {
    public String solution(int a, int b) {
        String answer = "";
        int[] days = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int sum = 0;
        for (int i = 0; i < a - 1; i++) {
            sum += days[i];
        }
        switch ((sum + b) % 7) {
            case  0 : answer = "THU"; break;
            case  1 : answer = "FRI"; break;
            case  2 : answer = "SAT"; break;
            case  3 : answer = "SUN"; break;
            case  4 : answer = "MON"; break;
            case  5 : answer = "TUE"; break;
            case  6 : answer = "WED"; break;
        }
        return answer;
    }

    // 다른 사람의 풀이 - 캘린더 함수 활용
    public String getDayName(int month, int day)
    {
        Calendar cal = new Calendar.Builder().setCalendarType("iso8601")
                .setDate(2016, month - 1, day).build();
        return cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, new Locale("ko-KR")).toUpperCase();
    }

    // 다른 사람의 풀이 - Date 함수사용 2
    public String solution2(int a, int b) {
        return LocalDate.of(2016, a, b).getDayOfWeek().toString().substring(0,3);
    }

    public static void main(String[] args) {
        P12901 problem = new P12901();
        System.out.println(problem.solution(2, 4));
    }
}

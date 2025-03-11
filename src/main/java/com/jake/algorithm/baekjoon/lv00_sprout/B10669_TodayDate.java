package com.jake.algorithm.baekjoon.lv00_sprout;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * [ 오늘 날짜 ]
 *
 * 서울의 오늘 날짜를 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 입력은 없다.
 *
 * 출력
 * 서울의 오늘 날짜를 "YYYY-MM-DD" 형식으로 출력한다.
 *
 * 작성일 : 2023.07.27
 */
class B10669_TodayDate {
    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(simpleDateFormat.format(date));
    }
}

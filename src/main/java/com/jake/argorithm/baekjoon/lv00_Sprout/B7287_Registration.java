package com.jake.argorithm.baekjoon.lv00_Sprout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * [ 등록 ]
 * <p>
 * 자신이 백준 온라인 저지(BOJ)에서 맞은 문제의 수와 아이디를 그대로 출력하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 이 문제는 입력이 없다.
 * <p>
 * 출력
 * 첫 줄에 자신이 맞은 문제의 수, 둘째 줄에 아이디를 출력한다.
 * <p>
 * 작성일 : 2023.07.27
 */
class B7287_Registration {
    static String myData;

    public static void main(String[] args) throws IOException {
        getMyData();

        int correct = getCorrect();
        System.out.println(correct);
        String id = getMyId();
        System.out.println(id);
    }

    public static int getCorrect() {
        int correct = 0;
        try {
            int start = myData.indexOf("맞은 문제");
            String scrap = myData.substring(start, start + 90);

            correct = Integer.parseInt(scrap.substring(scrap.length() - 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return correct;
    }

    public static String getMyId() {
        String myId = null;
        try {
            String startElement = "class=\"solvedac-tier\">&nbsp;</a>";
            int start = myData.indexOf(startElement);
            int end = myData.indexOf(" </h1><blockquote class=\"no-mathjax\">");

            myId = myData.substring(start + startElement.length(), end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myId;   
    }

    public static void getMyData() throws IOException {
        String url = "https://www.acmicpc.net/user/epikeran";
        URL myPageUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) myPageUrl.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3");

        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                response.append(line).append("\n");
            }

            br.close();

            myData =  response.toString();
        } else {
            throw new IOException("Failed to fetch data. Response code: " + conn.getResponseCode());
        }
    }
}

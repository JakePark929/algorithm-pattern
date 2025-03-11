package com.jake.algorithm.programmers.lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * [ 파일명 정렬 ]
 *
 * 세 차례의 코딩 테스트와 두 차례의 면접이라는 기나긴 블라인드 공채를 무사히 통과해
 * 카카오에 입사한 무지는 파일 저장소 서버 관리를 맡게 되었다.
 * 저장소 서버에는 프로그램의 과거 버전을 모두 담고 있어, 이름 순으로 정렬된 파일 목록은 보기가 불편했다. 파
 * 일을 이름 순으로 정렬하면 나중에 만들어진 ver-10.zip이 ver-9.zip보다 먼저 표시되기 때문이다.
 *
 * 버전 번호 외에도 숫자가 포함된 파일 목록은 여러 면에서 관리하기 불편했다.
 * 예컨대 파일 목록이 ["img12.png", "img10.png", "img2.png", "img1.png"]일 경우,
 * 일반적인 정렬은 ["img1.png", "img10.png", "img12.png", "img2.png"] 순이 되지만,
 * 숫자 순으로 정렬된 ["img1.png", "img2.png", "img10.png", img12.png"] 순이 훨씬 자연스럽다.
 *
 * 무지는 단순한 문자 코드 순이 아닌, 파일명에 포함된 숫자를 반영한 정렬 기능을 저장소 관리 프로그램에 구현하기로 했다.
 * 소스 파일 저장소에 저장된 파일명은 100 글자 이내로,
 * 영문 대소문자, 숫자, 공백(" "), 마침표("."), 빼기 부호("-")만으로 이루어져 있다.
 * 파일명은 영문자로 시작하며, 숫자를 하나 이상 포함하고 있다.
 *
 * 파일명은 크게 HEAD, NUMBER, TAIL의 세 부분으로 구성된다.
 *
 * HEAD는 숫자가 아닌 문자로 이루어져 있으며, 최소한 한 글자 이상이다.
 * NUMBER는 한 글자에서 최대 다섯 글자 사이의 연속된 숫자로 이루어져 있으며, 앞쪽에 0이 올 수 있다.
 * 0부터 99999 사이의 숫자로, 00000이나 0101 등도 가능하다.
 * TAIL은 그 나머지 부분으로, 여기에는 숫자가 다시 나타날 수도 있으며, 아무 글자도 없을 수 있다.
 *
 * 파일명	HEAD	NUMBER	TAIL
 * foo9.txt	foo	9	.txt
 * foo010bar020.zip	foo	010	bar020.zip
 * F-15	F-	15	(빈 문자열)
 *
 * 파일명을 세 부분으로 나눈 후, 다음 기준에 따라 파일명을 정렬한다.
 * 파일명은 우선 HEAD 부분을 기준으로 사전 순으로 정렬한다.
 * 이때, 문자열 비교 시 대소문자 구분을 하지 않는다.
 * MUZI와 muzi, MuZi는 정렬 시에 같은 순서로 취급된다.
 * 파일명의 HEAD 부분이 대소문자 차이 외에는 같을 경우, NUMBER의 숫자 순으로 정렬한다.
 * 9 < 10 < 0011 < 012 < 13 < 014 순으로 정렬된다. 숫자 앞의 0은 무시되며, 012와 12는 정렬 시에 같은 같은 값으로 처리된다.
 * 두 파일의 HEAD 부분과, NUMBER의 숫자도 같을 경우, 원래 입력에 주어진 순서를 유지한다.
 * MUZI01.zip과 muzi1.png가 입력으로 들어오면, 정렬 후에도 입력 시 주어진 두 파일의 순서가 바뀌어서는 안 된다.
 * 무지를 도와 파일명 정렬 프로그램을 구현하라.
 *
 * 작성일 : 24.09.29
 */
public class P17686 {
    static class File {
        String head;
        String number;
        String tail;
        String original;

        public File(String head, String number, String tail, String original) {
            this.head = head;
            this.number = number;
            this.tail = tail;
            this.original = original;
        }
    }

    public String[] solution(String[] files) {
        // 1. 파일을 HEAD, NUMBER, TAIL 로 분리하여 List 에 담는다.
        List<File> fileList = new ArrayList<>();

        for (String file : files) {
            String[] parts = splitFile(file);
            fileList.add(new File(parts[0], parts[1], parts[2], file));
        }

        // 2. 정렬 기준을 구현한다.
        fileList.sort((f1, f2) -> {
            // 2.1 HEAD 부분을 사전 순으로 정렬 (대소문자 구분 없이)
            int headCompare = f1.head.toLowerCase().compareTo(f2.head.toLowerCase());
            if (headCompare != 0) {

                return headCompare;
            }

            // 2.2 NUMBER 부분을 숫자로 정렬 (leading zero 는 무시)
            int num1 = Integer.parseInt(f1.number);
            int num2 = Integer.parseInt(f2.number);

            return Integer.compare(num1, num2);
        });

        // 3. 정렬된 결과를 원래 파일명 배열로 반환한다.
        String[] result = new String[files.length];
        for (int i = 0; i < fileList.size(); i++) {
            result[i] = fileList.get(i).original;
        }

        return result;
    }

    // 파일을 HEAD, NUMBER, TAIL 로 나누는 메소드
    public String[] splitFile(String file) {
        StringBuilder head = new StringBuilder();
        StringBuilder number = new StringBuilder();
        String tail = "";

        int idx = 0;
        // 1. HEAD 추출: 숫자가 나올 때까지가 HEAD
        while (idx < file.length() && !Character.isDigit(file.charAt(idx))) {
            head.append(file.charAt(idx));
            idx++;
        }

        // 2. NUMBER 추출: 숫자가 나올 때까지가 NUMBER
        while (idx < file.length() && Character.isDigit(file.charAt(idx))) {
            number.append(file.charAt(idx));
            idx++;
        }

        // 3. 나머지는 TAIL
        if (idx < file.length()) {
            tail = file.substring(idx);
        }

        return new String[]{head.toString(), number.toString(), tail};
    }

    // 다른 사람의 풀이 1
    public String[] solution1(String[] files) {
        Pattern p = Pattern.compile("([a-z\\s.-]+)([0-9]{1,5})");

        Arrays.sort(files, (s1, s2) -> {
            Matcher m1 = p.matcher(s1.toLowerCase());
            Matcher m2 = p.matcher(s2.toLowerCase());
            m1.find();
            m2.find();

            if(!m1.group(1).equals(m2.group(1))) {
                return m1.group(1).compareTo(m2.group(1));
            } else {
                return Integer.parseInt(m1.group(2)) - Integer.parseInt(m2.group(2));
            }
        });

        return files;
    }

    public static void main(String[] args) {
        String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        String[] sortedFiles = new P17686().solution1(files);

        System.out.println(Arrays.toString(sortedFiles));
    }
}

package com.jake.algorithm.programmers.lv1;

/**
 * [ 신규 아이디 추천 ]
 * <p>
 * 카카오에 입사한 신입 개발자 네오는 "카카오계정개발팀"에 배치되어,
 * 카카오 서비스에 가입하는 유저들의 아이디를 생성하는 업무를 담당하게 되었습니다.
 * "네오"에게 주어진 첫 업무는 새로 가입하는 유저들이 카카오 아이디 규칙에 맞지 않는 아이디를 입력했을 때,
 * 입력된 아이디와 유사하면서 규칙에 맞는 아이디를 추천해주는 프로그램을 개발하는 것입니다.
 * 다음은 카카오 아이디의 규칙입니다.
 * <p>
 * 아이디의 길이는 3자 이상 15자 이하여야 합니다.
 * 아이디는 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.) 문자만 사용할 수 있습니다.
 * 단, 마침표(.)는 처음과 끝에 사용할 수 없으며 또한 연속으로 사용할 수 없습니다.
 * "네오"는 다음과 같이 7단계의 순차적인 처리 과정을 통해 신규 유저가 입력한 아이디가 카카오 아이디 규칙에 맞는 지 검사하고
 * 규칙에 맞지 않은 경우 규칙에 맞는 새로운 아이디를 추천해 주려고 합니다.
 * 신규 유저가 입력한 아이디가 new_id 라고 한다면,
 * <p>
 * 1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
 * 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
 * 3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
 * 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
 * 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
 * 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
 * 만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
 * 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
 * <p>
 * 예를 들어, new_id 값이 "...!@BaT#*..y.abcdefghijklm" 라면, 위 7단계를 거치고 나면 new_id는 아래와 같이 변경됩니다.
 * <p>
 * 1단계 대문자 'B'와 'T'가 소문자 'b'와 't'로 바뀌었습니다.
 * "...!@BaT#*..y.abcdefghijklm" → "...!@bat#*..y.abcdefghijklm"
 * <p>
 * 2단계 '!', '@', '#', '*' 문자가 제거되었습니다.
 * "...!@bat#*..y.abcdefghijklm" → "...bat..y.abcdefghijklm"
 * <p>
 * 3단계 '...'와 '..' 가 '.'로 바뀌었습니다.
 * "...bat..y.abcdefghijklm" → ".bat.y.abcdefghijklm"
 * <p>
 * 4단계 아이디의 처음에 위치한 '.'가 제거되었습니다.
 * ".bat.y.abcdefghijklm" → "bat.y.abcdefghijklm"
 * <p>
 * 5단계 아이디가 빈 문자열이 아니므로 변화가 없습니다.
 * "bat.y.abcdefghijklm" → "bat.y.abcdefghijklm"
 * <p>
 * 6단계 아이디의 길이가 16자 이상이므로, 처음 15자를 제외한 나머지 문자들이 제거되었습니다.
 * "bat.y.abcdefghijklm" → "bat.y.abcdefghi"
 * <p>
 * 7단계 아이디의 길이가 2자 이하가 아니므로 변화가 없습니다.
 * "bat.y.abcdefghi" → "bat.y.abcdefghi"
 * <p>
 * 따라서 신규 유저가 입력한 new_id가 "...!@BaT#*..y.abcdefghijklm"일 때,
 * 네오의 프로그램이 추천하는 새로운 아이디는 "bat.y.abcdefghi" 입니다.
 * <p>
 * [문제]
 * 신규 유저가 입력한 아이디를 나타내는 new_id가 매개변수로 주어질 때,
 * "네오"가 설계한 7단계의 처리 과정을 거친 후의 추천 아이디를 return 하도록 solution 함수를 완성해 주세요.
 * <p>
 * [제한사항]
 * new_id는 길이 1 이상 1,000 이하인 문자열입니다.
 * new_id는 알파벳 대문자, 알파벳 소문자, 숫자, 특수문자로 구성되어 있습니다.
 * new_id에 나타날 수 있는 특수문자는 -_.~!@#$%^&*()=+[{]}:?,<>/ 로 한정됩니다.
 * <p>
 * 작성일 : 2023.06.30
 */
class P72410 {
    // length 3 ~ 15
    // lower, num, -, _, .
    // . no last, no first, no duplicate
    public String solution(String new_id) {
        // step 1 : 대문자 -> 소문자
        new_id = new_id.toLowerCase();
        System.out.println(new_id);

        // step 2 : 소문자, 숫자, 빼기, 밑줄, 마침표 이외 제외
        new_id = new_id.replaceAll("[^a-z0-9-_.]", "");
        System.out.println(new_id);

        // step 3 : 중복 마침표 제거
        new_id = removeDuplicatePoint(new_id);
        System.out.println(new_id);

        // step 4 : 앞 뒤 마침표 제거
        new_id = removeFirstLastPoint(new_id);
        System.out.println(new_id);

        // step 5 : 빈 문자열은 a 추가
        if (new_id == null || new_id.isBlank()) { new_id = new_id + "a"; }
        System.out.println(new_id);

        // step 6 : 16자 이상 제거
        if (new_id.length() >= 16) { new_id = new_id.substring(0, 15); }
        System.out.println(new_id);

        // step 7 : 3자 이하 추가
        new_id = addShortName(new_id);
        System.out.println(new_id);

        // step 8 : 앞 뒤 마침표 제거
        new_id = removeFirstLastPoint(new_id);
        System.out.println(new_id);

        return new_id;
    }

    private String removeDuplicatePoint(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (sb.length() > 0) {
                if (c == '.' && sb.charAt(sb.length() - 1) == '.') {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private String removeFirstLastPoint(String s) {
        if (s == null || s.isBlank()) {
            return s;
        }

        StringBuilder sb = new StringBuilder(s);

        if (sb.length() > 0 && sb.charAt(0) == '.') {
            sb.deleteCharAt(0);
        }

        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '.') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    private String addShortName(String s) {
        if (s == null || s.isBlank()) {
            return s;
        }

        StringBuilder sb = new StringBuilder(s);

        if (sb.length() <= 2) {
            while (sb.length() < 3) {
                sb.append(sb.charAt(sb.length() - 1));
            }
        }

        return sb.toString();
    }

    // 다른 사람의 풀이 1
    public String solution1(String new_id) {
        String answer = "";
        String temp = new_id.toLowerCase();

        temp = temp.replaceAll("[^-_.a-z0-9]", "");
        System.out.println(temp);
        temp = temp.replaceAll("[.]{2,}", ".");
        temp = temp.replaceAll("^[.]|[.]$", "");
        System.out.println(temp.length());
        if (temp.equals(""))
            temp += "a";
        if (temp.length() >= 16) {
            temp = temp.substring(0, 15);
            temp = temp.replaceAll("^[.]|[.]$", "");
        }
        if (temp.length() <= 2)
            while (temp.length() < 3)
                temp += temp.charAt(temp.length() - 1);

        answer = temp;
        return answer;
    }

    // 다른 사람의 풀이 2
    public String solution2(String new_id) {

        String s = new KAKAOID(new_id)
                .replaceToLowerCase()
                .filter()
                .toSingleDot()
                .noStartEndDot()
                .noBlank()
                .noGreaterThan16()
                .noLessThan2()
                .getResult();

        return s;
    }

    private static class KAKAOID {
        private String s;

        KAKAOID(String s) {
            this.s = s;
        }

        private KAKAOID replaceToLowerCase() {
            s = s.toLowerCase();
            return this;
        }

        private KAKAOID filter() {
            s = s.replaceAll("[^a-z0-9._-]", "");
            return this;
        }

        private KAKAOID toSingleDot() {
            s = s.replaceAll("[.]{2,}", ".");
            return this;
        }

        private KAKAOID noStartEndDot() {
            s = s.replaceAll("^[.]|[.]$", "");
            return this;
        }

        private KAKAOID noBlank() {
            s = s.isEmpty() ? "a" : s;
            return this;
        }

        private KAKAOID noGreaterThan16() {
            if (s.length() >= 16) {
                s = s.substring(0, 15);
            }
            s = s.replaceAll("[.]$", "");
            return this;
        }

        private KAKAOID noLessThan2() {
            StringBuilder sBuilder = new StringBuilder(s);
            while (sBuilder.length() <= 2) {
                sBuilder.append(sBuilder.charAt(sBuilder.length() - 1));
            }
            s = sBuilder.toString();
            return this;
        }

        private String getResult() {
            return s;
        }
    }

    public static void main(String[] args) {
        P72410 problem = new P72410();
        System.out.println(problem.solution("abcdefghijklmn.p"));
    }
}

package com.jake.argorithm.programmers.lv1;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * [ 성격 유형 검사하기 ]
 * <p>
 * 나만의 카카오 성격 유형 검사지를 만들려고 합니다.
 * 성격 유형 검사는 다음과 같은 4개 지표로 성격 유형을 구분합니다.
 * 성격은 각 지표에서 두 유형 중 하나로 결정됩니다.
 * <p>
 * 지표 번호	성격 유형
 * 1번 지표	라이언형(R), 튜브형(T)
 * 2번 지표	콘형(C), 프로도형(F)
 * 3번 지표	제이지형(J), 무지형(M)
 * 4번 지표	어피치형(A), 네오형(N)
 * 4개의 지표가 있으므로 성격 유형은 총 16(=2 x 2 x 2 x 2)가지가 나올 수 있습니다.
 * 예를 들어, "RFMN"이나 "TCMA"와 같은 성격 유형이 있습니다.
 * <p>
 * 검사지에는 총 n개의 질문이 있고, 각 질문에는 아래와 같은 7개의 선택지가 있습니다.
 * <p>
 * 매우 비동의
 * 비동의
 * 약간 비동의
 * 모르겠음
 * 약간 동의
 * 동의
 * 매우 동의
 * <p>
 * 각 질문은 1가지 지표로 성격 유형 점수를 판단합니다.
 * <p>
 * 예를 들어, 어떤 한 질문에서 4번 지표로 아래 표처럼 점수를 매길 수 있습니다.
 * <p>
 * 선택지	성격 유형 점수
 * 매우 비동의	네오형 3점
 * 비동의	네오형 2점
 * 약간 비동의	네오형 1점
 * 모르겠음	어떤 성격 유형도 점수를 얻지 않습니다
 * 약간 동의	어피치형 1점
 * 동의	어피치형 2점
 * 매우 동의	어피치형 3점
 * 이때 검사자가 질문에서 약간 동의 선택지를 선택할 경우 어피치형(A) 성격 유형 1점을 받게 됩니다.
 * 만약 검사자가 매우 비동의 선택지를 선택할 경우 네오형(N) 성격 유형 3점을 받게 됩니다.
 * <p>
 * 위 예시처럼 네오형이 비동의, 어피치형이 동의인 경우만 주어지지 않고,
 * 질문에 따라 네오형이 동의, 어피치형이 비동의인 경우도 주어질 수 있습니다.
 * 하지만 각 선택지는 고정적인 크기의 점수를 가지고 있습니다.
 * <p>
 * 매우 동의나 매우 비동의 선택지를 선택하면 3점을 얻습니다.
 * 동의나 비동의 선택지를 선택하면 2점을 얻습니다.
 * 약간 동의나 약간 비동의 선택지를 선택하면 1점을 얻습니다.
 * 모르겠음 선택지를 선택하면 점수를 얻지 않습니다.
 * 검사 결과는 모든 질문의 성격 유형 점수를 더하여
 * 각 지표에서 더 높은 점수를 받은 성격 유형이 검사자의 성격 유형이라고 판단합니다.
 * 단, 하나의 지표에서 각 성격 유형 점수가 같으면,
 * 두 성격 유형 중 사전 순으로 빠른 성격 유형을 검사자의 성격 유형이라고 판단합니다.
 * <p>
 * 질문마다 판단하는 지표를 담은 1차원 문자열 배열 survey와 검사자가
 * 각 질문마다 선택한 선택지를 담은 1차원 정수 배열 choices가 매개변수로 주어집니다.
 * 이때, 검사자의 성격 유형 검사 결과를 지표 번호 순서대로 return 하도록 solution 함수를 완성해주세요.
 * <p>
 * 제한사항
 * 1 ≤ survey의 길이 ( = n) ≤ 1,000
 * survey의 원소는 "RT", "TR", "FC", "CF", "MJ", "JM", "AN", "NA" 중 하나입니다.
 * survey[i]의 첫 번째 캐릭터는 i+1번 질문의 비동의 관련 선택지를 선택하면 받는 성격 유형을 의미합니다.
 * survey[i]의 두 번째 캐릭터는 i+1번 질문의 동의 관련 선택지를 선택하면 받는 성격 유형을 의미합니다.
 * choices의 길이 = survey의 길이
 * <p>
 * choices[i]는 검사자가 선택한 i+1번째 질문의 선택지를 의미합니다.
 * 1 ≤ choices의 원소 ≤ 7
 * <p>
 * choices	뜻
 * 1	매우 비동의
 * 2	비동의
 * 3	약간 비동의
 * 4	모르겠음
 * 5	약간 동의
 * 6	동의
 * 7	매우 동의
 * <p>
 * 작성일 : 2023.07.04
 */
class P118666 {
    public String solution(String[] survey, int[] choices) {
        StringBuilder answer = new StringBuilder();
        LinkedHashMap<String, Integer> mbti = new LinkedHashMap<>() {{
            put("R", 0);
            put("T", 0);
            put("C", 0);
            put("F", 0);
            put("J", 0);
            put("M", 0);
            put("A", 0);
            put("N", 0);
        }};

        for (int i = 0; i < survey.length; i++) {
            String agree = String.valueOf(survey[i].charAt(1));
            String disagree = String.valueOf(survey[i].charAt(0));
            if (choices[i] > 4) {
                mbti.replace(agree, mbti.get(agree) + choices[i] - 4);
            } else if (choices[i] < 4) {
                mbti.replace(disagree, mbti.get(disagree) + 4 - choices[i]);
            }
        }

        String[] compares = new String[]{"RT", "CF", "JM", "AN"};

        for (String compare : compares) {
            String first = String.valueOf(compare.charAt(0));
            String second = String.valueOf(compare.charAt(1));
            if (mbti.get(first) >= mbti.get(second)) {
                answer.append(first);
            } else {
                answer.append(second);
            }
        }

        return answer.toString();
    }

    // 다른 사람의 풀이 1
    public String solution1(String[] survey, int[] choices) {
        String answer = "";
        char[][] type = {{'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}};
        int[] score = {0, 3, 2, 1, 0, 1, 2, 3};
        HashMap<Character, Integer> point = new HashMap<>();

        // 점수 기록할 배열 초기화
        for (char[] t : type) {
            point.put(t[0], 0);
            point.put(t[1], 0);
        }

        // 점수 기록
        for (int idx = 0; idx < choices.length; idx++) {
            if (choices[idx] > 4) {
                point.put(survey[idx].charAt(1), point.get(survey[idx].charAt(1)) + score[choices[idx]]);
            } else {
                point.put(survey[idx].charAt(0), point.get(survey[idx].charAt(0)) + score[choices[idx]]);
            }
        }

        // 지표 별 점수 비교 후 유형 기입
        for (char[] t : type) {
            answer += (point.get(t[1]) <= point.get(t[0])) ? t[0] : t[1];
        }

        return answer;
    }

    // 다른 사람의 풀이 2
    public String solution2(String[] survey, int[] choices) {
        return new Survey(survey)
                .report(choices)
                .getReport();
    }

    public static class Report {
        private final String report;

        public Report(String report) {
            this.report = report;
        }

        public String getReport() {
            return report;
        }
    }

    public static class Survey {
        private final List<CharacterType> survey;

        public Survey(String[] survey) {
            this(Arrays.stream(survey)
                    .map(CharacterType::findType)
                    .collect(Collectors.toList()));
        }

        public Survey(List<CharacterType> survey) {
            this.survey = survey;
        }

        public Report report(int[] scores) {
            return IntStream.range(0, survey.size())
                    .mapToObj(i -> new Choice(survey.get(i), scores[i]))
                    .collect(Collectors.collectingAndThen(Collectors.toList(), this::report));
        }

        public Report report(List<Choice> choices) {
            Map<CharacterType, Integer> report = CharacterType.getAllTypes().stream()
                    .collect(Collectors.toMap(Function.identity(), __ -> 0));

            choices.forEach(choice -> report.put(choice.getCharacterType(),
                    report.get(choice.getCharacterType()) + choice.score));

            return report.entrySet().stream()
                    .sorted(Comparator.comparing(entry -> entry.getKey().indicator))
                    .map(entry -> {
                        if (entry.getValue() > 0) {
                            return entry.getKey().getUpperElement();
                        }

                        return entry.getKey().getLowerElement();
                    })
                    .map(CharacterTypeElement::name)
                    .collect(Collectors.collectingAndThen(Collectors.joining(), Report::new));
        }
    }

    public static class Choice {
        private final CharacterType characterType;
        private final Integer score;

        public Choice(String characterType, Integer score) {
            this(CharacterType.findType(characterType), score);
        }

        public Choice(CharacterType characterType, Integer score) {
            if (characterType.isReversed()) {
                this.characterType = characterType.reverse();
                this.score = Math.abs(7 - score) + 1 - 4;
            } else {
                this.characterType = characterType;
                this.score = score - 4;
            }
        }

        public CharacterType getCharacterType() {
            return characterType;
        }

        public Integer getScore() {
            return score;
        }
    }

    public enum CharacterType {
        RT(1, false), TR(1, true),
        CF(2, false), FC(2, true),
        JM(3, false), MJ(3, true),
        AN(4, false), NA(4, true);

        private final Integer indicator;
        private final boolean isReversed;

        CharacterType(Integer indicator, boolean isReversed) {
            this.indicator = indicator;
            this.isReversed = isReversed;
        }

        public static CharacterTypeElement findDefaultElementByIndicator(int indicator) {
            return Arrays.stream(values())
                    .filter(type -> type.indicator.equals(indicator))
                    .findFirst()
                    .map(CharacterType::getLowerElement)
                    .orElseThrow();
        }

        public static CharacterType findType(String type) {
            return Arrays.stream(values())
                    .filter(characterType -> characterType.name().equals(type))
                    .findAny()
                    .orElseThrow();
        }

        public static List<CharacterType> getAllTypes() {
            return List.of(RT, CF, JM, AN);
        }

        public CharacterType reverse() {
            return findType(new StringBuilder(this.name()).reverse().toString());
        }

        public boolean isReversed() {
            return isReversed;
        }

        public CharacterTypeElement getLowerElement() {
            return CharacterTypeElement.findType(String.valueOf(this.name().charAt(0)));
        }

        public CharacterTypeElement getUpperElement() {
            return CharacterTypeElement.findType(String.valueOf(this.name().charAt(1)));
        }
    }

    public enum CharacterTypeElement {
        R, T, C, F, J, M, A, N;

        public static CharacterTypeElement findType(String element) {
            return Arrays.stream(values())
                    .filter(characterType -> characterType.name().equals(element))
                    .findAny()
                    .orElseThrow();
        }
    }

    public static void main(String[] args) {
        P118666 problem = new P118666();
        System.out.println(problem.solution(new String[]{"AN", "CF", "MJ", "RT", "NA"}, new int[]{5, 3, 2, 7, 5}));
    }
}

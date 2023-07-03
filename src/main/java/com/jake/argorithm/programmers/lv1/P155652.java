package com.jake.argorithm.programmers.lv1;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * [ 둘만의 암호 ]
 *
 * 두 문자열 s와 skip, 그리고 자연수 index가 주어질 때, 다음 규칙에 따라 문자열을 만들려 합니다.
 * 암호의 규칙은 다음과 같습니다.
 *
 * 문자열 s의 각 알파벳을 index만큼 뒤의 알파벳으로 바꿔줍니다.
 * index만큼의 뒤의 알파벳이 z를 넘어갈 경우 다시 a로 돌아갑니다.
 * skip에 있는 알파벳은 제외하고 건너뜁니다.
 * 예를 들어 s = "aukks", skip = "wbqd", index = 5일 때,
 * a에서 5만큼 뒤에 있는 알파벳은 f지만 [b, c, d, e, f]에서 'b'와 'd'는 skip에 포함되므로 세지 않습니다.
 * 따라서 'b', 'd'를 제외하고 'a'에서 5만큼 뒤에 있는 알파벳은 [c, e, f, g, h] 순서에 의해 'h'가 됩니다.
 * 나머지 "ukks" 또한 위 규칙대로 바꾸면 "appy"가 되며 결과는 "happy"가 됩니다.
 *
 * 두 문자열 s와 skip, 그리고 자연수 index가 매개변수로 주어질 때
 * 위 규칙대로 s를 변환한 결과를 return하도록 solution 함수를 완성해주세요.
 *
 * 제한사항
 * 5 ≤ s의 길이 ≤ 50
 * 1 ≤ skip의 길이 ≤ 10
 * s와 skip은 알파벳 소문자로만 이루어져 있습니다.
 * skip에 포함되는 알파벳은 s에 포함되지 않습니다.
 * 1 ≤ index ≤ 20
 *
 * 작성일 : 2023.06.30
 */
public class P155652 {
    public String solution(String s, String skip, int index) {
        StringBuilder answer = new StringBuilder();

        index = index % 26;
        int originIndex = index;

        for (char c : s.toCharArray()) {
            index = originIndex;
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i <= index; i++) {
                char next = (char) ((c - 'a' + i) % 26 + 'a');
                temp.append(next);
                for(char sk : skip.toCharArray()) {
                    if (next == sk) {
                        index = index + 1;
                    }
                }
            }
            answer.append(temp.substring(temp.toString().length() - 1));
        }

        return answer.toString();
    }

    // 다른 사람의 풀이 1
    public String solution1(String s, String skip, int index) {
        StringBuilder answer = new StringBuilder();

        for (char letter : s.toCharArray()) {
            char temp = letter;
            int idx = 0;
            while (idx < index) {
                temp = temp == 'z' ? 'a' : (char) (temp + 1);
                if (!skip.contains(String.valueOf(temp))) {
                    idx += 1;
                }
            }
            answer.append(temp);
        }

        return answer.toString();
    }

    // 다른 사람의 풀이 2
    public String solution2(String s, String skip, int index) {
        return new StringGenerator(index, skip).generate(s);
    }

    private static class StringGenerator {
        private final Integer offsetIndex;
        private final Set<Character> skipCharacters;

        public StringGenerator(Integer offsetIndex, String skipCharacters) {
            this(offsetIndex, parseSkipCharacters(skipCharacters));
        }

        public StringGenerator(Integer offsetIndex, Set<Character> skipCharacters) {
            this.offsetIndex = offsetIndex;
            this.skipCharacters = skipCharacters;
        }

        public String generate(String base) {
            return base.chars()
                    .mapToObj(character -> (char) character)
                    .map(this::applyOffset)
                    .map(String::valueOf)
                    .collect(Collectors.joining());
        }

        private Character applyOffset(Character character) {
            int count = 0;
            Character applied = character;

            while (count < offsetIndex) {
                applied++;

                if (applied > 'z') {
                    applied = 'a';
                }

                if (skipCharacters.contains(applied)) {
                    continue;
                }

                count++;
            }

            return applied;
        }

        private static Set<Character> parseSkipCharacters(String skipCharacters) {
            return skipCharacters.chars()
                    .mapToObj(character -> (char) character)
                    .collect(Collectors.toSet());
        }
    }

    public static void main(String[] args) {
        P155652 problem = new P155652();
        System.out.println(problem.solution("aukks", "wbqd", 5));
    }
}

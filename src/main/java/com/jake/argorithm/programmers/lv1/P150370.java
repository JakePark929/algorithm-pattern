package com.jake.argorithm.programmers.lv1;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * [ 개인정보 수집 유효기간 ]
 * <p>
 * 고객의 약관 동의를 얻어서 수집된 1~n번으로 분류되는 개인정보 n개가 있습니다.
 * 약관 종류는 여러 가지 있으며 각 약관마다 개인정보 보관 유효기간이 정해져 있습니다.
 * 당신은 각 개인정보가 어떤 약관으로 수집됐는지 알고 있습니다.
 * 수집된 개인정보는 유효기간 전까지만 보관 가능하며, 유효기간이 지났다면 반드시 파기해야 합니다.
 * <p>
 * 예를 들어, A라는 약관의 유효기간이 12 달이고, 2021년 1월 5일에 수집된 개인정보가 A약관으로 수집되었다면
 * 해당 개인정보는 2022년 1월 4일까지 보관 가능하며 2022년 1월 5일부터 파기해야 할 개인정보입니다.
 * 당신은 오늘 날짜로 파기해야 할 개인정보 번호들을 구하려 합니다.
 * <p>
 * 모든 달은 28일까지 있다고 가정합니다.
 * <p>
 * 다음은 오늘 날짜가 2022.05.19일 때의 예시입니다.
 * <p>
 * 약관 종류	유효기간
 * A	6 달
 * B	12 달
 * C	3 달
 * <p>
 * 번호	개인정보 수집 일자	약관 종류
 * 1	2021.05.02	A
 * 2	2021.07.01	B
 * 3	2022.02.19	C
 * 4	2022.02.20	C
 * <p>
 * 첫 번째 개인정보는 A약관에 의해 2021년 11월 1일까지 보관 가능하며, 유효기간이 지났으므로 파기해야 할 개인정보입니다.
 * 두 번째 개인정보는 B약관에 의해 2022년 6월 28일까지 보관 가능하며, 유효기간이 지나지 않았으므로 아직 보관 가능합니다.
 * 세 번째 개인정보는 C약관에 의해 2022년 5월 18일까지 보관 가능하며, 유효기간이 지났으므로 파기해야 할 개인정보입니다.
 * 네 번째 개인정보는 C약관에 의해 2022년 5월 19일까지 보관 가능하며, 유효기간이 지나지 않았으므로 아직 보관 가능합니다.
 * <p>
 * 따라서 파기해야 할 개인정보 번호는 [1, 3]입니다.
 * <p>
 * 오늘 날짜를 의미하는 문자열 today,
 * 약관의 유효기간을 담은 1차원 문자열 배열 terms와
 * 수집된 개인정보의 정보를 담은 1차원 문자열 배열 privacies가 매개변수로 주어집니다. 이
 * 때 파기해야 할 개인정보의 번호를 오름차순으로 1차원 정수 배열에 담아 return 하도록 solution 함수를 완성해 주세요.
 * <p>
 * 제한사항
 * today는 "YYYY.MM.DD" 형태로 오늘 날짜를 나타냅니다.
 * 1 ≤ terms의 길이 ≤ 20
 * terms의 원소는 "약관 종류 유효기간" 형태의 약관 종류와 유효기간을 공백 하나로 구분한 문자열입니다.
 * 약관 종류는 A~Z중 알파벳 대문자 하나이며, terms 배열에서 약관 종류는 중복되지 않습니다.
 * 유효기간은 개인정보를 보관할 수 있는 달 수를 나타내는 정수이며, 1 이상 100 이하입니다.
 * 1 ≤ privacies의 길이 ≤ 100
 * privacies[i]는 i+1번 개인정보의 수집 일자와 약관 종류를 나타냅니다.
 * privacies의 원소는 "날짜 약관 종류" 형태의 날짜와 약관 종류를 공백 하나로 구분한 문자열입니다.
 * 날짜는 "YYYY.MM.DD" 형태의 개인정보가 수집된 날짜를 나타내며, today 이전의 날짜만 주어집니다.
 * privacies의 약관 종류는 항상 terms에 나타난 약관 종류만 주어집니다.
 * today와 privacies에 등장하는 날짜의 YYYY는 연도, MM은 월, DD는 일을 나타내며 점(.) 하나로 구분되어 있습니다.
 * 2000 ≤ YYYY ≤ 2022
 * 1 ≤ MM ≤ 12
 * MM이 한 자릿수인 경우 앞에 0이 붙습니다.
 * 1 ≤ DD ≤ 28
 * DD가 한 자릿수인 경우 앞에 0이 붙습니다.
 * 파기해야 할 개인정보가 하나 이상 존재하는 입력만 주어집니다.
 * <p>
 * 작성일 : 2023.07.04
 */
public class P150370 {
    public LinkedList<Integer> solution(String today, String[] terms, String[] privacies) {
        int todayY = Integer.parseInt(today.split("\\.")[0]);
        int todayM = Integer.parseInt(today.split("\\.")[1]);
        int todayD = Integer.parseInt(today.split("\\.")[2]);

        int[] day = {todayY, todayM, todayD};

        HashMap<String, Integer> term = new HashMap<>();

        for (String t : terms) {
            term.put(t.split(" ")[0], Integer.valueOf(t.split(" ")[1]));
        }

        LinkedList<Integer> list = new LinkedList<>();
        int count = 0;
        for (String privacy : privacies) {
            count++;
            String privateDay = privacy.split(" ")[0];
            String privateTerm = privacy.split(" ")[1];
            int[] expiredDay = getExpiredDay(privateDay, privateTerm, term);

            if (day[0] > expiredDay[0]) {
                list.add(count);
            } else if (day[0] == expiredDay[0] && day[1] > expiredDay[1]) {
                list.add(count);
            } else if (day[0] == expiredDay[0] && day[1] == expiredDay[1] && day[2] > expiredDay[2]) {
                list.add(count);
            }
        }

        return list;
    }

    public int[] getExpiredDay(String privateDay, String privateTerm, HashMap<String, Integer> term) {
        int dayY = Integer.parseInt(privateDay.split("\\.")[0]);
        int dayM = Integer.parseInt(privateDay.split("\\.")[1]);
        int dayD = Integer.parseInt(privateDay.split("\\.")[2]);

        int[] days = {dayY, dayM, dayD};

        return getDate(days, term.get(privateTerm));
    }

    public int[] getDate(int[] days, int add) {
        int[] year = new int[100];
        int[] month = new int[12];
        int[] day = new int[28];

        int firstY = 1997;

        Arrays.setAll(year, i -> i + firstY);
        Arrays.setAll(month, i -> i + 1);
        Arrays.setAll(day, i -> i + 1);

        int dayAdd = -1;

        int offsetD = (days[2] - 1 + dayAdd) / day.length;
        int calD = (days[2] - 1 + dayAdd) % day.length;
        if (calD < 0) {
            offsetD = offsetD - 1;
            calD = calD + day.length * Math.abs(offsetD);
        }

        int offsetM = (days[1] - 1 + add + offsetD) / month.length;
        int calM = (days[1] - 1 + add + offsetD) % month.length;
        if (calM < 0) {
            offsetM = offsetM - 1;
            calM = calM + month.length * (Math.abs(offsetM) + 1);
        }

        int offsetY = ((days[0] + offsetM) - firstY) / year.length;
        int calY = ((days[0] + offsetM) - firstY) % year.length;
        if (calY < 0) {
            offsetY = offsetY - 1;
            calY = calY + year.length * (Math.abs(offsetY) + 1);
        }

        return new int[]{year[calY % year.length], month[calM % month.length], day[calD % day.length]};
    }

    // 다른 사람의 풀이 1
    public int[] solution1(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> termMap = new HashMap<>();
        int date = getDate(today);

        for (String s : terms) {
            String[] term = s.split(" ");

            termMap.put(term[0], Integer.parseInt(term[1]));
        }
        for (int i = 0; i < privacies.length; i++) {
            String[] privacy = privacies[i].split(" ");

            if (getDate(privacy[0]) + (termMap.get(privacy[1]) * 28) <= date) {
                answer.add(i + 1);
            }
        }
        return answer.stream().mapToInt(integer -> integer).toArray();
    }

    private int getDate(String today) {
        String[] date = today.split("\\.");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);
        return (year * 12 * 28) + (month * 28) + day;
    }

    // 다른 사람의 풀이 2
    public int[] solution2(String today, String[] terms, String[] privaciesArray) {
        Policies policies = Arrays.stream(terms)
                .map(Policy::parse)
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(Policy::getType, Function.identity()), Policies::new));

        Privacies privacies = IntStream.range(0, privaciesArray.length)
                .mapToObj(i -> {
                    String[] splitted = privaciesArray[i].split(" ");
                    return new Privacy(i + 1,
                            LocalDate.parse(String.join("-", splitted[0].split("\\."))),
                            policies.findByType(splitted[1]));
                })
                .collect(Collectors.collectingAndThen(Collectors.toList(), Privacies::new));

        return privacies.getExpiredFrom(LocalDate.parse(String.join("-", today.split("\\.")))).stream()
                .mapToInt(Privacy::getId)
                .toArray();
    }

    private static class Privacies {
        private final List<Privacy> privacies;

        public Privacies(List<Privacy> privacies) {
            this.privacies = privacies;
        }

        public List<Privacy> getExpiredFrom(LocalDate date) {
            return this.privacies.stream()
                    .filter(privacy -> privacy.isExpiredFrom(date))
                    .collect(Collectors.toList());
        }
    }

    private static class Privacy {
        private final Integer id;
        private final LocalDate collectedAt;
        private final Policy policy;

        public Privacy(Integer id, LocalDate collectedAt, Policy policy) {
            this.id = id;
            this.collectedAt = collectedAt;
            this.policy = policy;
        }

        public boolean isExpiredFrom(LocalDate date) {
            return this.policy.isExpired(collectedAt, date);
        }

        public Integer getId() {
            return id;
        }
    }

    private static class Policies {
        private final Map<String, Policy> policies;

        public Policies(Map<String, Policy> policies) {
            this.policies = policies;
        }

        public Policy findByType(String type) {
            return policies.get(type);
        }
    }

    private static class Policy {
        private final String type;
        private final Period expiredAfter;

        public Policy(String type, Period expiredAfter) {
            this.type = type;
            this.expiredAfter = expiredAfter.minusDays(1L);
        }

        public static Policy parse(String typeWithExpired) {
            String[] splitted = typeWithExpired.split(" ");

            return new Policy(splitted[0], Period.ofMonths(Integer.parseInt(splitted[1])));
        }

        public boolean isExpired(LocalDate collectedAt, LocalDate comparing) {
            return collectedAt.plus(expiredAfter).isBefore(comparing);
        }

        public String getType() {
            return type;
        }
    }

    public static void main(String[] args) {
        P150370 problem = new P150370();
        System.out.println(problem.solution(
                "2022.05.19",
                new String[]{"A 6", "B 12", "C 3"},
                new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"}
        ));
//        System.out.println(problem.solution(
//                "2020.01.01",
//                new String[]{"Z 3", "D 5"},
//                new String[]{"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"}
//        ));
    }
}

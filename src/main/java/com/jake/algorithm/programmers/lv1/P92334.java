package com.jake.algorithm.programmers.lv1;

import java.util.*;
import java.util.stream.Collectors;

/**
 * [ 신고 결과 받기 ]
 * <p>
 * 신입사원 무지는 게시판 불량 이용자를 신고하고 처리 결과를 메일로 발송하는 시스템을 개발하려 합니다.
 * 무지가 개발하려는 시스템은 다음과 같습니다.
 * <p>
 * 각 유저는 한 번에 한 명의 유저를 신고할 수 있습니다.
 * 신고 횟수에 제한은 없습니다. 서로 다른 유저를 계속해서 신고할 수 있습니다.
 * 한 유저를 여러 번 신고할 수도 있지만, 동일한 유저에 대한 신고 횟수는 1회로 처리됩니다.
 * k번 이상 신고된 유저는 게시판 이용이 정지되며, 해당 유저를 신고한 모든 유저에게 정지 사실을 메일로 발송합니다.
 * 유저가 신고한 모든 내용을 취합하여 마지막에 한꺼번에 게시판 이용 정지를 시키면서 정지 메일을 발송합니다.
 * 다음은 전체 유저 목록이 ["muzi", "frodo", "apeach", "neo"]이고, k = 2(즉, 2번 이상 신고당하면 이용 정지)인 경우의 예시입니다.
 * <p>
 * 유저 ID	유저가 신고한 ID	설명
 * "muzi"	"frodo"	"muzi"가 "frodo"를 신고했습니다.
 * "apeach"	"frodo"	"apeach"가 "frodo"를 신고했습니다.
 * "frodo"	"neo"	"frodo"가 "neo"를 신고했습니다.
 * "muzi"	"neo"	"muzi"가 "neo"를 신고했습니다.
 * "apeach"	"muzi"	"apeach"가 "muzi"를 신고했습니다.
 * <p>
 * 각 유저별로 신고당한 횟수는 다음과 같습니다.
 * <p>
 * 유저 ID	신고당한 횟수
 * "muzi"	1
 * "frodo"	2
 * "apeach"	0
 * "neo"	2
 * <p>
 * 위 예시에서는 2번 이상 신고당한 "frodo"와 "neo"의 게시판 이용이 정지됩니다.
 * 이때, 각 유저별로 신고한 아이디와 정지된 아이디를 정리하면 다음과 같습니다.
 * <p>
 * 유저 ID	유저가 신고한 ID	정지된 ID
 * "muzi"	["frodo", "neo"]	["frodo", "neo"]
 * "frodo"	["neo"]	["neo"]
 * "apeach"	["muzi", "frodo"]	["frodo"]
 * "neo"	없음	없음
 * <p>
 * 따라서 "muzi"는 처리 결과 메일을 2회, "frodo"와 "apeach"는 각각 처리 결과 메일을 1회 받게 됩니다.
 * <p>
 * 이용자의 ID가 담긴 문자열 배열 id_list,
 * 각 이용자가 신고한 이용자의 ID 정보가 담긴 문자열 배열 report,
 * 정지 기준이 되는 신고 횟수 k가 매개변수로 주어질 때,
 * 각 유저별로 처리 결과 메일을 받은 횟수를 배열에 담아 return 하도록 solution 함수를 완성해주세요.
 * <p>
 * 제한사항
 * 2 ≤ id_list의 길이 ≤ 1,000
 * 1 ≤ id_list의 원소 길이 ≤ 10
 * id_list의 원소는 이용자의 id를 나타내는 문자열이며 알파벳 소문자로만 이루어져 있습니다.
 * id_list에는 같은 아이디가 중복해서 들어있지 않습니다.
 * 1 ≤ report의 길이 ≤ 200,000
 * 3 ≤ report의 원소 길이 ≤ 21
 * report의 원소는 "이용자id 신고한id"형태의 문자열입니다.
 * 예를 들어 "muzi frodo"의 경우 "muzi"가 "frodo"를 신고했다는 의미입니다.
 * id는 알파벳 소문자로만 이루어져 있습니다.
 * 이용자id와 신고한id는 공백(스페이스)하나로 구분되어 있습니다.
 * 자기 자신을 신고하는 경우는 없습니다.
 * 1 ≤ k ≤ 200, k는 자연수입니다.
 * return 하는 배열은 id_list에 담긴 id 순서대로 각 유저가 받은 결과 메일 수를 담으면 됩니다.
 * <p>
 * 작성일 : 2023.07.06
 */
class P92334 {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        HashMap<String, Integer> reports = new HashMap<>();
        ArrayList<String> criminals = new ArrayList<>();
        HashSet<String> newReport = new HashSet<>() {{
            this.addAll(Arrays.asList(report));
        }};
        LinkedHashMap<String, ArrayList<String>> mails = new LinkedHashMap<>() {{
            for (String id : id_list) {
                put(id, null);
            }
        }};

        for (String s : newReport) {
            String reporter = s.split(" ")[0];
            String reported = s.split(" ")[1];
            reports.merge(reported, 1, Integer::sum);
            if (mails.get(reporter) == null) {
                mails.put(reporter, new ArrayList<>() {{
                    add(reported);
                }});
            } else {
                mails.get(reporter).add(reported);
            }
        }

        for (Map.Entry<String, Integer> entry : reports.entrySet()) {
            if (entry.getValue() >= k) {
                criminals.add(entry.getKey());
            }
        }

        for (int i = 0; i < id_list.length; i++) {
            int count = 0;
            if (mails.get(id_list[i]) != null) {
                for (String criminal : criminals) {
                    if (mails.get(id_list[i]).contains(criminal)) {
                        count++;
                    }
                }
            }
            answer[i] = count;
        }

        return answer;
    }

    // 다른 사람의 풀이 1
    public int[] solution1(String[] id_list, String[] report, int k) {
        List<String> list = Arrays.stream(report).distinct().collect(Collectors.toList());
        HashMap<String, Integer> count = new HashMap<>();
        for (String s : list) {
            String target = s.split(" ")[1];
            count.put(target, count.getOrDefault(target, 0) + 1);
        }

        return Arrays.stream(id_list).map(_user -> {
            final String user = _user;
            List<String> reportList = list.stream().filter(s -> s.startsWith(user + " ")).collect(Collectors.toList());
            return reportList.stream().filter(s -> count.getOrDefault(s.split(" ")[1], 0) >= k).count();
        }).mapToInt(Long::intValue).toArray();
    }

    public static void main(String[] args) {
        P92334 problem = new P92334();
        System.out.println(Arrays.toString(problem.solution(
                new String[]{"con", "ryan"},
                new String[]{"ryan con", "ryan con", "ryan con", "ryan con"},
                3
        )));
    }
}

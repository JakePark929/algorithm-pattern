package com.jake.argorithm.programmers.lv3;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * [ 불량 사용자 ]
 *
 * 개발팀 내에서 이벤트 개발을 담당하고 있는 "무지"는 최근 진행된 카카오이모티콘 이벤트에
 * 비정상적인 방법으로 당첨을 시도한 응모자들을 발견하였습니다.
 * 이런 응모자들을 따로 모아 불량 사용자라는 이름으로 목록을 만들어서 당첨 처리 시 제외하도록
 * 이벤트 당첨자 담당자인 "프로도" 에게 전달하려고 합니다.
 * 이 때 개인정보 보호을 위해 사용자 아이디 중 일부 문자를 '*' 문자로 가려서 전달했습니다.
 * 가리고자 하는 문자 하나에 '*' 문자 하나를 사용하였고 아이디 당 최소 하나 이상의 '*' 문자를 사용하였습니다.
 * "무지"와 "프로도"는 불량 사용자 목록에 매핑된 응모자 아이디를 제재 아이디 라고 부르기로 하였습니다.
 *
 * 예를 들어, 이벤트에 응모한 전체 사용자 아이디 목록이 다음과 같다면
 *
 * 응모자 아이디
 * frodo
 * fradi
 * crodo
 * abc123
 * frodoc
 * 다음과 같이 불량 사용자 아이디 목록이 전달된 경우,
 *
 * 불량 사용자
 * fr*d*
 * abc1**
 * 불량 사용자에 매핑되어 당첨에서 제외되어야 야 할 제재 아이디 목록은 다음과 같이 두 가지 경우가 있을 수 있습니다.
 *
 * 제재 아이디
 * frodo
 * abc123
 * 제재 아이디
 * fradi
 * abc123
 *
 * 이벤트 응모자 아이디 목록이 담긴 배열 user_id와 불량 사용자 아이디 목록이 담긴 배열 banned_id가 매개변수로 주어질 때,
 * 당첨에서 제외되어야 할 제재 아이디 목록은 몇가지 경우의 수가 가능한 지 return 하도록 solution 함수를 완성해주세요.
 *
 * 작성일 : 24.10.01
 */
public class P64064 {
    Set<Set<String>> resultSet; // 결과를 저장할 중복 방지용 Set

    public int solution(String[] user_id, String[] banned_id) {
        resultSet = new HashSet<>(); // 중복을 방지하기 위한 Set
        boolean[] visited = new boolean[user_id.length]; // 각 user_id가 사용되었는지 체크

        // 재귀 함수로 가능한 모든 조합 찾기
        findMatching(0, user_id, banned_id, visited, new HashSet<>());

        return resultSet.size(); // 가능한 제재 아이디 목록의 경우의 수 반환
    }

    // 재귀적으로 가능한 매칭을 찾는 함수
    private void findMatching(int idx, String[] user_id, String[] banned_id, boolean[] visited, Set<String> currentSet) {
        if (idx == banned_id.length) { // 모든 banned_id에 대해 처리했다면
            resultSet.add(new HashSet<>(currentSet)); // 결과 저장

            return;
        }

        // banned_id[idx]에 해당하는 매칭 가능한 user_id를 찾음
        for (int i = 0; i < user_id.length; i++) {
            if (!visited[i] && isMatching(user_id[i], banned_id[idx])) { // 아직 사용되지 않았고 패턴이 맞는 경우
                visited[i] = true; // 사용 처리
                currentSet.add(user_id[i]); // 현재 세트에 추가

                findMatching(idx + 1, user_id, banned_id, visited, currentSet); // 다음 banned_id로 진행

                // 백트래킹 (복원)
                visited[i] = false;
                currentSet.remove(user_id[i]);
            }
        }
    }

    // user_id가 banned_id 패턴과 매칭되는지 확인하는 함수
    private boolean isMatching(String user, String banned) {
        if (user.length() != banned.length()) {
            return false; // 길이가 다르면 매칭 불가
        }

        // 각 문자가 동일한지 확인
        for (int i = 0; i < user.length(); i++) {
            if (banned.charAt(i) != '*' && user.charAt(i) != banned.charAt(i)) {
                return false; // 패턴이 일치하지 않으면 false
            }
        }

        return true; // 모두 일치하면 true
    }

    public static int solution2(String[] user_id, String[] banned_id) {
        Set<Set<String>> resultSet = new HashSet<>(); // 가능한 경우의 수를 저장할 Set
        Stack<String> stack = new Stack<>(); // 현재 선택된 사용자 아이디
        boolean[] visited = new boolean[user_id.length]; // 각 사용자가 방문되었는지 확인

        dfs(user_id, banned_id, 0, visited, stack, resultSet);

        return resultSet.size(); // 가능한 경우의 수 반환
    }

    public static void dfs(String[] user_id, String[] banned_id, int banIdx, boolean[] visited,
                           Stack<String> stack, Set<Set<String>> resultSet) {

        // 모든 불량 사용자 아이디에 대한 매핑이 완료된 경우
        if (banIdx == banned_id.length) {
            resultSet.add(new HashSet<>(stack)); // 스택의 현재 선택을 저장
            return;
        }

        // 현재 banIdx에 해당하는 불량 사용자 아이디에 매칭되는 사용자 아이디 찾기
        for (int i = 0; i < user_id.length; i++) {
            if (!visited[i] && isMatch(user_id[i], banned_id[banIdx])) {
                // 사용자 선택
                visited[i] = true;
                stack.push(user_id[i]);

                // 다음 불량 사용자로 넘어가기
                dfs(user_id, banned_id, banIdx + 1, visited, stack, resultSet);

                // 복원 (다음 탐색을 위해 스택과 방문 배열 원상복구)
                visited[i] = false;
                stack.pop();
            }
        }
    }

    // 불량 사용자 아이디와 응모자 아이디가 일치하는지 체크하는 함수
    public static boolean isMatch(String userId, String bannedId) {
        if (userId.length() != bannedId.length()) {
            return false;
        }
        for (int i = 0; i < userId.length(); i++) {
            if (bannedId.charAt(i) != '*' && userId.charAt(i) != bannedId.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // 다른 사람의 풀이 1
    Set<Integer> set;

    public int solution1(String[] user_id, String[] banned_id) {
        set = new HashSet<>();

        go(0, user_id, banned_id, 0);

        return set.size();
    }

    public void go(int index, String[] user_id, String[] banned_id, int bit) {
        if(index == banned_id.length) {
            set.add(bit);
            return;
        }

        String reg = banned_id[index].replace("*", "[\\w\\d]");
        for (int i = 0; i<user_id.length; ++i) {
            if((((bit>>i) & 1) == 1) || !user_id[i].matches(reg)) continue;
            go(index + 1, user_id, banned_id, (bit | 1<<i));
        }

    }

    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "abc1**"};

        final int solution = new P64064().solution(user_id, banned_id);

        System.out.println(solution);
    }
}

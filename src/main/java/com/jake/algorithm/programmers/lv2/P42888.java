package com.jake.algorithm.programmers.lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [ 오픈채팅방 ]
 *
 * 카카오톡 오픈채팅방에서는 친구가 아닌 사람들과 대화를 할 수 있는데,
 * 본래 닉네임이 아닌 가상의 닉네임을 사용하여 채팅방에 들어갈 수 있다.
 * 신입사원인 김크루는 카카오톡 오픈 채팅방을 개설한 사람을 위해,
 * 다양한 사람들이 들어오고, 나가는 것을 지켜볼 수 있는 관리자창을 만들기로 했다.
 * 채팅방에 누군가 들어오면 다음 메시지가 출력된다.
 *
 * "[닉네임]님이 들어왔습니다."
 *
 * 채팅방에서 누군가 나가면 다음 메시지가 출력된다.
 *
 * "[닉네임]님이 나갔습니다."
 *
 * 채팅방에서 닉네임을 변경하는 방법은 다음과 같이 두 가지이다.
 * 채팅방을 나간 후, 새로운 닉네임으로 다시 들어간다.
 * 채팅방에서 닉네임을 변경한다.
 * 닉네임을 변경할 때는 기존에 채팅방에 출력되어 있던 메시지의 닉네임도 전부 변경된다.
 *
 * 예를 들어, 채팅방에 "Muzi"와 "Prodo"라는 닉네임을 사용하는 사람이 순서대로 들어오면 채팅방에는 다음과 같이 메시지가 출력된다.
 *
 * "Muzi님이 들어왔습니다."
 * "Prodo님이 들어왔습니다."
 *
 * 채팅방에 있던 사람이 나가면 채팅방에는 다음과 같이 메시지가 남는다.
 *
 * "Muzi님이 들어왔습니다."
 * "Prodo님이 들어왔습니다."
 * "Muzi님이 나갔습니다."
 *
 * Muzi가 나간후 다시 들어올 때, Prodo 라는 닉네임으로 들어올 경우 기존에 채팅방에 남아있던 Muzi도 Prodo로 다음과 같이 변경된다.
 *
 * "Prodo님이 들어왔습니다."
 * "Prodo님이 들어왔습니다."
 * "Prodo님이 나갔습니다."
 * "Prodo님이 들어왔습니다."
 *
 * 채팅방은 중복 닉네임을 허용하기 때문에, 현재 채팅방에는 Prodo라는 닉네임을 사용하는 사람이 두 명이 있다.
 * 이제, 채팅방에 두 번째로 들어왔던 Prodo가 Ryan으로 닉네임을 변경하면 채팅방 메시지는 다음과 같이 변경된다.
 *
 * "Prodo님이 들어왔습니다."
 * "Ryan님이 들어왔습니다."
 * "Prodo님이 나갔습니다."
 * "Prodo님이 들어왔습니다."
 *
 * 채팅방에 들어오고 나가거나, 닉네임을 변경한 기록이 담긴 문자열 배열 record가 매개변수로 주어질 때,
 * 모든 기록이 처리된 후, 최종적으로 방을 개설한 사람이 보게 되는 메시지를 문자열 배열 형태로 return 하도록 solution 함수를 완성하라.
 *
 * 작성일 : 24.09.29
 */
public class P42888 {
    public String[] solution(String[] record) {
        // 유저 아이디를 키로, 닉네임을 값으로 저장할 맵
        Map<String, String> userMap = new HashMap<>();
        // 결과 메시지 리스트
        List<String> result = new ArrayList<>();

        // 각 기록을 처리
        for (String rec : record) {
            String[] parts = rec.split(" ");
            String command = parts[0];  // 명령어 (Enter, Leave, Change)
            String userId = parts[1];   // 유저 아이디

            switch (command) {
                case "Enter": {
                    // 닉네임 갱신 및 메시지 저장
                    String nickname = parts[2];
                    userMap.put(userId, nickname);
                    result.add(userId + "님이 들어왔습니다.");
                    break;
                }
                case "Leave":
                    // 메시지 저장
                    result.add(userId + "님이 나갔습니다.");
                    break;
                case "Change": {
                    // 닉네임 변경
                    String nickname = parts[2];
                    userMap.put(userId, nickname);
                    break;
                }
            }
        }

        // 최종 출력 메시지 생성
        String[] answer = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            // 메시지에 있는 유저 아이디를 실제 닉네임으로 교체
            String msg = result.get(i);
            String userId = msg.substring(0, msg.indexOf("님"));
            String finalMessage = msg.replace(userId, userMap.get(userId));
            answer[i] = finalMessage;
        }

        return answer;
    }

    // 다른 사람의 풀이 1
    private static final String ENTER_FORMAT = "%s님이 들어왔습니다.";
    private static final String LEAVE_FORMAT = "%s님이 나갔습니다.";

    private HashMap<String, UserInfo> userMap = new HashMap<>();

    private class UserInfo {
        public String userId;
        public String nickName;

        public UserInfo(String userId, String nickName) {
            this.userId = userId;
            this.nickName = nickName;
        }
    }

    private class Command {
        public char command;
        public String userId;

        public Command(char command, String userName) {
            this.command = command;
            this.userId = userName;
        }
    }

    public String[] solution2(String[] records) {
        ArrayList<Command> commandList = new ArrayList<>();

        for (String record : records) {
            String[] split = record.split(" ");
            String command = split[0];
            String userId = split[1];
            String nickName;

            switch(command.charAt(0)) {
                case 'E': // Enter
                    nickName = split[2];
                    if(!userMap.containsKey(userId)) {
                        userMap.put(userId, new UserInfo(userId, nickName));
                    } else {
                        userMap.get(userId).nickName = nickName;
                    }

                    commandList.add(new Command(command.charAt(0), userId));
                    break;
                case 'L': // Leave
                    commandList.add(new Command(command.charAt(0), userId));
                    break;
                case 'C': // Change
                    nickName = split[2];
                    userMap.get(userId).nickName = nickName;
                    break;
            }
        }

        return commandList.stream()
                .map(cmd -> String.format(cmd.command == 'E' ? ENTER_FORMAT : LEAVE_FORMAT , userMap.get(cmd.userId).nickName))
                .toArray(ary -> new String[commandList.size()]);
    }

    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};

        final String[] solution = new P42888().solution(record);
        System.out.println(Arrays.toString(solution));
    }
}

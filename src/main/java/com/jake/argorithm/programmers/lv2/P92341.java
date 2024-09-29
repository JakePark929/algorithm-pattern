package com.jake.argorithm.programmers.lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * [ 주차 요금 계산 ]
 *
 * 주차장의 요금표와 차량이 들어오고(입차) 나간(출차) 기록이 주어졌을 때,
 * 차량별로 주차 요금을 계산하려고 합니다.
 * 아래는 하나의 예시를 나타냅니다.
 *
 * 요금표
 * 기본 시간(분)	기본 요금(원)	단위 시간(분)	단위 요금(원)
 * 180	5000	10	600
 *
 * 어떤 차량이 입차된 후에 출차된 내역이 없다면, 23:59에 출차된 것으로 간주합니다.
 * 0000번 차량은 18:59에 입차된 이후, 출차된 내역이 없습니다. 따라서, 23:59에 출차된 것으로 간주합니다.
 * 00:00부터 23:59까지의 입/출차 내역을 바탕으로 차량별 누적 주차 시간을 계산하여 요금을 일괄로 정산합니다.
 * 누적 주차 시간이 기본 시간이하라면, 기본 요금을 청구합니다.
 * 누적 주차 시간이 기본 시간을 초과하면, 기본 요금에 더해서, 초과한 시간에 대해서 단위 시간 마다 단위 요금을 청구합니다.
 * 초과한 시간이 단위 시간으로 나누어 떨어지지 않으면, 올림합니다.
 * ⌈a⌉ : a보다 작지 않은 최소의 정수를 의미합니다. 즉, 올림을 의미합니다.
 * 주차 요금을 나타내는 정수 배열 fees, 자동차의 입/출차 내역을 나타내는 문자열 배열 records가 매개변수로 주어집니다.
 * 차량 번호가 작은 자동차부터 청구할 주차 요금을 차례대로 정수 배열에 담아서 return 하도록 solution 함수를 완성해주세요.
 *
 * 작성일 : 24.09.29
 */
public class P92341 {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> parkingTimes = new HashMap<>(); // 차량별 누적 주차 시간
        Map<String, Integer> inTimes = new HashMap<>(); // 입차 시간을 기록하는 맵

        // 주차 기록을 처리
        for (String record : records) {
            String[] parts = record.split(" ");
            String time = parts[0];
            String carNumber = parts[1];
            String status = parts[2];

            int minutes = timeToMinutes(time);

            if (status.equals("IN")) {
                inTimes.put(carNumber, minutes); // 입차 시간 기록
            } else {
                // 출차 시 주차 시간을 계산하여 누적
                int inTime = inTimes.remove(carNumber);
                int parkedTime = minutes - inTime;
                parkingTimes.put(carNumber, parkingTimes.getOrDefault(carNumber, 0) + parkedTime);
            }
        }

        // 출차 기록이 없는 차량 처리 (23:59에 출차된 것으로 간주)
        for (String carNumber : inTimes.keySet()) {
            int inTime = inTimes.get(carNumber);
            int parkedTime = timeToMinutes("23:59") - inTime;
            parkingTimes.put(carNumber, parkingTimes.getOrDefault(carNumber, 0) + parkedTime);
        }

        // 차량 번호 오름차순으로 정렬 후 요금 계산
        List<String> carNumbers = new ArrayList<>(parkingTimes.keySet());
        Collections.sort(carNumbers);

        int[] answer = new int[carNumbers.size()];
        for (int i = 0; i < carNumbers.size(); i++) {
            String carNumber = carNumbers.get(i);
            int totalTime = parkingTimes.get(carNumber);

            answer[i] = calculateFee(totalTime, fees); // 각 차량의 요금을 계산
        }

        return answer;
    }

    // 시간을 분으로 변환하는 함수
    private int timeToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        return hours * 60 + minutes;
    }

    // 주차 요금을 계산하는 함수
    private int calculateFee(int totalTime, int[] fees) {
        int basicTime = fees[0];  // 기본 시간
        int basicFee = fees[1];   // 기본 요금
        int unitTime = fees[2];   // 단위 시간
        int unitFee = fees[3];    // 단위 요금

        // 기본 시간 이하라면 기본 요금만 청구
        if (totalTime <= basicTime) {
            return basicFee;
        }

        // 초과 시간을 계산하여 추가 요금을 청구
        int extraTime = totalTime - basicTime;
        int extraFee = (int) Math.ceil((double) extraTime / unitTime) * unitFee;

        return basicFee + extraFee;
    }

    public int timeToInt(String time) {
        String temp[] = time.split(":");
        return Integer.parseInt(temp[0])*60 + Integer.parseInt(temp[1]);
    }

    public int[] solution2(int[] fees, String[] records) {
        TreeMap<String, Integer> map = new TreeMap<>();

        for(String record : records) {
            String[] temp = record.split(" ");
            int time = temp[2].equals("IN") ? -1 : 1;
            time *= timeToInt(temp[0]);
            map.put(temp[1], map.getOrDefault(temp[1], 0) + time);
        }

        int idx = 0;
        int[] ans = new int[map.size()];

        for(int time : map.values()) {
            if(time < 1) time += 1439;
            time -= fees[0];
            int cost = fees[1];
            if(time > 0)
                cost += (time%fees[2] == 0 ? time/fees[2] : time/fees[2]+1)*fees[3];

            ans[idx++] = cost;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {
                "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT",
                "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"
        };

        int[] result = new P92341().solution(fees, records);

        // 결과 출력
        System.out.println(Arrays.toString(result)); // [14600, 34400, 5000]
    }
}

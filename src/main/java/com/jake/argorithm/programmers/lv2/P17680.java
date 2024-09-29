package com.jake.argorithm.programmers.lv2;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * [ 캐시 ]
 * <p>
 * 지도개발팀에서 근무하는 제이지는 지도에서 도시 이름을 검색하면
 * 해당 도시와 관련된 맛집 게시물들을 데이터베이스에서 읽어 보여주는 서비스를 개발하고 있다.
 * 이 프로그램의 테스팅 업무를 담당하고 있는 어피치는 서비스를 오픈하기 전 각 로직에 대한 성능 측정을 수행하였는데,
 * 제이지가 작성한 부분 중 데이터베이스에서 게시물을 가져오는 부분의 실행시간이 너무 오래 걸린다는 것을 알게 되었다.
 * 어피치는 제이지에게 해당 로직을 개선하라고 닦달하기 시작하였고,
 * 제이지는 DB 캐시를 적용하여 성능 개선을 시도하고 있지만 캐시 크기를 얼마로 해야 효율적인지 몰라 난감한 상황이다.
 * <p>
 * 어피치에게 시달리는 제이지를 도와, DB 캐시를 적용할 때 캐시 크기에 따른 실행시간 측정 프로그램을 작성하시오.
 * <p>
 * 작성일 : 2024.09.29
 */
public class P17680 {
    private int cacheSize;
    private Set<String> cache;

    public int solution(int cacheSize, String[] cities) {
        this.cacheSize = cacheSize;
        this.cache = (cacheSize > 0) ? new LinkedHashSet<>() : null;

        int cost = 0;
        for (String city : cities) {
            cost += getCost(city.toUpperCase());
        }

        return cost;
    }

    private int getCost(String key) {
        if (cache == null) {
            return 5;
        }

        if (cache.contains(key)) {
            hitItem(key);

            return 1;
        }

        if (cache.size() == cacheSize) {
            removeLastItem();
        }

        cache.add(key);

        return 5;
    }

    private void hitItem(String key) {
        cache.remove(key);
        cache.add(key);
    }

    private void removeLastItem() {
        for (String item : cache) {
            cache.remove(item);
            break;
        }
    }

    public static int solution2(int cacheSize, String[] cities) {
        int answer = 0;
        ArrayList<String> list = new ArrayList<>();

        if (cacheSize == 0)
            return cities.length * 5;

        for (int i = 0; i < cities.length; i++) {
            cities[i] = cities[i].toUpperCase();

            if (list.contains(cities[i])) {
                answer++;
                list.remove(cities[i]);
                list.add(cities[i]);
            } else {
                answer += 5;
                if (list.size() == cacheSize) {
                    list.remove(0);
                    list.add(cities[i]);
                }
                else
                    list.add(cities[i]);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
        final int solution = new P17680().solution(8, cities);

        System.out.println(solution);
    }
}

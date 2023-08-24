package com.jake.datastructure;

import com.jake.datastructure.ds05_arrayqueue.ArrayQueue;
import com.jake.datastructure.ds06_linkedlistqueue.LinkedListQueue;
import com.jake.datastructure.ds07_arraydeque.ArrayDeque;
import com.jake.datastructure.ds08_linkedlistdeque.LinkedListDeque;
import com.jake.datastructure.ds09_heap.Heap;
import com.jake.datastructure.ds10_priorityqueue.PriorityQueue;

import java.util.Comparator;
import java.util.Random;

public class QueueTestMain {
    static void arrayQueueTest() {
        ArrayQueue<Integer> aq = new ArrayQueue<>();

        aq.offer(10);
        aq.offer(20);
        aq.offer(40);
        aq.offer(40);
        System.out.println(aq);

        aq.poll();
        aq.poll();
        System.out.println(aq);

        aq.offer(30);
        aq.offer(50);
        aq.offer(40);
        aq.offer(100);
        aq.offer(60);
        System.out.println(aq);

        aq.poll();
        System.out.println(aq);

//        aq.offer(50);
//        aq.offer(50);
//        aq.offer(50);
//        aq.offer(50);
//        System.out.println(aq);

        ArrayQueue<Integer> copyAq = aq;
        ArrayQueue<Integer> cloneAq = (ArrayQueue<Integer>) aq.clone();

//        aq.poll();
//        aq.poll();
//        aq.poll();
//        aq.poll();
//        aq.poll();
//        aq.poll();
//        aq.poll();
//        aq.poll();
//        System.out.println(aq);

        System.out.println(aq);
        System.out.println(copyAq);
        System.out.println(cloneAq);
        aq.sort();
        System.out.println(aq);
    }

    static void linkedListQueueTest() {
        LinkedListQueue<Integer> llq = new LinkedListQueue<>();

        llq.offer(50);
        llq.offer(40);
        llq.offer(30);
        System.out.println(llq);

        llq.poll();
        System.out.println(llq);

        llq.offer(20);
        llq.offer(10);
        System.out.println(llq);

        llq.sort();
        System.out.println(llq);

        LinkedListQueue<Integer> copyLlq = llq;
        LinkedListQueue<Integer> cloneLlq = (LinkedListQueue<Integer>) llq.clone();

        copyLlq.offer(60);
        copyLlq.offer(70);
        cloneLlq.offer(80);

        System.out.println(llq);
        System.out.println(copyLlq);
        System.out.println(cloneLlq);

        llq.clear();
        System.out.println(llq.peek());
        System.out.println(llq);
    }

    static void arrayDequeTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        ad.offer(50);
        ad.offer(30);
        ad.offer(40);
        System.out.println(ad);

        ad.poll();
        System.out.println(ad);

        ad.pollLast();
        System.out.println(ad);

        ad.offerFirst(10);
        ad.offerFirst(40);
        System.out.println(ad);

        ad.sort();
        System.out.println(ad);
        System.out.println(ad.size());
    }

    static void linkedListDequeTest() {
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();

        lld.offer(50);
        lld.offer(30);
        lld.offer(40);
        System.out.println(lld);
    }

    static void heapTest() {
        Heap<Integer> heap = new Heap<>();

        Random rnd = new Random();

        for (int i = 0; i < 2; i++) {
            heap.add(rnd.nextInt(100));
        }

        // 힙 내부 배열의 요소 상태
//        System.out.print("내부 배열 상태: ");
//        System.out.println(heap);
//
//        // 힙이 비어있을 때 까지 한 개씩 요소 뽑음
//        System.out.print("힙 요소 뽑기 : ");
//        while (!heap.isEmpty()) {
//            System.out.print(heap.remove() + " ");
//        }
//        System.out.println();

        System.out.println(heap);

        System.out.println();

        System.out.println("remove: " + heap.remove());
        System.out.println(heap.size());
        System.out.println(heap);
        System.out.println("remove: " + heap.remove());
        System.out.println(heap.size());
        System.out.println(heap);
    }

    static void heapClassTest() {
        Heap<Student> heap1 = new Heap<>();
        Heap<Student> heap2 = new Heap<>(comparator);

        heap1.add(new Student("김자바", 40));
        heap2.add(new Student("김자바", 40));

        heap1.add(new Student("이씨프", 27));
        heap2.add(new Student("이씨프", 27));

        heap1.add(new Student("조파이", 48));
        heap2.add(new Student("조파이", 48));

        heap1.add(new Student("김자바", 18));
        heap2.add(new Student("김자바", 18));

        heap1.add(new Student("상스윕", 32));
        heap2.add(new Student("상스윕", 32));

        heap1.add(new Student("양씨샵", 27));
        heap2.add(new Student("양씨샵", 27));

        System.out.println("[Heap 1] : 이름순(같을 경우 나이 오름차순)");
        while(!heap1.isEmpty()) {
            System.out.println(heap1.remove());
        }
        System.out.println();

        System.out.println("[Heap 2] : 나이 내림차순(같을 경우 이름순)");
        while(!heap2.isEmpty()) {
            System.out.println(heap2.remove());
        }

        System.out.println();
    }

    static void priorityQueueTest() {
        /*
         * 과학 점수가 높은 순
         * 과학 점수가 같을 경우 수학 점수가 높은 순
         * 둘 다 같을 경우 이름 순
         */
        Comparator<Students> comp = (o1, o2) -> {
            if (o1.science == o2.science) {
                if (o1.math == o2.math) {
                    return o1.name - o2.name;
                }
                return o2.math - o1.math;
            }
            return o2.science - o1.science;
        };
        
        PriorityQueue<Students> pq1 = new PriorityQueue<>(); // Comparable 정렬 순서
        PriorityQueue<Students> pq2 = new PriorityQueue<>(comp); // Comparator 정렬 순서

        Random rnd = new Random();

        char name = 'A';
        for (int i = 0; i < 10; i++) {
            int math = rnd.nextInt(10);
            int science = rnd.nextInt(10);

            pq1.offer(new Students(name, math, science));
            pq2.offer(new Students(name, math, science));

            name++;
        }

        System.out.println(pq1);
        System.out.println(pq2);

        System.out.println("[pq1 내부 배열 상태]");
        for (Object val : pq1.toArray()) {
            System.out.print(val);
        }

        System.out.println();

        System.out.println("[pq1 내부 배열 상태]");
        for (Object val : pq1.toArray()) {
            System.out.print(val);
        }

        System.out.println();

        System.out.println("[수학-과학-이름순 뽑기]");
        System.out.println("name\tmath\tscience");
        while(!pq1.isEmpty()) {
            System.out.println(pq1.poll());
        }

        System.out.println();

        System.out.println("[과학-수학-이름순 뽑기]");
        System.out.println("name\tmath\tscience");
        while(!pq2.isEmpty()) {
            System.out.println(pq2.poll());
        }

        System.out.println(pq1.poll());
    }

    private static final Comparator<Student> comparator = (o1, o2) -> {
        // 나이가 같다면 이름순
        if(o1.age == o2.age) {
            return o1.name.compareTo(o2.name);
        }

        return o2.age - o1.age;	// 나이 내림차순
    };

    private static class Students implements Comparable<Students> {
        char name;
        int math;
        int science;

        public Students(char name, int math, int science) {
            this.name = name;
            this.math = math;
            this.science = science;
        }

        /**
         * @param o the object to be compared.
         * @return 수학 점수가 높은 순
         *         수학 점수가 같을 경우 과학점수가 높은 순
         *         둘 다 같을 경우 이름 순
         */
        @Override
        public int compareTo(Students o) {
            if (this.math == o.math) {
                if (this.science == o.science) {
                    return this.name - o.name;
                }
                return o.science - this.science;
            }
            return o.math - this.math;
        }

        public String toString() {
            return name + " " + math + " " + science;
        }
    }

    private static class Student implements Comparable<Student> {
        String name;
        int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }


        @Override
        public int compareTo(Student o) {
            // 이름이 같다면 나이순 (오름차순)
            if (this.name.compareTo(o.name) == 0) {
                return this.age - o.age;
            }

            // 이름 순
            return this.name.compareTo(o.name);
        }

        public String toString() {
            return "이름: " + name + " 나이 : " + age;
        }
    }

    public static void main(String[] args) {
//        arrayQueueTest();
//        linkedListQueueTest();
//        arrayDequeTest();
//        linkedListDequeTest();
//        heapTest();
//        heapClassTest();
        priorityQueueTest();
    }
}

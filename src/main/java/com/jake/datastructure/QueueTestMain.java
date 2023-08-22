package com.jake.datastructure;

import com.jake.datastructure.ds05_arrayqueue.ArrayQueue;
import com.jake.datastructure.ds06_linkedlistqueue.LinkedListQueue;
import com.jake.datastructure.ds07_arraydeque.ArrayDeque;
import com.jake.datastructure.ds08_linkedlistdeque.LinkedListDeque;

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

    public static void main(String[] args) {
//        arrayQueueTest();
//        linkedListQueueTest();
//        arrayDequeTest();
        linkedListDequeTest();
    }
}

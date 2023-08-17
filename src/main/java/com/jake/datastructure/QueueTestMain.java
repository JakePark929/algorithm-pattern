package com.jake.datastructure;

import com.jake.datastructure.ds05_arrayqueue.ArrayQueue;

public class QueueTestMain {
    public static void main(String[] args) {
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
        aq.offer(30);
        aq.offer(30);
        aq.offer(30);
        aq.offer(30);
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
    }
}

package com.jake.datastructure.ds08_linkedlistdeque;

class Node<E> {
    E data; // 데이터를 담을 변수
    Node<E> prev;
    Node<E> next;

    Node(E data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

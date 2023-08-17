package com.jake.datastructure.ds03_doublelinkedlist;

class Node<E> {
    E data;
    Node<E> next; // 다음 노드 객체를 가리키는 reference 변수
    Node<E> prev; // 이전 노드 객체를 가리키는 reference 변수

    Node(E data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

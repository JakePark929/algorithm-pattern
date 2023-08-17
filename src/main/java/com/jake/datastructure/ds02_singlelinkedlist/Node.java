package com.jake.datastructure.ds02_singlelinkedlist;

class Node<E> {
    E data;
    Node<E> next; // 다음 노드 객체를 가리키는 레퍼러스 편수

    Node(E data) {
        this.data = data;
        this.next = null;
    }
}

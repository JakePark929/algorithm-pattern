package com.jake.datastructure.list;

import com.jake.datastructure.interface_form.List;

public class SingleLinkedList<E> implements List<E> {
    private Node<E> head; // 노드의 첫 부분
    private Node<E> tail; // 노드의 마지막 부분
    private int size; // 요소 개수

    // 생성자
    public SingleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // 특정 위치의 노드를 반환하는 메소드
    private Node<E> search(int index) {
        // 범위 밖(잘못된 위치)일 경우 예외 던지기
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> x = head; // head 가 가리키는 노드부터 시작

        for (int i = 0; i < index; i++) {
            x = x.next; // x 노드의 다음 노드를 x 에 저장한다
        }

        return x;
    }

    public void addFirst(E value) {
        Node<E> newNode = new Node<>(value); // 새 노드 생성
        newNode.next = head; // 새 노드의 다음 노드로 head 노드를 연결
        head = newNode; // head 가 가리키는 노드를 새 노드로 변경
        size++;

        /**
         * 다음에 가리킬 노드가 없는 경우(= 데이터가 새 노드 밖에 없는 경우)
         * 데이터가 한 개 (새 노드) 밖에 없으므로 새 노드는 처음 시작 노드이자 마지막 노드이다.
         * 즉, tail = head 이다.
         */
        if (head.next == null) {
            tail = head;
        }
    }


    @Override
    public boolean add(E value) {
        return false;
    }

    @Override
    public void add(int index, E value) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public boolean remove(Object value) {
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E value) {
        return null;
    }

    @Override
    public boolean contains(Object value) {
        return false;
    }

    @Override
    public int indexOf(Object value) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }
}

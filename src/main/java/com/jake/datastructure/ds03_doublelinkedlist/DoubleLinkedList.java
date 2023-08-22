package com.jake.datastructure.ds03_doublelinkedlist;

import com.jake.datastructure.interface_form.List;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * @param <E> the type of elements in this list
 * @author jake_park
 * @since 2023.08.16
 */
public class DoubleLinkedList<E> implements List<E>, Cloneable {
    private Node<E> head; // 노드의 첫 부분
    private Node<E> tail; // 노드의 마지막 부분
    private int size; // 요소 개수

    public DoubleLinkedList() {
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

        // 찾으려는 위치가 현재 사이즈의 절반보다 크면 뒤에서부터 검색
        Node<E> x;

        if (index + 1 > size / 2) {
            x = tail;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
        } else {
            // 아니면 앞에서 부터 검색
            x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        }

        return x;
    }

    public void addFirst(E value) {
        Node<E> newNode = new Node<>(value); // 새 노드 생성
        newNode.next = head; // 새 노드의 다음 노드로 head 노드를 연결

        /*
         * head 가 null 이 아닐 경우에만 기존 head 노드의 prev 변수가 새 노드를 가리키도록 한다.
         * 이유는 기존 head 노드가 없는 경우(null)는 데이터가
         * 아무것도 없던 상태였으므로 head.prev 를 하면 잘못된 참조가 된다.
         */
        if (head != null) {
            head.prev = newNode;
        }

        head = newNode;
        size++;

        /*
         * 다음에 가리킬 노드가 없는 경우(=데이터가 새 노드밖에 없는 경우)
         * 데이터가 한 개(새 노드)밖에 없으므로 새 노드는 처음 시작 노드이자 마지막 노드이다.
         * 즉, tail = head 이다.
         */
        if (head.next == null) {
            tail = head;
        }
    }

    public void addLast(E value) {
        if (size == 0) { // 처음 넣는 노드일 경우 addFirst 로 추가
            addFirst(value);
            return;
        }

        Node<E> newNode = new Node<>(value); // 새 노드 생성

        /*
         * 마지막 노드(tail)의 다음 노드(next)가 새 노드를 가리키도록 하고
         * tail 이 가리키는 노드를 새 노드로 바꿔준다.
         */
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    @Override
    public void add(int index, E value) {
        // 잘못된 인덱스를 참조할 경우 예외 발생
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        // 추가하려는 index 가 가장 앞인 경우 addFirst() 호출
        if (index == 0) {
            addFirst(value);
            return;
        }

        // 추가하려는 index 가 가장 마지막인 경우 addLast() 호출
        if (index == size) {
            addLast(value);
            return;
        }

        // 추가하려는 위치의 이전 노드
        Node<E> prevNode = search(index - 1);

        // 추가하려는 위치의 노드
        Node<E> nextNode = prevNode.next;

        // 추가하려는 노드
        Node<E> newNode = new Node<>(value);

        // 링크 끊기
        prevNode.next = null;
        nextNode.prev = null;

        // 링크 연결하기
        prevNode.next = newNode;
        newNode.prev = prevNode;
        newNode.next = nextNode;
        nextNode.prev = newNode;

        size++;
    }

    public E remove() {
        Node<E> headNode = head;

        if (headNode == null) {
            throw new NoSuchElementException();
        }

        // 삭제된 노드를 반환하기 위한 임시 변수
        E element = headNode.data;

        // head 의 다음 노드를 저장할 변수
        Node<E> nextNode = head.next;

        // head 노드의 데이터들을 모두 삭제
        head.data = null;
        head.next = null;

        /*
         * head 의 다음 노드(=nextNode)가 null 이 아닐 경우에만
         * prev 변수를 null 로 업데이트 해주어야 한다.
         * 이유는 nextNode 가 없는 경우(null)는 데이터가
         * 아무것도 없던 상태였으므로 nextNode.prev 를 하면 잘못된 참조가 된다.
         */
        if (nextNode != null) {
            nextNode.prev = null;
        }

        head = nextNode;
        size--;

        /*
         * 삭제된 요소가 리스트의 유일한 요소였을 경우
         * 그 요소는 head 이자 tail 이었으므로
         * 삭제되면서 tail 도 가리킬 요소가 없기 때문에
         * size 가 0 일 경우 tail 도 null 로 반환
         */
        if (size == 0) {
            tail = null;
        }

        return element;
    }

    @Override
    public E remove(int index) {
        // 잘못된 인덱스를 참조할 경우 예외 발생
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        // 삭제하려는 노드가 첫 번째 노드일 경우
        if (index == 0) {
            E element = head.data;
            remove();
            return element;
        }

        // 삭제할 노드의 이전 노드
        Node<E> prevNode = search(index - 1);

        // 삭제할 노드
        Node<E> removeNode = prevNode.next;

        // 삭제할 노드의 다음 노드
        Node<E> nextNode = removeNode.next;

        E element = removeNode.data; // 삭제되는 노드의 데이터를 반환하기 위한 임시변수

        /*
         * index == 0 일 때의 조건에서 이미 head 노드의 삭제에 대한 분기가 있기 때문에 prevNode 는 항상 존재한다.
         * 그러나 nextNode 의 경우는 null 일 수 있기 때문에(= 마지막 노드를 삭제하려는 경우)
         * 이전처럼 반드시 검사를 해준 뒤, nextNode.prev 에 접근해야 한다.
         */
        prevNode.next = null;
        removeNode.next = null;
        removeNode.prev = null;
        removeNode.data = null;

        if (nextNode != null) {
            nextNode.prev = prevNode;
            prevNode.next = nextNode;
        } else {
            /*
             * nextNode 가 null 이라는 것은 마지막 노드를 삭제했다는 의미이므로
             * prevNode 가 tail 이 된다. (연결 해 줄 것이 없음)
             */
            tail = prevNode;
        }

        size--;

        return element;
    }

    @Override
    public boolean remove(Object value) {
        Node<E> prevNode = head;
        Node<E> x = head; // 삭제할 노드를 저장하는 변수

        // value 와 일치하는 노드를 찾는다.
        for (; x != null; x = x.next) {
            if (value.equals(x.data)) {
                break;
            }
            prevNode = x;
        }

        // 일치하는 요소가 없을 경우 false 반환
        if (x == null) {
            return false;
        }

        // 삭제하려는 노드가 head 일 경우 remove() 로 삭제
        if (x.equals(head)) {
            remove();
        } else {
            // remove(int index) 와 같은 메커니즘으로 삭제
            Node<E> nextNode = x.next;

            prevNode.next = null;
            x.data = null;
            x.next = null;
            x.prev = null;

            if (nextNode != null) {
                nextNode.prev = prevNode;
                prevNode.next = nextNode;
            } else {
                tail = prevNode;
            }

            size--;
        }

        return true;
    }

    @Override
    public E get(int index) {
        return search(index).data;
    }

    @Override
    public E set(int index, E value) {
        Node<E> replaceNode = search(index);
        replaceNode.data = value;
        return replaceNode.data;
    }

    @Override
    public int indexOf(Object value) { // 정방향 탐색
        int index = 0;

        for (Node<E> x = head; x != null; x = x.next) {
            if (value.equals(x.data)) {
                return index;
            }
            index++;
        }

        return -1;
    }

    public int lastIndexOf(Object value) { // 역방향 탐색
        int index = size;

        for (Node<E> x = tail; x != null; x = x.prev) {
            index--;
            if (value.equals(x.data)) {
                return index;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (Node<E> x = head; x != null; ) {
            Node<E> nextNode = x.next;
            x.data = null;
            x.next = null;
            x.prev = null;
            x = nextNode;
        }

        head = tail = null;
        size = 0;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        @SuppressWarnings("unchecked")
        DoubleLinkedList<? super E> clone = (DoubleLinkedList<? super E>) super.clone();

        clone.head = null;
        clone.tail = null;
        clone.size = 0;

        for (Node<E> x = head; x != null; x = x.next) {
            clone.addLast(x.data);
        }

        return clone;
    }

    public Object[] toArray() {
        Object[] array = new Object[size];

        int idx = 0;
        for (Node<E> x = head; x != null; x = x.next) {
            array[idx++] = (E) x.data;
        }

        return array;
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            array = (T[]) Array.newInstance(array.getClass().getComponentType(), size);
        }

        int idx = 0;
        Object[] result = array;
        for (Node<E> x = head; x != null; x = x.next) {
            result[idx++] = x.data;
        }

        return array;
    }

    public void sort() {
        /*
         * Comparator 를 넘겨주지 않는 경우 해당 객체의 Comparable 에 구현된 정렬 방식을 사용한다.
         * 만약 구현되어 있지않으면 cannot be cast to class java.lang.Comparable 에러가 발생한다.
         * 만약 구현되어 있을 경우 null 로 파라미터를 넘기면 Arrays.sort() 가 객체의 compareTo 메소드에 정의된 방식대로 정렬한다.
         */
        sort(null);
    }

    @SuppressWarnings({"unckecked, rawtypes"})
    public void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);

        int idx = 0;
        for (Node<E> x = head; x != null; x = x.next, idx++) {
            x.data = (E) a[idx];
        }
    }

    @Override
    public String toString() {
        if (size == 0)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (Node<E> x = head; x != null; x = x.next) {
            b.append(x.data);
            if (x.next == null)
                return b.append(']').toString();
            b.append(", ");
        }

        return null;
    }
}

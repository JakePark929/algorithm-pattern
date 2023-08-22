package com.jake.datastructure.ds08_linkedlistdeque;

import com.jake.datastructure.interface_form.Queue;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 *
 * @param <E> the type of elements in this Queue
 * @author jake_park
 * @since 2023.08.21
 * @see Queue
 */
public class LinkedListDeque<E> implements Queue<E> {
    private Node<E> head; // 노드의 첫 부분
    private Node<E> tail; // 노드의 마지막 부분
    private int size; // 요소 개수

    public LinkedListDeque() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    public boolean offerFirst(E value) {
        Node<E> newNode = new Node<>(value); // 새 노드 생성
        newNode.next = head;

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
         * 다음에 가리킬 노드가 없는 경우(=데이터가 새 노드밖에 없는 경우 =이전의 head 가 null 인 경우)
         * 데이터가 한 개(새 노드)밖에 없으므로 새 노드는 처음 시작 노드이자 마지막 노드이다.
         * 즉, tail = head 이다.
         */
        if (head.next == null) {
            tail = head;
        }
        
        return true;
    }
    
    public boolean offerLast(E value) {
        // 데이터가 없을 경우 offerFirst() 로 인자를 넘겨줌
        if (size == 0) {
            return offerFirst(value);
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
        
        return true;
    }

    @Override
    public boolean offer(E value) {
        return offerLast(value);
    }

    public E pollFirst() {
        if (size == 0) {
            return null;
        }

        E element = head.data; // 반환하기 위한 데이터

        Node<E> nextNode = head.next; // head 의 다음 노드

        // head 가 가리키는 모든 데이터들을 삭제
        head.data = null;
        head.next = null;

        // 삭제하기 전 데이터가 두 개 이상 있을 경우 (head 와 tail 이 같지 않은 경우)
        if (nextNode != null) {
            nextNode.prev = null;
        }

        head = nextNode; // head 가 가리키는 노드를 삭제한 노드의 다음 노드로 갱신
        size--;

        /*
         * 삭제된 요소가 덱의 유일한 요소였을 경우
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
    public E poll() {
        return pollFirst();
    }

    public E remove() {
        return removeFirst();
    }

    public E removeFirst() {
        E element = poll();

        if (element == null) {
            throw new NoSuchElementException();
        }

        return element;
    }

    public E pollLast() {
        if (size == 0) {
            return null;
        }

        E element = tail.data; // 반환하기 위한 데이터

        Node<E> prevNode = tail.prev; // tail 의 이전 노드

        // tail 이 가리키는 노드의 데이터와 링크 삭제
        tail.data = null;
        tail.prev = null;

        // 삭제하기전 데이터가 두 개 이상 있을 경우(head 와 tail 이 같지 않을 경우)
        if (prevNode != null) {
            prevNode.next = null;
        }

        tail = prevNode;
        size--;

        /*
         * 삭제된 요소가 덱의 유일한 요소였을 경우
         * 그 요소는 head 이자 tail 이었으므로
         * 삭제되면서 tail 도 가리킬 요소가 없기 때문에
         * size 가 0 일 경우 tail 도 null 로 반환
         */
        if (size == 0) {
            tail = null;
        }

        return element;
    }

    public E removeLast() {
        E element = pollLast();

        if (element == null) {
            throw new NoSuchElementException();
        }

        return element;
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    public E peekFirst() {
        // 요소가 없을 경우 null 반환
        if (size == 0) {
            return null;
        }

        return head.data;
    }

    public E peekLast() {
        // 요소가 없을 경우 null 반환
        if (size == 0) {
            return null;
        }

        return tail.data;
    }

    public E element() {
        return getFirst();
    }

    public E getFirst() {
        E element = peek();

        // 앞의 원소가 null 이라면(size = 0) 예외를 던진다.
        if (element == null) {
            throw new NoSuchElementException();
        }

        return element;
    }

    public E getLast() {
        E element = peekLast();

        // 앞의 원소가 null 이라면(size = 0) 예외를 던진다.
        if (element == null) {
            throw new NoSuchElementException();
        }

        return element;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object value) {
        /*
         * head 데이터 부터 x 가 null 이 될 때 까지 value 와 x 의 데이터(x.data)가
         * 같은지를 비교하고 같은 경우 true 를 반환한다.
         */
        for (Node<E> x = head; x != null; x = x.next) {
            if (value.equals(x.data)) {
                return true;
            }
        }

        return false;
    }

    public void clear() {
        for (Node<E> x = head; x != null; ) {
            Node<E> next = x.next;
            x.data = null;
            x.prev = null;
            x.next = null;
            x = next;
        }
        size = 0;
        head = tail = null;
    }

    public Object[] toArray() {
        Object[] array = new Object[size];

        int idx = 0;
        for (Node<E> x = head; x != null; x = x.next) {
            array[idx++] = x.data;
        }

        return array;
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] arr) {
        if (arr.length < size) {
            // Array.newInstance(컴포넌트 타입, 생성할 크기)
            arr = (T[]) Array.newInstance(arr.getClass().getComponentType(), size());
        }

        int idx = 0;
        Object[] result = arr;
        for (Node<E> x = head; x != null; x = x.next) {
            result[idx++] = x.data;
        }

        return arr;
    }

    @Override
    public Object clone() {
        // super.clone() 은 CloneNotSupportedException 예외 처리를 해주어야 한다.
        try {
            LinkedListDeque<E> clone = (LinkedListDeque<E>) super.clone(); // 새로운 큐 객체 생성
            clone.head = null;
            clone.tail = null;
            clone.size = 0;

            // 내부까지 복사되는 것이 아니기 때문에 내부 데이터들을 모두 복사해준다.
            for (Node<E> x = head; x != null; x = x.next) {
                clone.offer(x.data);
            }

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new Error(e); // 예외처리 자유롭게 구성
        }
    }

    public void sort() {
        /*
         * Comparator 를 넘겨주지 않는 경우 해당 객체의 Comparable 에 구현된 정렬 방식을 사용한다.
         * 만약 구현되어 있지 않으면 cannot be cast to class java.lang.Comparable 에러가 발생한다.
         * 만약 구현되어 있을 경우 null 로 파라미터를 넘기면 Arrays.sort()가 객체의 compareTo 메소드에 정의된 방식대로 정렬한다.
         */
        sort(null);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);

        int i = 0;
        // 정렬된 a 배열을 큐로 복사한다.
        for (Node<E> x = head; x != null; x = x.next, i++) {
            x.data = (E) a[i];
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

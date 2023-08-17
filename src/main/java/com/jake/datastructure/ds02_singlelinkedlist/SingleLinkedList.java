package com.jake.datastructure.ds02_singlelinkedlist;

import com.jake.datastructure.interface_form.List;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @param <E> the type of elements in this list
 * @author jake_park
 * @since 2023.08.16
 */
public class SingleLinkedList<E> implements List<E>, Iterable<E>, Cloneable {
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

        /*
         * 다음에 가리킬 노드가 없는 경우(= 데이터가 새 노드 밖에 없는 경우)
         * 데이터가 한 개 (새 노드) 밖에 없으므로 새 노드는 처음 시작 노드이자 마지막 노드이다.
         * 즉, tail = head 이다.
         */
        if (head.next == null) {
            tail = head;
        }
    }

    public void addLast(E value) {
        Node<E> newNode = new Node<>(value); // 새 노드 생성

        if (size == 0) {
            addFirst(value);
            return;
        }

        /*
         * 마지막 노드(tail)의 다음 노드(next)가 새 노드를 가리키도록 하고
         * tail 이 가리키는 노드를 새 노드로 바꿔 준다.
         */

        tail.next = newNode;
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

        // 추가하려는 index 가 가장 앞에 추가하려는 경우 addFirst 호출
        if (index == 0) {
            addFirst(value);
            return;
        }

        // 추가하려는 index 가 마지막 위치일 겨우 addLast 호출
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

        /*
         * 이전 노드가 가리키는 노드를 끊은 뒤
         * 새 노드로 변경해준다.
         * 또한 새 노드가 가리키는 노드는 nextNode 로
         * 설정해 준다.
         */
        prevNode.next = newNode;
        newNode.next = nextNode;
        size++;
    }

    public E remove() {
        Node<E> headNode = head;

        if (headNode == null) {
            throw new NoSuchElementException();
        }

        // 삭제된 노드를 반환하기 위한 임시 변수
        E element = headNode.data;

        // head 의 다음 노드
        Node<E> nextNode = head.next;

        // head 노드의 데이터들을 모두 삭제
        head.data = null;
        head.next = null;

        // head 가 다음 노드를 가리키도록 업데이트
        head = nextNode;
        size--;

        /*
         * 삭제된 요소가 리스트의 유일한 요소였을 경우
         * 그 요소는 head 이자 tail 이였으므로
         * 삭제되면서 tail 도 가리킬 요소가 없기 때문에
         * size 가 0일 경우 tail 도 null 반환
         */
        if (size == 0) {
            tail = null;
        }

        return element;
    }

    @Override
    public E remove(int index) {
        // 잘못된 범위에 대한 예외
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        // 삭제하려는 노드가 첫 번째 원소일 경우
        if (index == 0) {
            return remove();
        }

        // 삭제할 노드의 이전 노드
        Node<E> prevNode = search(index - 1);

        // 삭제할 노드
        Node<E> removeNode = prevNode.next;

        // 삭제할 노드의 다음 노드
        Node<E> nextNode = removeNode.next;

        E element = removeNode.data; // 삭제되는 노드의 데이터를 반환하기 위한 임시 변수
        prevNode.next = nextNode; // 이전 노드가 가리키는 노드를 삭제하려는 노드의 다음 노드로 변경

        // 만약 삭제했던 노드가 마지막 노드라면 tail 을 prevNode 로 갱신
        if (prevNode.next == null) {
            tail = prevNode;
        }

        // 데이터 삭제
        removeNode.next = null;
        removeNode.data = null;
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

        // 만약 삭제하려는 노드가 head 라면 기존 remove() 를 사용
        if (x.equals(head)) {
            remove();
        } else {
            // 이전 노드의 링크를 삭제하려는 노드의 다음 노드로 연결
            prevNode.next = x.next;

            // 만약 삭제했던 노드가 마지막 노드라면 tail 을 prevNode 로 갱신
            if (prevNode.next == null) {
                tail = prevNode;
            }

            x.data = null;
            x.next = null;
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
    public int indexOf(Object value) {
        int index = 0;

        for (Node<E> x = head; x != null; x = x.next) {
            if (value.equals(x.data)) {
                return index;
            }

            index++;
        }

        // 찾고자 하는 요소를 찾지 못했을 경우 -1 반환
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
            x = nextNode;
        }

        head = tail = null;
        size = 0;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        @SuppressWarnings("unchecked")
        SingleLinkedList<? super E> clone = (SingleLinkedList<? super E>) super.clone();

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
        Object[] arr = this.toArray();
        Arrays.sort(arr, (Comparator) c);

//        int idx = 0;
//        for (Node<E> x = head; x != null; x = x.next, idx++) {
//            x.data = (E) a[idx];
//        }

        Iter it = (SingleLinkedList<E>.Iter) this.iterator();
        for (Object o : arr) {
            it.next();
            it.set((E) o);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<E> {
        private int nowIndex = 0;
        private Node<E> nextNode = head;
        private Node<E> nowNode;

        @Override
        public boolean hasNext() {
            return nowIndex < size;
        }

        @Override
        public E next() {
            int cs = nowIndex;
            if (cs >= size) {
                throw new NoSuchElementException();
            }
            nowNode = nextNode;
            nextNode = nextNode.next;
            nowIndex = cs + 1;
            return (E) nowNode.data;
        }

        public void set(E e) {
            if(nowNode == null) {
                throw new IllegalStateException();
            }
            nowNode.data = e;
        }

        public void remove() {
            throw new UnsupportedOperationException();
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

package com.jake.datastructure.ds10_priorityqueue;

import com.jake.datastructure.interface_form.Queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class PriorityQueue<E> implements Queue<E>, Cloneable {
    private final Comparator<? super E> comparator;
    private static final int DEFAULT_CAPACITY = 10; // 최소(기본) 용적 크기

    private Object[] array; // 요소를 담을 배열
    private int size; // 요소 개수

    // 생성자 Type 1 (초기 공간 할당 X)
    public PriorityQueue() {
        this(null);
    }

    public PriorityQueue(Comparator<? super E> comparator) {
        this.comparator = comparator;
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // 성성자 Type 2 (초기 공간 할당 O)
    public PriorityQueue(int capacity) {
        this(capacity, null);
    }

    public PriorityQueue(int capacity, Comparator<? super E> comparator) {
        this.comparator = comparator;
        this.array = new Object[capacity];
        this.size = 0;
    }

    // 받은 인덱스의 부모 노드 인덱스를 반환
    private int getParent(int index) {
        return index / 2;
    }

    // 받은 인덱스의 왼쪽 자식 노드 인덱스를 반환
    private int getLeftChild(int index) {
        return index * 2;
    }

    // 받은 인덱스의 오른쪽 자식 노드 인덱스를 반환
    private int getRightChild(int index) {
        return index * 2 + 1;
    }

    /**
     * @param newCapacity 새로운 용적 크기
     */
    private void resize(int newCapacity) {
        // 새로 만들 배열
        Object[] newArray = new Object[newCapacity];

        // 새 배열에 기존에 있던 배열의 요소들을 모두 복사해준다.
        for (int i = 1; i <= size; i++) {
            newArray[i] = array[i];
        }

        /*
         * 현재 배열은 GC 처리를 위해 null 로 처리한 뒤,
         * 새 배열을 연결해준다.
         */
        this.array = null;
        this.array = newArray;
    }

    @Override
    public boolean offer(E value) {
        // 배열의 용적이 꽉 차있을 경우 용적을 두 배로 늘려준다.
        if (size + 1 == array.length) {
            resize(array.length * 2);
        }

        siftUp(size + 1, value); // 가장 마지막에 추가되는 위치와 넣을 값(타겟)을 넘겨줌
        size++;

        return true;
    }

    /**
     * [ 상향 선별 ]
     *
     * @param idx    추가할 노드의 인덱스
     * @param target 재배치 할 노드
     */
    private void siftUp(int idx, E target) {
        if (comparator != null) {
            siftUpComparator(idx, target, comparator);
        } else {
            siftUpComparable(idx, target);
        }
    }

    // Comparator 를 이용한 sift-up
    @SuppressWarnings("unchecked")
    private void siftUpComparator(int idx, E target, Comparator<? super E> comp) {
        // root 노드보다 클 때 까지만 탐색한다.
        while (idx > 1) {
            int parentIdx = getParent(idx); // 삽입 노드의 부모 노드 인덱스 구하기
            Object parentVal = array[parentIdx]; // 부모 노드의 값

            // 타겟 노드 값이 부모노드 보다 크면 반복문 종료
            if (comp.compare(target, (E) parentVal) >= 0) {
                break;
            }

            /*
             * 부모 노드가 타겟 노드 보다 크므로
             * 현재 삽입 될 위치를 부모 노드 값으로 교체 해주고
             * 타겟 노드의 위치를 부모 노드의 위치로 변경해 준다.
             */
            array[idx] = parentVal;
            idx = parentIdx;
        }

        array[idx] = target;
    }

    // 삽입 할 객체의 Comparable 을 이용한 sift-up
    @SuppressWarnings("unchecked")
    private void siftUpComparable(int idx, E target) {
        // 타겟 노드가 비교 될 수 있도록 한 변수를 만든다.
        Comparable<? super E> comp = (Comparable<? super E>) target;

        while (idx > 1) {
            int parentIdx = getParent(idx);
            Object parentVal = array[parentIdx];

            if (comp.compareTo((E) parentVal) >= 0) {
                break;
            }

            array[idx] = parentVal;
            idx = parentIdx;
        }

        array[idx] = comp;
    }

    @Override
    public E poll() {
        // poll 은 뽑으려는 요소가(root) 가 null 일 경우 null 을 반환한다.
        if (array[1] == null) {
            return null;
        }

        // 그외의 경우 remove() 에서 반환되는 요소를 반환한다.
        return remove();
    }

    @SuppressWarnings("unchecked")
    public E remove() {
        if (array[1] == null) { // 만약 root 가 비어있을 경우 예외를 던지도록 함
            throw new NoSuchElementException();
        }

        E result = (E) array[1]; // 삭제된 요소를 반환하기 위한 임시 변수

        if (size == 1) {
            array[1] = null;
            size--;
            return result;
        }

        E target = (E) array[size]; // 타겟이 될 요소
        array[size] = null; // 타겟 노드를 비운다.
        size--; // 사이즈를 1 감소

        // 삭제할 노드의 인덱스와 이후 재배치 할 타겟 노드를 넘겨준다.
        siftDown(1, target); // 루트 노드가 삭제되므로 1을 넘겨준다.

        return result;
    }

    /**
     * [ 하향 선별 ]
     *
     * @param idx    삭제할 노드의 인덱스
     * @param target 재배치 할 노드
     */
    private void siftDown(int idx, E target) {
        // comparator 가 존재한 경우 comparator 를 인자로 넘겨준다.
        if (comparator != null) {
            siftDownComparator(idx, target, comparator);
        } else {
            siftDownComparable(idx, target);
        }
    }

    // Comparator 를 이용한 sift-down
    @SuppressWarnings("unchecked")
    private void siftDownComparator(int idx, E target, Comparator<? super E> comp) {
        array[idx] = null; // 삭제할 인덱스의 노드를 삭제

        int parentIdx = idx; // 삭제 노드부터 시작할 부모를 가리키는 변수
        int childIdx; // 교환될 자식을 가리키는 변수

        while ((childIdx = getLeftChild(parentIdx)) <= size) {
            // 왼쪽 자식 노드의 인덱스가 요소의 개수보다 작을 때 까지 반복
            int rightIdx = getRightChild(parentIdx); // 오른쪽 자식 인덱스
            Object childVal = array[childIdx]; // 왼쪽 자식의 값(교환될 값)

            /*
             * 오른쪽 자식 인덱스가 size 를 넘지 않으면서
             * 왼쪽 자식이 오른쪽 자식보다 큰 경우
             * 재배치 할 노드는 작은 자식과 비교해야 하므로
             * childIdx 와 childVal 을 오른쪽 자식으로 바꿔 준다.
             */
            if (rightIdx <= size && comp.compare((E) childVal, (E) array[rightIdx]) > 0) {
                childIdx = rightIdx;
                childVal = array[childIdx];
            }

            // 재배치 할 노드가 자식 노드보다 작을 경우 반복문을 종료한다.
            if (comp.compare(target, (E) childVal) <= 0) {
                break;
            }

            /*
             * 현재 부모 인덱스에 자식 노드 값을 대체해주고
             * 부모 인덱스를 자식 인덱스로 교체
             */
            array[parentIdx] = childVal;
            parentIdx = childIdx;
        }

        // 최종적으로 재배치 되는 위치에 타겟이 된 값을 넣어 준다.
        array[parentIdx] = target;

        /*
         * 용적의 사이즈가 최소 용적보다는 크면서 요소의 개수가 전체 용적의 1/4 일 경우
         * 용적을 반으로 줄임 (단, 최소 용적보단 커야함)
         */
        if (array.length > DEFAULT_CAPACITY && size < array.length / 4) {
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }
    }

    // Comparable 을 이용한 sift-down
    @SuppressWarnings("unchecked")
    private void siftDownComparable(int idx, E target) {
        Comparable<? super E> comp = (Comparable<? super E>) target;
        array[idx] = null;

        int parentIdx = idx;
        int childIdx;

        while ((childIdx = (parentIdx << 1)) <= size) {
            int rightIdx = childIdx + 1;
            Object childVal = array[childIdx];

            if (rightIdx <= size && ((Comparable<? super E>) childVal).compareTo((E) array[rightIdx]) > 0) {
                childIdx = rightIdx;
                childVal = array[childIdx];
            }

            if (comp.compareTo((E) childVal) <= 0) {
                break;
            }

            array[parentIdx] = childVal;
            parentIdx = childIdx;
        }

        array[parentIdx] = comp;

        if (array.length > DEFAULT_CAPACITY && size < array.length / 4) {
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }
    }

    public int size() {
        return this.size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E peek() {
        if (array[1] == null) { // root 요소가 null 일 경우 예외 던짐
            throw new NoSuchElementException();
        }

        return (E) array[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object value) {
        for (int i = 1; i <= size; i++) {
            if (array[i].equals(value)) {
                return true;
            }
        }

        return false;
    }

    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }

        size = 0;
    }

    public Object[] toArray() {
        return toArray(new Object[size]);
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array) {
        if (array.length <= size) {
            // copyOf(원본 배열, 복사할 길이, Class<? extends T[]> 타입)
            return (T[]) Arrays.copyOfRange(this.array, 1, size + 1, array.getClass());
        }
        // 원본배열, 원본배열 시작위치, 복사할 배열, 복사할 배열 시작위치, 복사할 요소수
        System.arraycopy(this.array, 1, array, 0, size);

        return array;
    }

    @Override
    public Object clone() {
        // super.clone() 은 CloneNotSupportedException 예외를 처리해 주어야 한다.
        try {
            PriorityQueue<?> cloneHeap = (PriorityQueue<?>) super.clone();
            cloneHeap.array = new Object[size + 1];

            // 단순히 clone() 만 한다고 내부 데이터까지 깊은 복사가 되는 것이 아니므로 내부 데이터들도 모두 복사해준다.
            System.arraycopy(array, 0, cloneHeap.array, 0, size + 1);

            return cloneHeap;
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }

    @Override
    public String toString() {
        if (array == null)
            return "null";

        int iMax = size;
        if (iMax == 0)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 1; i <= size; i++) {
            b.append(array[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }

        return null;
    }
}

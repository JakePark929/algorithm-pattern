package com.jake.datastructure.ds04_stack;

import com.jake.datastructure.interface_form.StackInterface;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EmptyStackException;

/**
 * @param <E> the type of elements in this list
 * @author JakePark
 */
public class Stack<E> implements StackInterface<E>, Cloneable {
    private static final int DEFAULT_CAPACITY = 10; // 최소(기본) 용적 크기
    private static final Object[] EMPTY_ARRAY = {}; // 빈 배열

    private Object[] array; // 요소를 담을 배열
    private int size; // 요소 개수

    // 생성자 1 (초기 공간 할당 X)
    public Stack() {
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    // 생성자 2 (초기 공간 할당 O)
    public Stack(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
    }

    private void resize() {
        int arrayCapacity = array.length;

        // 용량이 0 인 경우
        if (Arrays.equals(array, EMPTY_ARRAY)) {
            array = new Object[DEFAULT_CAPACITY];

            return;
        }

        // 용량이 가득 찬 경우
        if (size == arrayCapacity) {
            int newCapacity = arrayCapacity * 2;

            // copy
            array = Arrays.copyOf(array, newCapacity);

            return;
        }

        // 용량의 절반 미만으로 요소가 차지하고 있을 경우
        if (size < (arrayCapacity / 2)) {
            int newCapacity = arrayCapacity / 2;

            // copy
            array = Arrays.copyOf(array, Math.max(newCapacity, DEFAULT_CAPACITY));
        }
    }

    @Override
    public E push(E item) {
        // 용적이 꽉 차 있다면 용적을 재할당 해준다.
        if (size == array.length) {
            resize();
        }

        array[size] = item; // 마지막 위치에 요소 추가
        size++; // 사이즈 1 증가

        return item;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E pop() {
        // 만약 삭제할 요소가 없다면 Stack 이 비어있다는 의미이므로 예외를 발생시킨다.
        if (size == 0) {
            throw new EmptyStackException();
        }

        E element = (E) array[size - 1]; // 삭제될 요소를 반환하기 위한 임시 변수

        array[size - 1] = null; // 요소 삭제

        size--; // 사이즈 1 감소
        resize(); // 용적 재할당

        return element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() {
        // 만약 삭제할 요소가 없다면 Stack 이 비어있는 의미리므로 예외를 발생 시킨다.
        if (size == 0) {
            throw new EmptyStackException();
        }

        return (E) array[size - 1];
    }

    @Override
    public int search(Object value) {
        // value 가 null 일 때는 equals(null) 이 되어 null pointer exception 이 발생할 수 있으니,
        // == 로 null 값을 비교 해준다.
        if (value == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (array[i] == null) {
                    return size - i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                // 같은 객체를 찾았을 경우 size - i 값을 반환
                if (array[i].equals(value)) {
                    return size - i;
                }
            }
        }

        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        // 저장되어 있던 모든 요소를 null 처리 해준다.
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        resize();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // 새로운 객체 생성
        Stack<?> cloneStack = (Stack<?>) super.clone();

        // 새로운 객체의 배열도 생성해 주어야 함 (객체는 얕은 복사가 되기 때문)
        cloneStack.array = new Object[size];

        // 배열의 값을 복사함
        System.arraycopy(array, 0, cloneStack.array, 0, size);

        return cloneStack;
    }

    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            // copyOf(원본 배열, 복사할 길이, Class<? extends T[]> 타입)
            return (T[]) Arrays.copyOf(this.array, size, array.getClass());
        }
        // 원본배열, 원본배열 시작위치, 복사할 배열, 복사할 배열 시작위치, 복사할 요소수
        System.arraycopy(this.array, 0, array, 0, size);

        return array;
    }

    public void sort() {
        /*
         *  Comparator 를 넘겨주지 않는 경우 해당 객체의 Comparable 에 구현된 정렬 방식을 사용한다.
         *  만약 구현되어있지 않으면 cannot be cast to class java.lang.Comparable 에러가 발생한다.
         *  만약 구현되어있을 경우 null 로 파라미터를 넘기면 Arrays.sort()가 객체의 compareTo 메소드에 정의된 방식대로 정렬한다.
         */
        sort(null);
    }

    // Arrays.sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c) 사용
    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super E> c) {
        Arrays.sort((E[]) array, 0, size, c);
    }

    @Override
    public String toString() {
        if (array == null)
            return "null";

        int iMax = size - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(array[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }
}

package com.jake.datastructure.ds04_stack;

import com.jake.datastructure.ds01_arraylist.ArrayList;
import com.jake.datastructure.interface_form.StackInterface;

import java.util.EmptyStackException;

public class StackExtendArrayList<E> extends ArrayList<E> implements StackInterface<E> {
    // 초기 용적 할당 X
    public StackExtendArrayList() {
        super();
    }

    // 초기 용적 할당 O
    public StackExtendArrayList(int capacity) {
        super(capacity);
    }

    @Override
    public E push(E item) {
        addLast(item); // ArrayList 의 addLast()

        return item;
    }

    @Override
    public E pop() {
        int length = size();
        E element = remove(length - 1); // ArrayList 의 remove()

        return element;
    }

    @Override
    public E peek() {
        int length = size();
        if (length == 0) throw new EmptyStackException();
        E element = get(length - 1);

        return element;
    }

    @Override
    public int search(Object value) {
        int idx = lastIndexOf(value); // ArrayList 의 lastIndexOf()

        if (idx >= 0) {
            return size() - idx;
        }

        return -1;
    }
}

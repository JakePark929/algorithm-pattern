package com.jake.datastructure;

import com.jake.datastructure.ds04_stack.Stack;

public class ListStackTestMain {
    public static void main(String[] args) throws CloneNotSupportedException {
//        ArrayList<Integer> al = new ArrayList<>();
//        System.out.println(al.size());
//        al.add(10); // 10 추가
//        al.add(20); // 10 추가
//        al.add(30); // 10 추가
//        al.add(40); // 10 추가
//        al.add(50); // 10 추가
//        al.add(60); // 10 추가
//        al.add(70); // 10 추가
//        al.add(80); // 10 추가
//        al.add(90); // 10 추가
//        al.add(100); // 10 추가
//        al.add(110); // 10 추가
//        al.add(120); // 10 추가
//
//        System.out.println(al);
//
//        al.remove(11); // 10 추가
//        al.remove(10); // 10 추가
//        al.remove(9); // 10 추가
//        al.remove(8); // 10 추가
//
//        ArrayList<Integer> copyAl = al;
//        ArrayList<Integer> cloneAl = (ArrayList<Integer>) al.clone();
//
//        System.out.println(copyAl.add(20));
//        System.out.println(cloneAl.add(30));
//
//        System.out.println(al);
//        System.out.println(copyAl);
//        System.out.println(cloneAl);
//
//        System.out.println(al.size());
//        System.out.println(al.indexOf(30));
//        System.out.println(al.contains(30));
//        System.out.println(al.contains(120));
//        System.out.println(al.isEmpty());
//
//        al.clear();
//        System.out.println(al);
//
//        SingleLinkedList<Integer> sll = new SingleLinkedList<>();
//        sll.add(10);
//        sll.add(20);
//        sll.add(30);
//        System.out.println(sll);
//
//        sll.remove();
//        sll.remove((Integer) 30);
//        System.out.println(sll);
//
//        sll.set(0, 15);
//        System.out.println(sll.get(0));
//        System.out.println(sll.indexOf(15));
//
//        SingleLinkedList<Integer> copySll = sll;
//        SingleLinkedList<Integer> cloneSll = (SingleLinkedList<Integer>) sll.clone();
//
//        copySll.add(40);
//        cloneSll.add(50);
//        cloneSll.add(60);
//
//        System.out.println(sll);
//        System.out.println(copySll);
//        System.out.println(cloneSll);
//        System.out.println(sll.hashCode());
//        System.out.println(copySll.hashCode());
//        System.out.println(cloneSll.hashCode());
//        cloneSll.clear();
//        System.out.println(copySll);
//        System.out.println(cloneSll.size());
//        System.out.println(cloneSll.isEmpty());

//        DoubleLinkedList<Integer> dll = new DoubleLinkedList<>();
//        dll.add(10);
//        dll.add(30);
//        dll.add(20);
//        System.out.println(dll);
//
//        DoubleLinkedList<Integer> copyDll = dll;
//        DoubleLinkedList<Integer> cloneDll = (DoubleLinkedList<Integer>) dll.clone();
//
//        copyDll.add(10);
//        cloneDll.add(5);
//        System.out.println(dll);
//        System.out.println(copyDll);
//        System.out.println(cloneDll);
//
//        dll.remove();
//        dll.remove((Integer) 30);
//        System.out.println(dll);
//        System.out.println(dll.size());
//        System.out.println(dll.indexOf(40));
//        System.out.println(dll.lastIndexOf(40));
//        System.out.println(dll);
//        dll.add(1, 5);
//        System.out.println(dll);
//        dll.sort();
//        System.out.println(dll);

        Stack<Integer> stack = new Stack<>();

        stack.push(10);
        stack.push(30);
        stack.push(20);
        System.out.println(stack);

        stack.pop();
        System.out.println(stack);

        System.out.println(stack.peek());
        System.out.println(stack);

        Stack<Integer> copyStack = stack;
        Stack<Integer> cloneStack = (Stack<Integer>) stack.clone();

        copyStack.push(60);
        cloneStack.push(6);

        System.out.println(stack);
        System.out.println(copyStack);
        System.out.println(cloneStack);
    }
}

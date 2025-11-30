package deque;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList; // 导入 ArrayList 类

public class LinkedListDeque61B<T> implements Deque61B<T> {

    private class Node{
        T val = null;
        Node pre;
        Node next;
        private Node(){
            this.next = this;
            this.pre = this;
        }
        private Node(T value){
            this.val = value;
            this.next = this;
            this.pre = this;
        }
        private Node(T value, Node pre, Node next) {
            this.val = value;
            this.next = next;
            this.pre = pre;
        }
    }

    private Node sentinel;
    private int size = 0;

    public LinkedListDeque61B(){
        this.sentinel = new Node();
    }

    @Override
    public Iterator<T> iterator() {
        return new LLDIterator();
    }

    private class LLDIterator implements Iterator<T> {
        private Node cur;
        @Override
        public boolean hasNext() {
            return cur.next != sentinel;
        }

        @Override
        public T next() {
            T value = cur.val;
            cur = cur.next;
            return value;
        }
    }

    /* equals method*/
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof LinkedListDeque61B<?>)) return false;

        LinkedListDeque61B<?> other = (LinkedListDeque61B<?>) o;

        if (this.size() != other.size()) return false;

        for (int i = 0; i <= size; i++) {
            T a = this.get(i);
            Object b = other.get(i);
            if (!a.equals(b)) return false;
        }
        return true;
    }

    @Override
    public void addFirst(T x) {
        sentinel.next = new Node(x, sentinel, sentinel.next);
        sentinel.next.next.pre = sentinel.next;
        size++;
    }

    @Override
    public void addLast(T x) {
        sentinel.pre = new Node(x, sentinel.pre, sentinel);
        sentinel.pre.pre.next = sentinel.pre;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> lst = new ArrayList<>();
        if(size != 0) {
            Node cur = sentinel.next;
            while(cur != sentinel){
                lst.add(cur.val);
                cur = cur.next;
            }
        }
        return lst;
    }

    @Override
    public boolean isEmpty() {
        if (this.size != 0)
            return false;
        else
            return true;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty())
            return null;
        else {
            T result = sentinel.next.val;
            sentinel.next = sentinel.next.next;
            sentinel.next.pre = sentinel;
            return result;
        }
    }

    @Override
    public T removeLast() {
        if (this.isEmpty())
            return null;
        else {
            T result = sentinel.pre.val;
            sentinel.pre = sentinel.pre.pre;
            sentinel.pre.next = sentinel;
            return result;
        }
    }

    @Override
    public T get(int index) {
        if (this.isEmpty()) return null;
        else if (this.size() < index + 1 || index < 0) return null;
        else {
            Node cur = sentinel.next;
            while(index > 0){
                index--;
                cur = cur.next;
            }
            return cur.val;
        }
    }

    @Override
    public T getRecursive(int index) {
        if (this.isEmpty()) return null;
        else if (this.size() < index + 1 || index < 0) return null;
        else {
            return getRecursiveHelper(sentinel.next, index);
        }
    }

    private T getRecursiveHelper(Node node, int index){
        if (index == 0) return node.val;
        return getRecursiveHelper(node.next, index - 1);
    }

    @Override
    public String toString(){
        return this.toList().toString();
    }
}


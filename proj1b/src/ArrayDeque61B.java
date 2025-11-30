import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int front;
    private int back;
    private int size;

    public ArrayDeque61B(){
        items = (T[]) new Object[8];
        size = 0;
        front = 1;
        back = 0;
    }
    private void resize(){
        T[] newItems = (T[]) new Object[items.length * 2];
        for (int i = 0; i < size; i++){
            newItems[i] = items[Math.floorMod(front + i, items.length)];
        }
        items = newItems;
        front = 0;
        back = size - 1;
    }

    @Override
    public void addFirst(T x) {
        if(size == items.length) resize();
        front = Math.floorMod(front - 1, items.length);
        items[front] = x;
        size++;
    }

    @Override
    public void addLast(T x) {
        if(size == items.length) resize();
        back = Math.floorMod(back + 1, items.length);
        items[back] = x;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> lst = new ArrayList<>();
        for (int i = 0; i < size; i++){
            lst.add(items[Math.floorMod(front + i, items.length)]);
        }
        return lst;
    }

    @Override
    public boolean isEmpty() {
        if (size > 0)
            return false;
        else return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size < items.length / 3) sizeDown();
        T result = items[front];
        front = Math.floorMod(front + 1, items.length);
        size--;
        return result;
    }

    @Override
    public T removeLast() {
        if (size < items.length / 3) sizeDown();
        T result = items[back];
        back = Math.floorMod(back - 1, items.length);
        size--;
        return result;
    }

    private void sizeDown() {
        T[] newItems = (T[]) new Object[items.length / 2];
        for (int i = 0; i < size; i++){
            newItems[i] = items[Math.floorMod(front + i, items.length)];
        }
        items = newItems;
        front = 0;
        back = size - 1;
    }

    @Override
    public T get(int index) {
        if (size == 0 || index < 0 || index >= size)
            return null;
        else {
            return items[Math.floorMod(front + index, items.length)];
        }
    }

    @Override
    public T getRecursive(int index) {
        if (size == 0 || index < 0 || index >= size)
            return null;
        else {
            return getRecursiveHelper(index, front);
        }
    }

    private T getRecursiveHelper(int index, int front){
        if (index == 0) return items[front];
        else return getRecursiveHelper(index - 1, Math.floorMod(front + 1, items.length));
    }
}

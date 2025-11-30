package deque;
import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T>{
    private Comparator<T> comp;

    public MaxArrayDeque61B(Comparator<T> c) {
        super();
        this.comp = c;
    }

    public  T max() {
        return max(this.comp);
    }

    public T max(Comparator<T> c) {
        if(this.isEmpty()) return null;

        T maxElem = this.get(0);
        for (int i = 1; i < this.size(); i++) {
            T curr = this.get(i);
            if (c.compare(curr, maxElem) > 0) {
                maxElem = curr;
            }
        }
        return maxElem;
    }


}

import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int summ = 0;
        for (int i : L){
            summ += i;
        }
        return summ;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        List<Integer> newlist = new ArrayList<>();
        for (int i : L){
            if (i % 2 == 0) newlist.add(i);
        }
        return newlist;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        List<Integer> newlist = new ArrayList<>();
        for(int i : L1){
            if (L2.contains(i)) newlist.add(i);
        }
        return newlist;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        int summ = 0;
        for (String str : words) {
            for (char cc : str.toCharArray()) {
                if (cc == c) summ++;
            }
        }
        return summ;
    }
}

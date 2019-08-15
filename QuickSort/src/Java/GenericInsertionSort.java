package Java;

import java.util.List;

public class GenericInsertionSort<TType extends Comparable<TType>> {
    public void insertionSort(List<TType> a) {
        for (int i = 1; i < a.size(); ++i) {
            TType key = a.get(i);
            int j = i - 1;

            while (j >= 0 && a.get(j).compareTo(key) > 0) {
                a.set(j + 1, a.get(j));
                j = j - 1;
            }

            a.set(j + 1, key);
        }
    }
}

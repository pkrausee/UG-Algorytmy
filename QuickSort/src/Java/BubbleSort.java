package Java;

import java.util.List;

public class BubbleSort {
    public void bubbleSort(List<Integer> a, int low, int high) {
        for (int i = low; i <= high; i++)
            for (int j = low + 1; j <= high; j++)
                if (a.get(j - 1) > a.get(j))
                    swap(a, j - 1, j);
    }

    private void swap(List<Integer> a, int index1, int index2) {
        int temp = a.get(index1);
        a.set(index1, a.get(index2));
        a.set(index2, temp);
    }
}
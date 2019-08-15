package Java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GenericQuickSort<TType extends Comparable<TType>> {
    private void swap(List<TType> a, int index1, int index2) {
        TType temp = a.get(index1);
        a.set(index1, a.get(index2));
        a.set(index2, temp);
    }

    private int partition(List<TType> a, int low, int high) {
        TType pivot = a.get(high);
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (a.get(j).compareTo(pivot) <= 0) {
                i++;
                swap(a, i, j);
            }
        }

        swap(a, i + 1, high);

        return (i + 1);
    }

    private int randomizedPartition(List<TType> a, int low, int high) {
        Random rand = new Random();
        int pivot = rand.nextInt(high - low) + low;

        swap(a, pivot, high);

        return partition(a, low, high);
    }

    private int medianPartition(List<TType> a, int low, int high) {
        List<TType> b = new ArrayList<>();
        b.add(a.get(low));
        b.add(a.get(low + ((high - low) / 2)));
        b.add(a.get(high));
        Collections.sort(b);

        TType pivot = b.get(1);

        swap(a, a.indexOf(pivot), high);

        return partition(a, low, high);
    }

    public void quickSort(List<TType> a, int low, int high) {
        if (low < high) {
            int q = partition(a, low, high);
            quickSort(a, low, q - 1);
            quickSort(a, q + 1, high);
        }
    }

    public void randomizedQuickSort(List<TType> a, int low, int high) {
        if (low < high) {
            int q = randomizedPartition(a, low, high);
            randomizedQuickSort(a, low, q - 1);
            randomizedQuickSort(a, q + 1, high);
        }
    }

    public void medianQuickSort(List<TType> a, int low, int high) {
        if (low < high) {
            int q = medianPartition(a, low, high);
            medianQuickSort(a, low, q - 1);
            medianQuickSort(a, q + 1, high);
        }
    }

    private void quickSortBeforeInsertion(List<TType> a, int low, int high, int c) {
        if (low < high && (high - low + 1) > c) {
            int q = partition(a, low, high);
            quickSortWithBubble(a, low, q - 1, c);
            quickSortWithBubble(a, q + 1, high, c);
        }
    }

    public void quickSortWithInsertion(List<TType> a, int low, int high, int c) {
        quickSortBeforeInsertion(a, low, high, c);

        GenericInsertionSort<TType> i = new GenericInsertionSort<>();
        i.insertionSort(a);
    }

    public void quickSortWithBubble(List<TType> a, int low, int high, int c) {
        if (low < high) {
            if ((high - low + 1) < c) {
                GenericBubbleSort<TType> b = new GenericBubbleSort<>();
                b.bubbleSort(a, low, high);
            } else {
                int q = partition(a, low, high);
                quickSortWithBubble(a, low, q - 1, c);
                quickSortWithBubble(a, q + 1, high, c);
            }
        }
    }
}

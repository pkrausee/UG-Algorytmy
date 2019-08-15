package Test;

import Java.Generator;
import Java.GenericQuickSort;
import Java.QuickSort;

import java.util.ArrayList;
import java.util.List;

public class QuickSortTests {
    public static void main(String[] args) {
        Generator generator = new Generator();

        QuickSort quickSort = new QuickSort();
        GenericQuickSort<Integer>  genericQuickSort = new GenericQuickSort<> ();

        List<Integer> list = new ArrayList<>();

        generator.generateRandomValues(list, 10);

        List<Integer> copyList = new ArrayList<>(list);

        for (var a: list ){
            System.out.print(a + " ");
        }

        quickSort.quickSort(list, 0, list.size() - 1);
        genericQuickSort.quickSort(copyList, 0, copyList.size() - 1);

        System.out.println("\n-------------------");

        for (var a: list ){
            System.out.print(a + " ");
        }

        System.out.println("\n-------------------");

        for (var a: copyList ){
            System.out.print(a + " ");
        }
    }
}

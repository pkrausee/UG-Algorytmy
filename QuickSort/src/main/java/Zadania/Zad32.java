package Zadania;

import Java.NumberGenerator;
import Java.QuickSort;

import java.util.ArrayList;
import java.util.List;

public class Zad32
{
    public static void main(String[] args)
    {
        List<Integer> numbers = new ArrayList<Integer>();

        System.out.println("Porowanie czasu w milisekundach dla: ");
        String format = "| %1$-14s| %2$-27s| %3$-27s| %4$-21s|\n";
        System.out.format(format, "Rozmiar listy", "Lista posortowana rosnaco", "Lista posortowana malejaco", "Lista nieposortowana");

        long sTime, eTime, gen1Time, gen2Time, randTime;

        int amount = 5000;
        for (int i = 500; i <= amount; i += 500)
        {
            NumberGenerator.generateAscendingValues(numbers, i);
            sTime = System.currentTimeMillis();
            QuickSort.quickSort(numbers, 0, numbers.size() - 1);
            eTime = System.currentTimeMillis();
            gen1Time = eTime - sTime;

            NumberGenerator.generateDescendingValues(numbers, i);
            sTime = System.currentTimeMillis();
            QuickSort.quickSort(numbers, 0, numbers.size() - 1);
            eTime = System.currentTimeMillis();
            gen2Time = eTime - sTime;

            NumberGenerator.generateRandomValues(numbers, i);
            sTime = System.currentTimeMillis();
            QuickSort.quickSort(numbers, 0, numbers.size() - 1);
            eTime = System.currentTimeMillis();
            randTime = eTime - sTime;

            System.out.format(format, i, gen1Time, gen2Time, randTime);
        }

    }
}
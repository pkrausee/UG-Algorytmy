package Zadania;

import Java.NumberGenerator;
import Java.QuickSort;

import java.util.ArrayList;
import java.util.List;

public class Zad33
{
    public static void main(String[] args)
    {
        List<Integer> numbers = new ArrayList<Integer>();

        System.out.println("Porowanie czasu w milisekundach dla: ");
        String format = "| %1$-14s| %2$-10s | %3$-10s | %4$-10s | %5$-10s | %6$-10s | %7$-10s |\n";
        String format2 = "| %1$-13s | %2$-36s | %3$-36s |\n";
        System.out.format(format2, "", "DANE NIEKORZYSTNE", "DANE LOSOWE");
        System.out.format(format, "Rozmiar listy", "algorytm 1", "algorytm 2", "algorytm 3", "algorytm 1", "algorytm 2", "algorytm 3");

        long sTime, eTime, alg1Time, alg2Time, alg3Time;
        long alg1TimeR = 0;
        long alg2TimeR = 0;
        long alg3TimeR = 0;

        int amount = 5000;
        for (int i = 500; i <= amount; i += 500)
        {
            NumberGenerator.generateAscendingValues(numbers, i);
            sTime = System.currentTimeMillis();
            QuickSort.quickSort(numbers, 0, numbers.size() - 1);
            eTime = System.currentTimeMillis();
            alg1Time = eTime - sTime;


            NumberGenerator.generateAscendingValues(numbers, i);
            sTime = System.currentTimeMillis();
            QuickSort.randomizedQuickSort(numbers, 0, numbers.size() - 1);
            eTime = System.currentTimeMillis();
            alg2Time = eTime - sTime;


            NumberGenerator.generateAscendingValues(numbers, i);
            sTime = System.currentTimeMillis();
            QuickSort.medianQuickSort(numbers, 0, numbers.size() - 1);
            eTime = System.currentTimeMillis();
            alg3Time = eTime - sTime;

            for (int j = 0; j < 10; j++)
            {
                NumberGenerator.generateRandomValues(numbers, i);
                sTime = System.currentTimeMillis();
                QuickSort.quickSort(numbers, 0, numbers.size() - 1);
                eTime = System.currentTimeMillis();
                alg1TimeR += eTime - sTime;


                NumberGenerator.generateRandomValues(numbers, i);
                sTime = System.currentTimeMillis();
                QuickSort.randomizedQuickSort(numbers, 0, numbers.size() - 1);
                eTime = System.currentTimeMillis();
                alg2TimeR += eTime - sTime;


                NumberGenerator.generateRandomValues(numbers, i);
                sTime = System.currentTimeMillis();
                QuickSort.medianQuickSort(numbers, 0, numbers.size() - 1);
                eTime = System.currentTimeMillis();
                alg3TimeR += eTime - sTime;
            }

            System.out.format(format, i, alg1Time, alg2Time, alg3Time, alg1TimeR / 10, alg2TimeR / 10, alg3TimeR / 10);
        }
    }
}
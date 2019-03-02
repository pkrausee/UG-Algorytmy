import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class Quick
{
    private List<Integer> a = new ArrayList<Integer>();

    private void swap(int index1, int index2)
    {
        int temp = a.get(index1);
        a.set(index1, a.get(index2));
        a.set(index2, temp);
    }

    private int partition(int low, int high)
    {
        int pivot = a.get(high); // element wyzn podział
        int i = low - 1;

        for (int j = low; j <= high - 1; j++)
        {
            if (a.get(j) <= pivot)
            {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, high);

        return (i + 1);
    }

    private int randomizedPartition(int low, int high)
    {
        Random rand = new Random();
        int pivot = rand.nextInt(high - low) + low;

        swap(pivot, high);

        return partition(low, high);
    }

    private int medianPartition(int low, int high)
    {
        List<Integer> b = new ArrayList<Integer>();
        b.add(a.get(low));
        b.add(a.get(low + ((high - low)/2)));
        b.add(a.get(high));
        Collections.sort(b);

        int pivot = b.get(1);

        swap(pivot, high);

        return partition(low, high);
    }

    public void quicksort(int low, int high)
    {
        if (low < high)
        {
            int q = partition(low, high);
            quicksort(low, q - 1);
            quicksort(q + 1, high);
        }
    }

    public void randomizedQuicksort(int low, int high)
    {
        if(low < high)
        {
            int q = randomizedPartition(low, high);
            randomizedQuicksort(low, q - 1);
            randomizedQuicksort(q + 1, high);
        }
    }

    public void medianQuicksort(int low, int high)
    {
        if(low < high)
        {
            int q = medianPartition(low, high);
            medianQuicksort(low, q - 1);
            medianQuicksort(q + 1, high);
        }
    }

    public void show()
    {
        for(int e:a)
        {
            System.out.print(e + " ");
        }
        System.out.print("\n");
    }

    public void generateValues(int amount)
    {
        a.clear();
        for(int i = 1; i <= amount; i++)
            a.add(i);
    }

    public void generateRandomValues(int amount)
    {
        a.clear();
        Random rand = new Random();
        for(int i = 1; i <= amount; i++)
            a.add(rand.nextInt(amount));
    }

    public int getListSize()
    {
        return a.size();
    }
}

class Main
{
    public static void main(String args[])
    {
        Quick q = new Quick();

        System.out.println("Porowanie czasu w milisekundach dla: ");
        String format = "| %1$-14s| %2$-10s | %3$-10s | %4$-10s | %5$-10s | %6$-10s | %7$-10s |\n";
        String format2 = "| %1$-13s | %2$-36s | %3$-36s |\n";
        System.out.format(format2, "", "DANE NIEKORZYSTNE", "DANE LOSOWE");
        System.out.format(format, "Rozmiar listy", "algorytm 1", "algorytm 2", "algorytm 3", "algorytm 1", "algorytm 2", "algorytm 3");

        long sTime, eTime, alg1Time, alg2Time, alg3Time;
        long alg1TimeR = 0;
        long alg2TimeR = 0;
        long alg3TimeR = 0;

        int amount = 4000;
        for(int i = 500; i <= amount; i+=500)
        {
            q.generateValues(i);
            sTime = System.currentTimeMillis();
            q.quicksort(0, q.getListSize() - 1);
            eTime = System.currentTimeMillis();
            alg1Time = eTime - sTime;


            q.generateValues(i);
            sTime = System.currentTimeMillis();
            q.randomizedQuicksort(0, q.getListSize() - 1);
            eTime = System.currentTimeMillis();
            alg2Time = eTime - sTime;


            q.generateValues(i);
            sTime = System.currentTimeMillis();
            q.medianQuicksort(0, q.getListSize() - 1);
            eTime = System.currentTimeMillis();
            alg3Time = eTime - sTime;

            for(int j = 0; j < 10; j++)
            {
                q.generateRandomValues(i);
                sTime = System.currentTimeMillis();
                q.quicksort(0, q.getListSize() - 1);
                eTime = System.currentTimeMillis();
                alg1TimeR += eTime - sTime;


                q.generateRandomValues(i);
                sTime = System.currentTimeMillis();
                q.randomizedQuicksort(0, q.getListSize() - 1);
                eTime = System.currentTimeMillis();
                alg2TimeR += eTime - sTime;


                q.generateRandomValues(i);
                sTime = System.currentTimeMillis();
                q.medianQuicksort(0, q.getListSize() - 1);
                eTime = System.currentTimeMillis();
                alg3TimeR += eTime - sTime;
            }

            System.out.format(format, i, alg1Time, alg2Time, alg3Time, alg1TimeR / 10, alg2TimeR / 10, alg3TimeR / 10);
        }

    }
}
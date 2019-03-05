import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class Quick
{
    private void swap(List<Integer> a, int index1, int index2)
    {
        int temp = a.get(index1);
        a.set(index1, a.get(index2));
        a.set(index2, temp);
    }

    private int partition(List<Integer> a, int low, int high)
    {
        int pivot = a.get(high); // element wyzn podzial
        int i = low - 1;

        for (int j = low; j <= high - 1; j++)
        {
            if (a.get(j) <= pivot)
            {
                i++;
                swap(a, i, j);
            }
        }

        swap(a, i + 1, high);

        return (i + 1);
    }

    private int randomizedPartition(List<Integer> a, int low, int high)
    {
        Random rand = new Random();
        int pivot = rand.nextInt(high - low) + low;

        swap(a, pivot, high);

        return partition(a, low, high);
    }

    private int medianPartition(List<Integer> a, int low, int high)
    {
        List<Integer> b = new ArrayList<Integer>();
        b.add(a.get(low));
        b.add(a.get(low + ((high - low)/2)));
        b.add(a.get(high));
        Collections.sort(b);

        int pivot = b.get(1);

        swap(a, pivot, high);

        return partition(a, low, high);
    }

    public void quicksort(List<Integer> a, int low, int high)
    {
        if (low < high)
        {
            int q = partition(a, low, high);
            quicksort(a, low, q - 1);
            quicksort(a, q + 1, high);
        }
    }

    public void randomizedQuicksort(List<Integer> a, int low, int high)
    {
        if(low < high)
        {
            int q = randomizedPartition(a, low, high);
            randomizedQuicksort(a, low, q - 1);
            randomizedQuicksort(a, q + 1, high);
        }
    }

    public void medianQuicksort(List<Integer> a, int low, int high)
    {
        if(low < high)
        {
            int q = medianPartition(a, low, high);
            medianQuicksort(a, low, q - 1);
            medianQuicksort(a, q + 1, high);
        }
    }

    private void quicksortBeforeInsertion(List<Integer> a, int low, int high, int c)
    {
        if(low < high && (high - low + 1) > c)
        {
            int q = partition(a, low, high);
            quicksortWithBubble(a, low, q - 1, c);
            quicksortWithBubble(a,q + 1, high, c);
        }
    }

    public void quicksortWithInsertion(List<Integer> a, int low, int high, int c)
    {
        quicksortBeforeInsertion(a, low, high, c);

        Insertion i = new Insertion();
        i.insertionSort(a);
    }

    public void quicksortWithBubble(List<Integer> a, int low, int high, int c)
    {
        if (low < high)
        {
            if((high - low + 1) < c)
            {
                Bubble b = new Bubble();
                b.bubbleSort(a, low, high);
            }
            else
            {
                int q = partition(a, low, high);
                quicksortWithBubble(a, low, q - 1, c);
                quicksortWithBubble(a,q + 1, high, c);
            }
        }
    }
}


class Insertion
{
    public void insertionSort(List<Integer> a)
    {
        for (int i=1; i<a.size(); ++i)
        {
            int key = a.get(i);
            int j = i-1;

            while (j>=0 && a.get(j) > key)
            {
                a.set(j+1, a.get(j));
                j = j-1;
            }

            a.set(j+1, key);
        }
    }
}


class Bubble
{
    private void swap(List<Integer> a, int index1, int index2)
    {
        int temp = a.get(index1);
        a.set(index1, a.get(index2));
        a.set(index2, temp);
    }

    public void bubbleSort(List<Integer> a, int low, int high)
    {
        for(int i = low; i <= high; i++)
            for(int j = low + 1; j <= high; j++)
                if(a.get(j-1) > a.get(j))
                    swap(a,j-1, j);
    }
}


class Generator
{
    public void generateValues1(List<Integer> a, int amount)
    {
        a.clear();
        for(int i = 1; i <= amount; i++)
            a.add(i);
    }

    public void generateValues2(List<Integer> a, int amount)
    {
        a.clear();
        for(int i = amount; i >= 1; i--)
            a.add(i);
    }

    public void generateRandomValues(List<Integer> a, int amount)
    {
        a.clear();
        Random rand = new Random();
        for(int i = 1; i <= amount; i++)
            a.add(rand.nextInt(amount));
    }

    public void showList(List<Integer> a)
    {
        for(int b: a)
        {
            System.out.print(b + " ");
        }
        System.out.print("\n");
    }
}
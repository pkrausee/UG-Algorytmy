import java.util.ArrayList;
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

    public void quicksort(int low, int high)
    {
        if (low < high)
        {
            int q = partition(low, high);
            quicksort(low, q - 1);
            quicksort(q + 1, high);
        }
    }

    public void quicksortWithBubble(int low, int high, int c)
    {
        if (low < high)
        {
            if((high - low + 1) < c)
            {
                bubbleSort(low, high);
            }
            else
            {
                int q = partition(low, high);
                quicksortWithBubble(low, q - 1, c);
                quicksortWithBubble(q + 1, high, c);
            }
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

    public void generateValues1(int amount)
    {
        a.clear();
        for(int i = 1; i <= amount; i++)
            a.add(i);
    }

    public void generateValues2(int amount)
    {
        a.clear();
        for(int i = amount; i > 0; i--)
            a.add(i);
    }

    public void generateRandomValues(int amount)
    {
        a.clear();
        Random rand = new Random();
        for(int i = 0; i < amount; i++)
            a.add(rand.nextInt(amount) + 1);
    }

    private void bubbleSort(int low, int high)
    {
        for(int i = low; i <= high; i++)
            for(int j = low + 1; j <= high; j++)
                if(a.get(j-1) > a.get(j))
                    swap(j-1, j);
    }

    public int getListSize()
    {
        return a.size();
    }
}

class Main
{
    public static void main(String args[]) {
        Quick q = new Quick();

        System.out.println("Porowanie czasu w milisekundach dla: ");
        String format = "| %1$-14s| %2$-10s | %3$-10s | %4$-10s | %5$-10s |\n";
        String format2 = "| %1$-13s | %2$-23s | %3$-23s |\n";
        System.out.format(format2, "", "DANE NIEKORZYSTNE", "DANE LOSOWE");
        System.out.format(format, "Rozmiar listy", "algorytm 1", "algorytm 2", "algorytm 1", "algorytm 2");

        long sTime, eTime, alg1Time, alg2Time;
        long alg1TimeR = 0;
        long alg2TimeR = 0;

        int amount = 10000;
        for(int i = 500; i <= amount; i+=500)
        {
            q.generateValues2(i);
            sTime = System.currentTimeMillis();
            q.quicksort(0, q.getListSize() - 1);
            eTime = System.currentTimeMillis();
            alg1Time = eTime - sTime;

            q.generateValues2(i);
            sTime = System.currentTimeMillis();
            q.quicksortWithBubble(0, q.getListSize() - 1, 5);
            eTime = System.currentTimeMillis();
            alg2Time = eTime - sTime;

            for(int j = 0; j < 10; j++)
            {
                q.generateRandomValues(i);
                sTime = System.currentTimeMillis();
                q.quicksort(0, q.getListSize() - 1);
                eTime = System.currentTimeMillis();
                alg1TimeR += eTime - sTime;

                q.generateRandomValues(i);
                sTime = System.currentTimeMillis();
                q.quicksortWithBubble(0, q.getListSize() - 1, 5);
                eTime = System.currentTimeMillis();
                alg2TimeR += eTime - sTime;
            }

            System.out.format(format, i, alg1Time, alg2Time, alg1TimeR / 10, alg2TimeR / 10);
        }
    }
}
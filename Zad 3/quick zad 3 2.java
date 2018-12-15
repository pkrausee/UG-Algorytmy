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
        clearList();
        for(int i = 1; i <= amount; i++)
            a.add(i);
    }

    public void generateValues2(int amount)
    {
        clearList();
        for(int i = amount; i > 0; i--)
            a.add(i);
    }

    public void generateRandomValues(int amount)
    {
        clearList();
        Random rand = new Random();
        for(int i = 0; i < amount; i++)
            a.add(rand.nextInt(amount) + 1);
    }

    public int getListSize()
    {
        return a.size();
    }

    private void clearList()
    {
        a.clear();
    }
}

class Main
{
    public static void main(String args[])
    {
        Quick q = new Quick();

        System.out.println("Porowanie czasu w milisekundach dla: ");
        String format = "| %1$-14s| %2$-27s| %3$-27s| %4$-21s|\n";
        System.out.format(format, "Rozmiar listy", "Lista posortowana rosnaco", "Lista posortowana malejaco", "Lista nieposortowana");

        long sTime, eTime, gen1Time, gen2Time, randTime;

        int amount = 2000;
        for(int i = 500; i <= amount; i+=500)
        {
            //Pomiar dla listy posortowanej rosnaco
            q.generateValues1(i);

            sTime = System.currentTimeMillis();
            q.quicksort(0, q.getListSize() - 1);
            eTime = System.currentTimeMillis();
            gen1Time = eTime - sTime;


            //Pomiar dla listy posortowanej malejaco
            q.generateValues2(i);

            sTime = System.currentTimeMillis();
            q.quicksort(0, q.getListSize() - 1);
            eTime = System.currentTimeMillis();
            gen2Time = eTime - sTime;


            //Pomiar dla listy nieposortowanej (losowe wartosci)
            q.generateRandomValues(i);

            sTime = System.currentTimeMillis();
            q.quicksort(0, q.getListSize() - 1);
            eTime = System.currentTimeMillis();
            randTime = eTime - sTime;

            System.out.format(format, i, gen1Time, gen2Time, randTime);
        }

    }
}
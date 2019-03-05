import java.util.ArrayList;


class Quick
{
    private void swap(ArrayList<Record> a, int index1, int index2)
    {
        Record temp = a.get(index1);
        a.set(index1, a.get(index2));
        a.set(index2, temp);
    }

    private int partition(ArrayList<Record> a, int low, int high)
    {
        String pivot = a.get(high).getSurname(); // element wyzn podzial
        int i = low - 1;

        for (int j = low; j <= high - 1; j++)
        {
            if (a.get(j).getSurname().compareTo(pivot) <= 0)
            {
                i++;
                swap(a, i, j);
            }
        }

        swap(a, i + 1, high);

        return (i + 1);
    }

    public void quickSort(ArrayList<Record> a, int low, int high)
    {
        if (low < high)
        {
            int q = partition(a, low, high);
            quickSort(a, low, q - 1);
            quickSort(a, q + 1, high);
        }
    }
}


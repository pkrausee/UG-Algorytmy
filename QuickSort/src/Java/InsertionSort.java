package Java;

import java.util.List;

public class InsertionSort
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
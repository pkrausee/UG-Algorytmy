package Java;

import java.util.List;

public abstract class BubbleSort
{
    public static <TType extends Comparable<TType>> void bubbleSort(List<TType> a, int low, int high)
    {
        for (int i = low; i <= high; i++)
        {
            for (int j = low + 1; j <= high; j++)
            {
                if (a.get(j - 1).compareTo(a.get(j)) > 0)
                {
                    swap(a, j - 1, j);
                }
            }
        }
    }

    private static <TType extends Comparable<TType>> void  swap(List<TType> a, int index1, int index2)
    {
        TType temp = a.get(index1);
        a.set(index1, a.get(index2));
        a.set(index2, temp);
    }
}

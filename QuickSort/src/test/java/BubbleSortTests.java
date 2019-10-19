import Java.BubbleSort;
import org.hamcrest.CoreMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertThat;

public class BubbleSortTests
{
    @org.junit.Test
    public void testSortingRandomNumbers()
    {
        List<Integer> numbers = new ArrayList<Integer>();
        Collections.addAll(numbers, 5, 3, 2, 1, 2, 6, 3, 2, 8, 1, 2, 3);

        BubbleSort.bubbleSort(numbers, 0, numbers.size() - 1);

        assertThat(numbers, CoreMatchers.hasItems(1, 1, 2, 2, 2 , 2, 3, 3, 3, 5, 6, 8));
    }

    @org.junit.Test
    public void testSortingAscendingNumbers()
    {
        List<Integer> numbers = new ArrayList<Integer>();
        Collections.addAll(numbers, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ,12);

        BubbleSort.bubbleSort(numbers, 0, numbers.size() - 1);

        assertThat(numbers, CoreMatchers.hasItems(1, 1, 2, 2, 2 , 2, 3, 3, 3, 5, 6, 8));
    }

    @org.junit.Test
    public void testSortingDescendingNumbers()
    {
        List<Integer> numbers = new ArrayList<Integer>();
        Collections.addAll(numbers, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1);

        BubbleSort.bubbleSort(numbers, 0, numbers.size() - 1);

        assertThat(numbers, CoreMatchers.hasItems(1, 1, 2, 2, 2 , 2, 3, 3, 3, 5, 6, 8));
    }

    @org.junit.Test
    public void testSortingConstantNumbers()
    {
        List<Integer> numbers = new ArrayList<Integer>();
        Collections.addAll(numbers, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

        BubbleSort.bubbleSort(numbers, 0, numbers.size() - 1);

        assertThat(numbers, CoreMatchers.hasItems(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    }
}

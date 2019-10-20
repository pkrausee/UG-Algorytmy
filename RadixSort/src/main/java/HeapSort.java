import java.util.List;


class HeapSort
{
    private void swap(List<Record> tab, int a, int b)
    {
        Record temp = tab.get(a);
        tab.set(a, tab.get(b));
        tab.set(b, temp);
    }

    private void heapify(List<Record> tab, int node, int leftLimit, int rightLimit)
    {
        int leftSon;
        int rightSon;
        int largerElem;

        do
        {
            leftSon = 2 * node - leftLimit + 1;
            rightSon = 2 * node - leftLimit + 2;

            if (leftSon <= rightLimit && tab.get(leftSon).getSurname().compareTo(tab.get(node).getSurname()) > 0)
            {
                largerElem = leftSon;
            }
            else
            {
                largerElem = node;
            }
            if (rightSon <= rightLimit && tab.get(rightSon).getSurname().compareTo(tab.get(largerElem).getSurname()) > 0)
            {
                largerElem = rightSon;
            }

            if (largerElem != node)
            {
                swap(tab, node, largerElem);
                node = largerElem;
            }

        } while (node == rightSon || node == leftSon);
    }

    private void buildHeap(List<Record> tab, int leftLimit, int rightLimit)
    {
        for (int i = rightLimit / 2; i >= leftLimit; i--)
        {
            heapify(tab, i, leftLimit, rightLimit);
        }
    }

    public void heapSort(List<Record> tab, int leftLimit, int rightLimit)
    {
        buildHeap(tab, leftLimit, rightLimit);

        for (int i = rightLimit; i >= leftLimit + 1; i--)
        {
            swap(tab, rightLimit, leftLimit);

            rightLimit--;

            heapify(tab, leftLimit, leftLimit, rightLimit);
        }
    }
}

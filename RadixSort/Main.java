import java.util.ArrayList;


class Main
{
    public static void main(String args[])
    {
        Heap h = new Heap();
        Quick q = new Quick();
        Radix r = new Radix();
        Utilities u = new Utilities();
        FileSupport f = new FileSupport();

        ArrayList<Record> a = new ArrayList<>();
        StringBuffer sB = new StringBuffer("Pomiar czasu:\n");
        long startTime, endTime;

        f.getRecordsFromFile(a);

        sB.append("RadixSort: ");
        startTime = System.nanoTime();
        r.radixSort(a, u.maxLength(a));
        endTime = System.nanoTime();
        sB.append((endTime - startTime) / 1000000);
        sB.append(" milisec\n");

        f.getRecordsFromFile(a);

        sB.append("QuickSort: ");
        startTime = System.nanoTime();
        q.quickSort(a, 0, a.size() - 1);
        endTime = System.nanoTime();
        sB.append((endTime - startTime) / 1000000);
        sB.append(" milisec\n");

        f.getRecordsFromFile(a);

        sB.append("HeapSort: ");
        startTime = System.nanoTime();
        h.heapSort(a, 0, a.size() - 1);
        endTime = System.nanoTime();
        sB.append((endTime - startTime) / 1000000);
        sB.append(" milisec\n");

        f.saveRecordsToFile(a, "posortowane.txt");

        System.out.println(sB.toString());

    }
}
import java.util.ArrayList;
import java.util.List;

public class HeapSort
{
    private List<Integer> tab = new ArrayList<Integer>();
    private int leftLimit;
    private int rightLimit;

    private void swap(int a, int b) {
        int temp = tab.get(a);
        tab.set(a, tab.get(b));
        tab.set(b, temp);
    }

    private void heapify(int node) {
        int leftSon;
        int rightSon;
        int largerElem;

        do {

            leftSon = 2 * node - leftLimit + 1;
            rightSon = 2 * node - leftLimit + 2;

            if (leftSon <= rightLimit && tab.get(leftSon) > tab.get(node))
                largerElem = leftSon;
            else
                largerElem = node;
            if (rightSon <= rightLimit && tab.get(rightSon) > tab.get(largerElem))
                largerElem = rightSon;

            if (largerElem != node) {
                swap(node, largerElem);
                node = largerElem;
            }

        } while (node == rightSon || node == leftSon);

    }

    private void buildHeap() {
        for (int i = rightLimit / 2; i >= leftLimit; i--)
            heapify(i);
    }

    public void heapSort() {
        buildHeap();

        for (int i = rightLimit; i >= leftLimit + 1; i--) {
            swap(rightLimit, leftLimit);

            rightLimit--;

            heapify(leftLimit);
        }
    }

    public void setLimits()
    {
        leftLimit = 0;
        rightLimit = tab.size() - 1;
    }

    public void setLimitsFromTab()
    {
        leftLimit = tab.get(0);
        rightLimit = tab.get(1);
        tab.remove(0);
        tab.remove(0);
    }

    public void showTab() {
        for (int x : tab) {
            System.out.print(x + " ");
        }
    }

    public List<Integer> getTab() {
        return tab;
    }

    public void setTab(List<Integer> tab) {
        this.tab = tab;
    }

    public void addTabEl(int el) {
        tab.add(el);
    }

    public int getLeftLimit() {
        return leftLimit;
    }

    public void setLeftLimit(int leftLimit) {
        this.leftLimit = leftLimit;
    }

    public int getRightLimit() {
        return rightLimit;
    }

    public void setRightLimit(int rightLimit) {
        this.rightLimit = rightLimit;
    }
}

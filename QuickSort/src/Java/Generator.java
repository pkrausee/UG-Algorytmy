package Java;

import java.util.List;
import java.util.Random;

public class Generator {
    public void generateAscendingValues(List<Integer> a, int amount) {
        a.clear();
        for (int i = 1; i <= amount; i++)
            a.add(i);
    }

    public void generateDescendingValues(List<Integer> a, int amount) {
        a.clear();
        for (int i = amount; i >= 1; i--)
            a.add(i);
    }

    public void generateRandomValues(List<Integer> a, int amount) {
        a.clear();
        Random rand = new Random();
        for (int i = 1; i <= amount; i++)
            a.add(rand.nextInt(amount));
    }
}
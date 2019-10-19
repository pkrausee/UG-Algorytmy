import java.util.Map;


abstract class Frequency {
    public static void getFreq(Map<Integer, Integer> freq, byte[] file) {
        for (byte b : file) {
            Integer i = (int) b;

            if (freq.containsKey(i))
                freq.put(i, freq.get(i) + 1);
            else
                freq.put(i, 1);
        }
    }

    public static void printFreq(Map<Integer, Integer> freq) {
        for (Map.Entry<Integer, Integer> entry : freq.entrySet())
            System.out.println(entry.getKey() + " : " + entry.getValue());
    }
}
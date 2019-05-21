import java.util.ArrayList;


class NaiveAlg {
    public ArrayList<Integer> findPattern(String text, String pattern) {

        ArrayList<Integer> matchingPos = new ArrayList<>();

        for (int i = 0; i <= text.length() - pattern.length(); i++) {

            int j = 0;
            while (j < pattern.length() && (text.charAt(i + j) == pattern.charAt(j) || text.charAt(i + j) == '?'))
                j++;

            if (j == pattern.length())
                matchingPos.add(i);

        }

        return matchingPos;

    }
}
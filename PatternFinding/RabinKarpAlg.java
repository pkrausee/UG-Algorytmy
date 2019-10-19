import java.util.ArrayList;


class RabinKarpAlg {
    public ArrayList<Integer> findPattern(String text, String pattern, int d, int q) {

        /*

            d - alphabet size
            q - prime number

        */

        ArrayList<Integer> matchingPos = new ArrayList<>();

        int hash = 1;
        for (int i = 0; i < pattern.length() - 1; i++)
            hash = (hash * d) % q;

        int patternHash = 0;
        int currTextHash = 0;

        for (int i = 0; i < pattern.length(); i++) {
            patternHash = (d * patternHash + pattern.charAt(i)) % q;
            currTextHash = (d * currTextHash + text.charAt(i)) % q;
        }

        for (int i = 0; i < text.length() - pattern.length(); i++) {

            if (patternHash == currTextHash) {

                int j = 0;
                while (j < pattern.length() && (text.charAt(i + j) == pattern.charAt(j) || text.charAt(i + j) == '?'))
                    j++;

                if (j == pattern.length())
                    matchingPos.add(i);

            }

            if (i < text.length() - pattern.length()) {

                int newHash = (text.charAt(i) * hash) % q;

                if (currTextHash < newHash)
                    currTextHash = currTextHash + q;

                currTextHash = (d * (currTextHash - newHash) + text.charAt(i + pattern.length())) % q;

            }

        }

        return matchingPos;

    }
}
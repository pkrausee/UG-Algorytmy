import java.util.ArrayList;


class KnuthMorrisPrattAlg {
    private int[] getPrefixTable(String pattern) {

        int[] prefixTable = new int[pattern.length()];

        prefixTable[0] = 0;

        int i = 1;
        int prefixLen = 0;
        while (i < pattern.length()) {

            if (pattern.charAt(i) == pattern.charAt(prefixLen)) {

                prefixLen++;
                prefixTable[i] = prefixLen;
                i++;

            } else {

                if (prefixLen != 0) {
                    prefixLen = prefixTable[prefixLen - 1];
                } else {
                    prefixTable[i] = prefixLen;
                    i++;
                }

            }

        }

        return prefixTable;

    }

    public ArrayList<Integer> findPattern(String text, String pattern) {

        ArrayList<Integer> matchingPos = new ArrayList<>();

        int[] prefixTable = getPrefixTable(pattern);

        int textIter = 0;
        int patternIter = 0;

        while (textIter < text.length()) {

            if (pattern.charAt(patternIter) == text.charAt(textIter) || text.charAt(textIter) == '?') {
                patternIter++;
                textIter++;

                if (patternIter == pattern.length()) {
                    matchingPos.add(textIter - patternIter);
                    patternIter = prefixTable[patternIter - 1];
                }

            } else if (textIter < text.length() && pattern.charAt(patternIter) != text.charAt(textIter)) {

                if (patternIter != 0)
                    patternIter = prefixTable[patternIter - 1];
                else
                    textIter = textIter + 1;

            }

        }

        return matchingPos;
    }
}
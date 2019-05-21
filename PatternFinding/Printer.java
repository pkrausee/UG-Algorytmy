import java.util.ArrayList;


class Printer {
    public void print(String text, String pattern, ArrayList<Integer> patternMatches) {

        if(patternMatches.size() > 0) {

            int i = 0;

            for (int j = 0; j < text.length(); j++) {

                if (j >= patternMatches.get(i) && j <= patternMatches.get(i) + pattern.length() - 1)
                    System.out.print((char) 27 + "[34m" + text.charAt(j));
                else
                    System.out.print((char) 27 + "[39m" + text.charAt(j));

                if (j > patternMatches.get(i) + pattern.length() - 1 && i < patternMatches.size() - 1)
                    i++;

            }

            System.out.println();

        }

    }
}
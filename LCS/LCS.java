import java.util.ArrayList;
import java.util.List;

abstract class LCS {
    public static int length (String x, String y) {
        return getLCSvalues(x, y)[x.length()][y.length()];
    }

    public static void print (String x, String y) {
        printLCS(x, y, getLCSvalues(x, y), x.length(), y.length());

        System.out.println();
    }

    public static void printAll(String x, String y) {
        List<String> temp = printAllLCS(x, y, getLCSvalues(x, y), x.length(), y.length());

        for(String lcs : temp)
            System.out.print(lcs + " ");

        System.out.println();
    }

    private static int[][] getLCSvalues(String x, String y) {
        int xLen = x.length();
        int yLen = y.length();

        int[][] values = new int[xLen + 1][yLen + 1];

        for (int i = 1; i <= xLen; i++) {
            for (int j = 1; j <= yLen; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1))
                    values[i][j] = values[i - 1][j - 1] + 1;
                else {
                    if (values[i - 1][j] >= values[i][j - 1])
                        values[i][j] = values[i - 1][j];
                    else
                        values[i][j] = values[i][j - 1];
                }
            }
        }

        return values;
    }

    private static void printLCS(String x, String y, int[][] values, int xLen, int yLen) {
        if (xLen == 0 || yLen == 0)
            System.out.print("");
        else {
            if (x.charAt(xLen - 1) == y.charAt(yLen - 1)) {
                printLCS(x, y, values, xLen - 1, yLen - 1);
                System.out.print(x.charAt(xLen - 1));
            } else if (values[xLen][yLen] == values[xLen - 1][yLen]) {
                printLCS(x, y, values, xLen - 1, yLen);
            } else
                printLCS(x, y, values, xLen, yLen - 1);
        }
    }

    private static List<String> printAllLCS(String x, String y, int[][] values, int xLen, int yLen) {
        List<String> allLCS = new ArrayList<>();

        if(xLen == 0 || yLen == 0)
            allLCS.add("");
        else if(x.charAt(xLen - 1) == y.charAt(yLen - 1)) {
            for(String lcs : printAllLCS(x, y, values, xLen - 1, yLen - 1))
                allLCS.add(lcs + x.charAt(xLen - 1));
        } else {
            if (values[xLen - 1][yLen] >= values[xLen][yLen - 1])
                allLCS.addAll(printAllLCS(x, y, values, xLen - 1, yLen));

            if(values[xLen][yLen - 1] >= values[xLen - 1][yLen])
                allLCS.addAll(printAllLCS(x, y, values, xLen, yLen - 1));
        }

        return allLCS;
    }
}
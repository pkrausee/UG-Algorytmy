import java.util.ArrayList;
import java.util.List;

abstract class LCS {
    public static int length (String x, String y) {
        return getLCS_values(x, y)[x.length()][y.length()];
    }

    public static int lengthOptimal(String x, String y) {
        //Probably slower but saves some memory

        if(x.length() < y.length())
            return getLCS_memorySave(x, y)[1][x.length()];
        else
            return getLCS_memorySave(x, y)[1][y.length()];
    }

    public static void print (String x, String y) {
        printLCS(x, y, getLCS_values(x, y), x.length(), y.length());

        System.out.println();
    }

    public static void printIter (String x, String y) {
        System.out.println(printLCSiter(x, y, getLCS_values(x, y), x.length(), y.length()));
    }

    public static void printAll(String x, String y) {
        List<String> temp = printAllLCS(x, y, getLCS_values(x, y), x.length(), y.length());

        for(String lcs : temp)
            System.out.print(lcs + " ");

        System.out.println();
    }

    private static int[][] getLCS_values(String x, String y) {
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

    private static int[][] getLCS_memorySave(String x, String y) {
        //Memory saving version
        //Saves only 2 rows
        if(x.length() < y.length()) {
            //Swap to get shorter string
            String temp = x;
            x = y;
            y = temp;
        }

        int xLen = x.length();
        int yLen = y.length();

        int[][] values = new int[2][yLen + 1];

        for(int i = 1; i <= xLen; i++) {
            for(int j = 1;j <= yLen; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1))
                    values[1][j] = values[0][j - 1] + 1;
                else {
                    if (values[0][j] >= values[1][j - 1])
                        values[1][j] = values[0][j];
                    else
                        values[1][j] = values[1][j - 1];
                }
            }

            if(i != xLen) {
                System.arraycopy(values[1], 0, values[0], 0, yLen + 1);
                values[1] = new int[yLen + 1];
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

    private static String printLCSiter(String x, String y, int[][] values, int xLen, int yLen) {
        StringBuilder sf = new StringBuilder("");

        while(xLen != 0 && yLen != 0) {
            if (x.charAt(xLen - 1) == y.charAt(yLen - 1)) {
                sf.append(x.charAt(xLen - 1));
                xLen--;
                yLen--;
            } else if (values[xLen][yLen] == values[xLen - 1][yLen]) {
                xLen--;
            } else
                yLen--;
        }

        return sf.reverse().toString();
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
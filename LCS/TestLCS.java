class TestLCS {
    public static void main(String[] args) {
        String x = "abbaac";
        String y = "bacbacba";

        /*

                  b a c b a c b a
                  0 0 0 0 0 0 0 0
              a 0 0 1 1 1 1 1 1 1
              b 0 1 1 1 2 2 2 2 2
              b 0 1 1 1 2 2 2 3 3
              a 0 1 2 2 2 3 3 3 4
              a 0 1 2 2 2 3 3 3 4
              c 0 1 2 3 3 3 4 4 4

                  a b b a a c
                0 0 0 0 0 0 0
              b 0 0 1 1 1 1 1
              a 0 1 1 1 2 2 2
              c 0 1 1 1 2 2 3
              b 0 1 2 2 2 2 3
              a 0 1 2 2 3 3 3
              c 0 1 2 2 3 3 4
              b 0 1 2 3 3 3 4
              a 0 1 2 3 4 4 4

         */

        System.out.println(LCS.length(x, y));
        System.out.println(LCS.lengthOptimal(x, y));

        LCS.print(x, y);
        LCS.printIter(x, y);

        LCS.printAll(x, y);

    }
}

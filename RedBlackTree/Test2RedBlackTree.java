class Test2RedBlackTree {
    public static void main(String[] args) {

        RedBlackTree rbt = new RedBlackTree();

        long startTime = System.nanoTime();

        for (int i = 1; i <= 1000000; i++)
            rbt.insert(i);

//        rbt.findPRO(555444);

        rbt.find(555444);

        long endTime = System.nanoTime();

        System.out.println("Czas wykonania danych operacji: " + (double)(endTime - startTime) / 1000000000);
    }
}
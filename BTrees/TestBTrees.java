class TestBTrees {
    public static void main(String[] args) {
        RamBTree rbt = new RamBTree(10);
        FileBTree fbt = new FileBTree(10);

        long RstartTime, RendTime, FstartTime, FendTime;
        double ramTreeTime, fileTreeTime;

        //Test and time Ram BTree
        RstartTime = System.nanoTime();
        for(int i = 1; i <= 10000; i++)
            rbt.insert(i);
        RendTime = System.nanoTime();

        //Test and time File BTree
        FstartTime = System.nanoTime();
        for(int i = 1; i <= 10000; i++)
            fbt.insert(i);
        FendTime = System.nanoTime();

        ramTreeTime = (double)(RendTime - RstartTime) / 1000000000;
        fileTreeTime = (double)(FendTime - FstartTime) / 1000000000;

        System.out.println("Ram Tree time: " + ramTreeTime + " sec");
        System.out.println("File Tree time:\n \tbuild: " + fileTreeTime + " sec\n \tclear: " + fbt.clearTree() + " sec\n");

        String format = "| %1$-41s |\n";
        String format2 = "| %1$-5s | %2$-15s | %3$-15s |\n";
        System.out.println("---------------------------------------------");
        System.out.format(format, "Stats for rank: " + fbt.getRank());
        System.out.println("---------------------------------------------");
        System.out.format(format2, "Files", "Save Operations", "Read Operations");
        System.out.println("---------------------------------------------");
        System.out.format(format2, fbt.getFiles(), fbt.getSaveOperations(), fbt.getReadOperations());
        System.out.println("---------------------------------------------");
    }
}

class TestFileBTree {
    public static void main(String[] args) {

        FileBTree fbt = new FileBTree(2);

        for(int i = 1; i <= 20; i++){
            fbt.insert(i);

            fbt.traverse(fbt.getRoot());
            System.out.println();
        }

        fbt.delete(15);

        fbt.traverse(fbt.getRoot());
        System.out.println("\n" + fbt.getRootNode());

    }
}

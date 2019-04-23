class TestBTree {
    public static void main(String[] args) {

        BTree bt = new BTree(2);

        for(int i = 1; i <= 20; i++){
            bt.insert(i);

            bt.traverse(bt.getRoot());
            System.out.println();
        }

        bt.delete(15);

        bt.traverse(bt.getRoot());
        System.out.println("\n" + bt.getRoot());

    }
}

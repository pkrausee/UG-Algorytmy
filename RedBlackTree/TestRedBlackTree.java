class TestRedBlackTree {
    public static void main(String[] args) {

        RedBlackTree rbt = new RedBlackTree();

        rbt.insert(6);
        rbt.insert(5);
        rbt.insert(2);
        rbt.insert(4);
        rbt.insert(2);
        rbt.insert(6);
        rbt.insert(7);
        rbt.insert(4);
        rbt.insert(7);
        rbt.insert(1);

        rbt.printNode(rbt.getRoot(), 0);

        System.out.println("Max glebokosc: " + rbt.maxDepth(rbt.getRoot()));
        System.out.println("Min glebokosc: " + rbt.minDepth(rbt.getRoot()));
        System.out.println("Czarni synowie: " + rbt.countBlackNodes(rbt.getRoot()));
        System.out.println("Czerwoni synowie: " + rbt.countRedNodes(rbt.getRoot()));

    }
}
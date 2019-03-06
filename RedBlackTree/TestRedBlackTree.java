class TestRedBlackTree {
    public static void main(String[] args) {

        RedBlackTree rbt = new RedBlackTree();

        rbt.insert(4);
        rbt.insert(2);
        rbt.insert(8);
        rbt.insert(6);
        rbt.insert(12);
        rbt.insert(10);
        rbt.insert(14);

        rbt.printNode(rbt.getRoot(), 0);

        System.out.println("----------------------------");

        rbt.insert(9);

        rbt.printNode(rbt.getRoot(), 0);

        System.out.println("Max glebokosc: " + rbt.maxDepth(rbt.getRoot()));
        System.out.println("Min glebokosc: " + rbt.minDepth(rbt.getRoot()));
        System.out.println("Czarni synowie: " + rbt.countBlackNodes(rbt.getRoot()));
        System.out.println("Czerwoni synowie: " + rbt.countRedNodes(rbt.getRoot()));

        System.out.println("----------------------------");

        rbt.findPRO(9);

        System.out.println("----------------------------");

        rbt.delete(12);

        rbt.printNode(rbt.getRoot(), 0);
    }
}
class Main {
    public static void main(String[] args) {
        BTree bt = new BTree(2);

        for (int i = 0; i < 21; i++)
            bt.insert(i);

        bt.delete(16);

        bt.traverse(bt.getRoot());

//        System.out.println("\n" + bt.getRoot());
//        System.out.println(bt.getRoot().getSons());
//        for(Node n : bt.getRoot().getSons())
//            for(Node nn : n.getSons())
//                System.out.print(nn + ", ");
//        System.out.println();
//        for(Node n : bt.getRoot().getSons())
//            for(Node nn : n.getSons())
//                for(Node nnn : nn.getSons())
//                    System.out.print(nnn + ", ");

    }
}

import java.util.Collections;

class BTree {
    private Node root;
    private int rank;

    public BTree(int rank) {
        this.root = new Node();
        this.rank = rank;
    }

    private void split(Node node, int pos) {
        //Split son on given position
        Node toSplit = node.getSons().get(pos);

        Node newSon = new Node();
        newSon.setLeaf(toSplit.isLeaf());

        //Copy keys
        for (int i = 0; i < rank - 1; i++)
            newSon.getKeys().add(toSplit.getKeys().get(i));


        //Copy sons (if not a leaf)
        if (!toSplit.isLeaf()) {
            for (int i = 0; i < rank; i++) {
                newSon.getSons().add(toSplit.getSons().get(0));
                toSplit.getSons().remove(0);
            }
        }

        //Add newSon
        node.getSons().add(pos, newSon);

        //Add new key
        node.getKeys().add(pos, toSplit.getKeys().get(rank - 1));

        //Remove copied keys
        toSplit.getKeys().subList(0, rank).clear();
    }

    private void insertNF(Node node, int key) {
//        System.out.println("insert " + key);

        if (node.isLeaf()) {
            node.getKeys().add(key);
            Collections.sort(node.getKeys());

//            System.out.println("is leaf");

        } else {
//            System.out.println("not leaf");

            int pos = node.getKeys().size();

            while (pos >= 0 && key < node.getKeys().get(pos - 1))
                pos--;

            pos++;

            if (node.getSons().get(pos - 1).getKeys().size() == 2 * rank - 1) {
//                System.out.println("full son");

                split(node, pos - 1);

                if (key > node.getKeys().get(pos - 1))
                    pos++;
            }

            insertNF(node.getSons().get(pos - 1), key);
        }
    }

    private void join(Node father, Node leftSon, Node rightSon, int pos) {
//        System.out.println("Join");

        Node joinedNode = new Node();
        joinedNode.setLeaf(leftSon.isLeaf());

        //Copy keys
        for (int i = 0; i < leftSon.getKeys().size(); i++)
            joinedNode.getKeys().add(leftSon.getKeys().get(i));

        joinedNode.getKeys().add(father.getKeys().get(pos));
        father.getKeys().remove(pos);

        for (int i = 0; i < rightSon.getKeys().size(); i++)
            joinedNode.getKeys().add(rightSon.getKeys().get(i));

        //Copy sons (if not a leaf)
        if (!joinedNode.isLeaf()) {
            for (int i = 0; i < leftSon.getSons().size(); i++)
                joinedNode.getSons().add(leftSon.getSons().get(i));

            for (int i = 0; i < rightSon.getSons().size(); i++)
                joinedNode.getSons().add(rightSon.getSons().get(i));
        }

        if(root == father)
            this.root = joinedNode;

        father.getSons().remove(pos);
        father.getSons().remove(pos);

        father.getSons().add(pos, joinedNode);
    }

    private void leftRotate(Node node, int pos) {
//        System.out.println("Left rotate ");

        node.getSons().get(pos).getKeys().add(node.getKeys().get(pos));
        node.getKeys().remove(pos);

        node.getKeys().add(node.getSons().get(pos + 1).getKeys().get(0));
        node.getSons().get(pos + 1).getKeys().remove(0);

        Collections.sort(node.getKeys());
    }

    private void rightRotate(Node node, int pos) {
//        System.out.println("Right rotate");

        node.getSons().get(pos + 1).getKeys().add(0, node.getKeys().get(pos));
        node.getKeys().remove(pos);

        int lastId = node.getSons().get(pos).getKeys().size() - 1;

        node.getKeys().add(0, node.getSons().get(pos).getKeys().get(lastId));
        node.getSons().get(pos).getKeys().remove(lastId);

        Collections.sort(node.getKeys());
    }

    private int deleteMin(Node node) {
//        System.out.println("deleteMin");

        if (node.isLeaf()) {
            int min = node.getKeys().get(0);

            node.getKeys().remove(0);

            return min;
        } else {
            Node firstSon = node.getSons().get(0);

            if (firstSon.getKeys().size() > rank - 1) {
                deleteMin(firstSon);
            } else {
                Node secondSon = node.getSons().get(1);

                if (secondSon.getKeys().size() > rank - 1) {
                    leftRotate(node, 0);
                    deleteMin(firstSon);
                } else {
                    join(node, firstSon, secondSon, 0);
                    deleteMin(firstSon);
                }
            }
        }

        return 0;
    }

    private int deleteMax(Node node) {
//        System.out.println("deleteMax");

        int lastId = node.getKeys().size() - 1;

        if (node.isLeaf()) {
            int max = node.getKeys().get(lastId);

            node.getKeys().remove(lastId);

            return max;
        } else {
            Node firstSon = node.getSons().get(lastId);

            if (firstSon.getKeys().size() > rank - 1) {
                deleteMax(firstSon);
            } else {
                Node secondSon = node.getSons().get(lastId - 1);

                if (secondSon.getKeys().size() > rank - 1) {
                    rightRotate(node, lastId);
                    deleteMax(firstSon);
                } else {
                    join(node, firstSon, secondSon, lastId);
                    deleteMax(firstSon);
                }
            }
        }

        return 0;
    }

    private Integer findId(Node node, int key) {
        for (int i = 0; i < node.getKeys().size(); i++)
            if (node.getKeys().get(i) == key){
//                System.out.println("Found id: " + i);
                return i;
            }

        return null;
    }

    private void deleteKey(Node node, int key) {
        if (node.getKeys().contains(key)) {
            int pos = findId(node, key);

            if (node.isLeaf()) {
                node.getKeys().remove(pos);
            } else {
                Node leftSon = node.getSons().get(pos);
                Node rightSon = node.getSons().get(pos + 1);

                if (leftSon.getKeys().size() > rank - 1)
                    node.getKeys().set(pos, deleteMax(leftSon));
                else if (rightSon.getKeys().size() > rank - 1)
                    node.getKeys().set(pos, deleteMin(rightSon));
                else {
                    join(node, leftSon, rightSon, pos);
                    deleteKey(node.getSons().get(pos), key);
                }
            }
        } else if (!node.isLeaf()) {
            int pos = 0;

            while(pos < node.getKeys().size() && node.getKeys().get(pos) < key)
                pos++;

            Node leftSon = (pos > 0) ? node.getSons().get(pos - 1) : null;
            Node midSon = node.getSons().get(pos);
            Node rightSon = (pos < node.getSons().size() - 1) ? node.getSons().get(pos + 1) : null;

            if(midSon.getKeys().size() > rank - 1){
                deleteKey(midSon, key);
            }
            else if(leftSon != null && leftSon.getKeys().size() > rank - 1) {
                rightRotate(node, pos);
                deleteKey(midSon, key);
            } else if(rightSon != null && rightSon.getKeys().size() > rank - 1) {
                leftRotate(node, pos);
                deleteKey(midSon, key);
            } else {
                if(pos > 0) {
                    join(node, leftSon, midSon, pos - 1);

                    midSon = node.getSons().get(pos - 1);

                    deleteKey(midSon, key);
                } else {
                    join(node, midSon, rightSon, pos);

                    midSon = node.getSons().get(pos);

                    deleteKey(midSon, key);
                }
            }
        }
    }

    public void delete(int key) {
        deleteKey(root, key);
    }

    public void insert(int key) {
        if (this.root.getKeys().size() == 2 * rank - 1) {
//            System.out.println("split root");

            //Split root
            Node newRoot = new Node();

            newRoot.setLeaf(false);
            newRoot.getSons().add(this.root);

            split(newRoot, 0);

            this.root = newRoot;

            insert(key);
        } else {
            insertNF(root, key);
        }
    }

    public void search(Node node, int key) {
        int iter = 0;
        while (iter < node.getKeys().size() && key > node.getKeys().get(iter))
            iter++;

        if (iter < node.getKeys().size() && key == node.getKeys().get(iter)) {
            System.out.println("Key: " + key + " found on position: " + (iter + 1) + " in node: " + node);
        } else if (!node.isLeaf()) {
            search(node.getSons().get(iter), key);
        } else {
            System.out.println("Key not found");
        }
    }

    public void traverse(Node node) {
        int i;
        if (node.isLeaf()) {
            System.out.print(node.getKeys() + " ");
            return;
        }
        for(i = 0; i < node.getKeys().size(); i++) {
            traverse(node.getSons().get(i));
            System.out.print(node.getKeys().get(i) + " ");

        }

        traverse(node.getSons().get(node.getKeys().size()));
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}

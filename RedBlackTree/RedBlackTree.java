class RedBlackTree {
    private Record root;
    private Record sentinel;

    RedBlackTree() {
        this.sentinel = new Record();

        this.root = this.sentinel;
    }

    public void insert(int value) {
        //Set new element to be checked
        Record node = insertBST(value);

        while (node.getParent().isRed()) {
            if (node.getParent() == node.getParent().getParent().getLeftSon()) {
                //Left side
                if (node.getParent().getParent().getRightSon().isRed()) {
                    //First case
                    node.getParent().setRed(false);
                    node.getParent().getParent().getRightSon().setRed(false);
                    node.getParent().getParent().setRed(true);
                    node = node.getParent().getParent();
                } else {
                    if (node == node.getParent().getRightSon()) {
                        //Second case
                        node = node.getParent();
                        rotateLeft(node);
                    }
                    //ThirdCase
                    node.getParent().setRed(false);
                    node.getParent().getParent().setRed(true);
                    rotateRight(node.getParent().getParent());
                }
            } else {
                //Right side
                if (node.getParent().getParent().getLeftSon().isRed()) {
                    //First case
                    node.getParent().setRed(false);
                    node.getParent().getParent().getLeftSon().setRed(false);
                    node.getParent().getParent().setRed(true);
                    node = node.getParent().getParent();
                } else {
                    if (node == node.getParent().getLeftSon()) {
                        //Second case
                        node = node.getParent();
                        rotateRight(node);
                    }
                //ThirdCase
                node.getParent().setRed(false);
                node.getParent().getParent().setRed(true);
                rotateLeft(node.getParent().getParent());
                }
            }
        }

        this.root.setRed(false);
    }

    private Record insertBST(int value) {
        //Insert new element (BinarySearchTree algorithm)
        Record newElement = new Record(value, true, this.sentinel, this.sentinel, this.sentinel);

        Record parent = this.sentinel;
        Record iterator = this.root;

        while (iterator != this.sentinel) {
            parent = iterator;
            if (newElement.getValue() < iterator.getValue())
                iterator = iterator.getLeftSon();
            else
                iterator = iterator.getRightSon();
        }

        newElement.setParent(parent);

        if (parent == this.sentinel) {
            this.root = newElement;
            newElement.setRed(false);
        }
        else if (newElement.getValue() < parent.getValue())
            parent.setLeftSon(newElement);
        else
            parent.setRightSon(newElement);

        return newElement;
    }

    public void delete(Record node) {
        System.out.println("TBD");
    }

    private void transplantNode(Record node1, Record node2) {
        //Change subtrees
        if(node1.getParent() == this.sentinel)
            this.root = node2;
        else if (node1 == node1.getParent().getLeftSon())
            node1.getParent().setLeftSon(node2);
        else
            node1.getParent().setRightSon(node2);

        node2.setParent(node1.getParent());
    }

    public Record find(int value) {
        //Find the first occurrence of given value
        //Null if given value doesn't occur in this set
        if (this.root == this.sentinel) {
            return null;
        } else {
            Record temp = this.root;

            while (temp != this.sentinel && temp.getValue() != value) {
                if (temp.getValue() > value)
                    temp = temp.getLeftSon();
                else
                    temp = temp.getRightSon();
            }

            if (temp == this.sentinel)
                return null;
            else
                return temp;
        }
    }

    private void rotateLeft(Record node) {
        Record son = node.getRightSon();
        node.setRightSon(son.getLeftSon());

        if (son.getLeftSon() != this.sentinel)
            son.getLeftSon().setParent(node);

        son.setParent(node.getParent());

        if (node.getParent() == this.sentinel)
            this.root = son;
        else if (node == node.getParent().getLeftSon())
            node.getParent().setLeftSon(son);
        else
            node.getParent().setRightSon(son);

        son.setLeftSon(node);
        node.setParent(son);
    }

    private void rotateRight(Record node) {
        Record son = node.getLeftSon();
        node.setLeftSon(son.getRightSon());

        if (son.getRightSon() != this.sentinel)
            son.getRightSon().setParent(node);

        son.setParent(node.getParent());

        if (node.getParent() == this.sentinel)
            this.root = son;
        else if (node == node.getParent().getLeftSon())
            node.getParent().setLeftSon(son);
        else
            node.getParent().setRightSon(son);

        son.setRightSon(node);
        node.setParent(son);
    }

    public int countBlackNodes(Record position) {
        //Returns number of black nodes in current position
        if (position == sentinel)
            return 0;

        if (position.getLeftSon() == this.sentinel && position.getRightSon() == this.sentinel)
            return position.isRed() ? 0 : 1;

        return countBlackNodes(position.getLeftSon()) + countBlackNodes(position.getRightSon()) + (position.isRed() ? 0 : 1);
    }

    public int countRedNodes(Record position) {
        //Returns number of red nodes in current position
        if (position == sentinel)
            return 0;

        if (position.getLeftSon() == this.sentinel && position.getRightSon() == this.sentinel)
            return position.isRed() ? 1 : 0;

        return countRedNodes(position.getLeftSon()) + countRedNodes(position.getRightSon()) + (position.isRed() ? 1 : 0);
    }

    public int maxDepth(Record node) {
        if (node == sentinel)
            return 0;

        return Math.max(maxDepth(node.getLeftSon()), maxDepth(node.getRightSon())) + 1;
    }

    public int minDepth(Record node) {
        if (node == sentinel)
            return 0;

        return Math.min(minDepth(node.getLeftSon()), minDepth(node.getRightSon())) + 1;
    }

    public void printNode(Record node, int size) {
        //Simple print for visualization
        if (node.getRightSon() != this.sentinel)
            printNode(node.getRightSon(), size + 2);

        for (int i = 0; i < size; i++)
            System.out.print(" ");
        System.out.println(node);

        if (node.getLeftSon() != this.sentinel)
            printNode(node.getLeftSon(), size + 2);
    }

    public Record getRoot() {
        return root;
    }
}

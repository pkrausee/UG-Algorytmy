class RedBlackTree {
    private Record root;
    private Record sentinel;

    RedBlackTree() {
        this.root = null;
        this.sentinel = new Record();
    }

    public void insert(int value) {
        Record element = new Record(value, true, null, null, null);
        //Set Sentries
        element.setLeftSon(sentinel);
        element.setRightSon(sentinel);

        insertBST(element, this.root);

        while (element != this.root && element.getParent().isRed()) {
            if (element.getParent() == element.getParent().getParent().getLeftSon()) {
                //Left side
                if (element.getParent().getParent().getRightSon().isRed()) {
                    //First case
                    element.getParent().setRed(false);
                    element.getParent().getParent().getRightSon().setRed(false);
                    element.getParent().getParent().setRed(true);
                    element = element.getParent().getParent();
                } else {
                    if (element == element.getParent().getRightSon()) {
                        //Second case
                        rotate(element);
                        element = element.getLeftSon();
                    }
                    //Third case
                    element.getParent().setRed(false);
                    element.getParent().getParent().setRed(true);
                    rotate(element.getParent());
                }
            } else {
                //Right side
                if (element.getParent().getParent().getLeftSon().isRed()) {
                    //First case
                    element.getParent().setRed(false);
                    element.getParent().getParent().getLeftSon().setRed(false);
                    element.getParent().getParent().setRed(true);
                    element = element.getParent().getParent();
                } else {
                    if (element == element.getParent().getLeftSon()) {
                        //Second case
                        rotate(element);
                        element = element.getRightSon();
                    }
                    //Third case
                    element.getParent().setRed(false);
                    element.getParent().getParent().setRed(true);
                    rotate(element.getParent());
                }
            }

            this.root.setRed(false);
        }
    }

    private void insertBST(Record newElement, Record position) {
        if (this.root == null) {
            this.root = newElement;
            this.root.setRed(false);
        } else {
            if (position.getValue() <= newElement.getValue()) {
                if (position.getRightSon() == sentinel) {
                    position.setRightSon(newElement);
                    newElement.setParent(position);
                } else
                    insertBST(newElement, position.getRightSon());
            } else {
                if (position.getLeftSon() == sentinel) {
                    position.setLeftSon(newElement);
                    newElement.setParent(position);
                } else
                    insertBST(newElement, position.getLeftSon());
            }
        }
    }

    private void rotate(Record element) {
        Record parent = element.getParent();

        //Set new parents
        if (parent.getParent() != null) {
            if (parent == parent.getParent().getLeftSon())
                parent.getParent().setLeftSon(element);
            else
                parent.getParent().setRightSon(element);
        } else
            this.root = element;

        element.setParent(parent.getParent());
        parent.setParent(element);

        if (element == parent.getRightSon()) {
            //Rotate to the left
            element.getLeftSon().setParent(parent);
            parent.setRightSon(element.getLeftSon());
            element.setLeftSon(parent);
        } else {
            //Rotate to the right
            element.getRightSon().setParent(parent);
            parent.setLeftSon(element.getRightSon());
            element.setRightSon(parent);
        }
    }

    public int countBlackNodes(Record position) {
        //Returns number of black nodes in current position
        if(position == sentinel)
            return 0;

        if(position.getLeftSon() == this.sentinel && position.getRightSon() == this.sentinel)
            return position.isRed() ? 0 : 1;

        return countBlackNodes(position.getLeftSon()) + countBlackNodes(position.getRightSon()) + (position.isRed() ? 0 : 1);
    }

    public int countRedNodes(Record position) {
        //Returns number of red nodes in current position
        if(position == sentinel)
            return 0;

        if(position.getLeftSon() == this.sentinel && position.getRightSon() == this.sentinel)
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

    public void printRecord(Record r, int size) {
        //Simple print for visualization
        if (r.getRightSon() != this.sentinel)
            printRecord(r.getRightSon(), size + 2);

        for (int i = 0; i < size; i++)
            System.out.print(" ");
        System.out.println(r);

        if (r.getLeftSon() != this.sentinel)
            printRecord(r.getLeftSon(), size + 2);
    }

    public Record getRoot() {
        return root;
    }
}

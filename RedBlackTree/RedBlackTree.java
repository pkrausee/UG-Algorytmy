class RedBlackTree {
    private Record root;
    private Record sentinel;

    RedBlackTree() {
        this.sentinel = new Record();

        this.root = this.sentinel;
    }

    public void insert(int value) {
        //Check new element
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

    public void delete (int value) {
        //Check deleted position
        Record node = find(value);

        if(node != null) {
            node = deleteBST(node);

            if(node != null) {
                Record brother;

                while(node != this.root && !node.isRed()) {
                    if(node == node.getParent().getLeftSon()) {
                        //Left side
                        brother = node.getParent().getRightSon();
                        if(brother.isRed()) {
                            //First case
                            brother.setRed(false);
                            node.getParent().setRed(true);
                            rotateLeft(node.getParent());
                            brother = node.getParent().getLeftSon();
                        }

                        if(!brother.getLeftSon().isRed() && !brother.getRightSon().isRed()) {
                            //Second case
                            brother.setRed(true);
                            node = node.getParent();
                        } else {
                            if(!brother.getRightSon().isRed()) {
                                //Third case
                                brother.getLeftSon().setRed(false);
                                brother.setRed(true);
                                rotateRight(brother);
                                brother = node.getParent().getRightSon();
                            }
                            //Fourth case
                            brother.setRed(node.getParent().isRed());
                            node.getParent().setRed(false);
                            brother.getRightSon().setRed(false);
                            rotateLeft(node.getParent());
                            node = this.root;
                        }
                    } else {
                        //Right side
                        brother = node.getParent().getLeftSon();
                        if(brother.isRed()) {
                            //First case
                            brother.setRed(false);
                            node.getParent().setRed(true);
                            rotateRight(node.getParent());
                            brother = node.getParent().getRightSon();
                        }

                        if(!brother.getLeftSon().isRed() && !brother.getRightSon().isRed()) {
                            //Second case
                            brother.setRed(true);
                            node = node.getParent();
                        } else {
                            if(!brother.getLeftSon().isRed()) {
                                //Third case
                                brother.getRightSon().setRed(false);
                                brother.setRed(true);
                                rotateLeft(brother);
                                brother = node.getParent().getLeftSon();
                            }
                            //Fourth case
                            brother.setRed(node.getParent().isRed());
                            node.getParent().setRed(false);
                            brother.getLeftSon().setRed(false);
                            rotateRight(node.getParent());
                            node = this.root;
                        }
                    }
                }

                node.setRed(false);
            }
        }
    }

    private Record deleteBST(Record node) {
        //Delete element (BinarySearchTree algorithm)
        //Returns node that needs to be fixed or null if tree doesn't need fixing

        Record nodeToFix; //Node that is missing black sons
        Record newNode = node; //Deleted position
        boolean newNode_orgColor = newNode.isRed();

        if (node.getLeftSon() == this.sentinel) {
            nodeToFix = node.getRightSon();
            transplantNode(node, node.getRightSon());
        } else if (node.getRightSon() == this.sentinel) {
            nodeToFix = node.getLeftSon();
            transplantNode(node ,node.getLeftSon());
        } else {
            newNode = findMin(node.getRightSon());
            newNode_orgColor = newNode.isRed();
            nodeToFix = newNode.getRightSon();
            if(newNode.getParent() == node) {
                nodeToFix.setParent(newNode);
            } else {
                transplantNode(newNode, newNode.getRightSon());
                newNode.setRightSon(node.getRightSon());
                newNode.getRightSon().setParent(newNode);
            }
            transplantNode(node, newNode);
            newNode.setLeftSon(node.getLeftSon());
            newNode.getLeftSon().setParent(newNode);
            newNode.setRed(node.isRed());
        }

        if(!newNode_orgColor){
            return nodeToFix;
        }

        return null;
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

    public void findPRO(int value) {
        if (this.root == this.sentinel) {
            System.out.println("Nie mozna znalezc danego elementy");
        } else {
            Record temp = this.root;

            int depth = 1;
            int blackdepth = 1;

            while (temp != this.sentinel && temp.getValue() != value) {
                if (temp.getValue() > value)
                    temp = temp.getLeftSon();
                else
                    temp = temp.getRightSon();

                depth++;
                if(!temp.isRed())
                    blackdepth++;
            }

            if(temp != this.sentinel) {
                System.out.println("\nZnaleziono wartosc: " + temp);
                System.out.println("Kolor: " + (temp.isRed() ? "Czerwony" : "Czarny"));
                System.out.println("Glebokosc: " + depth);
                System.out.println("Czarna wysokosc: " + blackdepth + "\n");
            } else {
                System.out.println("Nie znaleziono wartosci");
            }
        }
    }

    public Record findMin (Record node) {
        //Find minimum in current node

        while(node.getLeftSon() != this.sentinel) {
            node = node.getLeftSon();
        }

        return node;
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

class Element {
    private int value;
    private int freq;

    Element(int value, int freq) {
        this.value = value;
        this.freq = freq;
    }

    Element() {
        this.value = 0;
        this.freq = 0;
    }

    public String toString() {
        return this.value + ":" + this.getFreq();
    }

    public boolean equals(Object o) {
        if (!(o instanceof Element)) return false;
        Element e = (Element) o;
        return e.value == this.value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }
}


class Node {
    private Element value;
    private Node left;
    private Node right;

    public Node(Element value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Node() {
        this.value = null;
        this.left = null;
        this.right = null;
    }

    public boolean isLeaf() {
        return (this.left == null && this.right == null);
    }

    public Element getValue() {
        return value;
    }

    public String toString() {
        return this.value.toString();
    }

    public void setValue(Element value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }


}

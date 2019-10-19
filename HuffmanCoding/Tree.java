import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.lang.Math.ceil;

class Tree {
    private Node root;
    private int values;
    private int nodes;

    public Tree() {
        this.root = new Node();
    }

    private Node extractMin(Set<Node> nodeMap) {
        Node min = null;

        for (Node iter : nodeMap) {
            if (min == null || min.getValue().getFreq() > iter.getValue().getFreq()) {
                min = iter;
            }
        }

        if (min != null)
            nodeMap.remove(min);

        return min;
    }

    public void buildTree(byte[] file) {
        Map<Integer, Integer> freq = new HashMap<>();
        Frequency.getFreq(freq, file);

        this.values = freq.size();

        int size = freq.size();

        Set<Node> nodeMap = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : freq.entrySet())
            nodeMap.add(new Node(new Element(entry.getKey(), entry.getValue()), null, null));

        this.nodes = nodeMap.size();

        for (int i = 2; i <= size; i++) {
            Node newNode = new Node(new Element(0, 0), null, null);
            nodes++;

            Node left = extractMin(nodeMap);
            Node right = extractMin(nodeMap);
            newNode.setLeft(left);
            newNode.setRight(right);
            newNode.getValue().setFreq(left.getValue().getFreq() + right.getValue().getFreq());

            this.root = newNode;

            nodeMap.add(newNode);
        }
    }

    public void printNode(Node node, int size) {
        if (node.getRight() != null)
            printNode(node.getRight(), size + 10);

        for (int i = 0; i < size; i++)
            System.out.print(" ");
        System.out.println(node);

        if (node.getLeft() != null)
            printNode(node.getLeft(), size + 10);
    }

    private void getStruct(Node node, StringBuilder route) {
        //Pomysł pożyczony od Michała...
        if (node.getLeft().isLeaf()) {
            route.append("1");
        } else {
            route.append("0");
            getStruct(node.getLeft(), route);
        }

        if (node.getRight().isLeaf()) {
            route.append("1");
        } else {
            route.append("0");
            getStruct(node.getRight(), route);
        }
    }

    private void getValues(Node node, StringBuilder vals) {
        if (node.getLeft().isLeaf()) {
            //Get 8-bit value
            vals.append(Integer.toBinaryString((node.getLeft().getValue().getValue() & 0xFF) + 0x100).substring(1));
            vals.append(" ");
        } else {
            getValues(node.getLeft(), vals);
        }

        if (node.getRight().isLeaf()) {
            vals.append(Integer.toBinaryString((node.getRight().getValue().getValue() & 0xFF) + 0x100).substring(1));
            vals.append(" ");
        } else {
            getValues(node.getRight(), vals);
        }
    }

    public int getConvertedSize() {
        return (int) ceil((double) (nodes / 8)) + values + 1;
    }

    public byte[] convertToByte() {
        byte[] tree = new byte[getConvertedSize()];

        int pos = 128;
        int byteVal = 0;

        int outputPos = 0;

        //Write tree structure
        StringBuilder struct = new StringBuilder();
        getStruct(root, struct);
        String treeStruct = struct.toString();
        for (int i = 0; i < treeStruct.length(); i++) {

            if (pos == 0) {
                tree[outputPos] = (byte) byteVal;

                outputPos++;
                byteVal = 0;
                pos = 128;
            }

            if (treeStruct.charAt(i) == '1')
                byteVal += pos;

            pos /= 2;
        }
        tree[outputPos] = (byte) byteVal;

        //Add values
        pos = 128;
        byteVal = 0;

        outputPos++;

        StringBuilder vals = new StringBuilder();
        getValues(root, vals);
        String treeVals = vals.toString();
        for (int i = 0; i < treeVals.length(); i++) {
            if (pos == 0 || treeVals.charAt(i) == ' ') {
                tree[outputPos] = (byte) byteVal;

                outputPos++;
                byteVal = 0;
                pos = 128;
            }

            if (treeVals.charAt(i) == '1')
                byteVal += pos;

            if (treeVals.charAt(i) != ' ')
                pos /= 2;
        }

        return tree;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getValues() {
        return values;
    }

    public void setValues(int values) {
        this.values = values;
    }

    public int getNodes() {
        return nodes;
    }

    public void setNodes(int nodes) {
        this.nodes = nodes;
    }
}

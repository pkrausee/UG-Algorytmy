import java.util.ArrayList;

class Node {
    private ArrayList<Integer> keys;
    private ArrayList<Node> sons;
    private boolean isLeaf;

    public Node() {
        this.keys = new ArrayList<Integer>();
        this.sons = new ArrayList<Node>();
        this.isLeaf = true;
    }

    public String toString() {
        return keys.toString();
    }

    public ArrayList<Integer> getKeys() {
        return keys;
    }

    public void setKeys(ArrayList<Integer> keys) {
        this.keys = keys;
    }

    public ArrayList<Node> getSons() {
        return sons;
    }

    public void setSons(ArrayList<Node> sons) {
        this.sons = sons;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}

import java.util.ArrayList;

class RamNode {
    private ArrayList<Integer> keys;
    private ArrayList<RamNode> sons;
    private boolean isLeaf;

    public RamNode() {
        this.keys = new ArrayList<Integer>();
        this.sons = new ArrayList<RamNode>();
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

    public ArrayList<RamNode> getSons() {
        return sons;
    }

    public void setSons(ArrayList<RamNode> sons) {
        this.sons = sons;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}



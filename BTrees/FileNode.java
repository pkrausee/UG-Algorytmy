import java.util.ArrayList;

class FileNode {
    private ArrayList<Integer> keys;
    private ArrayList<String> sons;
    private boolean isLeaf;

    public FileNode() {
        this.keys = new ArrayList<Integer>();
        this.sons = new ArrayList<String>();
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

    public ArrayList<String> getSons() {
        return sons;
    }

    public void setSons(ArrayList<String> sons) {
        this.sons = sons;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}

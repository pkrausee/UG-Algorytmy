import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class FileBTree {
    private String root;
    private int rank;

    int files;

    public FileBTree(int rank) {
        this.files = 1;

        this.root = getFilename();
        saveNode(new FileNode(), root);

        this.rank = rank;
    }

    private String getFilename() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss.SSS");

        return "n" + files + "_" + dateFormat.format(date);
    }

    private void saveNode(FileNode node, String filename) {
        try {
            // Just in case...
            if (!Files.deleteIfExists(Paths.get("./files/" + filename + ".txt")))
                files++;

            PrintWriter pw = new PrintWriter(new FileOutputStream("./files/" + filename + ".txt"));

            pw.println(node.isLeaf() ? "1" : "0");
            pw.println(node.getKeys().size());

            for (int key : node.getKeys())
                pw.println(key);

            for (String son : node.getSons())
                pw.println(son);

            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FileNode readNode(String filename) {
        FileNode node = new FileNode();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./files/" + filename + ".txt")));

            node.setLeaf(br.readLine().equals("1"));

            int keysSize = Integer.valueOf(br.readLine());

            ArrayList<Integer> keys = new ArrayList<>();
            for (int i = 0; i < keysSize; i++)
                keys.add(Integer.valueOf(br.readLine()));
            node.setKeys(keys);

            if (!node.isLeaf()) {
                ArrayList<String> sons = new ArrayList<>();
                for (int i = 0; i < keysSize + 1; i++)
                    sons.add(br.readLine());
                node.setSons(sons);
            }

            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return node;
    }

    private void deleteNode(String filename) {
        try {
            if (Files.deleteIfExists(Paths.get("./files/" + filename + ".txt")))
                files--;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void split(String nodeFilename, int pos) {
//        System.out.println("split");

        FileNode node = readNode(nodeFilename);

        //Split son on given position
        FileNode toSplit = readNode(node.getSons().get(pos));
        String toSplitFilename = node.getSons().get(pos);

        FileNode newSon = new FileNode();
        String newSonFilename = getFilename();

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
        node.getSons().add(pos, newSonFilename);

        //Add new key
        node.getKeys().add(pos, toSplit.getKeys().get(rank - 1));

        //Remove copied keys
        toSplit.getKeys().subList(0, rank).clear();

        //Save nodes
        saveNode(node, nodeFilename);
        saveNode(toSplit, toSplitFilename);
        saveNode(newSon, newSonFilename);
    }

    private void insertNF(String nodeFilename, int key) {
//        System.out.println("insert " + key);

        FileNode node = readNode(nodeFilename);

        if (node.isLeaf()) {
            node.getKeys().add(key);
            Collections.sort(node.getKeys());

//            System.out.println("is leaf");

            saveNode(node, nodeFilename);
        } else {
//            System.out.println("not leaf");

            int pos = node.getKeys().size();

            while (pos >= 0 && key < node.getKeys().get(pos - 1))
                pos--;

            pos++;

            if (readNode(node.getSons().get(pos - 1)).getKeys().size() == 2 * rank - 1) {
//                System.out.println("full son");

                split(nodeFilename, pos - 1);

                node = readNode(nodeFilename);

                if (key > node.getKeys().get(pos - 1))
                    pos++;
            }

            insertNF(node.getSons().get(pos - 1), key);
        }
    }

    private void join(String fatherFilename, String leftSonFilename, String rightSonFilename, int pos) {
//        System.out.println("Join");

        FileNode father = readNode(fatherFilename);
        FileNode leftSon = readNode(leftSonFilename);
        FileNode rightSon = readNode(rightSonFilename);

        FileNode joinedNode = new FileNode();
        String joinedNodeFilename = getFilename();

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

        if (root.equals(fatherFilename))
            this.root = joinedNodeFilename;

        father.getSons().remove(pos);
        father.getSons().remove(pos);

        father.getSons().add(pos, joinedNodeFilename);

        saveNode(father, fatherFilename);
        saveNode(joinedNode, joinedNodeFilename);

        deleteNode(leftSonFilename);
        deleteNode(rightSonFilename);
    }

    private void leftRotate(String nodeFilename, int pos) {
//        System.out.println("Left rotate ");

        FileNode node = readNode(nodeFilename);
        FileNode leftSon = readNode(node.getSons().get(pos));
        FileNode rightSon = readNode(node.getSons().get(pos + 1));

        leftSon.getKeys().add(node.getKeys().get(pos));
        node.getKeys().remove(pos);

        node.getKeys().add(rightSon.getKeys().get(0));
        rightSon.getKeys().remove(0);

        Collections.sort(node.getKeys());

        saveNode(node, nodeFilename);
        saveNode(rightSon, node.getSons().get(pos));
        saveNode(leftSon, node.getSons().get(pos + 1));
    }

    private void rightRotate(String nodeFilename, int pos) {
//        System.out.println("Right rotate");

        FileNode node = readNode(nodeFilename);
        FileNode leftSon = readNode(node.getSons().get(pos));
        FileNode rightSon = readNode(node.getSons().get(pos + 1));

        rightSon.getKeys().add(0, node.getKeys().get(pos));
        node.getKeys().remove(pos);

        int lastId = leftSon.getKeys().size() - 1;

        node.getKeys().add(0, leftSon.getKeys().get(lastId));
        leftSon.getKeys().remove(lastId);

        Collections.sort(node.getKeys());

        saveNode(node, nodeFilename);
        saveNode(rightSon, node.getSons().get(pos));
        saveNode(leftSon, node.getSons().get(pos + 1));
    }

    private int deleteMin(String nodeFilename) {
//        System.out.println("deleteMin");

        FileNode node = readNode(nodeFilename);

        if (node.isLeaf()) {
            int min = node.getKeys().get(0);

            node.getKeys().remove(0);

            saveNode(node, nodeFilename);

            return min;
        } else {
            FileNode firstSon = readNode(node.getSons().get(0));

            if (firstSon.getKeys().size() > rank - 1) {
                deleteMin(node.getSons().get(0));
            } else {
                FileNode secondSon = readNode(node.getSons().get(1));

                if (secondSon.getKeys().size() > rank - 1) {
                    leftRotate(nodeFilename, 0);

                    node = readNode(nodeFilename);

                    deleteMin(node.getSons().get(0));
                } else {
                    join(nodeFilename, node.getSons().get(0), node.getSons().get(1), 0);

                    node = readNode(nodeFilename);

                    deleteMin(node.getSons().get(0));
                }
            }
        }

        return 0;
    }

    private int deleteMax(String nodeFilename) {
//        System.out.println("deleteMax");

        FileNode node = readNode(nodeFilename);

        int lastId = node.getKeys().size() - 1;

        if (node.isLeaf()) {
            int max = node.getKeys().get(lastId);

            node.getKeys().remove(lastId);

            saveNode(node, nodeFilename);

            return max;
        } else {
            FileNode firstSon = readNode(node.getSons().get(lastId));

            if (firstSon.getKeys().size() > rank - 1) {
                deleteMax(node.getSons().get(lastId));
            } else {
                FileNode secondSon = readNode(node.getSons().get(lastId - 1));

                if (secondSon.getKeys().size() > rank - 1) {
                    rightRotate(nodeFilename, lastId);

                    node = readNode(nodeFilename);

                    deleteMax(node.getSons().get(lastId));
                } else {
                    join(nodeFilename, node.getSons().get(lastId), node.getSons().get(lastId - 1), lastId);

                    node = readNode(nodeFilename);

                    deleteMax(node.getSons().get(lastId));
                }
            }
        }

        return 0;
    }

    private Integer findId(String nodeFilename, int key) {
        FileNode node = readNode(nodeFilename);

        for (int i = 0; i < node.getKeys().size(); i++)
            if (node.getKeys().get(i) == key) {
//                System.out.println("Found id: " + i);
                return i;
            }

        return null;
    }

    private void deleteKey(String nodeFilename, int key) {

        FileNode node = readNode(nodeFilename);

        if (node.getKeys().contains(key)) {
            int pos = findId(nodeFilename, key);

            if (node.isLeaf()) {
                node.getKeys().remove(pos);

                saveNode(node, nodeFilename);
            } else {
                FileNode leftSon = readNode(node.getSons().get(pos));
                FileNode rightSon = readNode(node.getSons().get(pos + 1));

                if (leftSon.getKeys().size() > rank - 1) {
                    node.getKeys().set(pos, deleteMax(node.getSons().get(pos)));
                    saveNode(node, nodeFilename);
                } else if (rightSon.getKeys().size() > rank - 1) {
                    node.getKeys().set(pos, deleteMin(node.getSons().get(pos + 1)));
                    saveNode(node, nodeFilename);
                } else {
                    join(nodeFilename, node.getSons().get(pos), node.getSons().get(pos + 1), pos);

                    node = readNode(nodeFilename);

                    deleteKey(node.getSons().get(pos), key);
                }
            }
        } else if (!node.isLeaf()) {
            int pos = 0;

            while (pos < node.getKeys().size() && node.getKeys().get(pos) < key)
                pos++;

            FileNode leftSon = (pos > 0) ? readNode(node.getSons().get(pos - 1)) : null;
            FileNode midSon = readNode(node.getSons().get(pos));
            FileNode rightSon = (pos < node.getSons().size() - 1) ? readNode(node.getSons().get(pos + 1)) : null;

            if (midSon.getKeys().size() > rank - 1) {
                deleteKey(node.getSons().get(pos), key);
            } else if (leftSon != null && leftSon.getKeys().size() > rank - 1) {
                rightRotate(nodeFilename, pos);
                node = readNode(nodeFilename);
                deleteKey(node.getSons().get(pos), key);
            } else if (rightSon != null && rightSon.getKeys().size() > rank - 1) {
                leftRotate(nodeFilename, pos);
                node = readNode(nodeFilename);
                deleteKey(node.getSons().get(pos), key);
            } else {
                if (pos > 0) {
                    join(nodeFilename, node.getSons().get(pos - 1), node.getSons().get(pos), pos - 1);

                    node = readNode(nodeFilename);

                    deleteKey(node.getSons().get(pos - 1), key);
                } else {
                    join(nodeFilename, node.getSons().get(pos), node.getSons().get(pos + 1), pos);

                    node = readNode(nodeFilename);

                    deleteKey(node.getSons().get(pos), key);
                }
            }
        }
    }

    public void delete(int key) {
        deleteKey(root, key);
    }

    public void insert(int key) {
        if (readNode(root).getKeys().size() == 2 * rank - 1) {
//            System.out.println("split root");

            //Split root
            FileNode newRoot = new FileNode();
            String newRootFilename = getFilename();

            newRoot.setLeaf(false);
            newRoot.getSons().add(root);

            saveNode(newRoot, newRootFilename);

            split(newRootFilename, 0);

            root = newRootFilename;

            insert(key);
        } else {
            insertNF(root, key);
        }
    }

    public void search(String nodeFilename, int key) {
        FileNode node = readNode(nodeFilename);

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

    public void traverse(String nodeFilename) {
        FileNode node = readNode(nodeFilename);

        int i;
        if (node.isLeaf()) {
            System.out.print(node.getKeys() + " ");
            return;
        }
        for (i = 0; i < node.getKeys().size(); i++) {
            traverse(node.getSons().get(i));
            System.out.print(node.getKeys().get(i) + " ");

        }

        traverse(node.getSons().get(node.getKeys().size()));
    }

    public String getRoot() {
        return root;
    }

    public FileNode getRootNode() {
        return readNode(root);
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}

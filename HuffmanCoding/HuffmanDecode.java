import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

class HuffmanDecode {
    private int filePos = 0;

    public void decodeFile(byte[] file, String filename) {
        //Create tree
        Tree huffTree = new Tree();
        huffTree.setRoot(new Node(new Element(), null, null));
        createTree(huffTree, file);
        setValues(huffTree.getRoot(), file);

        int bitsToRemove = file[++filePos];

//        huffTree.printNode(huffTree.getRoot(), 0);

        //Encode the file
        try {
            OutputStream os = new FileOutputStream(filename, true);

            int iterator = 0;
            StringBuilder code = new StringBuilder();
            for(byte b : file) {

                if(iterator > filePos) {
                    String currByte = Integer.toBinaryString((b & 0xFF) + 0x100).substring(1);

                    for(int i = 0; i < currByte.length(); i++) {

                        code.append(currByte.charAt(i));
                        String[] unCode = getValue(huffTree, code.toString());

                        if(unCode != null && (iterator < file.length - 1 || i < 8 - bitsToRemove)) {
//                            System.out.println(code + " " + unCode[0] + " " + unCode[1]);

                            code = new StringBuilder(unCode[1]);

                            int val = Integer.valueOf(unCode[0]);
                            os.write(val);
                        }
                    }
                }

                iterator++;

            }

            os.close();
        } catch (FileNotFoundException e) {
            System.out.println("File no found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Write error");
            e.printStackTrace();
        }

        System.out.println("File has been decrypted");
    }

    private void createTree(Tree huffTree, byte[] file) {
        //Create tree from encrypted file
        List<Node> nodes = new ArrayList<>();
        Node currPos = huffTree.getRoot();

        huffTree.setNodes(huffTree.getNodes() + 1);

        int structPos = 0;
        String currByte = Integer.toBinaryString((file[filePos] & 0xFF) + 0x100).substring(1);

        while (currPos.getLeft() == null || currPos.getRight() == null || nodes.size() > 0) {

            if (!(structPos < 8)) {
                filePos++;
                structPos = 0;

                currByte = Integer.toBinaryString((file[filePos] & 0xFF) + 0x100).substring(1);
            }

//            System.out.println(currPos + " " + currPos.getLeft() + " " + currPos.getRight());

            if (currPos.getLeft() == null) {
                if (currByte.charAt(structPos) == '1') {
                    currPos.setLeft(new Node(new Element(0, 0), null, null));
                    huffTree.setNodes(huffTree.getNodes() + 1);
                } else {
                    currPos.setLeft(new Node(new Element(0, 0), null, null));
                    huffTree.setNodes(huffTree.getNodes() + 1);

                    nodes.add(currPos);

                    currPos = currPos.getLeft();
                }
                structPos++;
            } else if (currPos.getRight() == null) {
                if (currByte.charAt(structPos) == '1') {
                    currPos.setRight(new Node(new Element(0, 0), null, null));
                    huffTree.setNodes(huffTree.getNodes() + 1);
                } else {
                    currPos.setRight(new Node(new Element(0, 0), null, null));
                    huffTree.setNodes(huffTree.getNodes() + 1);

                    nodes.add(currPos);

                    currPos = currPos.getRight();
                }
                structPos++;
            } else if (nodes.size() > 0) {
                currPos = nodes.get(nodes.size() - 1);
                nodes.remove(nodes.size() - 1);
            }
        }
    }

    private void setValues(Node node, byte[] codedFile) {
        if (node.getLeft().isLeaf()) {
            filePos++;
            node.getLeft().getValue().setValue(codedFile[filePos]);
        } else {
            setValues(node.getLeft(), codedFile);
        }

        if (node.getRight().isLeaf()) {
            filePos++;
            node.getRight().getValue().setValue(codedFile[filePos]);
        } else {
            setValues(node.getRight(), codedFile);
        }
    }

    private String[] getValue (Tree huffTree, String code) {
        Node currPosition = huffTree.getRoot();

        int iterator;
        for(iterator = 0; iterator < code.length() && !currPosition.isLeaf(); iterator++) {
            if(code.charAt(iterator) == '0')
                currPosition = currPosition.getLeft();
            else
                currPosition = currPosition.getRight();
        }

        if(!currPosition.isLeaf())
            return null;

        //Set new string
        if(iterator != code.length()) {
            StringBuilder newCode = new StringBuilder();
            for (int j = iterator; j < code.length(); j++)
                newCode.append(code.charAt(j));

            code = newCode.toString();
        } else
            code = "";

        return new String[]{String.valueOf(currPosition.getValue().getValue()), code};
    }
}












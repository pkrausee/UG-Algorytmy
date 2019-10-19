import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.ceil;


abstract class Huffman {
    public static void encryptFile(byte[] file, String filename) {
        //Encrypt the file
        Tree huffTree = new Tree();
        huffTree.buildTree(file);

        Map<Element, String> codeMap = new HashMap<>();
        generateHuffmanCode(codeMap, huffTree.getRoot(), new StringBuilder());

        System.out.println("File has been encrypted");

        FileSupport.saveFile(createFile(huffTree, codeMap, file), filename);
    }

    private static void generateHuffmanCode(Map<Element, String> map, Node node, StringBuilder code) {
        //Generates Huffman code from tree
        if (node.getLeft().isLeaf()) {
            map.put(node.getLeft().getValue(), code.toString() + "0");
        } else {
            generateHuffmanCode(map, node.getLeft(), new StringBuilder(code + "0"));
        }

        if (node.getRight().isLeaf()) {
            map.put(node.getRight().getValue(), code.toString() + "1");
        } else {
            generateHuffmanCode(map, node.getRight(), new StringBuilder(code + "1"));
        }
    }

    public static void printHuffmanCode(byte[] file) {
        //Generate code and print
        Tree huffTree = new Tree();
        huffTree.buildTree(file);

        Map<Element, String> codeMap = new HashMap<>();
        generateHuffmanCode(codeMap, huffTree.getRoot(), new StringBuilder());

        huffTree.printNode(huffTree.getRoot(), 0);

        printFileSizes(codeMap);
        printCodesAndFreqMap(codeMap);
    }

    private static void printCodesAndFreqMap(Map<Element, String> map) {
        String format = "| %1$-5s | %2$-9s | %3$-16s |\n";

        System.out.println("----------------------------------------");
        System.out.format(format, "Value", "Frequency", "Huffman Code");

        for (Map.Entry<Element, String> entry : map.entrySet()) {
            System.out.println("----------------------------------------");
            System.out.format(format, entry.getKey().getValue(), entry.getKey().getFreq(), entry.getValue());
        }

        System.out.println("----------------------------------------\n");
    }

    private static void printFileSizes(Map<Element, String> map) {
        int normalSize = 0;
        int huffmanSize = 0;

        for (Map.Entry<Element, String> entry : map.entrySet()) {
            normalSize += 8 * entry.getKey().getFreq();
            huffmanSize += entry.getValue().length() * entry.getKey().getFreq();
        }

        String format = "| %1$-5s | %2$-9s | %3$-16s |\n";

        System.out.println("----------------------------------------");
        System.out.format(format, "", "Size", "Encoded Size");
        System.out.format(format, "", normalSize, (huffmanSize + "+coding info"));
        System.out.println("----------------------------------------\n");
    }

    private static int getNormalSize(Map<Element, String> map) {
        int normalSize = 0;

        for (Map.Entry<Element, String> entry : map.entrySet()) {
            normalSize += 8 * entry.getKey().getFreq();
        }

        return normalSize;
    }

    private static int getHuffSize(Map<Element, String> map) {
        int huffmanSize = 0;

        for (Map.Entry<Element, String> entry : map.entrySet()) {
            huffmanSize += entry.getValue().length() * entry.getKey().getFreq();
        }

        return huffmanSize;
    }

    private static byte[] createFile(Tree huffTree, Map<Element, String> map, byte[] source) {
        //Pack codes to byte[]

        /*
            First bytes - info about tree structure
            Next: values in tree

            Next: coded file
         */

        byte[] output = new byte[(int) ceil((double) getHuffSize(map) / 8) + 1 + huffTree.getConvertedSize()];
        int outputPos = 0;

        byte[] tree = huffTree.convertToByte();

        for (byte b : tree) {
            output[outputPos] = b;
            outputPos++;
        }

        outputPos++;

        int bits = 0;

        int pos = 128;
        int byteVal = 0;


        for (byte b : source) {
            String code = getCodeFromMap(map, b);

            if (code != null) {
                for (int i = 0; i < code.length(); i++) {

                    if (pos == 0) {
                        output[outputPos] = (byte) byteVal;

                        outputPos++;
                        byteVal = 0;
                        pos = 128;

                        bits = 0;
                    }

                    if (code.charAt(i) == '1')
                        byteVal += pos;

                    pos /= 2;
                    bits++;
                }

            }
        }

        output[outputPos] = (byte) byteVal;

        //Add information about how many bits were added to fill byte at the end
        output[huffTree.getConvertedSize()] = (byte) (8 - bits);

        return output;

    }

    private static String getCodeFromMap(Map<Element, String> map, byte b) {
        //Find element in map and get its code
        for (Map.Entry<Element, String> entry : map.entrySet()) {
            if (entry.getKey().getValue() == b)
                return entry.getValue();
        }
        return null;
    }
}

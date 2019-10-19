class TestHuffman {
    public static void main(String[] args) {

        byte[] file = FileSupport.readFile("text.txt");
        Huffman.printHuffmanCode(file);

        Huffman.encryptFile(file, "encrypted.huff");

        HuffmanDecode hd = new HuffmanDecode();
        byte[] encrypted = FileSupport.readFile("encrypted.huff");

        hd.decodeFile(encrypted, "decrypted.txt");

    }
}

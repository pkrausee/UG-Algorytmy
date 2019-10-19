import java.io.IOException;


class Main {
    public static void main(String[] args) throws IOException {

        String textFile = args[0];
        String patternFile = args[1];

//        String textFile = "tekst.txt";
//        String patternFile = "pattern.txt";

        NaiveAlg naiveAlg = new NaiveAlg();
        RabinKarpAlg rabinKarpAlg = new RabinKarpAlg();
        KnuthMorrisPrattAlg knuthMorrisPrattAlg = new KnuthMorrisPrattAlg();

        Printer printer = new Printer();

        String text = FileSupport.readFile(textFile);
        String pattern = FileSupport.readFile(patternFile);

        long timer;

        //Naive
        timer = System.nanoTime();

        naiveAlg.findPattern(text, pattern);
//        printer.print(text, pattern, naiveAlg.findPattern(text, pattern));

        System.out.println((char) 27 + "[32m" + "Naive time: " + (double)(System.nanoTime() - timer) / 1000000+ " milisec");

        //Rabin-Karp
        timer = System.nanoTime();

        rabinKarpAlg.findPattern(text, pattern, 128, 27077);
//        printer.print(text, pattern, rabinKarpAlg.findPattern(text, pattern, 128, 27077));

        System.out.println((char) 27 + "[32m" + "Rabin-Karp time: " + (double)(System.nanoTime() - timer) / 1000000 + " milisec");

        //Knuth-Morris-Pratt
        timer = System.nanoTime();

        knuthMorrisPrattAlg.findPattern(text, pattern);
//        printer.print(text, pattern, knuthMorrisPrattAlg.findPattern(text, pattern));

        System.out.println((char) 27 + "[32m" + "Knuth-Morris-Pratt time: " + (double)(System.nanoTime() - timer) / 1000000 + " milisec");

    }
}

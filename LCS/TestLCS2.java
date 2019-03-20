class TestLCS2 {
    public static void main(String[] args) {
        String x = "wmzluhrpxtrikovzhyid" +
                "tmdbpuavycahnhchiexa" +
                "dyyfjrztobjirfhrorev" +
                "ypdaqtucirjkckurflxa" +
                "ilyceztcwxylnmexiyff" +
                "kobnlhirvkvbdpahvcge" +
                "ytqirjdhdrkkpntjcscy" +
                "tbokbalkdmbguntphvhn";
        String y = "thvkcuafrzeljnmnwymo" +
                "brfoqvvcbtshozlsmfkw" +
                "rypesoqzeyewpchklyxk" +
                "zlqqxcxaunncyuvqdnhp" +
                "kscfdlhbdxvrxmohtrdj" +
                "telcmarnietcqvgfdttv" +
                "zhkgmewvskxhdwwjdjtj" +
                "wyacpdqpeghoecfegima";

        long startTime = System.nanoTime();

        System.out.println(LCS.length(x, y));
//        System.out.println(LCS.lengthOptimal(x, y));

        LCS.print(x, y);
//        LCS.printIter(x, y);

        long endTime = System.nanoTime();

        System.out.println("Operation time: " + (double)(endTime - startTime) / 1000000000 + " sec");
    }
}
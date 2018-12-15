class Heap
{
    public static void main(String[] args)
    {
        HeapSort h = new HeapSort();

        //Pobranie wartosci z pliku
        FileSupport fs = new FileSupport();
        fs.getElemFromFile("numbers.txt", h);


        //Ustawienie limitow
        //h.setLimits();
        h.setLimitsFromTab();

        System.out.println("Przed sortowaniem ");
        h.showTab();

        h.heapSort(); // Sortuj w zakresie

        System.out.println("\nPo sortowaniu ");
        h.showTab();


        //Wypisanie posortowanych wartosci do pliku
        fs.saveElemToFile("sorted.txt", h);
    }
}
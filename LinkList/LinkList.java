class Element {
    private String data;
    private Element prev;
    private Element next;

    Element(String data, Element prev, Element next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    Element(Element prev, Element next) {
        this.prev = prev;
        this.next = next;
    }

    public String toString() {
        return String.valueOf(data);
    }

    public String getData() {
        return data;
    }

    public Element getPrev() {
        return prev;
    }

    public Element getNext() {
        return next;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setPrev(Element prev) {
        this.prev = prev;
    }

    public void setNext(Element next) {
        this.next = next;
    }
}


class SList {
    private Element root;
    private Element head;

    public SList() {
        root = null;
        head = new Element(root, root);
    }

    public void WSTAW(String value) {
        Element newEl = new Element(value, null, root);

        if (root != null) {
            root.setPrev(newEl);
            head.setNext(newEl);
            newEl.setPrev(head);
        } else {
            head.setPrev(newEl);
            newEl.setNext(head);
        }
        root = newEl;
    }

    public void DRUKUJ() {
        if (root == null)
            System.out.println("Lista jest pusta\n");
        else {
            System.out.println("Zawartosc listy:");

            Element ptr = root;

            int id = 0;
            while (ptr.getData() != null) {
                System.out.println(id + " - " + ptr.getData());
                id++;

                ptr = ptr.getNext();
            }

            System.out.println();
        }
    }

    public Element SZUKAJ(String value) {
        if (root == null) {
            return null;
        } else {
            Element ptr = root;

            head.setData(value);
            int id = 0;
            while (ptr.getData().compareTo(value) != 0) {
                ptr = ptr.getNext();
                id++;
            }
            head.setData(null);

            if (ptr.getData() != null)
                return ptr;
            else
                return null;
        }
    }

    public void USUN(String value) {
        try {
            Element el = SZUKAJ(value);

            if (el.getPrev().getData() == null) {
                root = el.getNext();
                head.setNext(el.getNext());
            } else
                el.getPrev().setNext(el.getNext());

            el.getNext().setPrev(el.getPrev());
        } catch (NullPointerException npe) {
            System.out.println("Element nie znajduje sie na liscie\n");
        }
    }

    public void KASUJ() {
        root = null;
        head = new Element(root, root);
    }

    public SList BEZPOWTORZEN() {
        SList sL = new SList();

        Element ptr = root;

        while (ptr.getData() != null) {
            if (sL.SZUKAJ(ptr.getData()) == null)
                sL.WSTAW(ptr.getData());

            ptr = ptr.getNext();
        }

        return sL;
    }

    public SList SCAL(SList sL2) {
        SList sL = new SList();

        sL.setRoot(root);
        sL.setHead(head);

        Element temp = head;

        sL.getHead().getPrev().setNext(sL2.getRoot());

        KASUJ();
        sL2.KASUJ();

        return sL;
    }

    public Element getRoot() {
        return root;
    }

    public void setRoot(Element root) {
        this.root = root;
    }

    public Element getHead() {
        return head;
    }

    public void setHead(Element head) {
        this.head = head;
    }
}


class TestSList {
    public static void main(String[] args) {
        SList sL1 = new SList();

        //TEST WSTAW
        System.out.println("-----TEST WSTAW-----");
        sL1.WSTAW("Napis 1");
        sL1.WSTAW("Napis 2");
        sL1.WSTAW("Napis 3");
        sL1.WSTAW("Napis 2");
        sL1.WSTAW("Napis 3");

        sL1.DRUKUJ();

        //TEST SZUKAJ
        System.out.println("-----TEST SZUKAJ-----");
        try {
            Element el = sL1.SZUKAJ("Napis 3");

            System.out.println("Znaleziono element - " + el.toString() + "\n");
        } catch (NullPointerException npe) {
            System.out.println("Nie znaleziono elementu\n");
        }

        //TEST USUN
        System.out.println("-----TEST USUN-----");
        sL1.USUN("Napis 3");

        sL1.DRUKUJ();

        sL1.WSTAW("Napis 7");
        sL1.WSTAW("Napis 8");

        sL1.DRUKUJ();

        //TEST KASUJ
        System.out.println("-----TEST KASUJ-----");
        sL1.KASUJ();

        sL1.DRUKUJ();

        sL1.WSTAW("Napis 9");
        sL1.WSTAW("Napis 10");

        sL1.DRUKUJ();

        //TEST BEZPOWTORZEN
        System.out.println("-----TEST BEZPOWTORZEN-----");
        sL1.WSTAW("Napis 1");
        sL1.WSTAW("Napis 2");
        sL1.WSTAW("Napis 3");
        sL1.WSTAW("Napis 1");
        sL1.WSTAW("Napis 2");
        sL1.WSTAW("Napis 3");

        SList sL2 = sL1.BEZPOWTORZEN();

        sL2.DRUKUJ();

        //TEST SCAL
        System.out.println("-----TEST SCAL-----");
        sL1.DRUKUJ();
        sL2.DRUKUJ();

        SList sL3 = sL1.SCAL(sL2);

        sL3.DRUKUJ();

        sL1.DRUKUJ();
        sL2.DRUKUJ();
    }
}


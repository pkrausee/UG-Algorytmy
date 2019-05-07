import java.util.ArrayList;

class DisjointSets {
    private ArrayList<Set> setsList;
    private int numberOfSets;

    public DisjointSets() {
        this.setsList = new ArrayList<>();
        this.numberOfSets = 0;
    }

    public void makeSet() {
        numberOfSets++;

        makeSet(numberOfSets);
    }

    public void makeSet(int key) {
        if (!findKey(key))
            setsList.add(new Set(key));
    }

    public boolean findKey(int key) {
        for (Set s : setsList)
            if (s.getKey() == key)
                return true;

        return false;
    }

    public Set find(int key) {
        for (Set s : setsList)
            if (s.getKey() == key) {
//                System.out.println("find: " + key + " " + s);
                return findSet(s);
            }
        return null;
    }

    public Set findSet(Set set) {
        if (set.getFather() != null) {
            set.setFather(findSet(set.getFather()));

            return findSet(set.getFather());
        } else {
            return set;
        }
    }

    public void union(Set s1, Set s2) {
        if (s1.getRank() > s2.getRank()) {
            s2.setFather(s1);
        } else {
            s1.setFather(s2);
            if (s1.getRank() == s2.getRank())
                s2.setRank(s2.getRank() + 1);
        }


//        if (s1.getRank() == s2.getRank()) {
//            s2.setFather(s1);
//            s1.setRank(s1.getRank() + 1);
//        } else if (s1.getRank() > s2.getRank()) {
//            s2.setFather(s1);
//        } else {
//            s1.setFather(s2);
//        }
    }

    public boolean connected(Set s1, Set s2) {
        return findSet(s1) == findSet(s2);
    }

    public String toString() {
        return setsList.toString();
    }

    public ArrayList<Set> getSetsList() {
        return setsList;
    }

    public void setSetsList(ArrayList<Set> setsList) {
        this.setsList = setsList;
    }
}

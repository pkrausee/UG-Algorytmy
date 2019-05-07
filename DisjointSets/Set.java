class Set {
    private int key;
    private int rank;
    private Set father;

    public Set(int key) {
        //MakeSet operation

        this.key = key;
        this.rank = 0;
        this.father = null;
    }

    public String toString() {
        return key + " ";
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Set getFather() {
        return father;
    }

    public void setFather(Set father) {
        this.father = father;
    }
}
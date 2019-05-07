import java.util.Comparator;

class Edge {
    private int firstEnd;
    private int secondEnd;
    private int weight;

    public Edge(int firstEnd, int secondEnd, int weight) {
        this.firstEnd = firstEnd;
        this.secondEnd = secondEnd;
        this.weight = weight;
    }

    public String toString() {
        return "<- " + firstEnd + " - " + weight + " - " + secondEnd + " ->";
    }

    public int getFirstEnd() {
        return firstEnd;
    }

    public void setFirstEnd(int firstEnd) {
        this.firstEnd = firstEnd;
    }

    public int getSecondEnd() {
        return secondEnd;
    }

    public void setSecondEnd(int secondEnd) {
        this.secondEnd = secondEnd;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

class EdgeComparator implements Comparator<Edge>{
    public int compare(Edge e1, Edge e2) {
        return Integer.compare(e1.getWeight(), e2.getWeight());

//        if(e1.getWeight() < e2.getWeight()) {
//            return -1;
//        } else if (e1.getWeight() > e2.getWeight()) {
//            return 1;
//        } else {
//            return 0;
//        }
    }

}
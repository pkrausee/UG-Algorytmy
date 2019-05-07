public class TestKruskalTree {
    public static void main(String[] args) {
        KruskalTree kT = new KruskalTree();

        kT.addEdge(new Edge(1,2,3));
        kT.addEdge(new Edge(1,4,8));
        kT.addEdge(new Edge(1,6,1));
        kT.addEdge(new Edge(1,10,6));
        kT.addEdge(new Edge(2,3,8));
        kT.addEdge(new Edge(2,5,4));
        kT.addEdge(new Edge(2,9,4));
        kT.addEdge(new Edge(3,4,2));
        kT.addEdge(new Edge(3,8,2));
        kT.addEdge(new Edge(4,7,3));
        kT.addEdge(new Edge(4,10,1));
        kT.addEdge(new Edge(5,9,1));
        kT.addEdge(new Edge(7,8,6));

        kT.findTree();
    }
}

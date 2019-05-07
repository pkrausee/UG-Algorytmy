import java.util.ArrayList;

public class KruskalTree {
    private ArrayList<Edge> edges;
    private ArrayList<Integer> verticesList;

    public KruskalTree() {
        this.edges = new ArrayList<>();
        this.verticesList = new ArrayList<>();
    }

    public void addEdge(Edge e){
        edges.add(e);

        if(!verticesList.contains(e.getFirstEnd()))
            verticesList.add(e.getFirstEnd());

        if(!verticesList.contains(e.getSecondEnd()))
            verticesList.add(e.getSecondEnd());
    }

    public void findTree () {
        DisjointSets dS = new DisjointSets();

        for(int v : verticesList)
            dS.makeSet(v);

        edges.sort(new EdgeComparator());

        for(int i = 0; i < edges.size(); i++) {
//            System.out.println(edges.get(i));

            Set leftS = dS.find(edges.get(i).getFirstEnd());
            Set rightS = dS.find(edges.get(i).getSecondEnd());

//            System.out.println(leftS + " " + rightS);

            if(leftS != rightS){
                System.out.println(edges.get(i));
                dS.union(leftS, rightS);
            }
        }

    }
}

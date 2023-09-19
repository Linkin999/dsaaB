import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.DepthFirstSearch;
import edu.princeton.cs.algs4.StdOut;

public class Depthfirstsearch {
    public static void main(String[] args) {

        Graph graph=new Graph(4);

        graph.addEdge(0,1);
        graph.addEdge(1,2);
        StdOut.println(graph);
        DepthFirstSearch dfs = new DepthFirstSearch(graph,0);
        StdOut.println(dfs.marked(3));

    }
}